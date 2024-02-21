import sys
from heapq import heappop, heappush

input = sys.stdin.readline
INF = int(1e9)

N, M = map(int, input().split())

graph = [[] for _ in range(N + 1)]
distance = [INF] * (N + 1)

for _ in range(M):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))

q = []
distance[0] = 0
heappush(q, (0, 1))
while q:
    dist, node = heappop(q)
    if node == N:
        break
    for next_, cost in graph[node]:
        if (cost := dist + cost) < distance[next_]:
            distance[next_] = cost
            heappush(q, (cost, next_))

print(distance[N])