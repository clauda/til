(ns clojura.schema
  (:require [schema.core :as s]))

; https://github.com/plumatic/schema
; Automatic validations
(s/set-fn-validation! true)

(def Hitchhiker
  "The Hitchhiker's Guide to the Galaxy Schema"
  {:id s/Num :name s/Str})

(println (s/explain Hitchhiker))
(println (s/validate Hitchhiker {:id 15 :name "Douglas Adams"}))

(s/defn ship :- Hitchhiker
  [id :- s/Num name :- s/Str]
  {:id id :name name})

(println (ship 51 "Mr. Dent"))

(def Spaceship
  "Spaceship Schema"
  {:id     (s/constrained s/Int pos?)
   :name   s/Str
   :code   (s/pred pos-int? 'positive-integer)
   (s/optional-key :engine) s/Str})

(println
  (s/validate Spaceship
              {:id     1
               :name   "Coração de Ouro"
               :code   42
               :engine "Gerador de Improbabilidade Infinita"}))

; Aula 3

(defn bigger-than-zero? [n] (>= n 0))
(def Price (s/constrained s/Num bigger-than-zero?))

(def Order
  {:user  Hitchhiker
   :value Price
   :item  s/Keyword})

(s/defn new-order :- Order
  [user   :- Hitchhiker
   value  :- Price
   item   :- s/Keyword]
  {:user user :value value :item item})

(println (new-order (ship 2 "Arthur") 12.54 :sandwich))

(def Item [s/Keyword])                                      ; vector
(println (s/validate Item [:cheese]))

; Aula 4

(def Customers
  {(s/pred pos-int?) Hitchhiker})

(println (s/validate Customers {}))
(let [ford {:id 13 :name "Ford"}]
  (println (s/validate Customers {13 ford})))

; Aula 5

(def Orders
  {(s/pred pos-int?) [s/Str]})

(s/defn serve :- Customers
  [users      :- Customers,
   hitchhiker :- Hitchhiker]
  (let [id (:id hitchhiker)]
    (assoc users id hitchhiker)))

(s/defn ordering :- Orders
  [orders :- Orders,
   user_id :- (s/pred pos-int?),
   new-orders :- [s/Str]]
  (if (contains? orders user_id)
    (update orders user_id concat new-orders)
    (assoc orders user_id new-orders)))

(s/defn report
  [orders :- Orders
   hitchhiker_id :- s/Int])

(defn print-report []
  (let [guilherme {:id 15 :name "Guilherme Silveira"}
        daniela   {:id 20 :name "Daniela"}
        users (reduce serve {} [guilherme daniela])
        ; ugly...
        orders {}
        orders (ordering orders 15 ["2019"])
        orders (ordering orders 15 ["20120" "2020"])]
    (println users)
    (println orders)
    (report orders 20)))

(print-report)
