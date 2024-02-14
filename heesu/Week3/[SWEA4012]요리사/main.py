import sys
input = sys.stdin.readline

def comb(count, start):
    global arr, n, min_cha, check

    if count == n/2: # 조합 완성시
        sum_a = 0
        sum_b = 0
        for i in range(n-1):
            if check[i]: # A 음식이라면
                for j in range(i+1, n):
                    if check[j]:
                        sum_a += (arr[i][j] + arr[j][i])
            else: # B 음식이라면
                for j in range(i+1, n):
                    if not check[j]:
                        sum_b += (arr[i][j] + arr[j][i])
        min_cha = min(min_cha, abs(sum_a-sum_b))
    
    else:
        for i in range(start, n):
            check[i] = True
            comb(count+1, i+1)
            check[i] = False


T = int(input())
for test_case in range(1, T + 1):
    min_cha = sys.maxsize
    n = int(input())
    arr = []
    for _ in range(n):
        arr.append(list(map(int, input().split())))
    check = [False] * n
    comb(0, 0)
    
    print(f"#{test_case} {min_cha}")


