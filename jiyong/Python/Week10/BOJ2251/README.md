[백준][G5] 2251 물통  / (AC)
---
### 문제 설명


[[문제 링크](https://www.acmicpc.net/problem/2251)]

각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다. 처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다. 이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데, 이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다. 이 과정에서 손실되는 물은 없다고 가정한다.

이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 물의 양이 변할 수도 있다. 첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을 모두 구해내는 프로그램을 작성하시오.

---

### 제한사항


**입력**

첫째 줄에 세 정수 A, B, C가 주어진다.

**출력**

첫째 줄에 공백으로 구분하여 답을 출력한다. 각 용량은 오름차순으로 정렬한다.


---

### 문제 풀이

물의 이동을 관점으로 `완전 그래프`에서의 `BFS`를 수행하며, `A`물통이 비는 경우에만 방문을 체크하여 모든 경우의 수를 탐색하면 된다. 
방문을 체크할 때에는 순서쌍 (`a`,`b`,`c`)를 `tuple`로 만들어 `set`에 담아 비교하는 것이 가장 효율적이다. 

---

### 코드

```python
import sys
from collections import deque

A, B, C = list(map(int, sys.stdin.readline().split()))

visited = set()

answer = set()

dq = deque([(0, 0, C)])

while dq:
    a, b, c = dq.popleft()
    if (a, b, c) in visited:
        continue
    visited.add((a, b, c))

    # 첫 번째 물통이 비어있을 때, 세 번째 물통의 물의 양을 기록
    if a == 0:
        answer.add(c)
        
    # A 물통 -> B 물통
    move = min(a, B - b)
    if move > 0:
        dq.append((a - move, b + move, c))

    # A 물통 -> C 물통
    move = min(a, C - c)
    if move > 0:
        dq.append((a - move, b, c + move))

    # B 물통 -> A 물통
    move = min(b, A - a)
    if move > 0:
        dq.append((a + move, b - move, c))

    # B 물통 -> C 물통
    move = min(b, C - c)
    if move > 0:
        dq.append((a, b - move, c + move))

    # C 물통 -> A 물통
    move = min(c, A - a)
    if move > 0:
        dq.append((a + move, b, c - move))

    # C 물통 -> B 물통
    move = min(c, B - b)
    if move > 0:
        dq.append((a, b + move, c - move))

print(' '.join(map(str, sorted(answer))))
```

---

### 후기

생각보다 무식한 방법으로 풀어야하는 문제

문제 풀이 시간 : ? / 실행시간 : `60ms` / 메모리 : `34096KB` / 코드길이 : `1111B`

알고리즘 분류 : `그래프 이론`, `그래프 탐색`, `너비 우선 탐색`
