import sys
input = sys.stdin.readline

def dfs(node, start):
    global visited, arr, count

    visited[node] = True
    next = arr[node]

    if not visited[next]:
        dfs(next, start)
    elif next==start and visited[next]:
        answer.append(start)

n = int(input())
arr = [0] * (n+1)

for i in range(n):
    arr[i+1] = int(input())

answer = []



for i in range(1, n+1):
    visited = [False] * (n+1)
    dfs(i, i)
    
answer.sort()
print(len(answer))
print(*answer, sep = "\n")