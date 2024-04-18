import sys
from copy import deepcopy

input = sys.stdin.readline

delta = ((-1, 1), (-1, 0), (-1, -1), (0, -1), (1, -1), (1, 0), (1, 1), (0, 1))


def is_in(r: int, c: int) -> bool:
    return 0 <= r < 4 and 0 <= c < 4


class Shark:
    def __init__(self, r: int, c: int, cnt: int, d: int):
        self.r = r
        self.c = c
        self.cnt = cnt
        self.d = d

    def can_go(self) -> bool:
        nr = self.r + delta[self.d][0]
        nc = self.c + delta[self.d][1]
        return True if is_in(nr, nc) else False


class Fish:
    def __init__(self, r: int, c: int, num: int, d: int):
        self.r = r
        self.c = c
        self.num = num
        self.d = d

    def swap(self, map__: list, fish: list) -> None:
        self.find_direction(map__)
        nr, nc = self.r + delta[self.d][0], self.c + delta[self.d][1]
        n = map__[nr][nc]
        map__[self.r][self.c], map__[nr][nc] = map__[nr][nc], map__[self.r][self.c]
        if n:
            self.r, fish[n].r = fish[n].r, self.r
            self.c, fish[n].c = fish[n].c, self.c

        else:
            self.r = nr
            self.c = nc

    def find_direction(self, map__: list) -> None:
        global delta
        while (not (0 <= self.r + delta[self.d][0] < 4 and 0 <= self.c + delta[self.d][1] < 4)) or \
                map__[self.r + delta[self.d][0]][self.c + delta[self.d][1]] == -1:
            self.d = (self.d + 1) % 8


map_ = [[0] * 4 for _ in range(4)]
fish_list = [Fish(0, 0, 0, 0)]

for r in range(4):
    line = list(map(int, input().split()))
    for c in range(4):
        map_[r][c] = line[c * 2]
        fish_list.append(Fish(r, c, line[c * 2], line[c * 2 + 1] % 8))

fish_list.sort(key=lambda x: x.num)

# 상어 생성
shark = Shark(0, 0, fish_list[map_[0][0]].num, fish_list[map_[0][0]].d)
fish_list[map_[0][0]] = None
map_[0][0] = -1

answer = 0


def dfs(s: Shark, m: list, f: list):
    global answer
    for i in range(1, 17):
        if f[i]:
            f[i].swap(m, f)
    if not s.can_go():
        answer = max(answer, s.cnt)
        return
    else:
        for i in range(1, 4):
            nr, nc = s.r + i * delta[s.d][0], s.c + i * delta[s.d][1]
            if is_in(nr, nc) and m[nr][nc] != 0:
                nm = [x[:] for x in m]
                nf = deepcopy(f)
                ns = Shark(nr, nc, s.cnt + nf[nm[nr][nc]].num, nf[nm[nr][nc]].d)
                nf[nm[nr][nc]] = None
                nm[s.r][s.c] = 0
                nm[nr][nc] = -1
                dfs(ns, nm, nf)


dfs(shark, map_, fish_list)

print(answer)
