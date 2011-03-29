(ns euler.problem16
  (:use [clojure.contrib.math :only [expt]]))

(defn problem16
  ([] (problem16 (expt 2 1000)))
  ([x]
   (reduce +
     (map #(Integer. (str %))
          (str x)))))
