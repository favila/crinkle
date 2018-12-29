(ns crinkle.dom
  "Convenience macros for creating react dom elements."
  (:refer-clojure :exclude [map meta time]))

#_(doseq [s (sort
              '[a abbr address area article aside audio b base bdi bdo big blockquote body br
                button canvas caption cite code col colgroup data datalist dd del details dfn
                dialog div dl dt em embed fieldset figcaption figure footer form h1 h2 h3 h4 h5
                h6 head header hr html i iframe img input ins kbd keygen label legend li link
                main map mark menu menuitem meta meter nav noscript object ol optgroup option
                output p param picture pre progress q rp rt ruby s samp script section select
                small source span strong style sub summary sup table tbody td textarea tfoot th
                thead time title tr track u ul var video wbr

                circle clipPath defs ellipse g line linearGradient mask path pattern polygon
                polyline radialGradient rect stop svg text tspan])]
    (print
      (str
        "(defmacro " s "\n"
        "  ([] (with-meta `(crinkle.component/RE \"" s "\" nil) (clojure.core/meta &form)))\n"
        "  ([props]\n"
        "   (with-meta `(crinkle.component/RE \"" s "\" ~props) (clojure.core/meta &form)))\n"
        "  ([props & children]\n"
        "   (with-meta `(crinkle.component/RE \"" s "\" ~props ~@children)\n"
        "     (clojure.core/meta &form))))\n")))

