(ns euler.problem012
  (:use [euler.core :only [prime-factors]]))

(defn triangle-number
  [n]
  (/ (* (+ n 1) n) 2))

(defn triangle-numbers
  []
  (map first (iterate (fn [[a b]] [(triangle-number b) (inc b)]) [1 2])))

(defn number-of-factors-slow
  [n]
  (count
    (for [x (range 1 (inc n)) :when (zero? (rem n x))] x)))

;too slow brute force
;(defn problem-12-brute
;  ([] (problem-12 500))
;  ([n]
;   (first (drop-while #(>= 500 (number-of-divisors %)) (triangle-numbers)))))

; New strategy: Counting factors one at a time is too slow, we can calculate the
; number of factors by finding the prime factors.
; The formula is for each prime, multiply by (1 + <occurrances of that prime>)
;
; For example, 12's prime factorization is [3 4]
;          from 3  from 4
; Factors: (1 + 1)(1 + 1) = 2 * 2 = 4

(defn number-of-factors
  [n]
  (loop [number 1 prime-factors (seq (frequencies (prime-factors n)))]
    (if prime-factors
      (recur (* number (+ 1 ((first prime-factors) 1))) (next prime-factors))
      number)))

(defn problem-12
  ([] (problem-12 500))
  ([n]
   (first (drop-while #(>= 500 (number-of-factors %)) (triangle-numbers)))))
