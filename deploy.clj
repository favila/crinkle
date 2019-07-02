#!/bin/sh
#_(
    #_DEPS is same format as deps.edn. Multiline is okay.
    DEPS='
    {:deps {org.clojure/clojure {:mvn/version "1.10.1"}
            badigeon/badigeon   {:git/url "https://github.com/EwenG/badigeon.git"
                                 :sha     "e7f62a60d8e890fc84a47bcfdd59137e11ff1c95"
                                 :tag     "0.0.7"}}}
    '

        #_You can put other options here
    OPTS='
    -J-client
    '

        exec clojure $OPTS -Sdeps "$DEPS" "$0" "$@"

    )

(require
  '[clojure.string :as str]
  '[clojure.java.shell :as sh]
  '[clojure.java.io :as io]
  '[badigeon.clean :as clean]
  '[badigeon.jar :as jar]
  '[badigeon.install :as install]
  '[badigeon.sign :as sign]
  '[badigeon.deploy :as deploy])

(def GROUP+ARTIFACT 'crinkle/crinkle)

(defn git-describe []
  (let [{:keys [exit out err]} (sh/sh "git" "--no-replace-objects"
                                 "describe" "--match=v*.*"
                                 "--abbrev=40" "--long" "--dirty")]
    (if (zero? exit)
      (str/trimr out)
      (throw (ex-info "No annotated tag found with pattern \"v*.*\""
               {:exit-code exit
                :stderr    err})))))

(def re-git-describe
  #"(v([0-9]+)\.([0-9]+))-([0-9]+)-g([0-9a-f]+)(?:-(dirty))?")

(defn parsed-version [long-git-describe-str]
  (let [[_ tag major minor revision sha dirty?]
        (re-matches re-git-describe long-git-describe-str)
        major    (Long/parseLong major)
        minor    (Long/parseLong minor)
        revision (Long/parseLong revision)]
    (cond-> {:base-tag tag
             :major    major
             :minor    minor
             :revision revision
             :sha      sha
             :dirty?   (boolean dirty?)}
      (and (not dirty?) (zero? revision))
      (assoc :tag tag))))

(defn mvn-version [{:keys [major minor revision]}]
  (str major \. minor \. revision))

(defn do-deploy [ops]
  (let [ops         (set ops)
        ver         (parsed-version (git-describe))
        mver        (mvn-version ver)
        jarbasename (str (name GROUP+ARTIFACT) \- mver ".jar")
        jarfile     (str "target/" jarbasename)]

    (clean/clean "target")

    (io/copy (io/file "base-pom.xml") (io/file "pom.xml"))

    (jar/jar GROUP+ARTIFACT {:mvn/version mver}
      {;; The jar file produced.
       :out-path  jarfile
       :mvn/repos {"clojars" {:name "Clojars Repository"
                              :url  "https://clojars.org/repo"}}})

    (when (contains? ops "install")
      (install/install GROUP+ARTIFACT {:mvn/version mver}
        ;; The jar file to be installed
        jarfile
        ;; The pom.xml file to be installed. This file is generated when creating the jar with the badigeon.jar/jar function.
        "pom.xml"
        {;; The local repository where the jar should be installed.
         :local-repo (str (System/getProperty "user.home") "/.m2/repository")}))


    ;; Deploy the previously created jar file to a remote repository.
    (when (contains? ops "deploy")
      (let [;; Artifacts are maps with a required :file-path key and an optional :extension key
            artifacts [{:file-path jarfile :extension "jar"}
                       {:file-path "pom.xml" :extension "pom"}]
            ;; Artifacts must be signed when deploying non-snapshot versions of artifacts.
            artifacts (badigeon.sign/sign artifacts {:command "gpg"
                                                     :gpg-key "9F681F44CFF78B35"})]
        (badigeon.deploy/deploy GROUP+ARTIFACT mver
          artifacts
          {;; :id is used to match the repository in the ~/.m2/settings.xml for credentials when no credentials are explicitly provided.
           :id  "clojars"
           ;; The URL of the repository to deploy to.
           :url "https://clojars.org/repo"})))
    ))

(try
  (do-deploy *command-line-args*)
  (finally
    (shutdown-agents)))
