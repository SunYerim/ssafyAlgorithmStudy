import sys
import copy

# input data: (2+(2*2)+2)

expr = [*sys.stdin.readline().rstrip()]  # ['(', '2', '+', '(', '2', '*', '2', ')', '+', '2', ')']
br_count = expr.count('(')  # 2
answer = set()

for comb in range(1, 2 ** br_count):  # comb : 1 ~ 4 -> 0b001 ~ 0b100
    stack = []
    temp = copy.deepcopy(expr)  # ['(', '2', '+', '(', '2', '*', '2', ')', '+', '2', ')']
    for i in range(len(temp)):  # i : 0 ~ 11
        if temp[i] == '(':
            if comb & 1:
                stack.append('{')
                temp[i] = ''
            else:
                stack.append('(')
            comb >>= 1
        elif temp[i] == ')':
            if stack[-1] == '{':
                temp[i] = ''
            stack.pop()
    answer.add(''.join(temp))

print(*sorted(answer), sep='\n')

'''
output :
(2+2*2+2)
2+(2*2)+2
2+2*2+2
'''
