(ns euler.problem009
  (:use [clojure.contrib.math :only [expt]]))

; a < b < c
; a,b,c are natural numbers
; a + b + c = 1000
; a^2 + b^2 = c^2

; find (* a b c)
;

(defn problem-9                         ; brute forced :)
  ([] (problem-9 1000))
  ([sum]
     (apply *
            (first
             (for [a (range 1 (inc sum))
                   :let [a2 (* a a)]
                   b (range 1 a)
                   :let [b2 (* b b)]
                   c (range 1 b)
                   :when (and (= sum (+ a b c))
                              (= (+ (* c c) b2)
                                 a2))]
               [a b c])))))
