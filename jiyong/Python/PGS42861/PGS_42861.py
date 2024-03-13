import sys


def solution(n, costs):
    answer = 0
    link = list(range(n + 1))

    def union(a, b):
        a, b = find(a), find(b)
        if a == b:
            return
        link[b] = a

    def find(x):
        if link[x] == x:
            return x
        link[x] = find(link[x])
        return link[x]

    def kruskal():
        nonlocal answer
        cnt = 1
        costs.sort(key=lambda x: x[2])
        for s, e, w in costs:
            if find(s) == find(e):
                continue
            union(s, e)
            answer += w
            cnt += 1
            if cnt == n:
                return

    kruskal()
    return answer

