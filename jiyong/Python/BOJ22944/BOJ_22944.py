import sys
from collections import deque

input = sys.stdin.readline
N, H, D = map(int, input().split())
map_ = [[*input().rstrip()] for _ in range(N)]
dq = deque()
umbrella = []
answer = -1


def distance(r1, c1, r2, c2):
    return abs(r1 - r2) + abs(c1 - c2)


for r in range(N):
    for c in range(N):
        if map_[r][c] == 'S':
            S = (r, c)
        elif map_[r][c] == 'E':
            E = (r, c)
        elif map_[r][c] == 'U':
            umbrella.append((r, c))


umbrella.sort(key=lambda x: distance(*x, *S) + distance(*x, *E))

dq.append((*S, H, 0, 0, umbrella))

while dq:
    r, c, h, u, t, u_list = dq.popleft()
    dist = distance(r, c, *E)
    if dist <= h + u:
        answer = t + dist
        break
    for um in u_list:
        if (dist := distance(r, c, *um)) <= h + u:
            if u >= dist:
                dq.append((*um, h, D, t + dist, sorted([x for x in u_list if x != um], key=lambda x: distance(*x, *um) + distance(*x, *E))))
            else:
                dq.append((*um, h + u - dist, D, t + dist, sorted([x for x in u_list if x != um], key=lambda x: distance(*x, *um) + distance(*x, *E))))

print(answer)
