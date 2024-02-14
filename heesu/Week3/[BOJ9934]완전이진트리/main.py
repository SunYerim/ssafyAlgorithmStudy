import sys
k = int(sys.stdin.readline())
nums = list(map(int, sys.stdin.readline().split()))[::-1]
tree = [0 for _ in range(2 ** k)]

def inorder_tree(tree_arr, visit_list, cur_node):
    if 0 < cur_node < 2 ** k and not tree_arr[cur_node]:
        inorder_tree(tree_arr, visit_list, 2*cur_node) # 왼쪽 자식부터 순회
        tree_arr[cur_node] = nums.pop() # 그 다음 본인 순회
        inorder_tree(tree_arr, visit_list, 2*cur_node+1) # 오른쪽 자식 순회

inorder_tree(tree, nums, 1)
idx = 1
flag = 2
while flag <= 2 ** k:
    for i in range(idx, flag):
        print(tree[idx], end=' ')
        idx+=1
    print()
    flag = flag << 1