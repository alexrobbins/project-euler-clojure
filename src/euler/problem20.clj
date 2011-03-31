(ns euler.problem20
  (:use [euler.core :only [sum-of-digits]]))

(defn factorial
  [n]
  (loop [n n acc 1]
    (if (= n 1)
      acc
      (recur (dec n) (* n acc)))))

(defn problem20
  ([] (problem20 100))
  ([x]
   (sum-of-digits
     (factorial x))))
