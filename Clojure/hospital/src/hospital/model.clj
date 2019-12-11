(ns hospital.model
  (:require [schema.core :as s])
  (:import (clojure.lang PersistentQueue)))

(def empty-queue PersistentQueue/EMPTY)

(defn create-hospital []
  {:waiting       empty-queue
   :triage        empty-queue
   :laboratory    empty-queue})

(defn new-department []
  empty-queue)

(s/def PatientID s/Str)
(s/def Department (s/queue PatientID))
(s/def Hospital {s/Keyword Department})

;(s/validate PatientID "Guilherme")
;(s/validate PatientID 15)
;(s/validate Department (conj empty-queue "Guilherme" "Daniela"))
;(s/validate Hospital {:waiting (conj empty-queue "Guilherme" "Daniela")})