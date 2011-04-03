(ns euler.problem022
  (:use [clojure-csv.core :only [parse-csv]]))

(defn alphabetical-value
  [word]
  (let [baseline (dec (int \A))]
    (reduce +
      (map #(- (int %) baseline) (.toUpperCase word)))))

(defn problem22
  ([] (problem22 "assets/names.txt"))
  ([file]
   (loop [names (seq (sort (first (parse-csv (slurp "assets/names.txt")))))
          score 0 iteration 1]
     (if names
       (recur (next names)
              (+ score (* iteration (alphabetical-value (first names))))
              (inc iteration))
       score))))
