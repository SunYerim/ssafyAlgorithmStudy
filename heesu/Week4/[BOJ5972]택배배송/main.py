import sys
from collections import deque
import heapq

input = sys.stdin.readline

n, m = map(int, input().split())
arr = [[] for _ in range(n)]
visited = [sys.maxsize]*n
for _ in range(m):
    x, y, w = map(int, input().split())
    arr[x-1].append((y-1, w))
    arr[y-1].append((x-1, w))

priority_queue = [(0,0)]
while priority_queue:
    start, count = heapq.heappop(priority_queue)
    for end, w in arr[start]:
            s = count + w
            if visited[end] > s:
                heapq.heappush(priority_queue, (end, s))
                visited[end] = s

print(visited[n-1])