(ns crinkle.component
  (:require [cljs.tagged-literals]
            [cljs.analyzer]
            [cljs.core])
  (:import (cljs.tagged_literals JSValue)))

(defn- js-props* [props]
  (cond
    (nil? props) nil
    (instance? JSValue props) props
    (map? props) (cljs.tagged-literals/read-js props)
    :else (list 'crinkle.component/js-props props)))

(defmacro CE
  "Return a React Element for a crinkle component and props. Props will be
  passed through without modification. :key and :ref are optional keyword args.

  This is a more ergonomic crinkle.component/create-element-raw-props."
  [component props & {:keys [key ref]}]
  (assert (not (or (string? component) (keyword? component)))
    "CE macro cannot be used with dom components.")
  (with-meta
    `(crinkle.component/create-element-raw-props ~component ~props ~key ~ref)
    (meta &form)))

(defmacro RE
  "Return a react element like React.createElement.
  Component may be a string or a keyword literal for a dom component.
  :key and :ref are expected to be keys in the attribute.

  Props will be converted to plain js objects shallowly (and at compile time if
  possible)."
  ([component]
   (with-meta `(RE ~component nil) (meta &form)))
  ([component props & children]
   (let [c (cond
             (string? component) component
             (simple-keyword? component) (name component)
             :else component)
         p (js-props* props)]
     (with-meta `(~'js/crinkle.component.react-createElement* ~c ~p ~@children) (meta &form)))))

(defmacro QE
  "Return a react component using breeze-quiescent style props.

  The first prop argument is assigned to the \"value\" prop.
  The optional remaining props are assigned to the \"statics\" prop.

  To set key or ref, assoc :react/key or :react/ref to the second props arg."
  ([component]
   (with-meta
     (list 'js/crinkle.component.react-createElement* component
       (JSValue. {"value" nil "statics" nil}))
     (meta &form)))
  ([component dynamic-props]
   (with-meta `(QE ~component ~dynamic-props nil) (meta &form)))
  ([component dynamic-props & [fsa & rsa :as static-props]]
   (if (map? fsa)
     (let [k     (get fsa :react/key)
           r     (get fsa :react/ref)
           props (cond-> {"value"   dynamic-props
                          "statics" static-props}
                   k (assoc "key" k)
                   r (assoc "ref" r)
                   (or k r) (assoc "statics"
                                   (conj rsa (dissoc fsa :react/key :react/ref))))]
       (with-meta
         (list 'js/crinkle.component.react-createElement*
           component (JSValue. (update props "statics" vec)))
         (meta &form)))

     (with-meta
       (list 'js/crinkle.component.react-createElement* component
         `(crinkle.component/assemble-breeze-quiescent-props
            ~dynamic-props ~@static-props))
       (meta &form)))))

(defmacro keyed-fragment
  "Return a keyed React.Fragment"
  [key & children]
  (with-meta
    `(~'js/crinkle.component.react-createElement*
       crinkle.component/react-Fragment* ~(JSValue. {:key key}) ~@children)
    (meta &form)))

(defmacro fragment
  "Return an unkeyed React.Fragment"
  [& children]
  (with-meta
    `(~'js/crinkle.component.react-createElement*
       crinkle.component/react-Fragment* nil ~@children)
    (meta &form)))
