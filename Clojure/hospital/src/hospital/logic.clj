(ns hospital.logic
  (:require [hospital.model :as h.model]
            [schema.core :as s]))

; Test Driven Development

(defn has-available-seats?
  [hospital department]
  (some-> hospital
          department
          count
          (< 5)))

(defn arrives
  [hospital department person]
  (if (has-available-seats? hospital department)
    (update hospital department conj person)
    (throw (IllegalStateException. "No available seats"))))

(s/defn attend :- h.model/Hospital
  [hospital :- h.model/Hospital, department :- s/Keyword]
  (update hospital department pop))

(s/defn next-patient :- (s/maybe h.model/PatientID)
        "Returns next in the queue"
        [hospital :- h.model/Hospital, department :- s/Keyword]
        (-> hospital
            department
            peek))

(defn same-size? [hospital, another-hospital, from, to]
  (= (+ (count (get another-hospital from)) (count (get another-hospital to)))
     (+ (count (get hospital from)) (count (get hospital to)))))

(s/defn transfer :- h.model/Hospital
        "Transfer the next patient"
        [hospital :- h.model/Hospital, from :- s/Keyword, go-to :- s/Keyword]
        {:pre [(contains? hospital go-to), (contains? hospital from)]
         :post [(same-size? hospital % from go-to)]}
        (let [person (next-patient hospital from)]
          (-> hospital
              (attend from)
              (arrives go-to person))))

(defn patients-counter [hospital]
  (reduce + (map count (vals hospital))))
