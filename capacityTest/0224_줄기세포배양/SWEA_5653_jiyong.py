T = int(input())

delta = ((-1, 0), (1, 0), (0, -1), (0, 1))
for tc in range(1, T + 1):
    N, M, K = map(int, input().split())
    map_ = [[0] * (M + K) for _ in range(N + K)]
    visited = [[False] * (M + K) for _ in range(N + K)]
    stack = []
    for r in range(K // 2, K // 2 + N):
        line = list(map(int, input().split()))
        for i, c in enumerate(range(K // 2, K // 2 + M)):
            if line[i] != 0:
                map_[r][c] = line[i]
                visited[r][c] = True
                stack.append((r, c, line[i], 0, False))

    for time in range(1, K + 1):
        next_ = []
        while stack:
            now = stack.pop()
            r, c, life, t, activation = now
            # 활성 상태 일 경우
            if activation:
                if time < t + life:
                    next_.append(now)
                if time == t + 1:
                    for dr, dc in delta:
                        nr, nc = r + dr, c + dc
                        if map_[nr][nc] < life and not visited[nr][nc]:
                            map_[nr][nc] = life
                            visited[nr][nc] = True
                            next_.append((nr, nc, life, time, False))
            else:
                # 대기시간이 끝나면
                if time - t == life:
                    next_.append((r, c, life, time, True))
                else:
                    next_.append(now)
        stack = sorted(next_, key=lambda x: x[2])
    print(f'#{tc} {len(next_)}')
