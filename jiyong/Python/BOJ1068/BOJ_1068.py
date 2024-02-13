import sys

N = int(sys.stdin.readline())
root = -1
count = 0
tree = [[] for _ in range(N)]
route = sys.stdin.readline().split()
cut = int(sys.stdin.readline())

for idx, parent in enumerate(map(int, route)):
    if idx == cut: continue
    if parent == -1:
        root = idx
    else:
        tree[parent].append(idx)


def dfs(node):
    global count
    if not tree[node]:
        count += 1
    else:
        for next_node in tree[node]:
            dfs(next_node)


if root == -1:
    count = 0
else:
    dfs(root)

print(count)
