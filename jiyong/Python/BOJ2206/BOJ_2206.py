import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())
map_ = [list(map(int, [*input().rstrip()])) for _ in range(N)]
visited = [[-1] * M for _ in range(N)]
visited[0][0] = 1
delta = ((-1, 0), (1, 0), (0, -1), (0, 1))
dq = deque([(0, 0, 1, 1)])
answer = []

while dq:
    cur_r, cur_c, dist, crash = dq.popleft()
    if cur_r == N - 1 and cur_c == M - 1:
        answer.append(dist)
    for dr, dc in delta:
        nr = cur_r + dr
        nc = cur_c + dc
        if 0 <= nr < N and 0 <= nc < M:
            if crash == 1:
                if map_[nr][nc] == 1 and (visited[nr][nc] == -1 or visited[nr][nc] == 0):
                    dq.append((nr, nc, dist + 1, 0))
                    visited[nr][nc] = 0
                elif map_[nr][nc] == 0 and (visited[nr][nc] == -1 or visited[nr][nc] == 0):
                    dq.append((nr, nc, dist + 1, 1))
                    visited[nr][nc] = 1
            else:
                if map_[nr][nc] == 0 and visited[nr][nc] == -1:
                    dq.append((nr, nc, dist + 1, 0))
                    visited[nr][nc] = 0

print(min(answer) if answer else -1)
