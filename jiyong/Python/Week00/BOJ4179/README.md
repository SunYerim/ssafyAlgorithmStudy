
[백준][G4] 4179 불! / (AC)
---
> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/4179)]

지훈이는 미로에서 일을 한다. 지훈이를 미로에서 탈출하도록 도와주자!

미로에서의 지훈이의 위치와 불이 붙은 위치를 감안해서 지훈이가 불에 타기전에 탈출할 수 있는지의 여부, 그리고 얼마나 빨리 탈출할 수 있는지를 결정해야한다.

지훈이와 불은 매 분마다 한칸씩 수평또는 수직으로(비스듬하게 이동하지 않는다) 이동한다.

불은 각 지점에서 네 방향으로 확산된다.

지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있다.

지훈이와 불은 벽이 있는 공간은 통과하지 못한다.

---

> **제한사항**
> 

**입력**

입력의 첫째 줄에는 공백으로 구분된 두 정수 R과 C가 주어진다. 단, 1 ≤ R, C ≤ 1000 이다. R은 미로 행의 개수, C는 열의 개수이다.

다음 입력으로 R줄동안 각각의 미로 행이 주어진다.

각각의 문자들은 다음을 뜻한다.

- #: 벽
- .: 지나갈 수 있는 공간
- J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간)
- F: 불이 난 공간

J는 입력에서 하나만 주어진다.

**출력**

지훈이가 불이 도달하기 전에 미로를 탈출 할 수 없는 경우 IMPOSSIBLE 을 출력한다.

지훈이가 미로를 탈출할 수 있는 경우에는 가장 빠른 탈출시간을 출력한다.

---

> **문제 풀이**
> 

최단거리를 묻는 문제이므로 2차원 맵에서 BFS/Brute Force 로 접근.

이 문제는 특이하게 플레이어만 움직이는 것이 아니라, 불이 매 초마다 사방으로 번지는 상황에서 플레이어가 불을 피하여 탈출(맵 가장자리로 이동)할 수 있는지를 판별하는 문제.

1. 입력값 중 벽은 `-1` 나머지는 `0` 으로 초기화하여 `R` * `C` 크기의 배열로 맵을 구현.
2. 플레이어 `J` 와 `F` 는 각각 좌표를 별도 변수에 저장하여 BFS에 활용하였음(F는 다수 개를 허용함에 주의).
3. 먼저 `F` 부터 BFS 탐색하며 불이 해당 칸에 도착하는 시간을 맵에 표기.
4. 이후, `J` 를 BFS 탐색하며 `0` 이거나 현재 시간보다 큰 좌표로만 이동.
5. 맵의 가장자리에 도착하는지 판별하여 `최소시간` or `IMPOSSIBLE` 출력 

---

> **코드**
> 

```python
from collections import deque
import sys

R, C = map(int, sys.stdin.readline().split())
J, F = (), []
maze = [[0] * C for _ in range(R)]
for r in range(R):
    cur = sys.stdin.readline()
    for c, e in enumerate(cur.rstrip()):
        if e == '#':
            maze[r][c] = -1
        elif e == 'J':
            J = (r, c, 1)
        elif e == 'F':
            maze[r][c] = 1
            F.append((r, c, 1))
        else:
            maze[r][c] = 0
if J[0] == 0 or J[1] == 0 or J[0] == R - 1 or J[1] == C - 1:
    print(1)
else:
    dist = ((1, 0), (-1, 0), (0, 1), (0, -1))

    dQ = deque()
    dQ.extend(F)

    while dQ:
        cur_r, cur_c, time = dQ.popleft()

        for dr, dc in dist:
            nr, nc = cur_r + dr, cur_c + dc
            if 0 <= nr < R and 0 <= nc < C:
                if maze[nr][nc] == 0:
                    maze[nr][nc] = time
                    dQ.append((nr, nc, time + 1))

    dQ.append(J)
    maze[J[0]][J[1]] = -1
    found = 0

    while dQ and not found:
        cur_r, cur_c, time = dQ.popleft()

        for dr, dc in dist:
            nr, nc = cur_r + dr, cur_c + dc
            if 0 <= nr < R and 0 <= nc < C:
                if maze[nr][nc] == 0 or maze[nr][nc] > time:
                    if nr == 0 or nc == 0 or nr == R - 1 or nc == C - 1:
                        found = time + 1
                        break
                    maze[nr][nc] = -1
                    dQ.append((nr, nc, time + 1))
    print(found if found else 'IMPOSSIBLE')
```

---

> **후기**
> 

시행착오가 많았던 문제, 불과 플레이어를 하나의 큐로 담아 BFS했더니 자꾸 WA였고(아마 이게 문제는 아님), 각각을 분리하여 실행하는 구조로 바꾼 뒤에도 WA였는데, 알고보니 시작하자마자 탈출할 수 있는 경우(가장자리에서 시작)을 고려하지 않고 탐색하였기 때문에 일어난 일인듯 하다…

문제 풀이 시간 : 2시간 이상(약 이틀간 잡고 있었음) / 실행시간 : `336ms` / 메모리 : `132760KB` / 코드길이 : `1518B`

알고리즘 분류 : BFS