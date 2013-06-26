(ns chance.games
  "Hold the distributions of various games.  The value in each case is the win.
That is, it is the number of dollars you win in addition to your bet, the odds"
  (:use [chance.distribution]))

;; -----------------------------------------------------------------------------
;;  Toys

(def coin
  (->dist
   :head 0.5
   :tail 0.5))

(def another-coin
  (zip-dist
   [:head :tail]
   [0.5 0.5]))

(def rock-paper-scissors
  (zip-dist [:rock :paper :scissors]
            [    1      2         5]))
