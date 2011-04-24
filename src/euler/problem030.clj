(ns euler.problem030
  (:use [clojure.contrib.math :only [expt]]))

(defn sum-of-power-of-digits
  [power n]
  (reduce +
          (map #(expt (Integer. (str %)) power) (str n))))

(defn is-sum-of-power?
  [power n]
  (= n (sum-of-power-of-digits power n)))

(defn problem30
  ([] (problem30 5 1000000))
  ([power upper-limit]
   (reduce +
           (filter (partial is-sum-of-power? power)
                   (range 2 upper-limit)))))

;10 000 000 has the same value as 1 000 000, so I'm guessing that is the total.
