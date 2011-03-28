(ns euler.problem6)

(defn sum-of-squares
  [upper-limit]
  (reduce + 0
          (map #(* % %) (range 1 (inc upper-limit)))))

(defn square-of-sums
  [upper-limit]
  (let [sum-of-items (reduce + (range 1 (inc upper-limit)))]
    (* sum-of-items sum-of-items)))

(defn problem-6
  ([] (problem-6 100))
  ([x]
   (- (square-of-sums x) (sum-of-squares x))))
