from itertools import product

T = int(input())
delta = ((-1, 0), (1, 0), (0, -1), (0, 1))


def drop(c: int):
    r = 0
    while r < H and map_[r][c] == 0:
        r += 1
    if r < H:
        boom(r, c)
        clean()


def boom(r, c):
    radius = map_[r][c]
    map_[r][c] = 0
    for d in range(1, radius):
        for dr, dc in delta:
            nr = r + d * dr
            nc = c + d * dc
            if 0 <= nr < H and 0 <= nc < W:
                if map_[nr][nc] > 1:
                    boom(nr, nc)
                map_[nr][nc] = 0


def clean():
    for col in range(W):
        lp = H - 1
        while lp > 0:
            while lp >= 0 and map_[lp][col] != 0:
                lp -= 1
            hp = lp - 1
            while hp >= 0 and map_[hp][col] == 0:
                hp -= 1
            if hp >= 0:
                map_[lp][col], map_[hp][col] = map_[hp][col], map_[lp][col]
            lp -= 1


def counting():
    cnt = 0
    for j in range(H):
        for k in range(W):
            if map_[j][k] != 0:
                cnt += 1
    return cnt


for tc in range(1, T + 1):
    N, W, H = map(int, input().rstrip().split())
    original_map = [list(map(int, input().rstrip().split())) for _ in range(H)]
    answer = W * H
    for prod in product(range(W), repeat=N):
        map_ = [original_map[x][:] for x in range(H)]
        for i in prod:
            drop(i)
        answer = min(counting(), answer)
        if answer == 0:
            break
    print(f'#{tc} {answer}')
