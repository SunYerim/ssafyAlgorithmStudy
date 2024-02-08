import sys

input = sys.stdin.readline
n = int(input())
inorder = [*map(int, input().split())]
postorder = [*map(int, input().split())]

answer = []


def devide_subtree(inorder: list, postorder: list):
    global answer
    root = postorder[-1]
    answer.append(root)

    left_inorder = inorder[:inorder.index(root)]
    right_inorder = inorder[inorder.index(root) + 1:]
    left_postorder = postorder[:right_inorder[-1]]
    right_postorder = postorder[right_inorder[-1]:-1]

    # recursion
    devide_subtree(left_inorder, left_postorder)
    devide_subtree(right_inorder, right_postorder)
