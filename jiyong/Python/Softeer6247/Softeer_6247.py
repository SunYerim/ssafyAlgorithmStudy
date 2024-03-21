import sys
from collections import defaultdict
input = sys.stdin.readline

n, q = map(int, input().rstrip().split())
cars = list(map(int, input().rstrip().split()))
cars.sort()
order = defaultdict(int)
for v, k in enumerate(cars):
    order[k] = v

for _ in range(q):
    m = int(input().rstrip())
    print(order[m]*(n-order[m]-1))
