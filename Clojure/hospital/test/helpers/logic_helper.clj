(ns helpers.logic_helper
  (:require [clojure.test :refer :all]
            [hospital.logic :refer :all]
            [hospital.model :as h.model]
            [clojure.test.check.generators :as gen]
            [schema-generators.generators :as g])
  (:import (clojure.lang ExceptionInfo)))

(def random-name-gen
  (gen/fmap clojure.string/join
            (gen/vector gen/char-alphanumeric 5 10)))

(defn vector-to-queue [ex-vector]
  (reduce conj h.model/empty-queue ex-vector))

(def populated-queue-gen
  (gen/fmap
    vector-to-queue
    (gen/vector random-name-gen 0 4)))

(defn transfer-handler [the-hospital next-room]
  (try
    (transfer the-hospital :waiting next-room)
    (catch ExceptionInfo e
      ;(println "boom!")
      the-hospital)))

(defn addon-waiting [[hospital queue]]
  (assoc hospital :waiting queue))

(def hospital-gen
  (gen/fmap
    addon-waiting
    (gen/tuple (gen/not-empty (g/generator h.model/Hospital))
               populated-queue-gen)))

(def arrives-gen
  "Arrives generator"
  (gen/tuple (gen/return arrives)
             (gen/return :waiting)
             random-name-gen
             (gen/return 1)))

(defn add-nonexistent-department [department]
  (keyword (str department "-nonexistent")))

(defn transfer-gen [hospital]
  (let [departments (keys hospital)
        nonexistent-department (map add-nonexistent-department departments)
        all-departments (concat departments nonexistent-department)]
    (gen/tuple (gen/return transfer)
               (gen/elements all-departments)               ; ?
               (gen/elements all-departments)
               (gen/return 0))))

(defn action-gen [hospital]
  (gen/one-of [arrives-gen (transfer-gen hospital)]))

(defn many-actions-gen [hospital]
  (gen/not-empty (gen/vector (action-gen hospital) 1 100)))

(defn run-action [status [fun-action param1 param2 success-delta]]
  (let [hospital (:hospital status)
        current-delta (:delta status)]
    (try
      (let [new-hospital (fun-action hospital param1 param2)]
        {:hospital new-hospital
         :delta (+ success-delta current-delta)})
    (catch IllegalStateException e
      status)
    (catch AssertionError e
      status))))