(ns euler.problem15)

; 1  1  1  1
; 1  2  3  4
; 1  3  6  10
; 1  4 10  20

; The number of ways to get to a spot is Pascal's triangle, on its side.

(defn convert-coord-to-pascal-row-and-num
  [[x y :as coord]]
  {:row (+ y (- x 1)) :number x})

(defn pascal-value
  [{:keys [row number]}]

