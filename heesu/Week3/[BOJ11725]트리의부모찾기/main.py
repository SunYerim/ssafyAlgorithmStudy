import sys
from collections import deque

n = int(sys.stdin.readline())
tree_list = [[] for _ in range(n+1)]
result = [[0] for _ in range(n+1)]
visited = [False for _ in range(n+1)]
queue = deque([1])
for _ in range(n-1):
    e1, e2 = map(int,sys.stdin.readline().split())
    tree_list[e1].append(e2)
    tree_list[e2].append(e1)
while queue:
    node_num = queue.popleft()
    visited[node_num] = True
    for childs in tree_list[node_num]:
        if not visited[childs]:
            result[childs] = node_num
            queue.append(childs)

for i in result[2:]:
    print(i)