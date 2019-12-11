(ns hospital.logic-test
  (:require [clojure.test :refer :all]
            [hospital.logic :refer :all]
            [hospital.model :as h.model]
            [helpers.logic_helper :as helper]
            [clojure.test.check.clojure-test :refer (defspec)]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [schema.core :as s])
  (:import (clojure.lang ExceptionInfo)))

(s/set-fn-validation! true)

(deftest has-available-seats?-test

  (testing "Has available seat"
    (is (has-available-seats? {:waiting []}, :waiting)))

  (testing "Has no available seat when the queue is full"
    (is (not (has-available-seats? {:waiting [1 5 37 54 21]}, :waiting))))

  (testing "Has no available seat when has more than one full queue"
    (is (not (has-available-seats? {:waiting [1 2 3 4 5 6]}, :waiting))))

  (testing "Has available seat when the queue is not full"
    (is (has-available-seats? {:waiting [1 2 3 4]}, :waiting))
    (is (has-available-seats? {:waiting [1 2]}, :waiting)))

  (testing "Has no available seat when the department even exists"
    (is (not (has-available-seats? {:waiting [1 2 3 4]}, :raio-x))))

  (testing "Has available seat when queue has one random person"
    (doseq [queue (gen/sample (gen/vector gen/string-alphanumeric 0 4) 20)]
      (is (has-available-seats? {:waiting queue} :waiting)))))

(deftest arrives-test

  (let [full-hospitall {:waiting [1 35 42 64 21]}]

    (testing "accept people while has available seats"
      (is (= {:waiting [1, 2, 3, 4, 5]}
            (arrives {:waiting [1, 2, 3, 4]}, :waiting, 5)))
      (is (= {:waiting [1, 2, 5]}
            (arrives {:waiting [1, 2]}, :waiting, 5))))

    (testing "doens't accept if has no available seats"
      (is (thrown? ExceptionInfo
                   (arrives full-hospitall, :waiting 76))))))

(deftest transfer-test

  (testing "accept people"
    (let [original-hospital {:waiting (conj h.model/empty-queue "5"), :raio-x h.model/empty-queue}]
      (is (= {:waiting []
              :raio-x ["5"]}
            (transfer original-hospital :waiting :raio-x))))

    (let [original-hospital {:waiting (conj h.model/empty-queue "51" "5"), :raio-x (conj h.model/empty-queue "13")}]
      (is (= {:waiting ["5"]
              :raio-x ["13" "51"]}
            (transfer original-hospital :waiting :raio-x)))))

  (testing "refuse people"
    (let [full-hospital {:waiting (conj h.model/empty-queue "5"), :raio-x (conj h.model/empty-queue "1" "54" "43" "12" "51")}]
      (is (thrown? ExceptionInfo
                   (transfer full-hospital :waiting :raio-x)))))

  (testing "can't call transfer without a hospital"
    (is (thrown? ExceptionInfo
                 (transfer nil :waiting :raio-x))))
  (testing "mandatory rules"
    (let [hospital {:waiting (conj h.model/empty-queue "5"), :raio-x (conj h.model/empty-queue "1" "54" "43" "12")}]
      (is (thrown? AssertionError
                   (transfer hospital :nonexists :raio-x)))
      (is (thrown? AssertionError
                   (transfer hospital :raio-x :nonexists))))))

; defspec

(defspec add-person-on-queue-with-available-seats 13
         (prop/for-all
           [queue (gen/vector gen/string-alphanumeric 0 4)
            person gen/string-alphanumeric]
           (is (= {:waiting (conj queue person)}
                 (arrives {:waiting queue} :waiting person)))))

(defspec transfer-should-keep-people-size 9
         (prop/for-all
           [waiting (gen/fmap helper/vector-to-queue (gen/vector helper/random-name-gen 0 30))
            laboratory helper/populated-queue-gen
            triage helper/populated-queue-gen
            goes-to (gen/vector (gen/elements [:laboratory :triage]) 0 50)]

           (let [initial-hospital {:waiting waiting :triage triage :laboratory laboratory}
                 another-hospital (reduce helper/transfer-handler initial-hospital goes-to)]
              (= (patients-counter initial-hospital)
                 (patients-counter another-hospital)))))

(defspec one-day-in-hospital-simulation 50
         (prop/for-all
           [initial-hospital helper/hospital-gen]
           (let [actions (gen/generate (helper/many-actions-gen initial-hospital))
                 initial-status {:hospital initial-hospital, :delta 0}
                 initial-patients-counter (patients-counter initial-hospital)
                 final-status (reduce helper/run-action initial-status actions)
                 final-patientes-counter (patients-counter (:hospital final-status))]
             (println final-patientes-counter)
             (is (= (- final-patientes-counter (:delta final-status)) initial-patients-counter)))))
