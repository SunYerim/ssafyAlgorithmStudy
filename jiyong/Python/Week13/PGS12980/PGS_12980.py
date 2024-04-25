def solution(n):
    ans = 0
    while n:
        n, k = divmod(n, 2)
        ans += k
    return ans


if __name__ == '__main__':
    print(solution(5000))
    # ans : 5
