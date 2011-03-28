(ns euler.problem4)

(defn palindrome?
  [n]
  (= (str n) (apply str (reverse (str n)))))

(defn problem-4-v1
  ([] (problem-4 1000))
  ([n]
   (reduce max
     (for [x (range n 99 -1) y (range n 99 -1) :let [product (* x y)]
           :when (palindrome? product)] product))))
