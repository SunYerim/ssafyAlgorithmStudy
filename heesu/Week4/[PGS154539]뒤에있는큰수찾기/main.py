from collections import deque 
import heapq

def solution(numbers):
    answer = [0]*len(numbers)
    q = []

    for idx, number in enumerate(numbers):
        while q:
            n, i = heapq.heappop(q)
            if number > n:
                answer[i] = number
            else:
                heapq.heappush(q, (n, i))
                break
        heapq.heappush(q, (number, idx))

    while q:
        n, i = heapq.heappop(q)
        answer[i] = -1        


    return answer