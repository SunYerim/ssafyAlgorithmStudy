from heapq import heappush, heappop


def solution(n, s, a, b, fares):
    INF = 2147483647
    costs = [[INF] * (n + 1) for _ in range(3)]
    costs[0][a] = 0
    costs[1][b] = 0
    costs[2][s] = 0
    hq = []

    graph = [[] for _ in range(n + 1)]

    for c, d, f in fares:
        graph[c].append((f, d))
        graph[d].append((f, c))

    for i, t in enumerate([a, b, s]):
        heappush(hq, (0, t))
        while hq:
            cost, now = heappop(hq)
            if cost > costs[i][now]:
                continue

            for w, next_ in graph[now]:
                val = cost + w
                if costs[i][next_] > val:
                    costs[i][next_] = val
                    heappush(hq, (val, next_))

    return min([sum([costs[i][j] for i in range(3)]) for j in range(n+1)])
