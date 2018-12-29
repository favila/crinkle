(ns crinkle.component-test
  (:require [clojure.test :refer [deftest testing is]]
            [crinkle.component :as c]
            [crinkle.dom :as d]
            [react]
            [react-test-renderer :as rtr]))

(defn myrawcomponentfn [{:keys [a b c]}]
  #js[a b c])

(c/RE "div" {:key 1 :style #js{:color "w"}})

(c/CE myrawcomponentfn {:a "A" :b "B" :c "C"})

(defn myqcomponent [dynamic-var static-var1 static-var2])

(c/QE myqcomponent)

(c/QE myqcomponent "dynamic")

(c/QE myqcomponent (let [x 1] (inc x)) {:react/key "1"} {:services "Services"})

(c/QE myqcomponent (let [x 1] (inc x)) (let [x {}] (assoc x :react/key "1"))
  {:services "Services"})

(c/fragment
  (d/div nil 0)
  (d/div nil 1)
  (d/div nil 2))

(c/keyed-fragment "mykey"
  (d/div nil 0)
  (d/div nil 1)
  (d/div nil 2))
