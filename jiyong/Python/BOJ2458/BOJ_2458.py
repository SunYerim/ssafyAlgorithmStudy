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
    visited = [False] * (N + 1)
    visited[start] = True
    # 정방향 탐색
    stack = [start]

    while stack:
        next_ = stack.pop()
        for node in graph[next_]:
            if not visited[node]:
                visited[node] = True
                stack.append(node)

    stack = [start]

    while stack:
        next_ = stack.pop()
        for node in r_graph[next_]:
            if not visited[node]:
                visited[node] = True
                stack.append(node)

    return 1 if all(visited[1:]) else 0


answer = 0
for i in range(1, N + 1):
    answer += dfs(i)

print(answer)
