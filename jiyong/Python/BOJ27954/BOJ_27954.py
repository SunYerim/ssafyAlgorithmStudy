import sys
from collections import deque

N, M, Q = map(int, sys.stdin.readline().rstrip().split())

puzzle = [[*sys.stdin.readline().rstrip()] for _ in range(N)]


def show(puzzle):
    [print(''.join(x)) for x in puzzle]


def rotate(x, y, puzzle):
    # print(f'rotate({x}, {y})')
    puzzle = puzzle[:x][::-1] + puzzle[x:][::-1]
    for i, row in enumerate(puzzle):
        puzzle[i] = row[:y][::-1] + row[y:][::-1]
    return puzzle


def rotate_x(x1, x2, puzzle):
    # print(f'rotateX({x1}, {x2})')
    if x1 > x2:
        puzzle = puzzle[x1 - x2:] + puzzle[:x1 - x2]
    else:
        puzzle = puzzle[-(x2 - x1):] + puzzle[:-(x2 - x1)]
    return puzzle


def rotate_y(y1, y2, puzzle):
    # print(f'rotateY({y1}, {y2})')
    if y1 > y2:
        for i, row in enumerate(puzzle):
            puzzle[i] = row[y1 - y2:] + row[:y1 - y2]
    else:
        for i, row in enumerate(puzzle):
            puzzle[i] = row[-(y2 - y1):] + row[:-(y2 - y1)]
    return puzzle


queries = [sys.stdin.readline().rstrip() for _ in range(Q)]
i = 0
while i < len(queries):
    q1 = queries[i]
    q2 = queries[i + 1] if i + 1 < len(queries) else [0]
    if q1[0] == '1':
        _, x1, y1 = map(int, q1.split())
        if q2[0] == '1':
            _, x2, y2 = map(int, q2.split())
            if x1 != x2 and y1 != y2:
                puzzle = rotate(x1, y1, puzzle)
            elif x1 == x2 and y1 == y2:
                i += 1
            elif y1 == y2:
                puzzle = rotate_x(x1, x2, puzzle)
                i += 1
            else:
                puzzle = rotate_y(y1, y2, puzzle)
                i += 1
        else:
            puzzle = rotate(x1, y1, puzzle)
    else:
        show(puzzle)
    i += 1
