(ns real-world-clojure-api.core
  (:require [real-world-clojure-api.config :as config]))

(defn -main
  []
  (let [config (config/read-config)]
    (println "Start Real-World Clojure API with config" config)))
