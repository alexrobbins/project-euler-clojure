(ns euler.problem14)

(defn ex-seq
  [n]
  (concat
    (take-while #(not (= 1 %))
                (iterate (fn [x]
                           (if (even? x)
                             (/ x 2)
                             (+ (* 3 x) 1))) n))
    [1]))

(defn length-of-chain
  [n]
  (count (ex-seq n)))

(defn problem14
  ([] (problem14 1000000))
  ([n]
   (loop [longest [2 2] x 2]
     (if (>= x n)
       (first longest)
       (let [current-length (length-of-chain x)]
         (recur (if (> current-length (longest 1))
                  [x current-length]
                  longest)
                (inc x)))))))
