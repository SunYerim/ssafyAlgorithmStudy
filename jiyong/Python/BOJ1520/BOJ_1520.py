import sys

input = sys.stdin.readline

M, N = map(int, input().rstrip().split())

map_ = [list(map(int, input().rstrip().split())) for _ in range(M)]
cnt = 0
visited = [[0] * N for _ in range(M)]


def dfs(r: int, c: int, v_matrix: list):
    global cnt, M, N, visited
    if r == M - 1 and c == N - 1:
        visited = [[visited[i][j] or v_matrix[i][j] for j in range(N)] for i in range(M)]
        cnt += 1
        return
    for dr, dc in ((1, 0), (-1, 0), (0, 1), (0, -1)):
        nr = dr + r
        nc = dc + c
        if 0 <= nr < M and 0 <= nc < N and map_[r][c] > map_[nr][nc]:
            if visited[nr][nc] == 0:
                v_matrix[nr][nc] += 1
                dfs(nr, nc, v_matrix)
                v_matrix[nr][nc] -= 1
            else:
                visited = [[visited[i][j] or v_matrix[i][j] for j in range(N)] for i in range(M)]
                cnt += 1
                return


dfs(0, 0, [[False] * N for _ in range(M)])

print(cnt)
