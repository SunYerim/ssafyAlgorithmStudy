T = int(input())


def get_distance(r1, c1, r2, c2):
    return abs(r1 - r2) + abs(c1 - c2)


for tc in range(1, T + 1):
    N, M = map(int, input().split())
    homes = []
    for r in range(N):
        for c, h in enumerate(map(int, input().split())):
            if h == 1:
                homes.append((r, c))
    sum_map = [[[0] * (2 * N) for _ in range(N)] for __ in range(N)]
    for r in range(N):
        for c in range(N):
            for home in homes:
                sum_map[r][c][get_distance(r, c, *home) + 1] += 1
    answer = 0
    for r in range(N):
        for c in range(N):
            for k in range(1, 2 * N):
                sum_map[r][c][k] += sum_map[r][c][k - 1]
                if 0 <= sum_map[r][c][k] * M - (k ** 2 + (k - 1) ** 2):
                    answer = max(answer, sum_map[r][c][k])
    print(f'#{tc} {answer}')
