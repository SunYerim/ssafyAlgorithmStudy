import sys
input = sys.stdin.readline

def find(n):
    if parent[n] != n:
        parent[n] = find(parent[n])
    return parent[n]

def union(a, b):
    a = find(a)
    b = find(b)
    if a < b:
        parent[b] = a
    else:
        parent[a] = b


N, M = map(int, input().split())

edges = []
parent = list(range(N + 1))
for _ in range(M):
    A, B, C = map(int, input().split())
    edges.append((A, B, C))
edges.sort(key=lambda x: x[2])

answer = 0
last_edge = 0
for a, b, c in edges:
    if find(a) != find(b):
        union(a, b)
        answer += c
        last_edge = c
print(answer - last_edge)