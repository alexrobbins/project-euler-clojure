(ns euler.problem9
  (:use [clojure.contrib.math :only [expt]]))

; a < b < c
; a,b,c are natural numbers
; a + b + c = 1000
; a^2 + b^2 = c^2

; find (* a b c)
;

(defn problem-9 ; brute forced :)
  ([] (problem-9 1000))
  ([sum]
   (for [a (range (dec sum)) b (range sum) c (range (inc sum))
     :when (and (and (< a b) (< b c))
                (= 1000 (+ a b c))
                (= (+ (expt a 2) (expt b 2)) (expt c 2)))]
     (* a b c))))
