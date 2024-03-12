def solution(n, s, a, b, fares):
    INF = 2147483647
    dist_table = [[INF] * (n + 1) for _ in range(n + 1)]
    for c, d, f in fares:
        dist_table[c][c] = 0
        dist_table[d][d] = 0
        dist_table[c][d] = f
        dist_table[d][c] = f

    for k in range(1, n + 1):
        for r in range(1, n + 1):
            for c in range(1, n + 1):
                dist_table[r][c] = min(dist_table[r][k] + dist_table[k][c], dist_table[r][c])
    return min([dist_table[x][a] + dist_table[x][b] + dist_table[x][s] for x in range(1, n + 1)])
