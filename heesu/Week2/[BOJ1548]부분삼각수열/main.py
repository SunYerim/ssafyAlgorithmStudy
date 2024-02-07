import sys
from collections import deque

num = int(sys.stdin.readline())
if num < 3:
    print(num)
else:
    arr = list(map(int, sys.stdin.readline().split()))
    arr.sort()
    answer = 0
    left = 0
    right = left+2
    while right < num:
        if arr[left]+arr[left+1] > arr[right]:
            answer = max(answer, right-left+1)
            right+=1
        else:
            left+=1
    print(answer)