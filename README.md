Crinkle
=======

Crinkle is a CLJS wrapper for modern (v16+) React. Its goals:

* Keep props object opaque (instead of plain JS objects wth reserved "key",
  "ref", and "children" properties) as much as possible. This is to allow using
  immutable data structures directly as props without wrappers.
* Allow using normal CLJS functions as render functions (instead of requiring
  property access on its props arguments or wrapping in a function or
  React.Component).
* Keep key and ref separate (instead of mixed with props).
* Provide react hooks to compare changes by CLJS `=` (or a custom comparator)
  instead of React's `Object.is()`.
* Provide some macros for easier construction of class-based Components
  (instead of using `React.createClass()`). (TODO; this might be better as a
  separate project providing macro sugar to JS `class` syntax.)

Elevator Pitch
--------------

Why should you use Crinkle instead of any of the other CLJS react wrappers?
Because with Crinkle you can use *any* one-argument CLJS function as a render
function:

```clojure
;; Define a component
(defn my-function-component [{:keys [foo-text] :as props}]
  ;; Return React Elements using any library (or not) you want.
  ;; Here's one way using Crinkle:
  (RE :p foo-text))
  
;; Note: no wrappers or component factories! 
(react/render
  (CE my-function-component {:foo-text "Hello World!"})
  (js/document.body))
```

Even if you are using class components, you don't need any prop wrapping
or component factories.

```clojure
;; Class components are fine too
(def MyClassComponent
  (create-react-class/createReactClass
     #js{:render (fn [{:keys foo-text}] (RE :p foo-text))}))

(react/render
  (CE MyClassComponent {:foo-text "Hello World!"})
  (js/document.body))
```

React's `createElement` function requires a JS object for props because it
inspects it for special fields `key`, `ref`, and `children`. This means if you
want to use CLJS values for props you need to wrap your props (e.g.
`#js{:value my-cljs-props}`); if you want to `shouldComponentUpdate` or
`React.memo` you need to account for this wrapping; you may also want a special
`defcomponent` macro to unwrap the props for you and create a corresponding
generated create-element factory to wrap the props. You may have to create an
entire tower of protocols (for the lifecycle methods), factories, and macros
simply to account for this extra level of prop wrapping.

