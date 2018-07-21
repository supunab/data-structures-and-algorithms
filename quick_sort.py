import numpy as np


def quick_sort(numbers, i, j):
    if i>=j:
        return

    wall = i-1
    pivot = numbers[j]

    for k in xrange(i, j):
        if numbers[k] < pivot:
            wall += 1
            numbers[wall], numbers[k] = numbers[k], numbers[wall]
    
    numbers[wall+1], numbers[j] = numbers[j], numbers[wall+1]
    quick_sort(numbers, i, wall)
    quick_sort(numbers, wall+2, j)

numbers = np.array([12, 15 , 2,9, 11, 3, 4])
print(numbers)
quick_sort(numbers, 0, len(numbers)-1)
print(numbers)