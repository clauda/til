(ns clojura.multi-methods)

(defrecord PatientParticular [id, name, birthday, priority])
(defrecord PatientHealthCare [id, name, birthday, priority, health-plan])

(defn authorizer-strategy [attendance]
  (let [patient (:patient attendance)
        priority (:priority patient)]
    (cond (= :urgent priority) :authorized
          (contains? patient :health-plan) :health-care-authorizer
          :else :wallet)))

(defmulti needs-previous-authorization? authorizer-strategy)

(defmethod needs-previous-authorization? :authorized [attendance]
  false)

(defmethod needs-previous-authorization? :wallet [attendance]
  (>= (:value attendance 0) 50))

(defmethod needs-previous-authorization? :health-care-authorizer [attendance]
  (not (some #(= % (:procedure attendance)) (:health-plan (:patient attendance)))))

(let [particular {:id 15 :name "Bernardo" :birthday "25/10/2017" :priority :normal}
      health-care {:id 13 :name "Ester" :birthday "02/03/2012" :priority :urgent :health-plan [:therapy :neuro]}]
  (println (needs-previous-authorization? {:patient particular, :value 500 :procedure :therapy}))
  (println (needs-previous-authorization? {:patient health-care, :value 1000 :procedure :blood})))

; Using Records
(println "Using Records...")

(let [particular (->PatientParticular 15, "Bernardo", "25/10/2017", :normal)
      health-care (->PatientHealthCare 13, "Ester", "02/03/2012", :normal, [:therapy, :neuro])]
  (println (needs-previous-authorization? {:patient particular, :value 500 :procedure :therapy}))
  (println (needs-previous-authorization? {:patient health-care, :value 1000 :procedure :blood})))

(let [particular (->PatientParticular 15, "Bernardo", "25/10/2017", :urgent)
      health-care (->PatientHealthCare 13, "Ester", "02/03/2012", :urgent, [:therapy, :neuro])]
  (println (needs-previous-authorization? {:patient particular, :value 500 :procedure :therapy}))
  (println (needs-previous-authorization? {:patient health-care, :value 1000 :procedure :blood})))