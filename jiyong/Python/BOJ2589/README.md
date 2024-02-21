[백준][G5] 2589 보물섬 / (AC)
---
> **문제 설명**


[[문제 링크](https://www.acmicpc.net/problem/2589)]

보물섬 지도를 발견한 후크 선장은 보물을 찾아나섰다. 보물섬 지도는 아래 그림과 같이 직사각형 모양이며 여러 칸으로 나뉘어져 있다. 각 칸은 육지(L)나 바다(W)로 표시되어 있다. 이 지도에서 이동은 상하좌우로 이웃한 육지로만 가능하며, 한 칸 이동하는데 한 시간이 걸린다. 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다. 육지를 나타내는 두 곳 사이를 최단 거리로 이동하려면 같은 곳을 두 번 이상 지나가거나, 멀리 돌아가서는 안 된다.

<div align="center">
<img src="https://www.acmicpc.net/upload/images/c1bYIsKpI6m317EAx.jpg">
</div>

예를 들어 위와 같이 지도가 주어졌다면 보물은 아래 표시된 두 곳에 묻혀 있게 되고, 이 둘 사이의 최단 거리로 이동하는 시간은 8시간이 된다.

<div align="center">
<img src="https://www.acmicpc.net/upload/images/XqDkWCRUWbzZ.jpg">
</div>

보물 지도가 주어질 때, 보물이 묻혀 있는 두 곳 간의 최단 거리로 이동하는 시간을 구하는 프로그램을 작성하시오.


---

> **제한사항**
>

**입력**

첫째 줄에는 보물 지도의 세로의 크기와 가로의 크기가 빈칸을 사이에 두고 주어진다. 이어 L과 W로 표시된 보물 지도가 아래의 예와 같이 주어지며, 각 문자 사이에는 빈 칸이 없다. 보물 지도의 가로, 세로의 크기는 각각 50이하이다.


**출력**

첫째 줄에 보물이 묻혀 있는 두 곳 사이를 최단 거리로 이동하는 시간을 출력한다.

---

> **문제 풀이**

임의의 `육지` 중 인접한 칸에 `육지`가 세 칸 이상이라면, 그 곳은 출발점이나 도착점이 될 수 없다.

1. 맵 전체를 탐색하며 4면 중 `바다`가 2면 이상(맵 바깥도 포함)인 `육지`을 찾는다.
2. 1에서 찾은 곳들에서 BFS 탐색으로 맵 끝에 도착한 지점 중 가장 긴 곳을 찾는다.

---

> **코드**


```python
import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())

map_ = [[*input().rstrip()] for _ in range(N)]
delta = ((1, 0), (-1, 0), (0, 1), (0, -1))
max_length = 0
candidate = []


def is_in(r: int, c: int):
    return 0 <= r < N and 0 <= c < M


def bfs(init):
    global max_length
    dq = deque([init])
    visited = [[False] * M for _ in range(N)]
    visited[init[0]][init[1]] = True
    while dq:
        cur_r, cur_c, time = dq.popleft()
        flag = True
        for dr, dc in delta:
            nr = cur_r + dr
            nc = cur_c + dc
            if is_in(nr, nc) and not visited[nr][nc] and map_[nr][nc] == 'L':
                dq.append((nr, nc, time + 1))
                visited[nr][nc] = True
                flag = False
        if flag:
            max_length = max(max_length, time)


for i in range(N):
    for j in range(M):
        if map_[i][j] == 'L':
            cnt = 0
            for dr, dc in delta:
                nr = i + dr
                nc = j + dc
                if not (is_in(nr, nc)):
                    cnt += 1
                elif map_[nr][nc] == 'W':
                    cnt += 1
            if cnt > 1:
                candidate.append((i, j, 0))

for el in candidate:
    bfs(el)

print(max_length)
```

---

> **후기**

처음에는 출발점을 DFS를 통해 찾았는데, 그냥 맵을 탐색하면서 4면을 확인하는 방식으로 최적화 하였음.

문제 풀이 시간 : 30분 / 실행시간 : `332ms` / 메모리 : `115424KB` / 코드길이 : `1337B`

알고리즘 분류 : 그래프 이론/탐색, 브루트포스, BFS