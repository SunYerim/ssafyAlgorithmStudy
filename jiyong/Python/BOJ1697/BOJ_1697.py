import sys
from collections import deque

N, K = map(int, sys.stdin.readline().rstrip().split())
dq = deque()
dq.append((N, 0))
answer = -1
visited = [0] * 100_001
visited[N] = 1

if N == K:
    print(0)
else:
    while dq and answer < 0:
        cur, time = dq.popleft()
        for next_ in (cur + 1, cur - 1, cur * 2):
            if 0 <= next_ <= 100_000 and visited[next_] == 0:
                if next_ == K:
                    answer = time + 1
                    break
                visited[next_] = 1
                dq.append((next_, time + 1))

    print(answer)
