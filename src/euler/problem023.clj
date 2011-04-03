(ns euler.problem023
  (:use [euler.core :only [proper-divisors]]))

(defn proper-number-comparator-maker
  [comp-func]
  (fn [n]
    (comp-func n
               (reduce + (proper-divisors n)))))

(def perfect-number? (proper-number-comparator-maker =))
(def deficient? (proper-number-comparator-maker >))
(def abundant? (memoize (proper-number-comparator-maker <)));memoized for speed

; Upper limit 28123

(defn perfect-numbers [] (filter perfect-number? (range)))
(defn abundant-numbers [] (filter abundant? (range)))

(defn sum-of-abundant-numbers?
  [n]
  (loop [ab-nums (seq (take-while #(>= (/ n 2) %) (abundant-numbers)))]
    (if ab-nums
      (if (abundant? (- n (first ab-nums)))
        true
        (recur (next ab-nums)))
      false)))

(defn problem23
  ([] (problem23 28123))
  ([n]
   (reduce +
           (filter
             (complement sum-of-abundant-numbers?) (range n)))))
