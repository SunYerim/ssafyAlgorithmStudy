from collections import deque
import sys

input = sys.stdin.readline

d = [[-1, 0], [1, 0], [0, 1], [0, -1]]


def bfs(y, x):
    queue = deque()
    queue.append([y, x])
    visited[y][x] = True

    while queue:
        r, c = queue.popleft()
        for i in range(4):
            dr = r + d[i][0]
            dc = c + d[i][1]

            if (0 <= dr < n) and (0 <= dc < m):
                if graph[dr][dc] == 0:
                    visited[r][c] += 1

                if not visited[dr][dc] and graph[dr][dc] != 0:
                    queue.append([dr, dc])
                    visited[dr][dc] = True


n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]

time = 0
while True:
    count = 0
    visited = [[0] * m for _ in range(n)]
    for i in range(n):
        for k in range(m):
            if not visited[i][k] and graph[i][k] != 0:
                bfs(i, k)
                count += 1

    for i in range(n):
        for k in range(m):
            if visited[i][k]:
                graph[i][k] -= (visited[i][k] - 1)
                if graph[i][k] < 0: graph[i][k] = 0

    time += 1
    if count == 0:
        print(0)
        exit()
    if count >= 2:
        break

print(time - 1)
