(ns euler.problem031)

; Brute force that is too slow
;(defn inclusive-range
;  [bottom top]
;  (range bottom (inc top)))
;
; How to write this as a macro?
;(defn problem31
;  ([] (problem31 [1 2 5 10 20 50 100 200] 200))
;  ([coins goal]
;   (count
;     (for 
;       [ones (inclusive-range 0 (quot goal 1))
;        twos (inclusive-range 0 (quot goal 2))
;        fives (inclusive-range 0 (quot goal 5))
;        tens (inclusive-range 0 (quot goal 10))
;        twenties (inclusive-range 0 (quot goal 20))
;        fifties (inclusive-range 0 (quot goal 50))
;        hundreds (inclusive-range 0 (quot goal 100))
;        two-hundreds (inclusive-range 0 (quot goal 200))
;        :when (= goal
;                 (+ (* ones 1)
;                    (* twos 2)
;                    (* fives 5)
;                    (* tens 10)
;                    (* twenties 20)
;                    (* fifties 50)
;                    (* hundreds 100)
;                    (* two-hundreds 200)))]
;       :valid))))

(defn valid-coins
  [coins goal]
  (for [coin coins
        :let [remaining (- goal coin)]
        :when (<= 0 remaining)] {:remaining remaining, :coin coin}))

(defn bad-problem31 ;does not respect order
  ([] (bad-problem31 [1 2 5 10 20 50 100 200] 200))
  ([coins goal]
   (cond
     (> 0 goal) 0
     (zero? goal) 1
     true (reduce +
                  (map
                    #(#'bad-problem31 coins (- goal %))
                    coins)))))
(def bad-problem31 (memoize bad-problem31))

(defn problem31
  ([] (problem31 [1 2 5 10 20 50 100 200] 200))
  ([coins goal]
   (cond
