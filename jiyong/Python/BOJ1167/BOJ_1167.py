import sys

N = int(sys.stdin.readline().rstrip())
graph = [[] for _ in range(N + 1)]

for _ in range(N):
    input_ = [*map(int, sys.stdin.readline().rstrip().split())]
    s = input_[0]
    for e, w in input_[:-1]:
        graph[s].append([e, w])


def DFS():
    pass