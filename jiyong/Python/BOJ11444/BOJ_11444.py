import sys

n = int(sys.stdin.readline().rstrip())

fibo = [0, 1]

for _ in range(2, n + 1):
    fibo = [fibo[-1], (fibo[-1] + fibo[-2]) % 1_000_000_007]

print(fibo[-1] % 1_000_000_007)
# https://st-lab.tistory.com/252
# TLE