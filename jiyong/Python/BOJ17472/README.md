[백준][G1] 17472 다리 만들기 2 / (AC)
---
> **문제 설명**


[[문제 링크](https://www.acmicpc.net/problem/17472)]

섬으로 이루어진 나라가 있고, 모든 섬을 다리로 연결하려고 한다. 이 나라의 지도는 N×M 크기의 이차원 격자로 나타낼 수 있고, 격자의 각 칸은 땅이거나 바다이다.

섬은 연결된 땅이 상하좌우로 붙어있는 덩어리를 말하고, 아래 그림은 네 개의 섬으로 이루어진 나라이다. 색칠되어있는 칸은 땅이다.

<div align="center">
<img src="https://upload.acmicpc.net/38cb578e-b289-4b72-841e-422a1458d617/-/preview/" height="200px">
</div>

다리는 바다에만 건설할 수 있고, 다리의 길이는 다리가 격자에서 차지하는 칸의 수이다. 다리를 연결해서 모든 섬을 연결하려고 한다. 섬 A에서 다리를 통해 섬 B로 갈 수 있을 때, 섬 A와 B를 연결되었다고 한다.
다리의 양 끝은 섬과 인접한 바다 위에 있어야 하고, 한 다리의 방향이 중간에 바뀌면 안된다. 또, 다리의 길이는 2 이상이어야 한다.

다리의 방향이 중간에 바뀌면 안되기 때문에, 다리의 방향은 가로 또는 세로가 될 수 밖에 없다. 방향이 가로인 다리는 다리의 양 끝이 가로 방향으로 섬과 인접해야 하고, 방향이 세로인 다리는 다리의 양 끝이 세로
방향으로 섬과 인접해야 한다.

섬 A와 B를 연결하는 다리가 중간에 섬 C와 인접한 바다를 지나가는 경우에 섬 C는 A, B와 연결되어있는 것이 아니다.

아래 그림은 섬을 모두 연결하는 올바른 2가지 방법이고, 다리는 회색으로 색칠되어 있다. 섬은 정수, 다리는 알파벳 대문자로 구분했다.
<table align="center">
    <tr>
        <td width=" 350px" align="center">
                <img src="https://upload.acmicpc.net/41f71ecc-97b4-4351-b741-4b8336576246/-/preview/" height="250px">
        </td>
        <td width="350px" align="center">
                <img src="https://upload.acmicpc.net/3b158fdf-74ba-47d7-a224-9e5b753b8453/-/preview/" height="250px">
        </td>
    </tr>
<tr>
<td align="center">
다리의 총 길이: 13

D는 2와 4를 연결하는 다리이고, 3과는 연결되어 있지 않다.

</td>
<td align="center">
다리의 총 길이: 9 (최소)

</td>
</tr>
</table>

다음은 올바르지 않은 3가지 방법이다

<table align="center">
    <tr>
        <td width=" 250px" align="center">
                <img src="https://upload.acmicpc.net/c7c663a1-4ebb-4c89-9a6a-4157513c1a30/-/preview/" height="150px">
        </td>
        <td width="250px" align="center">
                <img src="https://upload.acmicpc.net/390361f9-0647-4ff8-9709-7c1de26c0929/-/preview/" height="150px">
        </td>
        <td width="250px" align="center">
                <img src="https://upload.acmicpc.net/2a1d4415-0a0d-4508-8a14-1956fdf650ec/-/preview/" height="150px">
        </td>
    </tr>
<tr>
<td align="center">
C의 방향이 중간에 바뀌었다
</td>
<td align="center">
D의 길이가 1이다.

</td>
<td align="center">
가로 다리인 A가 1과 가로로 연결되어 있지 않다.

</td>
</tr>
</table>


다리가 교차하는 경우가 있을 수도 있다. 교차하는 다리의 길이를 계산할 때는 각 칸이 각 다리의 길이에 모두 포함되어야 한다. 아래는 다리가 교차하는 경우와 기타 다른 경우에 대한 2가지 예시이다.

<table align="center">
    <tr>
        <td width=" 350px" align="center">
                <img src="https://upload.acmicpc.net/b6f340e2-8248-4385-9a6a-546e7a2648e4/-/preview/" height="250px">
        </td>
        <td width="350px" align="center">
                <img src="https://upload.acmicpc.net/dd98ec33-6796-455d-a612-8db31a9806f0/-/preview/" height="250px">
        </td>
    </tr>
<tr>
<td align="center">
A의 길이는 4이고, B의 길이도 4이다.

총 다리의 총 길이: 4 + 4 + 2 = 10
</td>
<td align="center">
다리 A: 2와 3을 연결 (길이 2)

다리 B: 3과 4를 연결 (길이 3)

다리 C: 2와 5를 연결 (길이 5)

다리 D: 1과 2를 연결 (길이 2)

총 길이: 12

</td>
</tr>
</table>

나라의 정보가 주어졌을 때, 모든 섬을 연결하는 다리 길이의 최솟값을 구해보자.

---

> **제한사항**
>

**입력**

첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. 둘째 줄부터 N개의 줄에 지도의 정보가 주어진다. 각 줄은 M개의 수로 이루어져 있으며, 수는 0 또는 1이다. 0은 바다, 1은 땅을 의미한다.

**출력**

모든 섬을 연결하는 다리 길이의 최솟값을 출력한다. 모든 섬을 연결하는 것이 불가능하면 -1을 출력한다.

---

> **문제 풀이**

1. 맵 전체를 탐색하며 `BFS`를 통해 각 섬에 번호를 부여하고 섬의 가장자리들을 큐에 저장.
2. 섬의 가장자리에서 출발하여, 맵 끝이나 다른 섬으로 도착할 때 까지 `DFS`를 이용하여 다리 경로를 찾는다.
3. 2에서 찾은 경로들로 모든 섬을 연결할 수 있는 `최소 신장 트리`를 찾는다.

---

> **코드**


```python
import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())

map_ = [list(map(int, input().split())) for _ in range(N)]
delta = ((-1, 0), (1, 0), (0, -1), (0, 1))
answer = 0
land_num = 1
bridge_q = deque()
edge_set = set()


def land_bfs(r: int, c: int):
    q = deque([(r, c)])
    while q:
        cur_r, cur_c = q.popleft()
        for dir_, (dr, dc) in enumerate(delta):
            nr = cur_r + dr
            nc = cur_c + dc
            if 0 <= nr < N and 0 <= nc < M:
                if map_[nr][nc] == 1:
                    map_[nr][nc] = land_num
                    q.append((nr, nc))
                elif map_[nr][nc] == 0:
                    bridge_q.append((dir_, cur_r, cur_c))


def bridge_dfs():
    while bridge_q:
        dir_, cur_r, cur_c = bridge_q.popleft()
        dist = 1
        while 0 <= (nr := cur_r + dist * delta[dir_][0]) < N and 0 <= (nc := cur_c + dist * delta[dir_][1]) < M:
            if map_[nr][nc] != 0:
                if dist > 2:
                    s, e = map_[cur_r][cur_c], map_[nr][nc]
                    if s == e: break
                    edge_set.add((s - 2, e - 2, dist - 1) if s < e else (e - 2, s - 2, dist - 1))
                break
            dist += 1


def kruskal():
    def find(x):
        if link[x] == x:
            return x
        else:
            link[x] = find(link[x])
            return link[x]

    def same(a, b):
        return find(a) == find(b)

    def unite(a, b):
        a, b = find(a), find(b)
        if a == b:
            return
        link[b] = a

    global answer
    edge_list = sorted(edge_set, key=lambda x: x[2])
    for s, e, dist in edge_list:
        if same(s, e):
            continue
        answer += dist
        unite(s, e)
    for i in range(len(link)):
        link[i] = find(i)


for r in range(N):
    for c in range(M):
        if map_[r][c] == 1:
            land_num += 1
            map_[r][c] = land_num
            land_bfs(r, c)
bridge_dfs()
link = [*range(land_num - 1)]
kruskal()

print(answer if answer and all([link[0] == x for x in link[1:]]) else -1)
```

---

> **후기**

각 알고리즘 별로 일반적인 유형과는 조금 다르게 변형하여 구현하는 부분들이 다소 있어 설계하는 시간이 제법 오래걸렸던 문제. 다리를 이을 때, 같은 섬으로 도착하는 경로를 판별하는 부분을 빠뜨려서 반례 때문에 애를 먹었다.

문제 풀이 시간 : 2시간 / 실행시간 : `132ms` / 메모리 : `112008KB` / 코드길이 : `2116B`

알고리즘 분류 : 구현, 그래프 이론/탐색, 브루트포스, BFS, DFS, 최소 신장 트리