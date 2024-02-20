N = int(input())
l, r = 0, 1
for _ in range(N):
    l, r = r % 10_007, (l + r) % 10_007
print(r % 10_007)
