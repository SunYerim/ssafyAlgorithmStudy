import sys, math

N, M = map(int, sys.stdin.readline().split())

eratos = [1] * (M - N + 1)
answer = (M - N + 1)
i = 2

while (sqr := i ** 2) <= M:
    for j in range(sqr * math.ceil(N / sqr), M + 1, sqr):
        if j >= N and eratos[j - N] == 1:
            eratos[j - N] = 0
            answer -= 1
    i += 1

print(answer)
