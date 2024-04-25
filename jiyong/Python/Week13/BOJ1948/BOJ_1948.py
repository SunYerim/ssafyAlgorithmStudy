import sys
from collections import deque
sys.setrecursionlimit(100000)

input = sys.stdin.readline


def dfs(u: int):
    visited[u] = 1
    cnt = 0
    for v, w in reverse_graph[u]:
        if dist[v] + w == dist[u]:
            cnt += 1
            if not visited[v]:
                cnt += dfs(v)
    return cnt


n = int(input())
m = int(input())
time = 0

graph = [[] for _ in range(n + 1)]
reverse_graph = [[] for _ in range(n + 1)]
visited = [0] * (n + 1)

for _ in range(m):
    s, e, w = map(int, input().split())
    graph[s].append((e, w))
    reverse_graph[e].append((s, w))

start, end = map(int, input().split())

dist = [0] * (n + 1)
in_degree = [0] + [len(reverse_graph[x]) for x in range(1, n + 1)]

dq = deque([(start, 0)])

while dq:
    now, d = dq.popleft()
    for nxt, cost in graph[now]:
        dist[nxt] = max(dist[nxt], d + cost)
        in_degree[nxt] -= 1
        if not in_degree[nxt]:
            dq.append((nxt, dist[nxt]))

print(dist[end])
print(dfs(end))