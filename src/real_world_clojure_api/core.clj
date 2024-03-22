(ns real-world-clojure-api.core
  (:require [real-world-clojure-api.config :as config]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))

(defn respond-hello [request]
  {:status 200 :body "Hello, world!"})

(def routes
  (route/expand-routes
   #{["/greet" :get respond-hello :route-name :greet]}))

(defn create-server []
  (http/create-server
   {::http/routes routes
    ::http/type   :jetty
    ::http/port   8890}))

(defn start []
  (http/start (create-server)))


(defn -main
  []
  (let [config (config/read-config)]
    (println "Start Real-World Clojure API with config" config)
    (start)))
