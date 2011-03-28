; What is the largest prime factor of 600851474143

; Strategy:
;
; Find the smallest prime factor. The largest prime factor cannot be bigger
; than the number divided by the smallest prime factor. This cuts our
; problem domain significantly.
;
; After we know the upper and lower limit of the possible answers, brute force
; it.

(ns euler.problem3
  (:use [clojure.contrib.lazy-seqs :only [primes]]))

;(defn factor?
;  ([n d]
;   (zero? (rem n d))))
;
;(defn smallest-factor ;Automatically prime, or it wouldn't be the smallest
;  ([number]
;    (loop [n 2]
;      (if (factor? number n)
;        n
;        (recur (inc n))))))
;
;(defn largest-prime-factor ; Works for smaller numbers,
;  ; but too slow for such a big number.
;  ([number]
;   (let [biggest-to-check (/ number (smallest-factor number))]
;     (let [x (last
;               (filter
;                 (partial factor? number)
;                 (take-while #(>= biggest-to-check %) primes)))]
;       (if (= x number)
;         "No prime factors"
;         x)))))

; Prime factorization is unique. Could have just divided each time and factored
; the quotient. No need to run up all the way checking primes.

; Method from labrepl solutions

(defn prime-factors
  "Returns the prime factors from smallest to largest"
  [n]
  (loop [factors [] n n]
    (if (< n 2)
      factors
      (let [next-factor (first (filter #(zero? (rem n %)) primes))]
            (recur (conj factors next-factor) (quot n next-factor))))))

(defn problem-3
  ([] (problem-3 600851475143))
  ([n] (last (prime-factors n))))
