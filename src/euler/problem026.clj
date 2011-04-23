(ns euler.problem026
  (:use [euler.core :only [divides?]]))

;http://en.wikipedia.org/wiki/Recurring_decimal
;
; A fraction in lowers terms with a prime denominator other than 2 or 5
; (coprime to 10) always products a repeating decimal.
; 
; After spending more time than I wanted trying to figure out the math,
; I found the algorithm in python here:
; http://tafakuri.net/?p=69
; (I'm trying to learn clojure, not math)

(defn recurring-cycle-length
  [n]
  (loop [n n]
    (if (divides? n 2)
      (recur (/ n 2))
      (if (divides? n 5)
        (recur (/ n 5))
        (if (> n 1)
          (let [a (mod 10 n)]
            (loop [a a cycle-length 1]
              (if (not (= a 1))
                (recur (mod (* 10 a) n) (inc cycle-length))
                cycle-length)))
          0)))))

(defn problem26
  ([] (problem26 1000))
  ([n]
   (first
     (reduce #(if (> (second %1) (second %2)) %1 %2)
             (for [x (range 2 n) :let [length (recurring-cycle-length x)]]
               [x length])))))
