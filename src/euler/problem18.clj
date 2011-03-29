(ns euler.problem18)

; Triangle defined in the problem
(def triangle [
                             [75]
                            [95 64]
                           [17 47 82]
                          [18 35 87 10]
                         [20  4 82 47 65]
                        [19  1 23 75  3 34]
                       [88  2 77 73  7 63 67]
                      [99 65  4 28  6 16 70 92]
                     [41 41 26 56 83 40 80 70 33]
                    [41 48 72 33 47 32 37 16 94 29]
                   [53 71 44 65 25 43 91 52 97 51 14]
                  [70 11 33 28 77 73 17 78 39 68 17 57]
                 [91 71 52 38 17 14 91 43 58 50 27 29 48]
                [63 66  4 68 89 53 67 30 73 16 69 87 40 31]
               [ 4 62 98 27 23  9 70 98 73 93 38 53 60  4 23]])

; Strategy: For each point in the triangle, there is one highest possible
; value for that point. Once we know that value for every point in a row,
; we don't ever need to consider the higher rows again.

(defn possible-parents
  "Get the possible points from the next row up."
  [[row col]]
  (cond
    (= row col 0) []
    (= col 0) [[(dec row) col]]
    (= row col) [[(dec row) (dec col)]]
    true [[(dec row) (dec col)] [(dec row) col]]))


(defn max-for-point
  "Get the maximum product at a certain point, recursing as necessary."
  [[row col :as point] triangle]
  (if (= 0 row)
    ((triangle 0) 0)
    (reduce max 1
      (let [triangle-value ((triangle row) col)]
        (map
          #(+ triangle-value (#'max-for-point % triangle))
          (possible-parents point))))))

; Once we know the max, it never changes.
(def max-for-point (memoize max-for-point))

; Just need to map across the bottom row, then find the max in that sequence.
(defn problem18
  ([] (problem18 (dec (count triangle)) triangle))
  ([row triangle]
   (apply max
          (for [column (range row)]
            (max-for-point [row column] triangle)))))
