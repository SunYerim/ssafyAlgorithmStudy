import sys
from collections import defaultdict

input = sys.stdin.readline
# 하, 좌, 상, 우
delta = ((1, 0), (0, -1), (-1, 0), (0, 1))


def dfs(r: int, c: int, d: int, n: int):
    cnt = 0
    nr = r + delta[d][0]
    nc = c + delta[d][1]
    while True:
        ## 종료조건 ##
        # 1. 블랙홀을 만나거나
        # 2. 시작지점으로 돌아오거나,
        if (0 <= nr < n and 0 <= nc < n) and (map_[nr][nc] == -1 or (nr == r and nc == c)):
            break
        # 벽에 튕겼을 때
        if not (0 <= nr < n and 0 <= nc < n) or map_[nr][nc] == 5:
            d = (d + 2) % 4
            # nr += delta[d][0]
            # nc += delta[d][1]
            cnt += 1
        # 아무것도 없다면 계속 진행
        elif map_[nr][nc] == 0:
            pass
        # 삼각형 블럭을 만날 때
        elif 0 < map_[nr][nc] < 5:
            triangle = map_[nr][nc]
            if d + 1 == triangle:
                d = (d + 3) % 4
            elif d == triangle % 4:
                d = (d + 1) % 4
            else:
                d = (d + 2) % 4
            cnt += 1
        # 웜홀을 만날 때
        else:
            hole = map_[nr][nc]
            if nr == worm_hole[hole][0][0] and nc == worm_hole[hole][0][1]:
                i = 1
            else:
                i = 0
            nr, nc = worm_hole[hole][i]

        nr += delta[d][0]
        nc += delta[d][1]
    return cnt


T = int(input())
for tc in range(1, T + 1):
    N = int(input().rstrip())
    map_ = [list(map(int, input().rstrip().split())) for _ in range(N)]
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
                    max_cnt = max(max_cnt, tmp)
    print(f'#{tc} {max_cnt}')
