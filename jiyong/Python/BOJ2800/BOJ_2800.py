import sys
import copy

expr = [*sys.stdin.readline().rstrip()]
br_count = expr.count('(')
answer = set()

for comb in range(1, 2 ** br_count):
    stack = []
    temp = copy.deepcopy(expr)
    for i in range(len(temp)):
        if temp[i] == '(':
            if comb & 1:
                stack.append('{')
                temp[i] = ''
            else:
                stack.append('(')
            comb >>= 1
        elif temp[i] == ')':
            if stack[-1] == '{': temp[i] = ''
            stack.pop()
    answer.add(''.join(temp))

print(*sorted(answer), sep='\n')
