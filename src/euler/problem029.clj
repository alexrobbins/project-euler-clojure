(ns euler.problem029
  (:use [clojure.contrib.math :only [expt]]))

(defn a-to-the-b
  [a b]
  (expt a b))

(defn problem29
  ([] (problem29 100))
  ([n]
   (count
     (let [domain (range 2 (inc n))]
       (set
         (for [a domain
               b domain]
           (expt a b)))))))
