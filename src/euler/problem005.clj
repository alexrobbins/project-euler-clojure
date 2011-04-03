(ns euler.problem005
  (:use [clojure.contrib.lazy-seqs :only [primes]]
        [clojure.contrib.math :only [expt]]))

;(* 1 2 3 4 5 6 7 8 9 10)
;
;(* 7 8 9 10)
;
;[1 2 3 [2 2] 5 [2 3] 7 [2 2 2] [3 3] [2 5]]
;
;(frequencies [1 2 2 3])

; merge-with max

; Strategy: Get the prime factorization of each number
; Get the max number of each prime factor across all factorizations
; Multiply the max number of each prime factor together

(defn prime-factors
  [n]
  (loop [factors [] n n]
    (if (= 1 n)
      factors
      (let [next-factor (first (filter #(= 0 (rem n %)) primes))]
        (recur (conj factors next-factor) (quot n next-factor))))))

(defn problem-5
  ([] (problem-5 20))
  ([n]
   (reduce (fn [n [base exp]] (* n (expt base exp))) 1
     (apply merge-with max
          (map (comp frequencies prime-factors)
               (range 2 (inc n)))))))
