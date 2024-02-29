T = int(input())


class Gear:
    def __init__(self, n, info, prev):
        self.num = n
        self.info = list(map(int, info.split()))
        self.rp = 2
        self.lp = 6
        self.prev = prev
        self.nxt = None
        if prev:
            prev.nxt = self

    def spin(self, d):
        global visited
        visited[self.num] = True
        if self.prev and not visited[self.num - 1]:
            if self.info[self.lp] != self.prev.info[self.prev.rp]:
                self.prev.spin(d * -1)
        self.lp = (self.lp - d + 8) % 8
        if self.nxt and not visited[self.num + 1]:
            if self.info[self.rp] != self.nxt.info[self.nxt.lp]:
                self.nxt.spin(d * -1)
        self.rp = (self.rp - d + 8) % 8


for tc in range(1, T + 1):
    score = 0
    K = int(input())
    gears = [None]
    for i in range(1, 5):
        gears.append(Gear(i, input().rstrip(), gears[i - 1]))
    for _ in range(K):
        num, dir_ = map(int, input().split())
        visited = [False] * 5
        gears[num].spin(dir_)
    for gear in gears[1:]:
        if gear.info[gear.rp - 2] == 1:
            score += 2 ** (gear.num - 1)
    print(f'#{tc} {score}')
