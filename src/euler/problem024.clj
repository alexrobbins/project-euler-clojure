(ns euler.problem024)

; From wikipedia article on permutation:
;
; The following algorithm generates the next permutation lexicographically after a given permutation.
;
; a) Find the largest index k such that a[k] < a[k + 1]. If no such index exists, the permutation is the last permutation.
;
; b) Find the largest index l such that a[k] < a[l]. Since k + 1 is such an index, l is well defined and satisfies k < l.
;
; c) Swap a[k] with a[l].
;
; d) Reverse the sequence from a[k + 1] up to and including the final element a[n].
;
; 012 a k=1 
;     b l=2
; 021 c
; 021 d (a[k+1] is a sequence of length 1)
; 021 a k=0
; 021 b l=2
; 120 c
; 102 d (a[k+1] is a sequence of length 2)

(defn get-largest-k
  [digits]
  (loop [r-digits (partition 2 1 (reverse digits))]
    (if r-digits
      (if ((fn [[x y]] (> x y)) (first r-digits))
        (dec (count r-digits))
        (recur (next r-digits)))
      nil)))

(defn get-largest-l
  [digits]
  (if-let [k (get-largest-k digits)]
    (let [a-of-k ((vec digits) k)]
      (loop [r-digits (reverse digits)]
        (if r-digits
          (if (> (first r-digits) a-of-k)
            (dec (count r-digits))
            (recur (next r-digits)))
          nil)))
    nil))

(defn swap-k-and-l
  [digits k l]
  (let [digits (vec digits)]
    (concat
      (subvec digits 0 k)
      [(digits l)]
      (subvec digits (inc k) l)
      [(digits k)]
      (subvec digits (inc l)))))

(defn reverse-from-k+1-to-end
  [digits k]
  (let [digits (vec digits)]
    (concat
      (subvec digits 0 (inc k))
      (reverse (subvec digits (inc k))))))

(defn get-next-lexicographic-permutation
  [digits]
    (let [k (get-largest-k digits) l (get-largest-l digits)]
      (-> digits
        (swap-k-and-l k l)
        (reverse-from-k+1-to-end k))))

(defn get-permutations
  [digits]
  (let [digits (sort digits)]
    (iterate get-next-lexicographic-permutation digits)))

; TODO This should be refactored to use nth instead of dropping and taking
(defn problem24
  ([] (problem24 [0 1 2 3 4 5 6 7 8 9] 1000000))
  ([digits n]
   (take 1
         (drop (dec n)
               (get-permutations digits)))))
