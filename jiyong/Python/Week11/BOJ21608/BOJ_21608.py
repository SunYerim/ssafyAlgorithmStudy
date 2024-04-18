import sys
from collections import defaultdict

input = sys.stdin.readline

N = int(input())

seats = [[0] * N for _ in range(N)]
prefer_list = defaultdict(set)
order = []

for _ in range(N * N):
    line = list(map(int, input().split()))
    prefer_list[line[0]] = set(line[1:])
    order.append(line[0])


def find_blank(num: int) -> None:
    global seats, N
    seat = (0, 0, -1, -1)  # (r, c, prefer, blank)
    for r in range(N):
        for c in range(N):
            if seats[r][c] != 0:
                continue
            blank = 0
            prefer = 0
            for dr, dc in ((1, 0), (0, 1), (-1, 0), (0, -1)):
                nr = r + dr
                nc = c + dc
                if 0 <= nr < N and 0 <= nc < N:
                    if seats[nr][nc] == 0:
                        blank += 1
                    elif seats[nr][nc] in prefer_list[num]:
                        prefer += 1
            if prefer > seat[2] or (prefer == seat[2] and blank > seat[3]):
                seat = (r, c, prefer, blank)
    seats[seat[0]][seat[1]] = num
    return


def get_score(r: int, c: int) -> int:
    cnt = 0
    for dr, dc in ((1, 0), (0, 1), (-1, 0), (0, -1)):
        nr = r + dr
        nc = c + dc
        if 0 <= nr < N and 0 <= nc < N and seats[nr][nc] in prefer_list[seats[r][c]]:
            cnt += 1
    return 10 ** (cnt - 1) if cnt else 0


for o in order:
    find_blank(o)

score = 0

for r in range(N):
    for c in range(N):
        score += get_score(r, c)
print(score)
