(ns cloxp.cljs-eval-test.core
  (:require [rksm.cloxp-com.cloxp-client :as cloxp]
            [rksm.cloxp-com.messenger :as messenger]
            [rksm.cloxp-cljs-repl.eval :as repl]))

(cloxp/start
 :host "localhost" :port 8084
 :then-do (fn [con]
            (try 
              (do
                (messenger/add-service con "eval-js" repl/eval-js-service)
                (messenger/add-service con "load-js" repl/load-js-service))
              (catch js/Error e (println e)))))
