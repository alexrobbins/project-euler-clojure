(ns euler.core
  (:use [clojure.contrib.lazy-seqs :only [primes]]))

(defn prime-factors
  [n]
  (loop [factors [] n n]
    (if (= 1 n)
      factors
      (let [next-factor (first (filter #(= 0 (rem n %)) primes))]
        (recur (conj factors next-factor) (quot n next-factor))))))
