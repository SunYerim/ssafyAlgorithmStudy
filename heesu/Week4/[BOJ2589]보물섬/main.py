import sys
from collections import deque
input = sys.stdin.readline

def bfs(i, j): # 시작점을 받으면 그래프 탐색. 거리를 카운트
    global max_count, arr, dxdy
    queue = deque([(i, j)])
    visited = [[0]*w for _ in range(h)]
    visited[i][j] = 1
    count = 0
    cnt=0
    while queue:
        x, y = queue.popleft()
        for d in range(4):
            curx = x + dxdy[d][0]
            cury = y + dxdy[d][1]
            if 0 <= curx < h and 0 <= cury < w and arr[curx][cury] == 'L' and not visited[curx][cury]:
                cnt = visited[x][y]+1
                visited[curx][cury] = cnt
                count = max(cnt, count)
                queue.append((curx, cury))
    max_count = max(cnt-1, max_count)

max_count = 0
dxdy = [[1,0],[-1,0],[0,1],[0,-1]]
h, w = map(int, input().split())
arr = [input().strip() for _ in range(h)]

for i in range(h):
    for j in range(w):
        if arr[i][j] == 'L':
            bfs(i, j)

print(max_count)