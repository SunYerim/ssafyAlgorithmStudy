from itertools import product

T = int(input())


def operate(operand, operator):
    result = operand[0]
    for i in range(N - 1):
        if operator[i] == '+':
            result += operand[i + 1]
        elif operator[i] == '-':
            result -= operand[i + 1]
        elif operator[i] == '*':
            result *= operand[i + 1]
        else:
            result = int(result / operand[i + 1])
    return result


for tc in range(1, T + 1):
    N = int(input())
    operator_count = list(map(int, input().split()))
    operands = list(map(int, input().split()))
    results = []
    operator = [None] * N
    for ops in product(['+', '-', '*', '/'], repeat=N - 1):
        if ops.count('+') <= operator_count[0] and ops.count('-') <= operator_count[1] and ops.count('*') <= \
                operator_count[2] and ops.count('/') <= operator_count[3]:
            results.append(operate(operands, ops))
    results.sort()
    print(f'#{tc} {results[-1] - results[0]}')
