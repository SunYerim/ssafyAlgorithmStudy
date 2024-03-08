import sys
from collections import defaultdict
from heapq import heappush, heappop

input = sys.stdin.readline
INF = int(1e9)
while True:
    N, M = map(int, input().split())
    if N == M == 0: break
    S, D = map(int, input().split())

    graph = [[] for _ in range(N)]

    for i in range(M):
        s, e, w = map(int, input().split())
        graph[s].append((w, e, i))
        graph[e].append((w, s, i))

    visited = defaultdict(bool)
    cost = [INF] * N
    cost[0] = 0
    hq = []
    while hq:
        pass

