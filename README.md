# cloxp.cljs-eval-test

For testing [cloxp-cljs-repl](https://github.com/cloxp/cloxp-cljs-repl).

# Usage

## 1. Install

```sh
$ git clone https://github.com/cloxp/cljs-eval-test
```

## 2. cloxp

Open cloxp (http://localhost:9001/cloxp.html), start a Clojure server, and load
cljs-eval-test project.

(Via a browser or eval the code below)

```js
clojure.Projects.loadProjectInteractively({
  projectDir: "/path/to/cloxp.cljs-eval-test",
  askToLoadNamespaces: false,
  setCurrentDir: true,
  informBrowsers: true
});
```

## 3. Try cljs eval

In a cloxp workspace run:

```clj
(require '[rksm.cloxp-cljs-repl.core :as cljs-repl])
(require '[cloxp.cljs-eval-test.server
           :refer [find-workspace-client start stop-server]])

; compiles cljs + starts cljs-repl server:
(start)

; open a web browser (on Mac OS):
(clojure.java.shell/sh "open" "http://localhost:8084/workspace-tester.html")

; try remote eval
(when-let [{id :id} (find-workspace-client)]
  (cljs-repl/eval-cljs-string
   "(js/alert \"This was send from the server\")"
   {:target-id id}))

; open a ClojureScript workspace via menu bar -> open
; choose the connection to the client and eval stuff in there!

```