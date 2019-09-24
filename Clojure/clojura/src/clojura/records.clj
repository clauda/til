(ns clojura.records)

(defrecord PatientParticular [id, name, birthday])
(defrecord PatientHealthCare [id, name, birthday, health-plan])

(defprotocol Billiable
  (needs-previous-authorization? [patient procedure value]))

(extend-type PatientParticular
  Billiable
  (needs-previous-authorization? [patient, procedure, value]
    (>= value 50)))

(extend-type PatientHealthCare
  Billiable
  (needs-previous-authorization? [patient, procedure, value]
    (let [plan (:health-plan patient)]
      (not (some #(= % procedure) plan)))))


(let [particular  (->PatientParticular 15, "Bernardo", "25/10/2017")
      health-care (->PatientHealthCare 13, "Ester", "02/03/2012", [:therapy, :neuro])]
  (println (needs-previous-authorization? particular, :therapy, 500))
  (println (needs-previous-authorization? particular, :therapy, 40))
  (println (needs-previous-authorization? health-care, :therapy, 499990))
  (println (needs-previous-authorization? health-care, :blood, 499990)))
