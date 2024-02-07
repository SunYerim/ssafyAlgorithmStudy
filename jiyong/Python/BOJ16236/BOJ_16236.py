import sys
from collections import deque


def bfs(dq):
    global eat, time
    while dq:
        x, y, size, t = dq.popleft()
        if sum(mob[:size]) == 0:
            break
        for dx, dy in delta:
            nx, ny = dx + x, dy + y
            if 0 <= nx < N and 0 <= ny < N and visited[nx][ny] < t and map_[nx][ny] <= size:
                if 0 < map_[nx][ny] < size:
                    print((nx, ny, t+1), end=" -> ")
                    mob[map_[nx][ny]] -= 1
                    map_[nx][ny] = 0
                    eat += 1
                    time = t + 1
                    dq.clear()
                    if eat == size:
                        size += 1
                        eat = 0
                    visited[nx][ny] = t
                    dq.append((nx, ny, size, t + 1))
                    break
                visited[nx][ny] = t
                dq.append((nx, ny, size, t + 1))


N = int(sys.stdin.readline().rstrip())
mob = [0] * 7
map_ = [[*map(int, sys.stdin.readline().rstrip().split())] for _ in range(N)]
eat = 0
time = 0

for r, row in enumerate(map_):
    for c, n in enumerate(row):
        if 0 < n < 7:
            mob[n] += 1
        if n == 9:
            map_[r][c] = 0
            pos = (r, c, 2, 0)

dq = deque([pos])
delta = ((-1, 0), (0, -1), (0, 1), (1, 0))
visited = [[-1] * N for _ in range(N)]
bfs(dq)
print()
print(time)
# print(map_)

# print(*map_, mob, pos, sep='\n')
