import sys
from collections import deque
input = sys.stdin.readline
n = int(input())
test_case = 0
dxdy = [[1, 0], [-1,0], [0, -1],[0, 1]]
while(n):
    test_case+=1
    arr = [list(map(int, input().split())) for _ in range(n)]
    visited = [[sys.maxsize] * n for _ in range(n)]
    visited[0][0] = arr[0][0]
    queue = deque([[0, 0, arr[0][0]]])
    while queue:
        x, y, count = queue.popleft()
        for d in range(4):
            curx = x + dxdy[d][0]
            cury = y + dxdy[d][1]
            if 0 <= curx < n and 0 <= cury < n:
                s = count + arr[curx][cury]
                if visited[curx][cury] > s:
                    queue.append([curx, cury, s])
                    visited[curx][cury] = s
    print(f"Problem {test_case}: {visited[n-1][n-1]}")
    n = int(input())