import sys
from collections import defaultdict

input = sys.stdin.readline

N = int(input())
dic = defaultdict(set)
answer = 0

for _ in range(N):
    x, y = map(int, input().split())
    dic[x].add(y)

x_set = list(dic.keys())

for i in range(len(x_set) - 1):
    for j in range(i + 1, len(x_set)):
        cnt = len(dic[x_set[i]] & dic[x_set[j]])
        if cnt >= 2:
            answer += (cnt * (cnt - 1) // 2)

print(answer)
