T = int(input())

for tc in range(1, T + 1):
    N, X = map(int, input().split())
    map_ = [list(map(int, input().split())) for _ in range(N)]
    cnt = 0
    # 가로(행) 체크
    for r in range(N):
        flag = False
        streak = 1
        for c in range(N - 1):
            if flag:
                if streak >= X:
                    streak = 0
                    flag = False
                elif map_[r][c] == map_[r][c + 1]:
                    streak += 1
                else:
                    break
            if not flag:
                if map_[r][c] == map_[r][c + 1]:
                    streak += 1
                else:
                    if map_[r][c] + 1 == map_[r][c + 1]:
                        if streak < X:
                            break
                        streak = 1
                    elif map_[r][c] - 1 == map_[r][c + 1]:
                        if N - c > X:
                            flag = True
                            streak = 1
                        else:
                            break
                    else:
                        break
        else:
            cnt += 1
    # 세로(열) 체크
    for c in range(N):
        flag = False
        streak = 1
        for r in range(N - 1):
            if flag:
                if streak >= X:
                    streak = 0
                    flag = False
                elif map_[r][c] == map_[r + 1][c]:
                    streak += 1
                else:
                    break
            if not flag:
                if map_[r][c] == map_[r + 1][c]:
                    streak += 1
                else:
                    if map_[r][c] + 1 == map_[r + 1][c]:
                        if streak < X:
                            break
                        streak = 1
                    elif map_[r][c] - 1 == map_[r + 1][c]:
                        if N - r > X:
                            flag = True
                            streak = 1
                        else:
                            break
                    else:
                        break
        else:
            cnt += 1
    print(f'#{tc} {cnt}')
