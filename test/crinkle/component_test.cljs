(ns crinkle.component-test
  (:require [clojure.test :refer [deftest testing is]]
            [crinkle.component :as c]
            [crinkle.dom :as d]
            [react-test-renderer :as rtr]))


;; For now one must manually inspect the generated js to verify these tests
(defn myrawcomponentfn [{:keys [a b c]}]
  #js[a b c])

(c/RE "div" {:key 1 :style #js{:color "w"}})

(c/CE myrawcomponentfn {:a "A" :b "B" :c "C"})

(c/fragment
  (d/div nil 0)
  (d/div nil 1)
  (d/div nil 2))

(c/keyed-fragment "mykey"
  (d/div nil 0)
  (d/div nil 1)
  (d/div nil 2))

(defn nil-component [_props]
  nil)

(defn prop-value [^js test-rendering]
  (-> test-rendering (.-root) (.-children) (aget 0) (.-props)))

(defn useEquiv-test-component [props]
  (let [v (c/useEquiv (select-keys props [:a]) =)]
    (c/CE nil-component v)))

(defn use=-test-component [props]
  (let [v (c/useEquiv (select-keys props [:a]) =)]
    (c/CE nil-component v)))

(deftest useEquiv-test
  (let [p1     {:a 1 :b 2}
        p2     {:a 1 :c 3}
        p3     {:a 2}
        expect {:a 1}
        tr     (.create rtr (c/CE useEquiv-test-component p1))
        v1     (prop-value tr)
        _      (is (= v1 expect))
        _      (.update tr (c/CE useEquiv-test-component p2))
        v2     (prop-value tr)
        _      (is (= v2 expect))
        _      (is (= v1 v2))
        _      (is (identical? v1 v2)
                 "useEquiv should return identical value for value equal to previous run")
        _      (.update tr (c/CE useEquiv-test-component p3))
        v3     (prop-value tr)
        _      (is (= v3 p3)
                 "useEquiv value should change when its input value changes")]))

(deftest use=-test
  (let [p1     {:a 1 :b 2}
        p2     {:a 1 :c 3}
        p3     {:a 2}
        expect {:a 1}
        tr     (.create rtr (c/CE use=-test-component p1))
        v1     (prop-value tr)
        _      (is (= v1 expect))
        _      (.update tr (c/CE use=-test-component p2))
        v2     (prop-value tr)
        _      (is (= v2 expect))
        _      (is (= v1 v2))
        _      (is (identical? v1 v2)
                 "use= should return identical value for value equal to previous run")
        _      (.update tr (c/CE useEquiv-test-component p3))
        v3     (prop-value tr)
        _      (is (= v3 p3)
                 "useEquiv value should change when its input value changes")]))
