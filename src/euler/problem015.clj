(ns euler.problem015)

; 1  1  1  1
; 1  2  3  4
; 1  3  6  10
; 1  4 10  20

; The number of ways to get to a spot is Pascal's triangle, on its side.

; Need to run through it one row at a time, adding item to the top and item
; to the left. There is symmetry, but addition isn't that hard so I don't
; think we need to take that into account (maybe if it was a bigger square).

; Note the #' signs. These work around a bug that causes the internal function
; references to continue to reference the non-memoized function. This is
; fixed in clojure 1.3

(defn paths-to-spot
  [[x y]]
  (cond
    (zero? x) 0
    (zero? y) 0
    (= 1 x) 1
    (= 1 y) 1
    true (+ (#'paths-to-spot [(dec x) y]) (#'paths-to-spot [x (dec y)]))))

(def paths-to-spot (memoize paths-to-spot))

(defn problem15
  ([] (problem15 [20 20]))
  ([[x y]]
   (paths-to-spot [(inc x) (inc y)])))
