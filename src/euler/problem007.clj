(ns euler.problem007
  (:use [clojure.contrib.lazy-seqs :only [primes]]))

(defn problem-7
  ([] (problem-7 10001))
  ([n]
   (nth primes (dec n))))
