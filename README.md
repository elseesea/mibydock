# Moby Dick word frequencies

Displays top 100 words used in *Moby Dick* text by word frequency, excluding words from a list of **stop words**.

### Overview of logic
- Read stop-words from a text file into array of stop-words
- Read lines from the *Moby Dick* text
  - For each line, parse words into a word array
    - For each word in word array, search map for word
    - if the word is not in stop-words array, then
      - if it exists in map, and , increment count in map
      - if it doesn't exist in map, add to map, and set count to 1
- Prepare for output
  - Convert map to 2d array
  - Sort 2d array by second dimension, i.e., the count
  - Print out the 100 most frequent words

## Requirements
Java 13 (although Java 8+ should work)

## Todo
1. Prompt for name of text file, to analyze texts besides Moby Dick
2. Prompt for name of stop words file
3. Prompt for adding or removing words from stop words file
4. Refactor code up into smaller functions
   - Process stop words
   - Process text
   - Output top 100
