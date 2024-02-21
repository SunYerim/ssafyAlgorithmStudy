import sys

input = sys.stdin.readline
length = 0
tc = int(input())


def find(x):
    if link[x] == x:
        return x
    tmp = find(link[x])
    distance[x] += distance[link[x]]
    link[x] = tmp
    return tmp


def union(a, b):
    distance[a] = abs(a - b) % 1000
    link[a] = b


for _ in range(tc):
    N = int(input())
    link = [*range(N + 1)]
    distance = [0] * (N + 1)
    while (order := input().rstrip()) != 'O':
        if order[0] == 'E':
            __, I = order.split()
            find(int(I))
            print(distance[int(I)])
        else:
            __, I, J = order.split()
            union(int(I), int(J))
