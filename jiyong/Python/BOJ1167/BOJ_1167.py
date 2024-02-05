import sys


def dfs(n):
    stack = [(n, 0)]
    global max_dist, max_node
    while stack:
        next_node, dist = stack.pop()
        for e, w in graph[next_node]:
            if not visited[e]:
                stack.append((e, dist + w))
                visited[e] = 1
            else:
                if max_dist < dist:
                    max_node = next_node
                    max_dist = dist


N = int(sys.stdin.readline().rstrip())
graph = [[] for _ in range(N + 1)]
max_dist = 0
max_node = None
for _ in range(N):
    input_ = [*map(int, sys.stdin.readline().rstrip().split())]
    s = input_[0]
    it = iter(input_[1:])
    for (e, w) in zip(it, it):
        graph[s].append([e, w])
visited = [0] * (N + 1)
visited[1] = 1
dfs(1)

visited = [0] * (N + 1)
visited[max_node] = 1
dfs(max_node)
print(max_dist)
