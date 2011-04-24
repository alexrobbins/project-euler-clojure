(ns euler.problem028)

; One more layer to help see patterns
;
; 43 44 45 46 47 48 49
; 42 21 22 23 24 25 26
; 41 20  7  8  9 10 27
; 40 19  6  1  2 11 28
; 39 18  5  4  3 12 29
; 38 17 16 15 14 13 30
; 37 36 35 34 33 32 31
;
; Top right corners are of the form n^2, 1 3 5 7
; Same as the width of that square (the top right of the 3x3 square is 9(
(defn top-right
  [n]
  (* n n))
(def top-right (memoize top-right))

; The top left corner is the top right corner minus the width of the square,
; plus 1.
(defn top-left
  [n]
  (- (top-right n) (dec n)))
(def top-left (memoize top-left))

(defn bottom-left
  [n]
  (- (top-left n) (dec n)))
(def bottom-left (memoize bottom-left))

(defn bottom-right
  [n]
  (- (bottom-left n) (dec n)))
(def bottom-right (memoize bottom-right))

(defn product-for-corners
  [n]
  (if (= n 1)
    1
    (+ (top-left n) (top-right n) (bottom-left n) (bottom-right n))))

(defn problem28
  ([] (problem28 1001))
  ([n]
   (reduce +
           (map product-for-corners (range 1 (inc n) 2)))))
