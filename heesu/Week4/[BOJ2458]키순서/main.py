import sys
input = sys.stdin.readline

n, m = map(int, input().split())
arr_in = [[] for _ in range(n+1)]
arr_out = [[] for _ in range(n+1)]
count_in = [0]*(n+1)
count_out = [0]*(n+1)
answer = 0

for _ in range(m):
    start, end = map(int, input().split())
    arr_out[start].append(end)
    arr_in[end].append(start)
    count_out[start]+=1
    count_in[end]+=1


for i in range(1, n+1):
    visited = [0]*(n+1)
    visited[i] = 1
    if count_in[i]:
        queue = []
        queue.extend(arr_in[i])
        while queue:
            node = queue.pop()
            if not visited[node]:
                visited[node] = 1
                if count_in[node]:
                    queue.extend(arr_in[node])
        
    if count_out[i]:
        queue = []
        queue.extend(arr_out[i])
        while queue:
            node = queue.pop()
            if not visited[node]:
                visited[node]=1
                if count_out[node]:
                    queue.extend(arr_out[node])
    
    if all(e == 1 for e in visited[1:]): answer+=1

print(answer)