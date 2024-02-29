T = int(input())

answer = 13


def back_tracking(depth, used):
    global answer
    if depth == D:
        if check():
            answer = min(used, answer)
        return
    if answer <= used:
        return
    for i in (-1, 0, 1):
        selected[depth] = i
        back_tracking(depth + 1, used + (1 if i != -1 else 0))


def check():
    for c in range(W):
        streak = 1
        able = False
        for r in range(D - 1):
            if (film[r][c] if selected[r] == -1 else selected[r]) == (
                    film[r + 1][c] if selected[r + 1] == -1 else selected[r + 1]):
                streak += 1
            else:
                streak = 1
            if streak == K:
                able = True
                break
        if not able:
            break
    return able


for tc in range(1, T + 1):
    D, W, K = map(int, input().split())
    film = [list(map(int, input().split())) for _ in range(D)]
    answer = 13
    selected = [0] * D
    back_tracking(0, 0)
    print(f'#{tc} {answer}')