(defmacro a
  ([] (with-meta `(crinkle.component/RE "a" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "a" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "a" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro abbr
  ([] (with-meta `(crinkle.component/RE "abbr" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "abbr" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "abbr" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro address
  ([] (with-meta `(crinkle.component/RE "address" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "address" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "address" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro area
  ([] (with-meta `(crinkle.component/RE "area" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "area" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "area" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro article
  ([] (with-meta `(crinkle.component/RE "article" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "article" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "article" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro aside
  ([] (with-meta `(crinkle.component/RE "aside" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "aside" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "aside" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro audio
  ([] (with-meta `(crinkle.component/RE "audio" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "audio" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "audio" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro b
  ([] (with-meta `(crinkle.component/RE "b" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "b" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "b" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro base
  ([] (with-meta `(crinkle.component/RE "base" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "base" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "base" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro bdi
  ([] (with-meta `(crinkle.component/RE "bdi" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "bdi" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "bdi" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro bdo
  ([] (with-meta `(crinkle.component/RE "bdo" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "bdo" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "bdo" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro big
  ([] (with-meta `(crinkle.component/RE "big" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "big" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "big" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro blockquote
  ([] (with-meta `(crinkle.component/RE "blockquote" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "blockquote" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "blockquote" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro body
  ([] (with-meta `(crinkle.component/RE "body" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "body" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "body" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro br
  ([] (with-meta `(crinkle.component/RE "br" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "br" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "br" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro button
  ([] (with-meta `(crinkle.component/RE "button" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "button" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "button" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro canvas
  ([] (with-meta `(crinkle.component/RE "canvas" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "canvas" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "canvas" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro caption
  ([] (with-meta `(crinkle.component/RE "caption" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "caption" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "caption" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro circle
  ([] (with-meta `(crinkle.component/RE "circle" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "circle" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "circle" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro cite
  ([] (with-meta `(crinkle.component/RE "cite" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "cite" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "cite" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro clipPath
  ([] (with-meta `(crinkle.component/RE "clipPath" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "clipPath" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "clipPath" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro code
  ([] (with-meta `(crinkle.component/RE "code" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "code" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "code" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro col
  ([] (with-meta `(crinkle.component/RE "col" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "col" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "col" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro colgroup
  ([] (with-meta `(crinkle.component/RE "colgroup" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "colgroup" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "colgroup" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro data
  ([] (with-meta `(crinkle.component/RE "data" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "data" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "data" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro datalist
  ([] (with-meta `(crinkle.component/RE "datalist" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "datalist" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "datalist" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro dd
  ([] (with-meta `(crinkle.component/RE "dd" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "dd" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "dd" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro defs
  ([] (with-meta `(crinkle.component/RE "defs" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "defs" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "defs" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro del
  ([] (with-meta `(crinkle.component/RE "del" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "del" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "del" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro details
  ([] (with-meta `(crinkle.component/RE "details" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "details" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "details" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro dfn
  ([] (with-meta `(crinkle.component/RE "dfn" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "dfn" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "dfn" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro dialog
  ([] (with-meta `(crinkle.component/RE "dialog" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "dialog" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "dialog" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro div
  ([] (with-meta `(crinkle.component/RE "div" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "div" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "div" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro dl
  ([] (with-meta `(crinkle.component/RE "dl" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "dl" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "dl" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro dt
  ([] (with-meta `(crinkle.component/RE "dt" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "dt" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "dt" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro ellipse
  ([] (with-meta `(crinkle.component/RE "ellipse" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "ellipse" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "ellipse" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro em
  ([] (with-meta `(crinkle.component/RE "em" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "em" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "em" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro embed
  ([] (with-meta `(crinkle.component/RE "embed" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "embed" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "embed" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro fieldset
  ([] (with-meta `(crinkle.component/RE "fieldset" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "fieldset" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "fieldset" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro figcaption
  ([] (with-meta `(crinkle.component/RE "figcaption" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "figcaption" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "figcaption" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro figure
  ([] (with-meta `(crinkle.component/RE "figure" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "figure" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "figure" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro footer
  ([] (with-meta `(crinkle.component/RE "footer" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "footer" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "footer" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro form
  ([] (with-meta `(crinkle.component/RE "form" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "form" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "form" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro g
  ([] (with-meta `(crinkle.component/RE "g" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "g" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "g" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro h1
  ([] (with-meta `(crinkle.component/RE "h1" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "h1" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "h1" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro h2
  ([] (with-meta `(crinkle.component/RE "h2" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "h2" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "h2" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro h3
  ([] (with-meta `(crinkle.component/RE "h3" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "h3" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "h3" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro h4
  ([] (with-meta `(crinkle.component/RE "h4" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "h4" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "h4" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro h5
  ([] (with-meta `(crinkle.component/RE "h5" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "h5" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "h5" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro h6
  ([] (with-meta `(crinkle.component/RE "h6" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "h6" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "h6" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro head
  ([] (with-meta `(crinkle.component/RE "head" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "head" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "head" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro header
  ([] (with-meta `(crinkle.component/RE "header" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "header" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "header" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro hr
  ([] (with-meta `(crinkle.component/RE "hr" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "hr" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "hr" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro html
  ([] (with-meta `(crinkle.component/RE "html" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "html" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "html" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro i
  ([] (with-meta `(crinkle.component/RE "i" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "i" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "i" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro iframe
  ([] (with-meta `(crinkle.component/RE "iframe" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "iframe" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "iframe" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro img
  ([] (with-meta `(crinkle.component/RE "img" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "img" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "img" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro input
  ([] (with-meta `(crinkle.component/RE "input" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "input" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "input" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro ins
  ([] (with-meta `(crinkle.component/RE "ins" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "ins" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "ins" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro kbd
  ([] (with-meta `(crinkle.component/RE "kbd" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "kbd" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "kbd" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro keygen
  ([] (with-meta `(crinkle.component/RE "keygen" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "keygen" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "keygen" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro label
  ([] (with-meta `(crinkle.component/RE "label" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "label" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "label" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro legend
  ([] (with-meta `(crinkle.component/RE "legend" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "legend" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "legend" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro li
  ([] (with-meta `(crinkle.component/RE "li" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "li" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "li" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro line
  ([] (with-meta `(crinkle.component/RE "line" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "line" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "line" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro linearGradient
  ([] (with-meta `(crinkle.component/RE "linearGradient" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "linearGradient" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "linearGradient" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro link
  ([] (with-meta `(crinkle.component/RE "link" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "link" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "link" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro main
  ([] (with-meta `(crinkle.component/RE "main" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "main" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "main" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro map
  ([] (with-meta `(crinkle.component/RE "map" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "map" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "map" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro mark
  ([] (with-meta `(crinkle.component/RE "mark" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "mark" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "mark" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro mask
  ([] (with-meta `(crinkle.component/RE "mask" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "mask" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "mask" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro menu
  ([] (with-meta `(crinkle.component/RE "menu" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "menu" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "menu" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro menuitem
  ([] (with-meta `(crinkle.component/RE "menuitem" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "menuitem" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "menuitem" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro meta
  ([] (with-meta `(crinkle.component/RE "meta" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "meta" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "meta" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro meter
  ([] (with-meta `(crinkle.component/RE "meter" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "meter" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "meter" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro nav
  ([] (with-meta `(crinkle.component/RE "nav" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "nav" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "nav" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro noscript
  ([] (with-meta `(crinkle.component/RE "noscript" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "noscript" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "noscript" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro object
  ([] (with-meta `(crinkle.component/RE "object" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "object" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "object" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro ol
  ([] (with-meta `(crinkle.component/RE "ol" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "ol" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "ol" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro optgroup
  ([] (with-meta `(crinkle.component/RE "optgroup" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "optgroup" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "optgroup" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro option
  ([] (with-meta `(crinkle.component/RE "option" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "option" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "option" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro output
  ([] (with-meta `(crinkle.component/RE "output" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "output" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "output" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro p
  ([] (with-meta `(crinkle.component/RE "p" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "p" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "p" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro param
  ([] (with-meta `(crinkle.component/RE "param" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "param" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "param" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro path
  ([] (with-meta `(crinkle.component/RE "path" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "path" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "path" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro pattern
  ([] (with-meta `(crinkle.component/RE "pattern" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "pattern" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "pattern" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro picture
  ([] (with-meta `(crinkle.component/RE "picture" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "picture" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "picture" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro polygon
  ([] (with-meta `(crinkle.component/RE "polygon" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "polygon" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "polygon" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro polyline
  ([] (with-meta `(crinkle.component/RE "polyline" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "polyline" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "polyline" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro pre
  ([] (with-meta `(crinkle.component/RE "pre" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "pre" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "pre" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro progress
  ([] (with-meta `(crinkle.component/RE "progress" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "progress" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "progress" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro q
  ([] (with-meta `(crinkle.component/RE "q" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "q" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "q" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro radialGradient
  ([] (with-meta `(crinkle.component/RE "radialGradient" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "radialGradient" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "radialGradient" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro rect
  ([] (with-meta `(crinkle.component/RE "rect" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "rect" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "rect" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro rp
  ([] (with-meta `(crinkle.component/RE "rp" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "rp" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "rp" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro rt
  ([] (with-meta `(crinkle.component/RE "rt" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "rt" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "rt" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro ruby
  ([] (with-meta `(crinkle.component/RE "ruby" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "ruby" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "ruby" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro s
  ([] (with-meta `(crinkle.component/RE "s" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "s" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "s" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro samp
  ([] (with-meta `(crinkle.component/RE "samp" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "samp" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "samp" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro script
  ([] (with-meta `(crinkle.component/RE "script" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "script" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "script" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro section
  ([] (with-meta `(crinkle.component/RE "section" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "section" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "section" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro select
  ([] (with-meta `(crinkle.component/RE "select" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "select" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "select" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro small
  ([] (with-meta `(crinkle.component/RE "small" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "small" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "small" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro source
  ([] (with-meta `(crinkle.component/RE "source" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "source" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "source" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro span
  ([] (with-meta `(crinkle.component/RE "span" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "span" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "span" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro stop
  ([] (with-meta `(crinkle.component/RE "stop" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "stop" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "stop" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro strong
  ([] (with-meta `(crinkle.component/RE "strong" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "strong" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "strong" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro style
  ([] (with-meta `(crinkle.component/RE "style" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "style" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "style" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro sub
  ([] (with-meta `(crinkle.component/RE "sub" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "sub" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "sub" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro summary
  ([] (with-meta `(crinkle.component/RE "summary" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "summary" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "summary" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro sup
  ([] (with-meta `(crinkle.component/RE "sup" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "sup" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "sup" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro svg
  ([] (with-meta `(crinkle.component/RE "svg" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "svg" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "svg" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro table
  ([] (with-meta `(crinkle.component/RE "table" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "table" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "table" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro tbody
  ([] (with-meta `(crinkle.component/RE "tbody" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "tbody" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "tbody" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro td
  ([] (with-meta `(crinkle.component/RE "td" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "td" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "td" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro text
  ([] (with-meta `(crinkle.component/RE "text" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "text" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "text" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro textarea
  ([] (with-meta `(crinkle.component/RE "textarea" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "textarea" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "textarea" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro tfoot
  ([] (with-meta `(crinkle.component/RE "tfoot" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "tfoot" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "tfoot" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro th
  ([] (with-meta `(crinkle.component/RE "th" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "th" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "th" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro thead
  ([] (with-meta `(crinkle.component/RE "thead" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "thead" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "thead" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro time
  ([] (with-meta `(crinkle.component/RE "time" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "time" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "time" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro title
  ([] (with-meta `(crinkle.component/RE "title" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "title" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "title" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro tr
  ([] (with-meta `(crinkle.component/RE "tr" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "tr" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "tr" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro track
  ([] (with-meta `(crinkle.component/RE "track" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "track" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "track" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro tspan
  ([] (with-meta `(crinkle.component/RE "tspan" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "tspan" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "tspan" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro u
  ([] (with-meta `(crinkle.component/RE "u" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "u" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "u" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro ul
  ([] (with-meta `(crinkle.component/RE "ul" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "ul" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "ul" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro var
  ([] (with-meta `(crinkle.component/RE "var" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "var" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "var" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro video
  ([] (with-meta `(crinkle.component/RE "video" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "video" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "video" ~props ~@children)
     (clojure.core/meta &form))))

(defmacro wbr
  ([] (with-meta `(crinkle.component/RE "wbr" nil) (clojure.core/meta &form)))
  ([props]
   (with-meta `(crinkle.component/RE "wbr" ~props) (clojure.core/meta &form)))
  ([props & children]
   (with-meta `(crinkle.component/RE "wbr" ~props ~@children)
     (clojure.core/meta &form))))
