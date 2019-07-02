(ns crinkle.component
  (:require [cljs.tagged-literals]
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

(defmacro js-undefined
  "Expand to the special js undefined value. Useful for useEffect, which needs
  not to return (i.e. return undefined) when no cleanup function is desired.
  Returning nil/null or anything else isn't acceptable to React"
  []
  (with-meta (list 'js* "(void 0)") (meta &form)))
