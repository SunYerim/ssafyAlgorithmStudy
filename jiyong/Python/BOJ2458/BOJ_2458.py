import sys

input = sys.stdin.readline

N, M = map(int, input().split())

graph = [[] for _ in range(N + 1)]
r_graph = [[] for _ in range(N + 1)]

for _ in range(M):
    s, e = map(int, input().split())
    graph[s].append(e)
    r_graph[e].append(s)


def dfs(start: int):
    visited = [False]*(N+1)
    # 정방향 탐색
    stack = [start]

