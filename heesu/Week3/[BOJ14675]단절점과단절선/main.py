import sys
n = int(sys.stdin.readline())
tree_list = [[] for _ in range(n+1)]
for _ in range(n-1):
    start, end = map(int, sys.stdin.readline().split())
    tree_list[start].append(end)
    tree_list[end].append(start)

q = int(sys.stdin.readline())
for _ in range(q):
    q_type, num = map(int, sys.stdin.readline().split())
    print("no") if q_type == 1 and len(tree_list[num]) == 1 else print("yes")