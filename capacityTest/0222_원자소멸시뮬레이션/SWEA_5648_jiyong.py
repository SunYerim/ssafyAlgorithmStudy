'''
핵심 아이디어 :
1. 주어지는 원자의 좌표는 개구간(-1000, 1000) 에서 정의되므로 해당 구간 바깥에서는 절대 원자가 만날 수 없다.
2. 마주보는 두 원자간의 거리가 홀수인 원자는 정수 시간에 만날 수 없으므로, 모든 좌표값을 2배로 바꿈으로써 0.5초마다의 움직임을 이산적으로 처리하는 방식으로 구현
'''

from collections import deque

map_ = [[None for _ in range(4001)] for __ in range(4001)]
delta = ((0, 1), (0, -1), (-1, 0), (1, 0))
answer = 0


class Atomic:

    def __init__(self, x, y, d, w):
        self.x = x  # x 좌표
        self.y = y  # y 좌표
        self.d = d  # 원자가 이동하는 방향
        self.w = w  # 원자가 가진 에너지
        self.t = 0  # 원자의 시점

    def move(self):
        global answer
        # 이동할 좌표
        dx = self.x + delta[self.d][0]
        dy = self.y + delta[self.d][1]
        # 현재 칸 참조 해제
        map_[self.x][self.y] = None
        self.t += 1
        # 맵 밖으로 나가면 소멸
        if not (0 <= dx < 4001 and 0 <= dy < 4001) or self.t == -1:
            return False
        # 원자가 있는 칸으로 이동한 경우 -> 소멸
        if isinstance(map_[dx][dy], Atomic):
            now = map_[dx][dy]
            map_[dx][dy] = self.t
            answer += (now.w + self.w)
            now.t = -2
            return False
        # 이번 시점에 원자가 소멸한 칸으로 이동한 경우 -> 소멸
        elif map_[dx][dy] == self.t:
            answer += self.w
            return False
        # 이동하기
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
        if not atomic_list:
            break
        temp = deque()
        while atomic_list:
            atomic = atomic_list.popleft()
            if atomic.move():
                temp.append(atomic)
        atomic_list = temp
    print(f'#{tc} {answer}')
