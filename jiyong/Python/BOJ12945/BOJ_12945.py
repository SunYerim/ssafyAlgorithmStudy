import sys
N = int(sys.stdin.readline())

boxes = [int(sys.stdin.readline()) for _ in range(N)]
boxes.sort()

l, r = 0, N // 2
count = N

while l < N // 2 and r < N:
    if boxes[r] >= boxes[l] * 2:
        count -= 1
        l += 1
    r += 1
print(count)
