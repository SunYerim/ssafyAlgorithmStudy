import sys

expr = [*sys.stdin.readline().rstrip()]
answer = []
stack = []

for e in expr:
    if e.isalpha():
        answer.append(e)
    else:
        if e == '(':
            stack.append(e)
        elif e == ')':
            while stack and stack[-1] != '(':
                answer += stack.pop()
            stack.pop()
        elif e == '*' or e == '/':
            while stack and (stack[-1] == '*' or stack[-1] == '/'):
                answer += stack.pop()
            stack.append(e)
        elif e == '+' or e == '-':
            while stack and stack[-1] != '(':
                answer += stack.pop()
            stack.append(e)

answer += reversed(stack)

print(''.join(answer))
