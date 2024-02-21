import sys
from collections import defaultdict

input = sys.stdin.readline
# 하, 좌, 상, 우
delta = ((-1, 0), (0, -1), (1, 0), (0, 1))


def dfs(r: int, c: int, d: int, N: int):
    cnt = 0
    nr = r
    nc = c
    while True:
        nr += delta[d][0]
        nc += delta[d][1]
        if not (0 <= nr < N and 0 <= nc < N) or map_[nr][nc] == 5:
            d = (d + 2) % 4
            cnt += 1
        elif map_[nr][nc] == 0:
            continue
        elif map_[nr][nc] == -1 or (nr == r and nc == c) or visited[nr][nc][d] != 0:
            cnt += visited[nr][nc][d]
            break

    return cnt


T = int(input())
for tc in range(1, T + 1):
    N = int(input().rstrip())
    map_ = [list(map(int, input().rstrip().split())) for _ in range(N)]
    visited = [[[0, 0, 0, 0] for _ in range(N)] for __ in range(N)]
    max_cnt = 0

    # 웜홀 쌍 찾기
    worm_hole = defaultdict(list)
    for r in range(N):
        for c in range(N):
            if 5 < map_[r][c] <= 10:
                worm_hole[map_[r][c]].append((r, c))

    # 브루트포스
    for r in range(N):
        for c in range(N):
            if map_[r][c] == 0:
                for d in range(4):
                    tmp = dfs(r, c, d, N)
                    visited[r][c][d] = tmp
                    max_cnt = max(max_cnt, tmp)
    print(max_cnt)
