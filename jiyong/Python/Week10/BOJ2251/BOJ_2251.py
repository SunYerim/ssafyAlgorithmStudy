import sys
from collections import deque

A, B, C = list(map(int, sys.stdin.readline().split()))

visited = set()

answer = set()

dq = deque([(0, 0, C)])

while dq:
    a, b, c = dq.popleft()
    if (a, b, c) in visited:
        continue
    visited.add((a, b, c))

    # 첫 번째 물통이 비어있을 때, 세 번째 물통의 물의 양을 기록
    if a == 0:
        answer.add(c)

    # A 물통 -> B 물통
    move = min(a, B - b)
    if move > 0:
        dq.append((a - move, b + move, c))

    # A 물통 -> C 물통
    move = min(a, C - c)
    if move > 0:
        dq.append((a - move, b, c + move))

    # B 물통 -> A 물통
    move = min(b, A - a)
    if move > 0:
        dq.append((a + move, b - move, c))

    # B 물통 -> C 물통
    move = min(b, C - c)
    if move > 0:
        dq.append((a, b - move, c + move))

    # C 물통 -> A 물통
    move = min(c, A - a)
    if move > 0:
        dq.append((a + move, b, c - move))

    # C 물통 -> B 물통
    move = min(c, B - b)
    if move > 0:
        dq.append((a, b + move, c - move))

print(' '.join(map(str, sorted(answer))))
