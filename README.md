# Clojure-Parallelized-Text-Analysis
Text analysis program written in clojure using parallel programming.

Given a text file, this program does the following:
- Creates a probability distribution for single characters, pairs of characters, and triplets of characters in the file.
- Calculates the total "information" in the file given by the following formula:
    H(S)=∑(nc)(−pc)lg(pc)
  where pc is the probability of each character (item) in a data stream, nc is
  the number of times each character c occurs, and the summation is over all characters in the stream.
- Implements parallel programming, allowing the program to be run concurrently using a user defined number of cores


