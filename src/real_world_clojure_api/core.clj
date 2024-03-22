(ns real-world-clojure-api.core
  (:require [real-world-clojure-api.config :as config]
            [com.stuartsierra.component :as component]
            [real-world-clojure-api.component.example-component :as example-component]
            [real-world-clojure-api.component.pedestal-component :as pedestal-component]))



(defn real-world-clojure-api-system
  [config]
  (component/system-map :example-component
                        (example-component/new-example-component config)
                        :pedestal-component/new-pedestal-component
                        (component/using
                         (pedestal-component/new-pedestal-component config)
                         [:example-component])))

(defn -main
  []
  (let [system (-> (config/read-config)
                   (real-world-clojure-api-system)
                   (component/start-system))]
    (println "Start Real-World Clojure API with config")
    (.addShutdownHook
     (Runtime/getRuntime)
     (new Thread #(component/stop-system system)))))
