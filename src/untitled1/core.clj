(ns untitled1.core)

(require '[com.climate.claypoole :as cp])

(defn read-from-file
  [file]
  (try
    (slurp file)
    (catch Exception e (println "Error: " (.getMessage e))))
  )

(defn calcFreq
  [ints x]
  (cp/pmap 64 #(double (/ % x)) ints)
  )

(defn log2 [n]
  (/ (Math/log n) (Math/log 2))
  )

(defn calcInfo
  [numChar, pctChar]
  (* numChar -1 pctChar (log2 pctChar))
  )
(comment
  (defn groupPairs
    [x, vector, string]

    (println x)
    (println (count string))
    (println (count x))
    (if (== (count x) 0)
      (do (conj vector string)
          (println vector)
          ))

    (if (== (count string) 2)
      (do

        (println "Count is 2")
        (println string)
        (groupPairs (subs x 1) (conj vector 1) "")
        )

      )
    (println string)
    (groupPairs (subs x 1) vector (concat string (subs x 0 1)))


    )
  )

(defn groupPairs
  [pairs, string]
  (if (empty? string)
    0
    (if (== (count string) 1)
      (conj pairs string)

      (conj pairs (subs string 0 2))))

  (if (empty? string)
    pairs
    (if (== (count string) 1)
      pairs
      (recur (conj pairs (subs string 0 2)) (subs string 2))))
  )

(defn groupTriplets
  [pairs, string]
  (if (empty? string)
    0
    (if (<= (count string) 2)
      (conj pairs string)

      (conj pairs (subs string 0 3))))

  (if (empty? string)
    pairs
    (if (<= (count string) 2)
      pairs
      (recur (conj pairs (subs string 0 3)) (subs string 3))))
  )

(def testStr "abcdefg")



(defn -main
  ;read from file
  [& args]
  (def x (read-from-file "WarAndPeace.txt"))



  ;calculate information for single chars

  (def total (count x))
  (def singFreq (frequencies x))


  (def occur (calcFreq (vals singFreq) total))

  (def pctg (zipmap (keys singFreq) occur))

  (def info (reduce + (cp/pmap 64 calcInfo (vals singFreq) (vals pctg))))





  ;calculate information for pairs of chars
  (def pairs (groupPairs [] x))
  (def doubTotal (count pairs))
  (def doubFreq (frequencies pairs))


  (def doubOccur (calcFreq (vals doubFreq) doubTotal))

  (def doubPctg (zipmap (keys doubFreq) doubOccur))

  (def doubInfo (reduce + (cp/pmap 64 calcInfo (vals doubFreq) (vals doubPctg))))



  ;calculate information for triplets of chars
  (def triplets (groupTriplets [] x))
  (def tripTotal (count triplets))
  (def tripFreq (frequencies triplets))


  (def tripOccur (calcFreq (vals tripFreq) tripTotal))

  (def tripPctg (zipmap (keys tripFreq) tripOccur))

  (def tripInfo (reduce + (cp/pmap 64 calcInfo (vals tripFreq) (vals tripPctg))))



  )
