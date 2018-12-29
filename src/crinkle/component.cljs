(ns crinkle.component
  (:require-macros crinkle.component)
  (:require [react]
            [goog.object :as gobj]))

;; INTERNAL USE ONLY! These are aliases for macroexpansion to target
;; If crinkle.component macros expanded to react/createElement directly, then
;; all ns that used these macros would have to require react also.
;; Instead, the macros expand to these vars.
;; NOTE: cljs cannot detect that these are not-native functions and so always
;; tests for the arity fns. To mitigate, macros should expand to
;; (js/crinkle.component.react-createElement* ...) instead of (react-createElement* ...)
(def react-createElement* react/createElement)
(def react-Fragment* react/Fragment)

(defn- create-element-raw-props*-dev
  ; dev-mode react
  [^js base-e props]
  (let [^js e (gobj/clone base-e)]
    (set! (.-props e) props)
    (set! (.-_self e) (.-_self base-e))
    (set! (.-_source e) (.-_source base-e))
    (.freeze js/Object e)
    e))

(defn- create-element-raw-props*-prod [^js base-e props]
  ; prod-mode react
  (set! (.-props base-e) props)
  base-e)

(def ^{:arglists '([base-e props])
       :jsdoc ["@param {*} base-e" "@param {*} props" "@return {*}"]}
  create-element-raw-props*
  (if ^boolean (.isFrozen js/Object (react/createElement "span"))
    create-element-raw-props*-dev
    create-element-raw-props*-prod))

(defn create-element-raw-props
  "Like React.createElement, but keeps key and ref distinct from other props,
  allowing props to be opaque.

  Does not support children or defaultProps; it should only be used on
  crinkle components which expect to get a cljs object as their props."
  [type props key ref]
  (let [fake-props (when (or (some? key) (some? ref))
                     #js{:key key :ref ref})
        base-e     (react/createElement type fake-props)]
    (create-element-raw-props* base-e props)))

(defn element-factory-raw-props
  "Return an element factory for component that uses `create-element-raw-props`
  to create the element. The factory accepts three args: props, key, and ref"
  [component]
  (fn [props key ref]
    (create-element-raw-props component props key ref)))

(defn- js-props-iter [^not-native it]
  (let [o #js{}]
    (while ^boolean (.hasNext it)
      (let [^not-native e (.next it)]
        (gobj/set o (-name ^not-native (-key e)) (-val e))))
    o))

(defn- js-props-rkv [m]
  (reduce-kv (fn [o k v] (gobj/set o (-name ^not-native k) v) o) #js{} m))

(defn- js-props-rm [m]
  (reduce (fn [o ^not-native e]
            (gobj/set o (-name ^not-native (-key e)) (-val e)) o) #js{} m))

(defn js-props
  "Takes an object which is (possibly) a ClojureScript map. If the value is a
  ClojureScript map, convert it to a JS object shallowly. Otherwise, return the
  argument unchanged.

  Used to convert maps to js before passing to react components that
  expect raw js objects."
  [obj]
  (cond
    (nil? obj) nil
    (map? obj) (cond
                 (iterable? obj)
                 (js-props-iter (iter obj))

                 (satisfies? IKVReduce obj)
                 (js-props-rkv obj)

                 :else
                 (js-props-rm obj))
    :else obj))

(defn assemble-breeze-quiescent-props
  "INTERNAL USE ONLY! Assemble props in the breeze-quiescent style."
  [value & [fsa & rsa :as static-args]]
  (let [props #js{:value value :statics static-args}
        k     (get fsa :react/key)
        r     (get fsa :react/ref)]
    (when (some? k)
      (gobj/set props "key" k))
    (when (some? r)
      (gobj/set props "ref" r))
    (when (or (some? k) (some? r))
      (gobj/set props "statics"
        (conj rsa (dissoc fsa :react/key :react/ref))))
    props))

(defn memo
  "Memoizes a component function; like React.memo, except the default comparison
  function is clojure's ="
  ([component]
    (react/memo component =))
  ([component equal?]
    (react/memo component equal?)))

(defn crinkle-factory
  "Returns an element factory for a render function that expects opaque props.
  Will memoize the render function.

  The factory provides an optional second argument that may contain :key or :ref."
  [renderfn]
  (let [mr (memo renderfn)]
    (fn
      ([props]
       (create-element-raw-props mr props nil nil))
      ([props {:keys [key ref]}]
        (create-element-raw-props mr props key ref)))))
