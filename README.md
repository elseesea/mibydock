# mibydock

read stop-words into array of stop-words
read a line from text, parse words into a word array
for each word in word array
search map for word
if word is not in stop-words array, then
    if it exists in map, and , increment count in map
    if it doesn't exist in map, add to map, and set count to 1
