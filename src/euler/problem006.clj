(ns euler.problem006)

(let [square #(* % %)
      domain #(rest (range (inc %)))]

  (defn sum-of-squares
    [items]
    (reduce + (map square items)))

  (defn square-of-sums
    [items]
    (square (reduce + items)))

  (defn problem-6
    ([] (problem-6 100))
    ([limit]
       (apply - ((juxt square-of-sums sum-of-squares)
                 (domain limit))))))
