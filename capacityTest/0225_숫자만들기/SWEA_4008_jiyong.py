T = int(input())

min_val = int(1e8)
max_val = -int(1e8)


def operate(ops):
    global min_val, max_val
    result = operands[0]
    for i in range(N - 1):
        if ops[i] == '+':
            result += operands[i + 1]
        elif ops[i] == '-':
            result -= operands[i + 1]
        elif ops[i] == '*':
            result *= operands[i + 1]
        else:
            result = int(result / operands[i + 1])
    min_val = min(min_val, result)
    max_val = max(max_val, result)


def product(cnt):
    if cnt == N - 1:
        operate(operator)
    for i, op in enumerate(['+', '-', '*', '/']):
        if operator_count[i] == 0:
            continue
        operator[cnt] = op
        operator_count[i] -= 1
        product(cnt + 1)
        operator_count[i] += 1


for tc in range(1, T + 1):
    N = int(input())
    operator_count = list(map(int, input().split()))
    operator = [None] * (N - 1)
    operands = list(map(int, input().split()))
    min_val = int(1e8)
    max_val = -int(1e8)
    product(0)
    print(f'#{tc} {max_val - min_val}')
