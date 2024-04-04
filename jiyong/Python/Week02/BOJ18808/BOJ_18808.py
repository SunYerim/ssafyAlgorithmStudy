import sys

input = sys.stdin.readline

N, M, K = map(int, input().rstrip().split())

laptop = [[0] * M for _ in range(N)]
answer = 0


def is_in(r, c):
    return 0 <= r < N and 0 <= c < M


# 스티커 루프 시작
def attach(sticker):
    global answer, laptop
    # 회전 케이스 반복
    for d in range(4):
        # 좌측 맨 위 (0, 0) 부터 브루트 포스
        for row in range(len(laptop)):
            for col in range(len(laptop[0])):
                # 각 스티커 좌표를 순회하며 기준점만큼 편향(bias)값을 더 함
                for r, c in sticker:
                    nr, nc = row + r, col + c
                    # 만약 인덱스 범위를 초과하거나, 이미 스티커가 붙어있다면 반복문 종료
                    if (not is_in(nr, nc)) or laptop[nr][nc]:
                        break
                # 반복문이 중도 종료되지 않고 다 실행 되었다면, ( => if all(conditions): )
                else:
                    # 스티커 붙이기
                    for r, c in sticker:
                        laptop[row + r][col + c] = 1
                    # 정답 카운트 선형 연산
                    answer += len(sticker)
                    # 함수 종료
                    return
        # 시계 방향으로 90도 회전 (3회) -  0도 -> 90도 -> 180도 -> 270도
        if d < 3: sticker = [(c, -r) for r, c in sticker]


for _ in range(K):
    R, C = map(int, input().rstrip().split())
    sticker = []  # 스티커 좌표 배열
    # 스티커 좌표 저장
    for r in range(R):
        for c, s in enumerate(input().rstrip().split()):
            if s == '1':
                sticker.append((r, c))
    # 스티커 붙이기 함수
    attach(sticker)

print(answer)
