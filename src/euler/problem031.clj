(ns euler.problem031)

(declare combinations)

(defn add-coin-to-results-for-value
  "Add coins to the previous results."
  [coin value coins]
  (map #(merge-with + (array-map coin 1) %) (combinations coins value)))

(defn valid-coins
  "Return a seq of valid coins and the amount left after that coin is added."
  [coins goal]
  (filter #(<= 0 (:remaining %))
    (map #(array-map :remaining (- goal %) :current-coin %) coins)))

(defn combinations
  [coins goal]
  (cond 
    (= 0 goal) #{{}}
    true (set
           (apply concat
             (map #(add-coin-to-results-for-value (:current-coin %)
                                                  (:remaining %)
                                                  coins)
                  (valid-coins coins goal))))))

(def combinations (memoize combinations))

(defn number-of-combinations-for-goal
  "Gets the last value, but builds a memoization cache by moving up from zero."
  [coins goal]
  (last
    (map
      #(let [count (count (combinations coins %))]
         (do
           (println % ": " count)
           count))
      (range 0 (inc goal)))))

; The current solution is still too slow, but it isn't too slow to run. Not
; going to work on this further since I'm tired of this problem.
(defn problem31
  ([] (problem31 [1 2 5 10 20 50 100 200] 200))
  ([coins goal]
   (number-of-combinations-for-goal coins goal)))
