import sys
from heapq import heappop, heappush

input = sys.stdin.readline

delta = ((1, 0), (-1, 0), (0, 1), (0, -1))
INF = int(1e9)


def dijkstra(map_):
    N = len(map_)
    distance = [[INF] * N for _ in range(N)]
    q = []
    heappush(q, (map_[0][0], (0, 0)))
    distance[0][0] = 0
    while q:
        dist, (cur_r, cur_c) = heappop(q)
        if cur_r == N - 1 and cur_c == N - 1:
            break
        for dr, dc in delta:
            nr = cur_r + dr
            nc = cur_c + dc
            if 0 <= nr < N and 0 <= nc < N:
                cost = dist + map_[nr][nc]
                if cost < distance[nr][nc]:
                    distance[nr][nc] = cost
                    heappush(q, (cost, (nr, nc)))
    return distance[N - 1][N - 1]


idx = 1
while (N := int(input())) != 0:
    map_ = [list(map(int, input().split())) for _ in range(N)]
    print(f'Problem {idx}: {dijkstra(map_)}')
    idx += 1
