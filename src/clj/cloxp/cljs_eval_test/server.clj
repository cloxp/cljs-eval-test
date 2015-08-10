(ns cloxp.cljs-eval-test.server
  (require [rksm.cloxp-com.server :as com.server]
           [rksm.cloxp-com.messenger :as com.messenger]
           [rksm.cloxp-cljs.compilation :as comp]
           [clojure.core.async :as async :refer [<!! >!! chan go]]
           [clojure.java.io :as io]))

(defonce project-dir (-> "." io/file .getCanonicalFile))

(defonce server (atom nil))

(defn ensure-compiled-cljs
  []
  (comp/compile-cljs-in-project 'cloxp.cljs-eval-test.core
                                nil project-dir nil nil))

(defn start-server
  []
  (assert (nil? @server))
  (ensure-compiled-cljs)
  (reset! server (com.server/ensure-server!
                  :port 8084
                  :fs-root (io/file project-dir "public"))))

(defn stop-server
  []
  (when-let [s @server]
    (com.server/stop-server! s)
    (reset! server nil)))

(defn start
  []
  (start-server)
  (ensure-compiled-cljs))

; -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

(defn find-workspace-client
  []
  (->> (com.server/all-connections)
    (filter #(re-find #"workspace-tester.html" (:document-url %)))
    first))

; -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

(comment

 (start)

 (stop-server)

 (when-let [{id :id} (find-workspace-client)]
   (cljs-repl/eval-cljs-string
    "(js/alert \"This was send from the server\")"
    {:target-id id}))

 )