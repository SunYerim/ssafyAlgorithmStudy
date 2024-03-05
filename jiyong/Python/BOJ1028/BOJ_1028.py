import sys
from math import log2

input = sys.stdin.readline

R, C = map(int, input().split())

mine = [[[0] * 4 for _ in range(C + 2)] for __ in range(R + 2)]

for r in range(R):
    line = input().rstrip()
    for c in range(C):
        for i in range(4):
            mine[r + 1][c + 1][i] = int(line[c])

for r in range(1, R + 1):
    for c in range(1, C + 1):
        temp = (mine[r - 1][c][0] & mine[r][c - 1][0]) << 1
        mine[r][c][0] += 1 << (int(log2(temp))) if temp > 0 else 0

for r in range(R, 0, -1):
    for c in range(1, C + 1):
        temp = (mine[r + 1][c][1] & mine[r][c - 1][1]) << 1
        mine[r][c][1] += 1 << (int(log2(temp))) if temp > 0 else 0

for r in range(1, R + 1):
    for c in range(C, 0, -1):
        temp = (mine[r - 1][c][2] & mine[r][c + 1][2]) << 1
        mine[r][c][2] += 1 << (int(log2(temp))) if temp > 0 else 0

for r in range(R, 0, -1):
    for c in range(C, 0, -1):
        temp = (mine[r + 1][c][3] & mine[r][c + 1][3]) << 1
        mine[r][c][3] += 1 << (int(log2(temp))) if temp > 0 else 0

answer = 0
for r in range(1, R + 1):
    for c in range(1, C + 1):
        temp = mine[r][c][0] & mine[r][c][1] & mine[r][c][2] & mine[r][c][3]
        if temp > 0:
            answer = max(answer, int(log2(temp)) + 1)
print(answer)
print(*mine, sep='\n')
