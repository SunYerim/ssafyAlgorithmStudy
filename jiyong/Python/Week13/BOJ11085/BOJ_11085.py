import sys
from heapq import heappop, heappush

input = sys.stdin.readline


def union(a: int, b: int):
    a_root = find(a)
    b_root = find(b)
    if a_root == b_root:
        return
    link[a_root] = b_root


def find(x: int):
    if link[x] == x:
        return x
    link[x] = find(link[x])
    return link[x]


p, w = map(int, input().split())
c, v = map(int, input().split())

hq = []
link = list(range(p))
for _ in range(w):
    s, e, width = map(int, input().split())
    heappush(hq, (-width, s, e))

while hq:
    cost, s, e = heappop(hq)
    cost = -cost
    union(s, e)

    if find(c) == find(v):
        print(cost)
        break

