def solution(m, n, puddles):
    answer = 0
    map_ = [[0]*(m+1) for _ in range(n+1)]
    map_[1][1] = 1
    for r in range(1, len(map_)):
        for c in range(1, len(map_[0])):
            if (r, c) == (1, 1): continue
            if [c, r] not in puddles:
                map_[r][c] = map_[r][c-1] + map_[r-1][c]
            else:
                map_[r][c] = 0
    return map_[-1][-1] % 1_000_000_007