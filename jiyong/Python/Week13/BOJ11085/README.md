[백준][G3] 11085 군사 이동 / (AC)
---
> **문제 설명**


[[문제 링크](https://www.acmicpc.net/problem/11085)]

전쟁 당시 Baekjoon World의 국왕은 Cube World를 공격할 작전을 세운 적이 있습니다. Baekjoon World와 Cube World는 p개의 지점과 w개의 길로 표현됩니다. 모든 길은 양방향이며, 각 길마다 너비가 존재하여 이에 비례하는 수의 군사가 지나갈 수 있습니다.

Baekjoon World의 국왕은 군사들이 뭉치는 것이 유리하다고 생각해서, 미리 Cube World로 가는 경로를 정해 두고 그 경로로만 모든 군사를 보냈습니다. Baekjoon World의 국왕은 총명해서, 경로 상에 있는 길 중 너비가 가장 좁은 길의 너비를 최대화하는 경로를 택했습니다.

그런데 전쟁 때문에 어느 길로 보냈는지에 대한 기록이 불타 없어져 버렸습니다. 전쟁사를 완성하려면 이 기록이 꼭 필요합니다. 위대한 과학자인 당신이 다시 복구해 주세요.

---

> **제한사항**


**입력**

첫 줄에 `p`와 `w`가 공백을 사이에 두고 주어집니다. (2 ≤ p ≤ 1 000; 1 ≤ w ≤ 50 000)

다음 줄에 Baekjoon World의 수도 `c`와 Cube World의 수도 `v`가 공백을 사이에 두고 주어집니다. (0 ≤ c, v < p; c ≠ v)

다음 w줄에 길이 연결하는 두 지점 <span>$w_{start}$</span>, <span>$w_{end}$</span>,와 길의 너비 <span>$w_{width}$</span>가 공백을 사이에 두고 주어집니다. (0 ≤ <span>$w_{start}$</span>, <span>$w_{end}$</span> < p; <span>$w_{start}$</span> ≠ <span>$w_{end}$</span>; 1 ≤ <span>$w_{width}$</span> ≤ 1 000)

**출력**

첫 줄에 Baekjoon World의 국왕이 정한 경로 상에 있는 길 중 너비가 가장 좁은 길의 너비를 출력합니다.


---

> **문제 풀이**

가장 좁은 길이 최대화되는 경로를 구하는 것이 `최대 유량`가 흡사 해보이는데, 무방향 그래프라는 점이 차이가 있다. 
또, 경로 자체가 아닌 `너비`를 묻기에 경로에 유념하지 않아도 된다. 
즉, `c`로 부터 `v`로 가는 길이 어떠하든, 경로 중 가장 좁은 길이 최대화가 되면 된다.

먼저, 모든 노드를 `비활성화` 상태로 보고, `너비`가 넓은 길을 순서대로 선택하며 해당 길에 이어진 노드를 `활성화`하는 방식으로 그래프를 형성한다. 노드를 `활성화`할 때마다, `c`와 `v`를 잇는 길이 존재하는지 확인해보면서 최대가 되는 가장 좁은 길을 찾으면 된다.

위의 조건에 가장 알맞는 솔루션은 `서로소 집합(Disjoint set)`을 사용하여 데이터를 다루는 것이다. 

---

> **코드**


```python
import sys
from heapq import heappop, heappush

input = sys.stdin.readline


def union(a: int, b: int):
    a_root = find(a)
    b_root = find(b)
    if a_root == b_root:
        return
    link[a_root] = b_root


def find(x: int):
    if link[x] == x:
        return x
    link[x] = find(link[x])
    return link[x]


p, w = map(int, input().split())
c, v = map(int, input().split())

hq = []
link = list(range(p))
for _ in range(w):
    s, e, width = map(int, input().split())
    heappush(hq, (-width, s, e))

while hq:
    cost, s, e = heappop(hq)
    cost = -cost
    union(s, e)

    if find(c) == find(v):
        print(cost)
        break
```

---

> **후기**

처음에는 다익스트라를 변형해서 접근 해봤는데, 뭔가 말로 형용하기 어려운 논리적 오류가 있어 실패했다..  

문제 풀이 시간 : 1시간(?) / 실행시간 : `196ms` / 메모리 : `118008KB` / 코드길이 : `646B`

알고리즘 분류 : `그래프 이론`, `그래프 탐색`, `분리 집합`
