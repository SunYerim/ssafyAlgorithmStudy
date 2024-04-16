import sys

input = sys.stdin.readline

n, m, r = map(int, input().split())
items = list(map(int, input().split()))
dist = [[0]*n for _ in range(n)]

for _ in range(r):
