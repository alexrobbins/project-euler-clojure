(ns euler.problem7
  (:use [clojure.contrib.lazy-seqs :only [primes]]))

(defn problem-7
  ([] (problem-7 10001))
  ([n]
   (first (drop (dec n) primes))))
