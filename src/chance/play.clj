(ns chance.play
  "Framework for the class"
  (:use [chance.games]
        [chance.distribution]
        [clojure.pprint]))

;; Infinite sequences & Lazy sequences

;; Seqs not collections

;; Seqs not iterators
















































;; Demonstrate coin
(comment
  coin)

;; **TODO** Add a deck of cards, and n-sided dice to chance.games

;; Demonstrate two coin toss. Explain.
(comment
  (pprint
   ((probabilistic vector) coin rock-paper-scissors)))

;; Multiple tosses.  Same but reducing now
(comment
  (reduce
   (probabilistic vector)
   [coin die]))


;; **TODO - FIXME ** Try with 3 tosses. Make it flat
(comment
  (reduce
   (probabilistic vector)
   [coin rock-paper-scissors die]))



;; Game 1.
;;  * Throw 5 dice
;;  * Score is the total of each face seen





;; Game 2.
;;  * Throw 5 dice
;;  * Score is the least face seen




;; Game 3
;;  * Throw 5 dice
;;  * Score is the number of 6s



;; Game 4
;;   * Throw 5 dice and flip a coin
;;   * Score is sum of dice
;;   * If coin is head, double it.



;; Game 5
;; throw and flip, flip kills


;; Game 6
;;  * Flip coins twice
;;  * Bet 1 each time
;;  * :head you win 1, :tail you lose 1
;;  * Start with 1



;;; Game 7,
;;; Same as 6 but now also record the sequence of flips
;;; Do not modify your tails-wins fn




;;; Game 8
;;; As above, but stop when you hit 0 balance



;;; Game 9
;;;  As above but bet your entire balance each time (gambler's ruin)



;;; Game 10
;;;  As above but bet half your balance each time (gambler's ruin)
