import sys
from math import ceil, log2

input = sys.stdin.readline

# 부대 정보 입력
N = int(input())
nums = [*map(int, input().split())]

seg_tree = [0] * 2 ** (ceil(log2(N)) + 1)


def segmentation(left, right, i=1):
    if left == right:
        seg_tree[i] = nums[left]
        return seg_tree[i]
    mid = (left + right) // 2
    seg_tree[i] = segmentation(left, mid, i * 2) + segmentation(mid + 1, right, i * 2 + 1)
    return seg_tree[i]


def search(start, end, left, right, i=1):
    if end < left or start > right:
        return 0

    if left <= start and end <= right:
        return seg_tree[i]

    mid = (start + end) // 2
    return search(start, mid, left, right, i * 2) + search(mid + 1, end, left, right, i * 2 + 1)


def update(start, end, idx, diff, i=1):
    if start > idx or idx > end:
        return
    seg_tree[i] += diff
    if start != end:
        mid = (start + end) // 2
        update(start, mid, idx, diff, i * 2)
        update(mid + 1, end, idx, diff, i * 2 + 1)


def binary_search(num):
    lp = 0
    rp = N - 1
    while lp <= rp:
        mid = (lp + rp) // 2
        result = search(0, N - 1, 0, mid)
        if result < num:
            lp = mid + 1
        else:
            rp = mid - 1
    return lp + 1


# 세그먼트 트리 초기화
segmentation(0, N - 1)

# 명령 입력
M = int(input())

for _ in range(M):
    query = input().split()
    if query[0] == '1':
        update(0, N - 1, int(query[1]) - 1, int(query[2]))
    else:
        print(binary_search(int(query[1])))
