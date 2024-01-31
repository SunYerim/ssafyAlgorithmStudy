import sys

# 테스트 케이스
T = int(sys.stdin.readline())


def show(rank, scores, j):
    rank.sort(key=lambda x: scores[x], reverse=True)
    idx = 0
    current_score = -1
    i = 0
    while i < len(rank):
        if current_score != scores[rank[i]]:
            idx = i
            current_score = scores[rank[i]]
        if rank[i] == j:
            print(idx + 1)
            return rank
        else:
            i += 1


# 테스트 케이스 반복
for tc in range(T):
    # 선수
    N = int(sys.stdin.readline())

    # 쿼리
    M = int(sys.stdin.readline())

    rank = [str(i) for i in range(1, N + 1)]
    scores = {str(k): 0 for k in range(1, N + 1)}

    # 쿼리 반복
    for _ in range(M):
        query = sys.stdin.readline()
        if query[0] == 'R':
            __, j, k = query.rstrip().split()
            scores[j] += int(k)
        else:
            __, j = query.rstrip().split()
            rank = show(rank, scores, j)
