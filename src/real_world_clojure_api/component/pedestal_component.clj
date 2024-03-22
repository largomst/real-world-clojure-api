(ns real-world-clojure-api.component.pedestal-component
  (:require [com.stuartsierra.component :as component]))

(defrecord PedestalComponent
           [config example-component]
  component/Lifecycle

  (start [component]
    (println ";; Starting PedestalComponent")
    (assoc component :state ::started))

  (stop [component]
    (println ";; Stopping PedestalComponent")
    (assoc component :connection nil)))


(defn new-pedestal-component
  [config]
  (map->PedestalComponent {:config config}))