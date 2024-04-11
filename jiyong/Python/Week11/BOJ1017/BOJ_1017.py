import sys
from collections import defaultdict

input = sys.stdin.readline

eratos = [True] * 2000
for i in range(2, 2000):
    if eratos[i]:
        for j in range(i + i, 2000, i):
            eratos[j] = False

N = int(input())
nums = list(map(int, input().split()))
first_num = nums[0]
odd = []
even = []
edges = defaultdict(list)
match = {}
visited = {}

for num in nums:
    if num % 2:
        odd.append(num)
    else:
        even.append(num)

for o in odd:
    for e in even:
        if eratos[o + e]:
            edges[o].append(e)


def dfs(u: int, f: int) -> bool:
    global match, visited, edges
    if visited[u]:
        return False
    visited[u] = True
    for v in edges[u]:
        if v == f:
            continue
        if match[v] == -1 or dfs(match[v], f):
            match[v] = u
            return True
    return False


def bit_match(n: int, m: int) -> int:
    global match, visited, odd, even
    match = {k: -1 for k in even if k != m}
    cnt = 0
    for i in odd:
        visited = {k: False for k in odd}
        visited[n] = True
        if dfs(i, m):
            cnt += 1
    return cnt


answer = []

if first_num % 2:
    for e in even:
        if eratos[first_num + e] and bit_match(first_num, e) == N // 2 - 1:
            answer.append(e)
else:
    for o in odd:
        if eratos[first_num + o] and bit_match(o, first_num) == N // 2 - 1:
            answer.append(o)

if answer:
    print(*sorted(answer), sep=' ')
else:
    print(-1)
