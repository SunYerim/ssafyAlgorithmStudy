from collections import deque

n, w, L = map(int, input().split())

trucks = deque()
trucks.extend([*map(int, input().split())])

print(trucks.pop())