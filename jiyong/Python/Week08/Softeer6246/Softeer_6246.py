import sys
input = sys.stdin.readline

n, m = map(int, input().rstrip().split())
map_ = [list(map(int, input().rstrip().split())) for _ in range(n)]
visited = [[-1]*n for _ in range(n)]
sr, sc = map(int, input().rstrip().split())
for i in range(2, m):
    r, c = map(int, input().rstrip().split())
    visited[r-1][c-1] = i
er, ec = map(int, input().rstrip().split())
visited[sr-1][sc-1] = 1
visited[er-1][ec-1] = m
cnt = 0


def dfs(r: int, c: int, step: int):
    global cnt, n, m, visited, map_, er, ec
    if r == er-1 and c == ec-1:
        cnt += 1
        return
    for dr, dc in ((1, 0), (-1, 0), (0, 1), (0, -1)):
        nr = r + dr
        nc = c + dc
        if 0 <= nr < n and 0 <= nc < n and map_[nr][nc] == 0:
            if visited[nr][nc] == -1:
                visited[nr][nc] = 0
                dfs(nr, nc, step)
                visited[nr][nc] = -1
            elif visited[nr][nc] == step + 1:
                temp = visited[nr][nc]
                visited[nr][nc] = 0
                dfs(nr, nc, step + 1)
                visited[nr][nc] = temp

dfs(sr-1,sc-1, 1)

print(cnt)