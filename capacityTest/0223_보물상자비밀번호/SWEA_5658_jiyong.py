T = int(input())

for tc in range(1, T + 1):
    N, K = map(int, input().split())
    num = list(input().rstrip())
    num_list = set()
    for concat in zip(*[num[i:] + num[:i] for i in range(N // 4)]):
        num_list.add(''.join(concat))
    print(f'#{tc} {int(sorted(num_list, reverse=1)[K - 1], 16)}')
