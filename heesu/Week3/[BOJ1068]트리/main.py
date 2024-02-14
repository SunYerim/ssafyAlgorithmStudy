import sys
from collections import defaultdict
from collections import deque

n = int(sys.stdin.readline())
arr = list(map(int, sys.stdin.readline().split()))
erase = int(sys.stdin.readline())
tree_map = defaultdict(list)
for i, parent in enumerate(arr):
    if i != erase:
        tree_map[parent].append(i)
count = 0
queue = deque([-1])
while queue:
    node = queue.popleft()
    for n in tree_map[node]:
        if n in tree_map:
            queue.append(n)
        else:
            count+=1
print(count)