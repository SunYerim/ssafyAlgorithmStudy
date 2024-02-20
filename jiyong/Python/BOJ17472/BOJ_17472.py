import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())

map_ = [list(map(int, input().split())) for _ in range(N)]
delta = ((-1, 0), (1, 0), (0, -1), (0, 1))
answer = 0
bridge_num = 1
bridge_q = deque()
edge_set = set()


def land_bfs(r: int, c: int):
    q = deque([(r, c)])
    while q:
        cur_r, cur_c = q.popleft()
        for dir_, (dr, dc) in enumerate(delta):
            nr = cur_r + dr
            nc = cur_c + dc
            if 0 <= nr < N and 0 <= nc < M:
                if map_[nr][nc] == 1:
                    map_[nr][nc] = bridge_num
                    q.append((nr, nc))
                elif map_[nr][nc] == 0:
                    bridge_q.append((dir_, cur_r, cur_c))


def bridge_bfs():
    while bridge_q:
        dir_, cur_r, cur_c = bridge_q.popleft()
        dist = 1
        while 0 <= (nr := cur_r + dist * delta[dir_][0]) < N and 0 <= (nc := cur_c + dist * delta[dir_][1]) < M:
            if map_[nr][nc] != 0:
                if dist > 2:
                    s, e = map_[cur_r][cur_c], map_[nr][nc]
                    if s == e: break
                    edge_set.add((s - 2, e - 2, dist - 1) if s < e else (e - 2, s - 2, dist - 1))
                break
            dist += 1


def kruskal():
    def find(x):
        if link[x] == x:
            return x
        else:
            link[x] = find(link[x])
            return link[x]

    def same(a, b):
        return find(a) == find(b)

    def unite(a, b):
        a, b = find(a), find(b)
        if a == b:
            return
        if sz[a] < sz[b]:
            a, b = b, a
        sz[a] += sz[b];
        sz[b] = 0
        link[b] = a

    global answer
    edge_list = sorted(edge_set, key=lambda x: x[2])
    for s, e, dist in edge_list:
        if same(s, e):
            continue
        answer += dist
        unite(s, e)
    for i in range(len(link)):
        link[i] = find(i)


for r in range(N):
    for c in range(M):
        if map_[r][c] == 1:
            bridge_num += 1
            map_[r][c] = bridge_num
            land_bfs(r, c)
bridge_bfs()
link = [*range(bridge_num - 1)]
sz = [1] * (bridge_num - 1)
kruskal()

print(answer if answer and all([link[0] == x for x in link[1:]]) else -1)
