(ns euler.problem002)

(defn fibonaccis
  ([]
   (map first
     (iterate (fn [[a b]] [b (+ a b)]) [1 2]))))
; Oh, clojure has fibonaccis defined in contrib.lazy-seqs

(defn euler-2
  ([]
   (reduce +
          (filter even?
                  (take-while #(> 4000000 %) (fibonaccis))))))
