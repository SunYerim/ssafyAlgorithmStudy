[백준][G4] 11559 Puyo Puyo / (AC)
---
> **문제 설명**
>

[[문제 링크](https://www.acmicpc.net/problem/11559)]

뿌요뿌요의 룰은 다음과 같다.

- 필드에 여러 가지 색깔의 `뿌요`를 놓는다. `뿌요`는 중력의 영향을 받아 아래에 바닥이나 다른 `뿌요`가 나올 때까지 아래로 떨어진다.

- `뿌요`를 놓고 난 후, 같은 색 `뿌요`가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 `뿌요`들이 한꺼번에 없어진다. 이때 1연쇄가 시작된다.

- `뿌요`들이 없어지고 나서 위에 다른 `뿌요`들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어지게 된다.

- 아래로 떨어지고 나서 다시 같은 색의 `뿌요`들이 4개 이상 모이게 되면 또 터지게 되는데, 터진 후 `뿌요`들이 내려오고 다시 터짐을 반복할 때마다 1연쇄씩 늘어난다.

- 터질 수 있는 `뿌요`가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.

남규는 최근 뿌요뿌요 게임에 푹 빠졌다. 이 게임은 1:1로 붙는 대전게임이라 잘 쌓는 것도 중요하지만, 상대방이 터뜨린다면 연쇄가 몇 번이 될지 바로 파악할 수 있는 능력도 필요하다. 하지만 아직 실력이 부족하여 남규는 자기 필드에만 신경 쓰기 바쁘다. 상대방의 필드가 주어졌을 때, 연쇄가 몇 번 연속으로 일어날지 계산하여 남규를 도와주자!

---

> **제한사항**
>

**입력**

총 `12`개의 줄에 필드의 정보가 주어지며, 각 줄에는 `6`개의 문자가 있다.

이때 `.`은 빈공간이고 `.`이 아닌것은 각각의 색깔의 `뿌요`를 나타낸다.

`R`은 빨강, `G`는 초록, `B`는 파랑, `P`는 보라, `Y`는 노랑이다.

입력으로 주어지는 필드는 `뿌요`들이 전부 아래로 떨어진 뒤의 상태이다. 즉, `뿌요` 아래에 빈 칸이 있는 경우는 없다.

**출력**

현재 주어진 상황에서 몇 연쇄가 되는지 출력한다. 하나도 터지지 않는다면 `0`을 출력한다.

---

> **문제 풀이**

별다른 알고리즘 없이, 시뮬레이션을 통해 `뿌요`가 몇 연쇄까지 일어나는지를 판별해보면 된다.

**동작 로직**
1. 현재 상황에서 터트릴 수 있는 `뿌요 그룹`이 있는지 찾는다. (BFS 활용)
2. 만약, 터트릴 수 있는 `뿌요 그룹`이 있다면 해당 자리를 `.`으로 바꾼다. (deepcopy를 이용)
3. 1~2번 과정에서 찾은 모든 `뿌요 그룹`을 처리한 후, 공중에 있는 `뿌요`를 찾아 바닥으로 떨어뜨린다.
4. 더 이상 터트릴 수 있는 `뿌요 그룹`이 존재하지 않을 때 까지 1~3번 과정을 반복한다.

---

> **코드**
>

```python
import sys
from collections import deque

input = sys.stdin.readline

map_ = [list(input()) for _ in range(12)]
delta = ((1, 0), (0, 1), (-1, 0), (0, -1))
answer = 0


# 특정 뿌요가 터트릴 수 있는 위치에 있는지 판별하는 함수
def boom(r: int, c: int, map_copy: list):
    global delta
    alpha = map_copy[r][c]
    cnt = 0
    dq = deque([(r, c)])
    while dq:
        cr, cc = dq.popleft()
        for dr, dc in delta:
            nr = cr + dr
            nc = cc + dc
            if 0 <= nr < 12 and 0 <= nc < 6 and map_copy[nr][nc] == alpha:
                cnt += 1
                map_copy[nr][nc] = '.'
                dq.append((nr, nc))
    return cnt >= 4


# 터트릴 수 있는 뿌요 그룹 찾기
def find_boom():
    global map_
    flag = False
    for r in range(12):
        for c in range(6):
            if map_[r][c] != '.':
                map_copy = [map_[x][:] for x in range(12)]
                if boom(r, c, map_copy):
                    map_ = map_copy
                    flag = True
    return flag


# 공중에 있는 뿌요를 찾아 떨어뜨리기
def drop_puyo():
    for c in range(6):
        while True:
            row_blank = find_blank(c)
            if row_blank:
                row_puyo = find_puyo(c, row_blank)
                if row_puyo != -1:
                    map_[row_blank][c], map_[row_puyo][c] = map_[row_puyo][c], map_[row_blank][c]
                else:
                    break
            else:
                break


# 특정 열에서 가장 아래에 있는 빈 공간 찾기
def find_blank(col: int):
    global map_
    row = 11
    while row > 0 and map_[row][col] != '.':
        row -= 1
    return row


# 특정 열에서 떨어지지 않은 뿌요 찾기 
def find_puyo(col: int, start: int):
    global map_
    row = start - 1
    while row >= 0 and map_[row][col] == '.':
        row -= 1
    return row


# main
while find_boom():
    answer += 1
    drop_puyo()

print(answer)
```

---

> **후기**

해당 문제는 순수 구현을 하는 문제라, 동작 로직만 잘 설계하고 구현만 해내면 어렵지 않게 풀 수 있는 문제.  

문제 풀이 시간 : 30분 / 실행시간 : `60ms` / 메모리 : `34148KB` / 코드길이 : `1690B`

알고리즘 분류 : `시뮬레이션`, `구현`, `너비 우선 탐색`
