(defproject cloxp/cljs-eval-test "0.1.0-SNAPSHOT"
  :description "Example and for testing cloxp-cljs-repl"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "0.0-3308"]
                 [org.rksm/cloxp-com "0.1.9-SNAPSHOT"]
                 [cloxp-cljs-repl "0.1.0-SNAPSHOT"]]
  :plugins [[lein-cljsbuild "1.0.6"]]
  :cljsbuild {:builds {:default {:source-paths ["src/cljs"]}}}
  :source-paths ["src/clj" "src/cljs"]
  :clean-targets ["cloxp-cljs-build" :target-path]
  :deploy-repositories [["releases" :clojars]])
