import math
import timeit

first_timer =timeit.default_timer()

data = []
with open('sgb-words.txt') as f:
   # data = f.readlines()
    data = [data.rstrip() for data in f]

def count_sort_letters(array, size, col, base, max_len):
  
  output   = [0] * size
  count    = [0] * (base + 1) # One addition cell to account for dummy letter
  min_base = ord('a') - 1 # subtract one too allow for dummy character

  for item in array: 
  
    letter = ord(item[col]) - min_base if col < len(item) else 0
    count[letter] += 1

  for i in range(len(count)-1):   # Accumulate counts
      count[i + 1] += count[i]

  for item in reversed(array):
  
    letter = ord(item[col]) - min_base if col < len(item) else 0
    output[count[letter] - 1] = item
    count[letter] -= 1

  return output

def radix_sort_letters(array, max_col = None):

  if not max_col:
    max_col = len(max(array, key = len)) # edit to max length

  for col in range(max_col-1, -1, -1): 
    array = count_sort_letters(array, len(array), col, 26, max_col)

  return array

data = radix_sort_letters(data)

second_timer = timeit.default_timer()

#print("The execution time of LSD Radix Sort is:", second_timer - first_timer,"s")




