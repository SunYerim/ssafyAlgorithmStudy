import sys

input = sys.stdin.readline

N = int(input())
k = int(input())


def bi_search():
    lp, rp = 1, N * N
    answer = rp
    while lp <= rp:
        mid = (lp + rp) // 2
        cnt = 0
        for i in range(1, N + 1):
            if mid // i == 0:
                break
            cnt += min(N, mid // i)
        if cnt < k:
            lp = mid + 1
        else:
            rp = mid - 1
            answer = min(mid, answer)
    return  answer


print(bi_search())
