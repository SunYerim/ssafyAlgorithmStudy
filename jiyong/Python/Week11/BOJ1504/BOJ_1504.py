import sys
from heapq import heappop, heappush

input = sys.stdin.readline
INF = int(1e9)

N, E = map(int, input().split())
graph = [[] for _ in range(N + 1)]
dist = [[INF] * (N + 1) for _ in range(3)]
answer = INF

for _ in range(E):
    a, b, c = map(int, input().split())
    graph[a].append((c, b))
    graph[b].append((c, a))

v1, v2 = map(int, input().split())


def dijkstra(start: int, idx: int):
    hq = []
    heappush(hq, (0, start))
    dist[idx][start] = 0
    while hq:
        cost, now = heappop(hq)
        if cost > dist[idx][now]:
            continue
        for n_cost, nxt in graph[now]:
            sum_cost = cost + n_cost
            if dist[idx][nxt] > sum_cost:
                dist[idx][nxt] = sum_cost
                heappush(hq, (sum_cost, nxt))


# main
for i, v in enumerate((1, v1, v2)):
    dijkstra(v, i)

# 1 -> v1 -> v2 -> N
answer = min(answer, dist[0][v1] + dist[1][v2] + dist[2][N])

# 1 -> v2 -> v1 -> N
answer = min(answer, dist[0][v2] + dist[2][v1] + dist[1][N])

print(answer if answer != INF else -1)
