(ns euler.problem23
  (:use [euler.core :only [proper-divisors]]))

(defn proper-number-comparator-maker
  [comp-func]
  (fn [n]
    (comp-func n
               (reduce + (proper-divisors n)))))

(def perfect-number? (proper-number-comparator-maker =))
(def deficient? (proper-number-comparator-maker >))
(def abundant? (proper-number-comparator-maker <))

; Upper limit 28123

(defn perfect-numbers [] (filter perfect-number? (range)))

; perfect-numbers is super slow, something is broken
