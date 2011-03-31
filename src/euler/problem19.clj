(ns euler.problem19
  (:use [clj-time.core]))

(defn get-first-sunday
  [date]
  (loop [current-date date]
    (if (= (day-of-week current-date) 7)
      current-date
      (recur (plus current-date (days 1))))))

; Strategy: Moving from the start date, find the first day that is a Sunday.
; Then keep adding 7 days to that day, staying on Sundays, and check if each day
; is the first of a month. If it is, add one to the count.

(defn problem19
  ([] (problem19 (interval (date-time 1901) (date-time 2000 12 31))))
  ([intvl]
   (let [start-day (get-first-sunday (start intvl))]
     (loop [current-day start-day total 0]
       (if (after? current-day (end intvl))
         total
         (if (= 1 (day current-day))
           (recur (plus current-day (days 7)) (inc total))
           (recur (plus current-day (days 7)) total)))))))
