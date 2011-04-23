(ns euler.problem009
  (:use [clojure.contrib.math :only [expt]]))

; a < b < c
; a,b,c are natural numbers
; a + b + c = 1000
; a^2 + b^2 = c^2

; find (* a b c)
;

(defn problem-9
  ([] (problem-9 1000))
  ([sum]
     (apply *
            (first
             (for [c (range 1 (inc sum))
                   :let [c2 (* c c)]
                   b (range 1 c)
                   :let [b2 (* b b)]
                   a (range 1 b)
                   :when (and (= sum (+ a b c))
                              (= (+ (* a a) b2)
                                 c2))]
               [a b c])))))
