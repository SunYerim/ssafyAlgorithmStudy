import sys

input = sys.stdin.readline
N = int(input())
tree = [[] for _ in range(N + 1)]
idx = 1
dw = [[] for _ in range(N + 1)]
find_root = [False] * (N + 2)

for _ in range(N):
    root, left, right = map(int, input().split())
    tree[root] = [left, right]
    find_root[left] = True
    find_root[right] = True

find_root[0] = True
root = find_root.index(False)


def dfs(root, depth):
    global idx
    left, right = tree[root]
    if left != -1:
        dfs(left, depth + 1)
    dw[depth].append(idx)
    idx += 1
    if right != -1:
        dfs(right, depth + 1)


dfs(root, 1)

max_width = 0
max_depth = 0
for idx, nodes in enumerate(dw):
    if not nodes:
        continue
    elif nodes[-1] - nodes[0] + 1 > max_width:
        max_width = nodes[-1] - nodes[0] + 1
        max_depth = idx

print(max_depth, max_width)
