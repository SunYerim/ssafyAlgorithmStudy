import sys

input = sys.stdin.readline
length = 0
tc = int(input())


def find(x):
    while link[x] != x:
        x = link[x]
    return x


def r_find(x):
    while r_link[x] != x:
        x = r_link[x]
    return x


def same(a, b):
    return find(a) == find(b)


def unite(a, b):
    r_link[b] = a
    a, b = find(a), find(b)
    if a == b:
        return
    link[a] = b
    distance[a] = abs(b - a) + distance[b]
    update(a, abs(b - a))


def update(x, d):
    while r_find(x) != x:
        distance[r_find(x)] += d
        x = r_find(x)


for _ in range(tc):
    N = int(input())
    link = [*range(N + 1)]
    r_link = [*range(N + 1)]
    distance = [0] * (N + 1)
    while (order := input().rstrip()) != 'O':
        if order[0] == 'E':
            __, I = order.split()
            print(distance[int(I)])
        else:
            __, I, J = order.split()
            unite(int(I), int(J))
