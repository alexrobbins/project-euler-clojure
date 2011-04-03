(ns euler.problem021
  (:use [euler.core :only [divisors]]))

(defn proper-divisors
  [n]
  (filter #(> n %) (divisors n)))

(defn sum-of-divisors
  [n]
  (reduce + (proper-divisors n)))

(def sum-of-divisors (memoize sum-of-divisors))

(defn amicable?
  [n]
  (cond
    (= 0 n) false
    (= 1 n) false
    (= n (sum-of-divisors n)) false
    true (= (sum-of-divisors (sum-of-divisors n)) n)))

(defn problem21
  ([] (problem21 10000))
  ([x]
   (reduce +
           (filter amicable?  (range x)))))
