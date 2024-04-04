import sys
from collections import deque

input = sys.stdin.readline

map_ = [list(input()) for _ in range(12)]
delta = ((1, 0), (0, 1), (-1, 0), (0, -1))
answer = 0


# 특정 뿌요가 터트릴 수 있는 위치에 있는지 판별하는 함수
def boom(r: int, c: int, map_copy: list):
    global delta
    alpha = map_copy[r][c]
    cnt = 0
    dq = deque([(r, c)])
    while dq:
        cr, cc = dq.popleft()
        for dr, dc in delta:
            nr = cr + dr
            nc = cc + dc
            if 0 <= nr < 12 and 0 <= nc < 6 and map_copy[nr][nc] == alpha:
                cnt += 1
                map_copy[nr][nc] = '.'
                dq.append((nr, nc))
    return cnt >= 4


# 터트릴 수 있는 뿌요 그룹 찾기
def find_boom():
    global map_
    flag = False
    for r in range(12):
        for c in range(6):
            if map_[r][c] != '.':
                map_copy = [map_[x][:] for x in range(12)]
                if boom(r, c, map_copy):
                    map_ = map_copy
                    flag = True
    return flag


# 공중에 있는 뿌요를 찾아 떨어뜨리기
def drop_puyo():
    for c in range(6):
        while True:
            row_blank = find_blank(c)
            if row_blank:
                row_puyo = find_puyo(c, row_blank)
                if row_puyo != -1:
                    map_[row_blank][c], map_[row_puyo][c] = map_[row_puyo][c], map_[row_blank][c]
                else:
                    break
            else:
                break


# 특정 열에서 가장 아래에 있는 빈 공간 찾기
def find_blank(col: int):
    global map_
    row = 11
    while row > 0 and map_[row][col] != '.':
        row -= 1
    return row


# 특정 열에서 떨어지지 않은 뿌요 찾기
def find_puyo(col: int, start: int):
    global map_
    row = start - 1
    while row >= 0 and map_[row][col] == '.':
        row -= 1
    return row


# main
while find_boom():
    answer += 1
    drop_puyo()

print(answer)