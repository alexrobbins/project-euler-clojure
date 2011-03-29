(ns euler.problem17)
;
; Note the #' signs. These work around a bug that causes the internal function
; references to continue to reference the non-memoized function. This is
; fixed in clojure 1.3

(def digits
  {1 "one"
   2 "two"
   3 "three"
   4 "four"
   5 "five"
   6 "six"
   7 "seven"
   8 "eight"
   9 "nine"})

(def tens
  {
   2 "twenty"
   3 "thirty"
   4 "forty"
   5 "fifty"
   6 "sixty"
   7 "seventy"
   8 "eighty"
   9 "ninety"})

(def teens
  {0 "ten"
   1 "eleven"
   2 "twelve"
   3 "thirteen"
   4 "fourteen"
   5 "fifteen"
   6 "sixteen"
   7 "seventeen"
   8 "eighteen"
   9 "nineteen"})

(defn spell-out-number
  [number]
  (cond
    (= 0 number) nil
    (= 1000 number) "onethousand"
    (< 99 number) (str (digits (quot number 100)) "hundred"
                       (if-let [rest-of-number
                                (#'spell-out-number (rem number 100))]
                         (str "and" rest-of-number)))
    (< 19 number) (str (tens (quot number 10))
                       (#'spell-out-number (rem number 10)))
    (< 9 number) (teens (rem number 10))
    true (digits number)))

(def spell-out-number (memoize spell-out-number))

(defn problem17
  ([] (problem17 1000))
  ([x]
   (reduce +
           (map (comp count spell-out-number) (range 1 (inc x))))))
