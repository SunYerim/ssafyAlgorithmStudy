from collections import deque
import sys

R, C = map(int, sys.stdin.readline().split())
J, F = (), []
maze = [[0] * C for _ in range(R)]
for r in range(R):
    cur = sys.stdin.readline()
    for c, e in enumerate(cur.rstrip()):
        if e == '#':
            maze[r][c] = -1
        elif e == 'J':
            J = (r, c, 1)
        elif e == 'F':
            maze[r][c] = 1
            F.append((r, c, 1))
        else:
            maze[r][c] = 0
if J[0] == 0 or J[1] == 0 or J[0] == R - 1 or J[1] == C - 1:
    print(1)
else:
    dist = ((1, 0), (-1, 0), (0, 1), (0, -1))

    dQ = deque()
    dQ.extend(F)

    while dQ:
        cur_r, cur_c, time = dQ.popleft()

        for dr, dc in dist:
            nr, nc = cur_r + dr, cur_c + dc
            if 0 <= nr < R and 0 <= nc < C:
                if maze[nr][nc] == 0:
                    maze[nr][nc] = time
                    dQ.append((nr, nc, time + 1))

    dQ.append(J)
    maze[J[0]][J[1]] = -1
    found = 0

    while dQ and not found:
        cur_r, cur_c, time = dQ.popleft()

        for dr, dc in dist:
            nr, nc = cur_r + dr, cur_c + dc
            if 0 <= nr < R and 0 <= nc < C:
                if maze[nr][nc] == 0 or maze[nr][nc] > time:
                    if nr == 0 or nc == 0 or nr == R - 1 or nc == C - 1:
                        found = time + 1
                        break
                    maze[nr][nc] = -1
                    dQ.append((nr, nc, time + 1))
    print(found if found else 'IMPOSSIBLE')
