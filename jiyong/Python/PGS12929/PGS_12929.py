def solution(n):
    answer = 0
    stack = [1]
    for i in range(1, n+1):
        stack.append((2 * (2 * i - 1) * stack[-1]) / (i + 1))
    return stack[-1]