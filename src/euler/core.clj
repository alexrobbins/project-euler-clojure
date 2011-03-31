(ns euler.core
  (:use [clojure.contrib.combinatorics :only [cartesian-product]]
        [clojure.contrib.lazy-seqs :only [primes]]
        [clojure.contrib.math :only [expt]]))

; This function was swiped from the labrepl euler solutions file.
; It is a lot faster than the factorizer I wrote.
(defn prime-factors
  [n]
  (loop [factors [] n n]
    (if (= 1 n)
      factors
      (let [next-factor (first (filter #(= 0 (rem n %)) primes))]
        (recur (conj factors next-factor) (quot n next-factor))))))




; These are functions that seemed to come up over and over. I didn't go
; back and put them into earlier problems that could have used them.

; Lots of the problems have you sum the digits. Dropping this into core.
(defn sum-of-digits
  [n]
  (reduce +
          (map #(Integer. (str %))
               (str n))))

; Fast method to get all the divisors of a number.
; Finds the prime factors, then does a cartesian product of the possible
; exponents of the prime factors.
; Ex: (prime-factors 20) => [2 2 5]
; That is 2^2 and 5^1. Possible divisors are:
;   2^0 * 5^0 = 1
;   2^0 * 5^1 = 5
;   2^1 * 5^0 = 2
;   2^1 * 5^1 = 10
;   2^2 * 5^0 = 4
;   2^2 * 5^1 = 20
(defn divisors
  [n]
  (let [d-counts (-> n
                   (prime-factors)
                   (frequencies))]
    (let [factors (map first d-counts)
          counts (map second d-counts)]
      (sort
        (map
          #(reduce * (map (fn [[base exp]] (expt base exp)) %))
          (map (comp (partial partition 2)(partial interleave factors))
               (apply cartesian-product
                      (loop [counts (seq counts) seqs []]
                        (if counts
                          (recur (next counts)
                                 (conj seqs (range (inc (first counts)))))
                          seqs)))))))))

(defn proper-divisors
  "Since divisors returns a sorted list, we can just drop the last divisor."
  [n]
  (butlast (divisors n)))
