[백준][G4] 2458 키 순서 / (AC)
---
> **문제 설명**


[[문제 링크](https://www.acmicpc.net/problem/2458)]

1번부터 N번까지 번호가 붙여져 있는 학생들에 대하여 두 학생끼리 키를 비교한 결과의 일부가 주어져 있다. 단, N명의 학생들의 키는 모두 다르다고 가정한다. 예를 들어, 6명의 학생들에 대하여 6번만 키를 비교하였고, 그 결과가 다음과 같다고 하자.

- 1번 학생의 키 < 5번 학생의 키
- 3번 학생의 키 < 4번 학생의 키
- 5번 학생의 키 < 4번 학생의 키
- 4번 학생의 키 < 2번 학생의 키
- 4번 학생의 키 < 6번 학생의 키
- 5번 학생의 키 < 2번 학생의 키

이 비교 결과로부터 모든 학생 중에서 키가 가장 작은 학생부터 자신이 몇 번째인지 알 수 있는 학생들도 있고 그렇지 못한 학생들도 있다는 사실을 아래처럼 그림을 그려 쉽게 확인할 수 있다. a번 학생의 키가 b번 학생의 키보다 작다면, a에서 b로 화살표를 그려서 표현하였다.

<div align="center">
<img src="https://upload.acmicpc.net/8f9e2484-a3aa-4b97-b1fa-387df4ae58d0/-/preview/" height="200px">
</div>

1번은 5번보다 키가 작고, 5번은 4번보다 작기 때문에, 1번은 4번보다 작게 된다. 그러면 1번, 3번, 5번은 모두 4번보다 작게 된다. 또한 4번은 2번과 6번보다 작기 때문에, 4번 학생은 자기보다 작은 학생이 3명이 있고, 자기보다 큰 학생이 2명이 있게 되어 자신의 키가 몇 번째인지 정확히 알 수 있다. 그러나 4번을 제외한 학생들은 자신의 키가 몇 번째인지 알 수 없다.

학생들의 키를 비교한 결과가 주어질 때, 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇 명인지 계산하여 출력하는 프로그램을 작성하시오.


---

> **제한사항**
>

**입력**

첫째 줄에 학생들의 수 N (2 ≤ N ≤ 500)과 두 학생 키를 비교한 횟수 M (0 ≤ M ≤ N(N-1)/2)이 주어진다.

다음 M개의 각 줄에는 두 학생의 키를 비교한 결과를 나타내는 N보다 작거나 같은 서로 다른 양의 정수 a와 b가 주어진다. 이는 번호가 a인 학생이 번호가 b인 학생보다 키가 작은 것을 의미한다.


**출력**

자신이 키가 몇 번째인지 알 수 있는 학생이 모두 몇 명인지를 출력한다.

---

> **문제 풀이**



---

> **코드**


```python
import sys

input = sys.stdin.readline

N, M = map(int, input().split())

graph = [[] for _ in range(N + 1)]
r_graph = [[] for _ in range(N + 1)]

for _ in range(M):
    s, e = map(int, input().split())
    graph[s].append(e)
    r_graph[e].append(s)


def dfs(start: int):
    visited = [False] * (N + 1)
    visited[start] = True
    # 정방향 탐색
    stack = [start]

    while stack:
        next_ = stack.pop()
        for node in graph[next_]:
            if not visited[node]:
                visited[node] = True
                stack.append(node)

    # 역방향 탐색
    stack = [start]
    
    while stack:
        next_ = stack.pop()
        for node in r_graph[next_]:
            if not visited[node]:
                visited[node] = True
                stack.append(node)

    return 1 if all(visited[1:]) else 0


answer = 0
for i in range(1, N + 1):
    answer += dfs(i)

print(answer)
```

---

> **후기**

처음에는 출발점을 DFS를 통해 찾았는데, 그냥 맵을 탐색하면서 4면을 확인하는 방식으로 최적화 하였음.

문제 풀이 시간 : 30분 / 실행시간 : `332ms` / 메모리 : `115424KB` / 코드길이 : `1337B`

알고리즘 분류 : 그래프 이론/탐색, 브루트포스, BFS