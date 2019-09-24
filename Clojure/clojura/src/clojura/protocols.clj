(ns clojura.protocols)

(defrecord Patient [id, name, birthdate])

(let [patient (->Patient 15 "Laise" "18/9/1981")]
  (println (:id patient))
  (println (vals patient))
  (println (class patient))
  (println (record? patient))
  (println (.name patient)))

; Using map
(println (map->Patient {:id 42 :name "Arthur Dent" :birthdate "18/9/1981"}))
