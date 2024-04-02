import sys

input = sys.stdin.readline

n = int(input())
S = input().rstrip()
pi = [0] * n
answer = 0
lp, rp = 0, 1

while rp < n:
    if S[lp] == S[rp]:
        lp += 1
        pi[rp] = lp
        rp += 1
    else:
        if lp != 0:
            lp = pi[lp - 1]
        else:
            pi[rp] = 0
            rp += 1

for i in range(n):
    if pi[i]:
        if pi[pi[i] - 1]:
            pi[i] = pi[pi[i] - 1]
        answer += i - pi[i] + 1
print(answer)
