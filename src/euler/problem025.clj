(ns euler.problem025
  (:use [clojure.contrib.lazy-seqs :only [fibs]]))

(defn number-of-digits
  [n]
  (count (str n)))

(defn problem25
  ([] (problem25 1000))
  ([n]
   (first
     (first
       (drop-while
         (fn [[i curr-fib]] (< (number-of-digits curr-fib) n))
         (map-indexed vector (fibs)))))))
