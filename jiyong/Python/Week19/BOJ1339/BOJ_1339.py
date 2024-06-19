import sys

input = sys.stdin.readline

N = int(input())
words = [input().rstrip() for _ in range(N)]

dic = {}

for word in words:
    cnt = len(word) - 1
    for w in word:
        if w not in dic:
            dic[w] = pow(10, cnt)
        else:
            dic[w] += pow(10, cnt)
        cnt -= 1

dic = sorted(dic.values(), reverse=True)

result = 0
num = 9

for v in dic:
    result += v * num
    num -= 1

print(result)
