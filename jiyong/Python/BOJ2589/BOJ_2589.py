import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())

map_ = [[*input().rstrip()] for _ in range(N)]
visited = [[False] * M for _ in range(N)]
delta = ((1, 0), (-1, 0), (0, 1), (0, -1))
max_length = 0
applicant = []


def is_in(r: int, c: int):
    return 0 <= r < N and 0 <= c < M


def dfs(r: int, c: int):
    stack = [(r, c)]
    visited[r][c] = True
    while stack:
        cur_r, cur_c = stack.pop()
        flag = True
        for dr, dc in delta:
            nr = cur_r + dr
            nc = cur_c + dc
            if is_in(nr, nc) and not visited[nr][nc] and map_[nr][nc] == 'L':
                stack.append((nr, nc))
                visited[nr][nc] = True
                flag = False
        if flag:
            applicant.append((cur_r, cur_c, 0))


def bfs(init):
    global max_length
    dq = deque([init])
    visited = [[False] * M for _ in range(N)]
    visited[init[0]][init[1]] = True
    while dq:
        cur_r, cur_c, time = dq.popleft()
        flag = True
        for dr, dc in delta:
            nr = cur_r + dr
            nc = cur_c + dc
            if is_in(nr, nc) and not visited[nr][nc] and map_[nr][nc] == 'L':
                dq.append((nr, nc, time + 1))
                visited[nr][nc] = True
                flag = False
        if flag:
            max_length = max(max_length, time)


for i in range(N):
    for j in range(M):
        if map_[i][j] == 'L' and not visited[i][j]:
            applicant.append((i, j, 0))
            dfs(i, j)

for el in applicant:
    bfs(el)

print(max_length)