'''
핵심 아이디어 :
1. 매 시간마다 살아있는 줄기 세포를 자료구조로 저장하여 일괄처리하고, K시간 뒤 자료구조의 길이를 통해 답을 구한다.
2. 같은 시점에 두 개 이상의 줄기세포가 동시에 번식하는 경우 생명력이 높은 세포가 우선 -> 생명력으로 정렬하여 우선 처리 (오름차순 정렬 후 스택 활용)
'''

T = int(input())

delta = ((-1, 0), (1, 0), (0, -1), (0, 1))
for tc in range(1, T + 1):
    N, M, K = map(int, input().split())
    map_ = [[0] * (M + K) for _ in range(N + K)]    # K가 1보다 크거나 같으므로 아무리 많이 번식하여도 한쪽 방향으로 K/2 칸 번식 가능
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
        # 다음 시점에 사용될 스택
        next_ = []
        while stack:
            now = stack.pop()
            # 객체 언패킹
            r, c, life, t, activation = now
            # 활성 상태 일 경우
            if activation:
                # 아직 살아있을 경우 -> 그대로 다음 스택에 추가
                if time < t + life:
                    next_.append(now)
                # 이번 시점에 활성화 된 경우 -> 4방향으로 번식
                if time == t + 1:
                    for dr, dc in delta:
                        nr, nc = r + dr, c + dc
                        if map_[nr][nc] < life and not visited[nr][nc]:
                            map_[nr][nc] = life
                            visited[nr][nc] = True
                            next_.append((nr, nc, life, time, False))
            else:
                # 비활성 상태에서 대기시간이 끝나면 -> 활성 상태로 다음 스택에 추가
                if time - t == life:
                    next_.append((r, c, life, time, True))
                # 아직 대기 시간이라면 -> 그대로 다음 스택에 추가
                else:
                    next_.append(now)
        # 생명력 기준으로 정렬
        stack = sorted(next_, key=lambda x: x[2])
    print(f'#{tc} {len(next_)}')
