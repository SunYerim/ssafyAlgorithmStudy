from collections import deque


def solution(land, height):
    answer = 0
    N = len(land)
    land_num = 1
    delta = ((1, 0), (-1, 0), (0, 1), (0, -1))
    visited = [[0] * (N) for _ in range(N)]
    ladders = []
    edges = set()

    def bfs(r, c, num):
        dq = deque([(r, c)])
        visited[r][c] = num
        while dq:
            cur_r, cur_c = dq.popleft()
            for dr, dc in delta:
                nr = cur_r + dr
                nc = cur_c + dc
                if 0 <= nr < N and 0 <= nc < N and visited[nr][nc] == 0:
                    h = abs(land[cur_r][cur_c] - land[nr][nc])
                    if h <= height:
                        visited[nr][nc] = num
                        dq.append((nr, nc))
                    else:
                        ladders.append((cur_r, cur_c, nr, nc, h))

    def find(x):
        if link[x] == x:
            return x
        link[x] = find(link[x])
        return link[x]

    def union(a, b):
        a, b = find(a), find(b)
        if a == b:
            return
        link[b] = a

    def kruskal():
        cnt = 1
        nonlocal answer
        for s, e, cost in edges:
            if s == e:
                continue
            if find(s) == find(e):
                continue
            union(s, e)
            answer += cost
            cnt += 1
            if cnt == land_num - 1:
                return

    for i in range(N):
        for j in range(N):
            if visited[i][j] == 0:
                bfs(i, j, land_num)
                land_num += 1

    link = list(range(land_num))

    for i in range(len(ladders)):
        r1, c1, r2, c2, w = ladders[i]
        if visited[r1][c1] == visited[r2][c2]: continue
        edges.add((visited[r1][c1], visited[r2][c2], w))
    edges = list(edges)
    edges.sort(key=lambda x: x[2])
    kruskal()

    print(*visited, sep='\n')
    print(edges)
    print(ladders)

    return answer


solution([[1, 4, 8, 10], [5, 5, 5, 5], [10, 10, 10, 10], [10, 10, 10, 20]], 3)
