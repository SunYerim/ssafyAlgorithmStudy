import sys

input = sys.stdin.readline

R, C = map(int, input().split())

mine = [[[0] * 2 for _ in range(C + 2)] for _ in range(R + 2)]


def is_in(r: int, c: int) -> bool:
    return 1 <= r <= R and 1 <= c <= C


def find_dia(r: int, c: int, dist: int) -> int:
    result = 1
    for d in range(dist - 1, answer - 1, -1):
        if is_in(r + d, c - d) and is_in(r + d, c + d) and is_in(r + 2 * d, c):
            if mine[r + d][c - d][1] >= (d + 1) and mine[r + d][c + d][0] >= (d + 1):
                result = d + 1
                break
    return result


for r in range(R):
    line = input()
    for c in range(C):
        for i in range(2):
            mine[r + 1][c + 1][i] = int(line[c])

for r in range(R):
    for c in range(C):
        # ↙
        if mine[R - r][c + 1][0] == 1:
            mine[R - r][c + 1][0] = mine[R + 1 - r][c][0] + 1
        # ↘
        if mine[R - r][C - c][1] == 1:
            mine[R - r][C - c][1] = mine[R + 1 - r][C + 1 - c][1] + 1

answer = 0
for r in range(R + 2):
    for c in range(C + 2):
        dist = min(mine[r][c])
        if dist > answer:
            answer = max(answer, find_dia(r, c, dist))

print(answer)
