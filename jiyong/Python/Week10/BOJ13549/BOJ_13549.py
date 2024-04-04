import sys
from heapq import heappush, heappop

N, K = map(int, sys.stdin.readline().split())
INF = 100001
dist = [INF] * 100001
dist[N] = 0

hq = []
heappush(hq, (0, N))
while hq:
    t, now = heappop(hq)
    if now == K:
        break
    for d in (1, -1, 2):
        if d == 2:
            nxt = now * 2
            time = t
        else:
            nxt = now + d
            time = t + 1
        if 0 <= nxt <= 100000 and dist[nxt] > time:
            dist[nxt] = time
            heappush(hq, (time, nxt))

print(dist[K])