(ns real-world-clojure-api.component.example-component
  (:require [com.stuartsierra.component :as component]))

(defrecord ExampleComponent
           [config]
  component/Lifecycle

  (start [component]
    (println ";; Starting ExampleComponent")
    (assoc component :state ::started))

  (stop [component]
    (println ";; Stopping ExampleComponent")
    (assoc component :connection nil)))


(defn new-example-component
  [config]
  (map->ExampleComponent {:config config}))