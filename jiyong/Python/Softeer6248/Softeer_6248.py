import sys

input = sys.stdin.readline
n, m = map(int, input().rstrip().split())

graph = [[] for _ in range(n + 1)]
visited = [[False] * (n + 1) for _ in range(2)]

for _ in range(m):
    s, e = map(int, input().rstrip().split())
    graph[s].append(e)

S, T = map(int, input().rstrip().split())

visited[0][S] = True
visited[1][T] = True


def dfs(now, target, order):
    if now == target:
        return False
    flag = True
    for nxt in graph[now]:
        if not visited[order][nxt]:
            visited[order][nxt] = True
            if dfs(nxt, target, order):
                visited[order][nxt] = False
            else:
                flag = False
    return flag


print(sum(1 for x, y in zip(*visited) if x and y) - 2)
