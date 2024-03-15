import sys

input = sys.stdin.readline

N, K = map(int, input().split())

dp = [[0] * (K + 1) for _ in range(N + 1)]
items = []

for n in range(1, N + 1):
    w, v = map(int, input().split())
    for k in range(1, K + 1):
        if k >= w:
            dp[n][k] = max(dp[n - 1][k], v + dp[n][k - w])
        else:
            dp[n][k] = dp[n][k - 1]
print(dp[N][K])
