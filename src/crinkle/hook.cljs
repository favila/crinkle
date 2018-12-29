(ns crinkle.hook
  (:require-macros [crinkle.hook])
  (:require [react]
            [crinkle.component]
            [goog.object :as gobj])
  (:import (goog.async nextTick)))

(def ^:dynamic *component* nil)
(def ^:dynamic ^boolean *first-render?* false)
(defonce ^:private REG-IDX (volatile! 0))
(def ^:dynamic *effect-fns* nil)


(defn- action-updater [reducerfn c i action]
  (when-some [reg (. c -__registers)]
    (let [entry (aget reg i)
          ov    (aget entry 0)
          nv    (reducerfn ov action)]
      (when (not= ov nv)
        (aset reg i nv)
        (.forceUpdate c))))
  nil)

(defn useReducer*
  {:jsdoc ["@param {!function(*,*):*}" "@param{*}" "@param{*=}"
           "@return {{0:*, 1:!function(*):void}}"]}
  ([reducer initial-state]
   (let [i   @REG-IDX
         reg (. *component* -__registers)]
     (vreset! REG-IDX (inc i))
     (if *first-render?*
       (let [c        *component*
             dispatch (fn [action]
                        (action-updater reducer c i action)
                        nil)
             entry    #js[initial-state dispatch]]
         (aset reg i entry))
       (aget reg i))))
  ([reducer initial-state initial-action]
   (if *first-render?*
     (useReducer* reducer (reducer initial-state initial-action))
     (useReducer* reducer nil))))

(defn- state-update-reducer* [state fn|newstate]
  (if (fn? fn|newstate)
    (fn|newstate state)
    fn|newstate))

(defn useState* [initial-state]
  {:jsdoc ["@param {*}" "@return {{0:*, 1:!function(*):null}}"]}
  (useReducer* state-update-reducer* initial-state))

(defn- ^boolean shallow-equal? [^array a ^array b]
  ;; INVARIANTS: a and b are both arrays of the same size.
  ;; empty arrays are equal
  (let [l (alength a)]
    (if (== 0 l)
      true
      (loop [i l]
        (if (== -1 i)
          true
          (if (= (aget a i) (aget b i))
            (recur (dec i))
            false))))))


(defn- run-cleanup-effects! [c]
  (when (some? (. c -__effect-cleanup))
    (let [a (. c -__effect-cleanup)
          l (alength a)]
      (loop [i 0]
        (when (< i l)
          (let [f (aget a i)]
            (try (f) (catch :default _ nil)))
          (recur (inc i)))))
    (set! (.-length (. c -__effect-cleanup)) 0)))

(defn- run-effects! [c effect-fns]
  (let [effect-cleanup (. c -__effect-cleanup)]
    (when (some? effect-cleanup)
      (let [a effect-fns
            l (alength a)]
        (loop [i 0]
          (when (< i l)
            (let [f (aget a i)]
              (when-some [cleanup (f)]
                (.push effect-cleanup cleanup)))
            (recur (inc i))))))))

(defn useRef* [initial-value]
  {:jsdoc ["@param {*}" "@return{{current: *}}"]}
  (let [i   @REG-IDX
        reg (. *component* -__registers)]
    (vreset! REG-IDX (inc i))
    (if *first-render?*
      (aset reg i (js* "{current: ~{}}" initial-value))
      (aget reg i))))

(defn useMemo*
  {:jsdoc ["@param {function():*}" "@param {!Array=}" "@return{*}"]}
  ([creator]
   (let [i   @REG-IDX
         j   (inc i)
         reg (. *component* -__registers)]
     (vreset! REG-IDX (inc j))
     (if (or *first-render?* (not (identical? (aget reg j) creator)))
       (do
         (aset reg j creator)
         (aset reg i (creator)))
       (aget reg i))))
  ([creator inputs]
   (let [i   @REG-IDX
         j   (inc i)
         reg (. *component* -__registers)]
     (vreset! REG-IDX (inc j))
     (if (or *first-render?* (not (shallow-equal? (aget reg j) inputs)))
       (do
         (aset reg j inputs)
         (aset reg i (creator)))
       (aget reg i)))))

(defn useEffect*
  {:jsdoc ["@param {function():*}" "@param {!Array=}" "@return {null}"]}
  ([effect]
   (.push *effect-fns* effect)
   nil)
  ([effect inputs]
   (let [i   @REG-IDX
         reg (. *component* -__registers)]
     (vreset! REG-IDX (inc i))
     (if *first-render?*
       (do
         (aset reg i inputs)
         (.push *effect-fns* effect))
       (let [prev-inputs (aget reg i)]
         (when-not (shallow-equal? prev-inputs inputs)
           (aset reg i inputs)
           (.push *effect-fns* effect)))))
   nil))


(defn HookRunner
  "@constructor
   @extends{react/.Component}"
  [_props]
  (.call react/Component (js-this))
  (this-as c
    (set! (. c -__registers) nil)
    (set! (. c -__effect-cleanup) nil)
    (set! (. c -__effect-fns) nil)))

(goog/inherits HookRunner react/Component)

(specify! (. HookRunner -prototype)
  Object
  (shouldComponentUpdate [c np]
    ;; INVARIANTS: setState not used
    (or
      (not (identical? (gobj/get (. c -props) "renderfn") (gobj/get np "renderfn")))
      (not (identical? (gobj/get (. c -props) "props") (gobj/get np "props")))))

  (componentWillUpdate [c]
    (run-cleanup-effects! c))

  (render [c]
    (let [firstrender? (nil? (. c -__registers))
          renderfn     (gobj/get (. c -props) "renderfn")
          props        (gobj/get (. c -props) "props")]
      (binding [*first-render?* firstrender?
                *effect-fns*    #js[]
                *component*     c]
        (try
          (let [renderable (renderfn props)]
            (when (pos? (alength *effect-fns*))
              (set! (. c -__effect-fns) *effect-fns*))
            renderable)
          (finally
            (vreset! REG-IDX 0))))))

  (componentDidMount [c]
    (let [efs (. c -__effect-fns)]
      (when (some? efs)
        (when (nil? (. c -__effect-cleanup))
          (set! (. c -__effect-cleanup) #js[]))
        (set! (. c -__effect-fns) nil)
        ;; TODO: use better schedulers, e.g. idlize queues or react-scheduler
        ;; TODO: support layout and mutation effects
        (nextTick #(run-effects! c efs))))
    nil)

  (componentWillUnmount [c]
    (run-cleanup-effects! c)
    (set! (. c -__registers) nil)
    (set! (. c -__effect-cleanup) nil)
    (set! (. c -__effect-fns) nil)))

