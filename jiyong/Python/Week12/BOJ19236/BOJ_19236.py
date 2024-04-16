import sys

input = sys.stdin.readline

delta = ((-1, 0), (-1, -1), (0, -1), (1, -1), (1, 0), (1, 1), (0, 1), (-1, 1))


def isIn(r: int, c: int) -> bool:
    return 0 <= r < 4 and 0 <= c < 4


class Shark:
    def __init__(self, r: int, c: int, cnt: int, d: int):
        self.r = r
        self.c = c
        self.cnt = cnt
        self.d = d

    def eat(self, r: int, c: int, map__: list):
        pass

    def can_go(self) -> bool:
        nr = self.r + delta[self.d][0]
        nc = self.c + delta[self.d][1]
        return True if isIn(nr, nc) else False


class Fish:
    def __init__(self, r: int, c: int, num: int, d: int):
        self.r = r
        self.c = c
        self.num = num
        self.d = d

    def swap(self, fish):
        self.r, fish.r = fish.r, self.r
        self.c, fish.c = fish.c, self.c

    def find_direction(self, map__: list):
        global delta
        while not (0 <= self.r + delta[self.d][0] < 4 and 0 <= self.c + delta[self.d][1] < 4) or type(
                map__[self.r + delta[self.d][0]][self.c + delta[self.d][1]]) == type(Shark):
            d = (d + 1) % 8


map_ = [[None] * 4 for _ in range(4)]
fish_list = []

for r in range(4):
    line = map(int, input().split())
    for c in range(4):
        map_[r][c] = Fish(r, c, line[c * 2], line[c * 2 + 1])
        fish_list.append(map_[r][c])

fish_list.sort(key=lambda x: x.num)

# 상어 생성
fish_list.remove(map_[0][0])
map_[0][0] = Shark(0, 0, map_[0][0].num, map_[0][0].d)

