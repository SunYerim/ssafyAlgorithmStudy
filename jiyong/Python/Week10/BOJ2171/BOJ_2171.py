import sys
from collections import defaultdict

input = sys.stdin.readline

N = int(input())
dic = defaultdict(set)
answer = 0

for _ in range(N):
    x, y = map(int, input().split())
    dic[x].add(y)

set_list = list(dic.values())

for i in range(len(set_list)-1):
    for j in range(i+1, len(set_list)):
        cnt = len(set_list[i] & set_list[j])
        if cnt >= 2:
            answer += (cnt * (cnt - 1) // 2)

print(answer)
