(ns real-world-clojure-api.core
  (:require [real-world-clojure-api.config :as config]
            [com.stuartsierra.component :as component]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [real-world-clojure-api.component.example-component :as example-component]
            [real-world-clojure-api.component.pedestal-component :as pedestal-component]))

(defn respond-hello [request]
  {:status 200 :body "Hello, world!"})

(def routes
  (route/expand-routes
   #{["/greet" :get respond-hello :route-name :greet]}))

(defn create-server [config]
  (http/create-server
   {::http/routes routes
    ::http/type   :jetty
    ::http/join? false
    ::http/port (-> config :server :port)}))

(defn start [config]
  (http/start (create-server config)))

(defn real-world-clojure-api-system
  [config]
  (component/system-map :example-component (example-component/new-example-component
                                            config)
                        :pedestal-component/new-pedestal-component (pedestal-component/new-pedestal-component config)))

(defn -main
  []
  (let [system (-> (config/read-config)
                   (real-world-clojure-api-system)
                   (component/start-system))]
    (println "Start Real-World Clojure API with config")
    (.addShutdownHook
     (Runtime/getRuntime)
     (new Thread #(component/stop-system system)))))
