from itertools import combinations

T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    min_val = int(1e9)
    synergy = [list(map(int, input().split())) for _ in range(N)]
    for comb_A in combinations(range(N), N // 2):
        comb_B = [x for x in range(N) if x not in comb_A]
        synergy_A = sum([sum([synergy[x][y] + synergy[y][x] for x in comb_A[i + 1:]]) for i, y in enumerate(comb_A)])
        synergy_B = sum([sum([synergy[x][y] + synergy[y][x] for x in comb_B[i + 1:]]) for i, y in enumerate(comb_B)])
        min_val = min(min_val, abs(synergy_B - synergy_A))
    print(f'#{tc} {min_val}')
