# import sys
# t = int(sys.stdin.readline())
# for _ in range(t):
#     stack = []
#     keys = sys.stdin.readline()
#     index = 0
#     for key in keys:
#         if key == '-' and index > 0:
#             del stack[index-1]
#             index-=1
#         elif key == ">":
#             if index < len(stack):
#                 index += 1
#         elif key == "<":
#             if index > 0:
#                 index -= 1
#         else:
#             stack.insert(index, key)
#             index+=1
#     for i in stack:
#         print(i, end="")

#  -> 스택 하나 + 인덱스로 커서 위치 처리 -> 시간초과 발생
#  시간초과 해결하는 방법 생각 안 나서 인터넷 검색
#  커서를 기준으로 좌측, 우측을 관리하는 스택을 각각 2개 관리해서 시간을 단축


import sys
input = sys.stdin.readline
n = int(input())

for _ in range(n):
    keys = list(input().strip())
    left, right = [], []
    for key in keys:
        if key == '-':
            if left:
                left.pop()
        elif key == '>':
            if right:
                left.append(right.pop())
        elif key == '<':
            if left:
                right.append(left.pop())
        else:
            left.append(key)
    for i in left+right[::-1]:
        print(i, end="")
    print()