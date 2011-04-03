(ns euler.problem001)

(defn multiples
  ([base]
   (iterate #(+ base %) base)))

(def multiples-of-3 (multiples 3))
(def multiples-of-5 (multiples 5))

(defn euler-1
  ([]
   (apply +
          (set
            (concat
              (take-while #(> 1000 %) multiples-of-3)
              (take-while #(> 1000 %) multiples-of-5))))))
