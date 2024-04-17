import sys

input = sys.stdin.readline

INF = int(1e9)
n, m, r = map(int, input().split())
items = list(map(int, input().split()))
dist = [[INF] * n for _ in range(n)]

for _ in range(r):
    a, b, l = map(int, input().split())
    dist[a - 1][b - 1] = min(dist[a - 1][b - 1], l)
    dist[b - 1][a - 1] = min(dist[b - 1][a - 1], l)
    dist[a - 1][a - 1] = 0
    dist[b - 1][b - 1] = 0

for k in range(n):
    for i in range(n):
        if i == k: continue
        for j in range(n):
            if i == j or j == k: continue
            dist[i][j] = min(dist[i][k] + dist[k][j], dist[i][j])

val = max([sum([items[i] for i, x in enumerate(line) if x <= m]) for line in dist])
print(val if val else 0)
