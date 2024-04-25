import sys

input = sys.stdin.readline

n, c = map(int, input().split())

homes = [int(input()) for _ in range(n)]
homes.sort()

s, e = 1, homes[-1] - homes[0]
answer = 0
if c == 2:
    answer = homes[-1] - homes[0]
else:
    while s < e:
        last = homes[0]
        cnt = 1

        mid = (s + e) // 2
        for home in homes[1:]:
            if home - last >= mid:
                cnt += 1
                last = home

        if cnt >= c:
            answer = max(mid, answer)
            s = mid + 1
        else:
            e = mid

print(answer)

