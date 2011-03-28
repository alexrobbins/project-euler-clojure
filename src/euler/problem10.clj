(ns euler.problem10
  (:use [clojure.contrib.lazy-seqs :only [primes]]))

(defn problem-10
  ([] (problem-10 2000000))
  ([x]
   (reduce +
           (take-while #(< % x) primes))))
