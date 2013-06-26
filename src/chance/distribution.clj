(ns chance.distribution
  "Distribution operators"
  (:use     [plumbing.core])
  (:require [clojure.math.combinatorics :as combo]))

;; ----------------------------------------------------------------------------
;;  A Distribution is a sorted map, where the keys of the map are the elements of the
;;  probability space (the events) and the values of the map are the probabilities
;;  of the events.  Ie an unbiased coin is {-1 1/2, +1 1/2}.

;; -----------------------------------------------------------------------------
;;  Invariants

;; Helpers.  Use probability verbs rather than hash-map verbs
(def events keys)
(def probs  vals)

(defn- =approx
  "Helper fn to get approx floating point ="
  ([a b] (=approx a b 0.00001))
  ([a b eps]
     (<= (Math/abs (- a b)) eps)))

(defn normalised?
  "Sum of all elements must be close to 1"
  [dist]
  (=approx 1.0 (sum (probs dist))))

(defn >=zero?
  "All probabilities must be >= 0"
  [dist]
  (every? (comp not neg?) (probs dist)))

(defn unique-vals?
  "All the events are unique"
  [dist]
  (apply distinct? (events dist)))

;; This is useful as a contract on anything outputting a distribution
(defn distribution?
  "Does dist conform to basic requirements of a distribution ?"
  [dist]
  (and
   (unique-vals? dist)
   (normalised? dist)
   (>=zero? dist)))

;; ----------------------------------------------------------------------------
;;  Constructors
;;
(defn normalise
  "Reset the probs so they sum to 1"
  [dist]
  (let [norm (sum (probs dist))]
    (map-vals #(/ % norm) dist)))

;; Pull this out here to abstract it
(def dist hash-map)

(defn ->dist
  "Construct a distribution from event prob seq (like hash-map)"
  [& kvs]
  {:post [(distribution? %)]}
  (normalise
   (apply dist kvs)))

(defn zip-dist
  "Construct a distribution by passing in a seq of vals and probs"
  [vals probs]
  (apply ->dist (interleave vals probs)))

;; ----------------------------------------------------------------------------
;;  Combinator
(defn probabilistic
  "Accepts a binary function f. Returns a binary function of two distributions, which
returns the probability distribution of each outcome of applying f to the elements of
the distributions."
  ([f]
     (fn [dist1 dist2]
       {:post [(distribution? %)]}
       (reduce
        (fn [m [[v1 p1] [v2 p2]]]
          (update-in m [(f v1 v2)] (fnil (partial + (* p1 p2)) 0)))
        (dist)
        (combo/cartesian-product dist1 dist2)))))
