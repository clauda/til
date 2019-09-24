(ns clojura.cache
  (:require [clojura.logic :as c.logic]))


(defn attendance [id]
  (println "Loading" id)
  (Thread/sleep 1000)
  { :id id, :loaded-at (c.logic/now)})

; Pure!
(defn- pre-consultation
  [cache id loader-fn]
  (if (contains? cache id)
    cache
    (let [patient (loader-fn id)]
      (assoc cache id patient))))

(defprotocol Loadable
  (load! [this id]))

(defrecord Cache
  [cache loader-fn]
  Loadable
  (load! [this id]
    (swap! cache pre-consultation id loader-fn)
    (get @cache id)))

(def patients (->Cache (atom {}), attendance))
(println patients)
(load! patients 15)
(load! patients 30)
(println patients)