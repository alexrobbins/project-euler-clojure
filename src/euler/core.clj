(ns euler.core
  (:use [clojure.contrib.lazy-seqs :only [primes]]))

; This function was swiped from the labrepl euler solutions file.
; It is a lot faster than the factorizer I wrote.
(defn prime-factors
  [n]
  (loop [factors [] n n]
    (if (= 1 n)
      factors
      (let [next-factor (first (filter #(= 0 (rem n %)) primes))]
        (recur (conj factors next-factor) (quot n next-factor))))))
