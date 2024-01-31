import sys
from collections import deque

N, K = map(int, sys.stdin.readline().rstrip().split())
dq = deque()
dq.append((N, 0))
answer = -1
visited = [[0] * 500_001 for _ in range(2)]
visited[0][N] = 1

if N == K:
    print(0)
else:
    while dq and answer < 0:
        cur, time = dq.popleft()
        target = K + ((time + 1) * (time + 2) // 2)
        if target > 500_000:
            break
        if visited[(time + 1) % 2][target]:
            answer = time + 1
            break
        for next_ in (cur + 1, cur - 1, cur * 2):
            if 0 <= next_ <= 500_000 and visited[(time + 1) % 2][next_] == 0:
                if next_ == target:
                    answer = time + 1
                    break
                visited[(time + 1) % 2][next_] = 1
                dq.append((next_, time + 1))

    print(answer)
