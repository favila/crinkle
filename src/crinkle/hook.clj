(ns crinkle.hook
  (:require [cljs.tagged_literals])
  (:import (cljs.tagged_literals JSValue)))

(defmacro with-hooks
  "Return a component which uses the supplied body (which may contain hook calls)
  as its render method. The body will receive props as a single argument."
  [prop-destructure & body]
  (assert (and (vector? prop-destructure) (== (count prop-destructure) 1))
    "Prop argument must be in a vector of exactly one element.")
  (with-meta
    `(let [renderfn# (fn ~prop-destructure ~@body)]
       (fn [props]
         (crinkle.component/RE crinkle.hook/HookRunner
           (js* "{renderfn: ~{}, props: ~{}}" renderfn# ~props))))
    (meta &form)))

(defmacro state [initial]
  (with-meta `(crinkle.hook/useState* ~initial)
    (assoc (meta &form) :tag 'array)))

(defmacro effect
  [watch-vec & body]
  (assert (every? simple-symbol? watch-vec))
  (let [f (if (and (== 1 (count body)) (qualified-symbol? (first body)))
            (first body)
            `(fn [] ~@body))]
    (with-meta
      (if (nil? watch-vec)
        `(crinkle.hook/useEffect* ~f)
        `(crinkle.hook/useEffect* ~f (JSValue. ~watch-vec)))
      (meta &form))))

(defmacro reducer
  "Return current state and a dispatch function which receives an action and
  applies it to the current state, and schedules a rerender if the state
  is not equal."
  ([reducer initial-state]
   (assert (qualified-symbol? reducer)
     "Reduction function should be a defn-ed, not local.")
   (with-meta `(crinkle.hook/useReducer* ~reducer ~initial-state) (meta &form)))
  ([reducer initial-state initial-action]
   (assert (qualified-symbol? reducer)
     "Reduction function should be a defn-ed, not local.")
   (with-meta `(crinkle.hook/useReducer* ~reducer ~initial-state ~initial-action)
     (meta &form))))

(defmacro memo
  [watch-vec & body]
  (assert (every? simple-symbol? watch-vec))
  (let [f (if (and (== 1 (count body)) (qualified-symbol? (first body)))
            (first body)
            `(fn [] ~@body))]
    (with-meta
      (if (nil? watch-vec)
        `(crinkle.hook/useMemo* ~f (JSValue. ~watch-vec))
        `(crinkle.hook/useMemo* ~f))
      (meta &form))))

(defmacro callback
  [watch-vec & body]
  (assert (vector? watch-vec))
  (assert (every? simple-symbol? watch-vec))
  (let [f (if (and (== 1 (count body)) (qualified-symbol? (first body)))
            (first body)
            `(fn [] ~@body))]
    (with-meta `(crinkle.hook/useMemo* ~f (JSValue. ~watch-vec)) (meta &form))))

(defmacro ref
  "Return or initialize a react hook Ref."
  ([] (with-meta `(crinkle.hook/useRef* nil) (meta &form)))
  ([initial] (with-meta `(crinkle.hook/useRef* ~initial) (meta &form))))

(defmacro ref-get [ref]
  (with-meta `(.-current ref) (meta &form)))

(defmacro ref-set! [ref value]
  (with-meta `(set! (.-current ref) value) (meta &form)))
