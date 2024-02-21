# 최단거리는 BFS가 효율적
from collections import deque

def solution(maps):
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    n = len(maps)
    m = len(maps[0])
    queue = deque([(0, 0, 1)])
    maps[0][0] = 0
    
    while queue:
        i, j, dist = queue.popleft()
        if i == n - 1 and j == m - 1:
            return dist
        
        for d in range(4):
            curx = i + dx[d]
            cury = j + dy[d]
            if 0<=curx<n and 0<=cury<m and maps[curx][cury]:
                queue.append((curx, cury, dist+1))
                maps[curx][cury] = 0
    return -1