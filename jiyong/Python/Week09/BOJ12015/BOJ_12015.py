import sys

input = sys.stdin.readline

N = int(input())

seq = list(map(int, input().split()))
lis = [0] * N
lis[0] = seq[0]
length = 0
answer = 1


def bi_search(n: int, target: int):
    lp, rp = 0, n
    mid = (lp + rp) // 2
    while lp < rp:
        if lis[mid] == target:
            return mid
        elif lis[mid - 1] < target < lis[mid]:
            return mid
        elif lis[mid] < target:
            lp = mid + 1
        else:
            rp = mid - 1
        mid = (lp + rp) // 2
    return lp


for i in range(1, N):
    if lis[length] >= seq[i]:
        lis[bi_search(length, seq[i])] = seq[i]
    else:
        length += 1
        lis[length] = seq[i]
    answer = max(answer, length + 1)

print(answer)
