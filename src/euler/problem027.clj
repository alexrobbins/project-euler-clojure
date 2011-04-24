(ns euler.problem027
  (:use [clojure.contrib.lazy-seqs :only [primes]]))

(defn absolute-range
  "return a seq of numbers given n, such that -n < x < -n"
  [n]
  (range (inc (* -1 n)) n))

(defn prime?
  [n]
  (loop [primes primes]
    (let [next-prime (first primes)]
      (if (= n next-prime)
        true
        (if (> next-prime n)
          false
          (recur (rest primes)))))))

(def prime? (memoize prime?))

(defn make-quadratic
  "Make a quadratic function of the form n^2 + an + b"
  [a b]
  #(+ (* % %) (* % a) b))

(defn number-of-consecutive-primes
  "Find the number of consecutive primes for input of n, starting with n=0"
  [func]
  (dec
    (first
      (drop-while #(prime? (func %)) (range)))))

(defn problem27
  ([] (problem27 1000))
  ([n]
   (apply * 
          (first 
            (reduce #(if (> (second %1) (second %2)) %1 %2)
                    (for [a (absolute-range n) b (absolute-range n)
                          :let [quadratic (make-quadratic a b)]]
                      [[a b] (number-of-consecutive-primes quadratic)]))))))