Crinkle's approach is different: it doesn't use `createElement` and thus doesn't
need wrapper values around your real CLJS props. Instead, props are completely
opaque; `:key` and `:ref` are optional and must be provided separately from
props. This sorcery is provided by the function
`crinkle.component/create-element-raw-props`, but most of the time you will use
the `CE` macro. Read [Crinkle Elements](#crinkle-elements) for full
documentation.

Installation
------------

React 16.8 or later is required, but not declared as a cljs-discoverable
dependency so you will have to provide it yourself. This project provides it
via npm using shadow-cljs; you can also use [cljsjs/react](https://github.com/cljsjs/packages/tree/master/react)
or any cljs packaging of react which uses `:global-exports` to expose the
name `react`.

You can consume this as a maven artifact from clojars:

[![Clojars Project](https://img.shields.io/clojars/v/crinkle.svg)](https://clojars.org/crinkle)

Or a `deps.edn`-based git dependency.

I tag `vMAJOR.MINOR` commits; revision numbers are incremented automatically and
may or may not be tagged. (Like Clojurescript version numbers.)

Usage
-----

### React Elements

`crinkle.component/RE` creates a React Element using React's normal key, props,
and children semantics. It's also the only way to create native elements.

```clojure
;;; Create DOM react elements
(require '[crinkle.component :as c :refer [RE CE]]) ;; element and component ctors

(RE :div) ;; div with no props or elements
(RE "div" nil) ;; same. String or Keyword is fine (no runtime difference).

;; Like post-React.DOM react, you can use any dom element name you want
(RE :my-custom-element nil)

;; props can be map literals (no runtime cost), but one level deep only!
(RE :div {:className "my-class" :style #js{:width "100%"} :key "mykey"}
  ;; :key and :ref are special, like in React
  ;; Second argument is always props (use nil to omit props)
  ;; Third and follow arguments are assigned to :children property of props.
  (RE :span {:key "line1"} "Some text here"))
(RE :div #js{:className "my-class"}) ;; or js-object literals

;; Props argument can be something that evaluates at runtime to nil, map,
;; or js-object (This does map to js-object conversion at runtime shallowly.)
(RE :div (#(do {:className "my-class" :style #js{:width "100%"}})))
 
;; The crinkle.dom namespace provides convenince macros to be a little shorter.
;; Only the old React.DOM elements are supported.

(d/div {:className "my-class"})

;; You can also use a string for a dom element
(RE "div")
;; Or provide a React class or render function
;; (Props and children are turned to js-objects)
(RE my-react-class-or-function {:custom-prop "abc"})
```

### React DOM constructors

The `crinkle.dom` namespace contains convenience macros (not functions!) for
creating React DOM elements. It is very light sugar over using the
`crinkle.component/RE` macro directly. Examples:

```clojure
(require '[crinkle.dom :as d])
;; Each group expands to the same JS code
(RE "div")
(d/div)


(RE "div" {:style #js{:color "red"}})
(RE "div" #js{:style #js{:color "red"}})
(d/div {:style #js{:color "red"}})
(d/div #js{:style #js{:color "red"}})

(RE "div" {:className "box"} (RE "a" {:href ""} "Link Text"))
(d/div {:className "box"} (d/a {:href ""} "Link Text"))
```

### Crinkle Elements

Crinkle components use cljs props and do not mix key and ref with props, but
they also expand to React element literals without any extra runtime wrappers.
Use the `crinkle.component/CE` macro to create React Elements with Crinkle's
raw-props semantics. (CE = Crinkle Element)

```clojure
(CE my-react-class-or-function ; required
  opaque-props ; required
  ;; optional keyword arguments
  :key "optional-react-key"
  :ref optional-ref)
```

Props will not be interpreted: the component or render function should accept
props exactly as you call it here, even if they are CLJS objects.

Generic "rest args are children" semantics are not supported because React's
`children` must be a property of a plain js-object. Your component should look
in its props for sub-elements to render.

Additional utilities:

* `crinkle.component/fragment` creates a react (v 16) Fragment element.
* `crinkle.component/keyed-fragment` does the same, but accepts a react key as
  a first argument.
* `crinkle.component/memo` is React.Memo, except the default comparison function
  is `cljs.core/=`
* `crinke.component/crinkle-factory` creates a crinkle element factory with
  `crinkle.component/memo`-ized props.

```clojure
(def make-foo (c/crinkle-factory Foo-class))

;; Returns a React Element with a memoized Foo-class and opaque props.
(make-foo {:foo-prop "bar"}) ;; will rerender only when props changes

;; Pass React key or ref through the optional second argument.
(make-foo {:foo-prop "bar"} {:key "optional-key" :ref myref})
```

### Breeze-Quiescent Elements

`crinkle.component/QE` is a compatibility macro for Breeze-flavored Quiescent
components.

### React Hooks

React hooks are great, but using them efficiently requires an array
of objects to ensure the hook's function (effect, memoized value constructor,
callback constructor, etc) is not rerun every render. Unfortunately, the
comparator for the objects in this array is not customizeable like
`React.memo()` so there is no way to benefit from using CLJS immutable data
structures.

Crinkle has hooks to help mitigate this problem: `crinkle.component/useEquiv`,
`crinkle.component/useEquivDeps`, `crinkle.component/use=` and
`crinkle.component/use=deps`. These hooks each take an object or objects and
compare them against the value encountered on the previous committed render,
and return the previous value if they are equal. In other words, these hooks
"upgrade" value-equality to identity-equality for the sake of React dep
checking.

```clojure
;; Without use=, a new callback function would be created on every render
(defn my-render [{:keys [foo]}]
  (let [em  (react/useContext MyEventManager)
        bar (assoc foo :b 2)
        cb  (react/useCallback
              (fn [_e] (send! em bar))
              ;; bar is never identical to bar from previous render,
              ;; even though it may be equal
              #js[em bar])]
    ;; Therefore this component will *always* rerender
    (CE memoized-renderer {:callback cb})))

;; With use=, a new callback function will only be created when bar is not
;; `cljs.core/=` to the bar in the previous committed render
(defn my-efficient-render [{:keys [foo]}]
  (let [em (react/useContext MyEventManager)
        bar (c/use= (assoc foo :b 2))
        cb (react/useCallback
             (fn [_e] (send! em bar))
             ;; If foo hasn't changed, this bar *will* be identical to the bar
             ;; from the previous committed render; meaning react will give you
             ;; the same identical callback function from that render also!
             #js[em bar])]
    ;; Therefore this component will not necessarily rerender
    (CE memoized-renderer {:callback cb})))

;; You can also memoize an entire deps array at once
(defn my-efficient-render2 [{:keys [foo bar baz]}]
  (let [cb (react/useCallback
             (fn [_e] (do-thing! foo bar baz))
             ;; Surround the usual js array with c/use=deps
             (c/use=deps #js[foo bar baz]))]
    (CE memoized-renderer {:callback cb})))


;; Or you can use a custom comparator
(defn my-efficient-render3 [{:keys [foo bar baz]}]
  (let [foo+ (c/useEquiv (next-foo foo) my-custom-equiv-fn)
        cb   (react/useCallback
               (fn [_e] (do-thing! foo bar baz))
               (c/useEquivDeps #js[foo bar baz] my-custom-equiv-fn))]
    (CE memoized-renderer {:callback cb :foo-plus foo+})))
```


Development
-----------

This is a [shadow-cljs][shadow-cljs-usersguide] project with dependencies
and source-paths through `deps.edn`.

The following instructions are the minumum needed to see test output and an
integrated repl for those unfamiliar with shadow-cljs. See the full
[shadow-cljs users guide][shadow-cljs-usersguide] for details.

You must install node, npm and shadow-cljs first. (Follow instructions for
shadow-cljs installation.)

To watch tests:

```bash
shadow-cljs watch test
```

Then the following will be available:

* [Shadow build dashboard on port 9630](http://localhost:9630/dashboard)
* [Test output on port 8201](http://localhost:8021/)
* nREPL server on port 43083. (In IntelliJ IDEA with Cursive, use the included
  *Shadow nRepl* run configuration to connect to this port.)

Using the nREPL port you can get an integrated CLJS repl:

```clojure
Connecting to remote nREPL server...
Clojure 1.9.0

;; starting namespace is shadow cljs's `shadow.user` convenience ns.
(shadow/active-builds)
=> #{:test}
(shadow/repl :test)
To quit, type: :cljs/quit
=> [:selected :test]
```

License
-------

MIT Copyright (c) 2018 Francis Avila

See `LICENSE.txt` for full license.


[shadow-cljs-usersguide]: (https://shadow-cljs.github.io/docs/UsersGuide.html)
