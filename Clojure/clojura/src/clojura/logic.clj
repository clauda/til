(ns clojura.logic
  (:require [clojura.model :as c.model]))

(defn now []
  (c.model/to-ms (java.util.Date.)))