from collections import deque

map_ = [[None for _ in range(4001)] for __ in range(4001)]
delta = ((0, 1), (0, -1), (-1, 0), (1, 0))
answer = 0


class Atomic:

    def __init__(self, x, y, d, w):
        self.x = x
        self.y = y
        self.d = d
        self.w = w
        self.t = 0

    def move(self):
        global answer
        dx = self.x + delta[self.d][0]
        dy = self.y + delta[self.d][1]
        map_[self.x][self.y] = None
        self.t += 1
        if not (0 <= dx < 4001 and 0 <= dy < 4001) or self.t == -1:
            return False
        # 원자가 있는 칸으로 이동한 경우
        if isinstance(map_[dx][dy], Atomic):
            now = map_[dx][dy]
            map_[dx][dy] = self.t
            answer += (now.w + self.w)
            now.t = -2
            return False
        elif map_[dx][dy] == self.t:
            answer += self.w
            return False
        else:
            map_[dx][dy] = self
            self.x = dx
            self.y = dy
            return True


T = int(input())
for tc in range(1, T + 1):
    N = int(input())
    atomic_list = deque()
    for _ in range(N):
        x, y, d, w = map(int, input().split())
        x, y = 2 * x + 2000, 2 * y + 2000
        map_[x][y] = Atomic(x, y, d, w)
        atomic_list.append(map_[x][y])

    answer = 0
    for _ in range(4001):
        temp = deque()
        while atomic_list:
            atomic = atomic_list.popleft()
            if atomic.move():
                temp.append(atomic)
        atomic_list = temp
    print(f'#{tc} {answer}')
