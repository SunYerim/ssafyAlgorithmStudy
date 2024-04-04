import sys

input = sys.stdin.readline
n = int(input())
inorder = [*map(int, input().split())]
postorder = [*map(int, input().split())]

answer = []
stack = [[[0, len(inorder)], [0, len(postorder)]]]

pos = [0] * (n + 1)
for i in range(n):
    pos[inorder[i]] = i

while stack:
    in_idx, post_idx = stack.pop()
    root = postorder[post_idx[1] - 1]
    answer.append(root)
    root_idx = pos[root]

    left_in_idx = [in_idx[0], root_idx]
    right_in_idx = [root_idx + 1, in_idx[1]]
    left_post_idx = [post_idx[0], post_idx[0] + (left_in_idx[1] - left_in_idx[0])]
    right_post_idx = [post_idx[0] + (left_in_idx[1] - left_in_idx[0]), post_idx[1] - 1]

    if right_in_idx[0] < right_in_idx[1]:
        stack.append([right_in_idx, right_post_idx])
    if left_in_idx[0] < left_in_idx[1]:
        stack.append([left_in_idx, left_post_idx])

print(*answer, sep=' ')
