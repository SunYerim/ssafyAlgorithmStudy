import sys
from collections import deque

N, K = map(int, sys.stdin.readline().rstrip().split())
dq = deque()
dq.append((N, 0))
answer = -1
visited = [-1] * 500_001
visited[N] = 1


def is_in(n):
    return 0 <= n <= 500_000


if N == K:
    print(0)
else:
    while dq:
        cur, time = dq.popleft()
        target = K + ((time + 1) * (time + 2) / 2)
        if target > 500_000: break

        if is_in(cur + 1) and visited[cur + 1] < time:
            if cur + 1 == target:
                answer = time + 1
                break
            visited[cur + 1] = time
            dq.append((cur + 1, time + 1))

        if is_in(cur - 1) and visited[cur - 1] < time:
            if cur - 1 == target:
                answer = time + 1
                break
            visited[cur - 1] = time
            dq.append((cur - 1, time + 1))

        if is_in(cur * 2) and visited[cur * 2] < time:
            if cur * 2 == target:
                answer = time + 1
                break
            visited[cur * 2] = time
            dq.append((cur * 2, time + 1))

    print(answer)
