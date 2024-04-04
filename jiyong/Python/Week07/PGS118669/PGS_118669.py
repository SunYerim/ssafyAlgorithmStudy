from heapq import heappop, heappush


def solution(n, paths, gates, summits):
    answer = []
    graph = [[] for _ in range(n + 1)]
    for s, e, w in paths:
        graph[s].append((w, e))
        graph[e].append((w, s))

    for summit in summits:
        graph[summit].clear()

    dist = [10_000_001] * (n + 1)

    def dijkstra(start):
        hq = []
        heappush(hq, (0, start))
        dist[start] = 0
        while hq:
            d, now = heappop(hq)
            if dist[now] < d:
                continue
            for cost, nxt in graph[now]:
                new_cost = max(cost, d)
                if new_cost < dist[nxt]:
                    heappush(hq, (new_cost, nxt))
                    dist[nxt] = new_cost

    for gate in gates:
        dijkstra(gate)

    return sorted([(dist[x], x) for x in summits])[0][::-1]
