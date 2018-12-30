Crinkle
=======

Crinkle is a CLJS wrapper for modern (v16+) React. Its goals:

* Keep props object opaque (instead of plain JS objects wth reserved "key",
  "ref", and "children" properties) as much as possible. This is to allow using
  immutable data structures directly as props.
* Allow using normal CLJS functions as render functions (instead of requiring
  property access on its props arguments or wrapping in a function or
  React.Component).
* Keep key and ref separate (instead of mixed with props).
* Provide a Hooks-like interface that compares changes by CLJS `=` (instead of
  React's `Object.equal()`). (EXPERIMENTAL)
* Provide some macros for easier construction of class-based Components
  (instead of using `React.createClass()`). (TODO; this might be better as a
  separate project providing macro sugar to JS `class` syntax.)

Installation
------------

Packaging and distribution is not figured out yet.

A `deps.edn` file is included, so using this as a git dependency via deps.edn is
possible.

Usage
-----


### React Elements

`crinkle.component/RE` creates a React Element using React's normal key, props,
and children semantics. It's also the only way to create native elements.

```clojure

;;; Create DOM react elements
(require '[crinkle.component :as c] ;; element and component ctors
         '[crinkle.dom :as d])      ;; dom element ctors
         
(c/RE :div) ;; div with no props or elements
(c/RE "div" nil) ;; same. String or Keyword is fine (no runtime difference).

;; Like post-React.DOM react, you can use any dom element name you want
(c/RE :my-custom-element nil)

;; props can be map literals (no runtime cost), but one level deep only!
(c/RE :div {:className "my-class" :style #js{:width "100%"} :key "mykey"}
  ;; :key and :ref are special, like in React
  ;; Second argument is always props (use nil to omit props)
  ;; Third and follow arguments are assigned to :children property of props.
  (c/RE :span {:key "line1"} "Some text here"))
(c/RE :div #js{:className "my-class"}) ;; or js-object literals

;; Props argument can be something that evaluates at runtime to nil, map,
;; or js-object (This does map to js-object conversion at runtime shallowly.)
(c/RE :div (#(do {:className "my-class" :style #js{:width "100%"}})))
 
;; The crinkle.dom namespace provides convenince macros to be a little shorter.
;; Only the old React.DOM elements are supported.

(d/div {:className "my-class"})

;; You can also use a string for a dom element
(c/RE "div")
;; Or provide a React class or render function
;; (Props and children are turned to js-objects)
(c/RE my-react-class-or-function {:custom-prop "abc"})

```

### Crinkle Elements

Crinkle components use cljs props and do not mix key and ref with props, but
they also expand to React element literals without any extra runtime wrappers.
Use the `crinkle.component/CE` macro to create React Elements with Crinkle's
raw-props semantics. (CE = Crinkle Element)

```clojure
(c/CE my-react-class-or-function ; required
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
* `crinke.component/crinkle-factory` creates a crikle element factory with
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

React hooks are great, but using them efficiently requires given them an array
of objects to monitor for changes. Unfortunately, the comparator for the
elements in this array is not customizeable like `React.memo()` so there is no
way to benefit from using CLJS immutable data structures. Working around this
problem is a TODO, and may be impossible without patching react.

Some experiments reimplementing hooks in a CLJS-friendly way using normal
React components are in `crinkle.hook`, but right now this experiment suffers
from not being able to access the same effect scheduling that Hooks can.

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
