
[백준][G5] 1068 트리 / (AC)
---
> **문제 설명**


[[문제 링크](https://www.acmicpc.net/problem/1068)]

트리에서 리프 노드란, 자식의 개수가 0인 노드를 말한다.

트리가 주어졌을 때, 노드 하나를 지울 것이다. 그 때, 남은 트리에서 리프 노드의 개수를 구하는 프로그램을 작성하시오. 노드를 지우면 그 노드와 노드의 모든 자손이 트리에서 제거된다.

예를 들어, 다음과 같은 트리가 있다고 하자.

<div align="center">
<img src="https://upload.acmicpc.net/560de878-d961-475e-ada4-e1f0774e5a84/-/preview/" height="150px">
</div>

현재 리프 노드의 개수는 3개이다. (초록색 색칠된 노드) 이때, 1번을 지우면, 다음과 같이 변한다. 검정색으로 색칠된 노드가 트리에서 제거된 노드이다.

<div align="center">
<img src="https://upload.acmicpc.net/d46ddf4e-1b82-44cc-8c90-12f76e5bf88f/-/preview/" height="150px">
</div>


이제 리프 노드의 개수는 1개이다.


---

> **제한사항** 



**입력**

첫째 줄에 트리의 노드의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄에는 0번 노드부터 N-1번 노드까지, 각 노드의 부모가 주어진다. 만약 부모가 없다면 (루트) -1이 주어진다. 셋째 줄에는 지울 노드의 번호가 주어진다.

**출력**

첫째 줄에 입력으로 주어진 트리에서 입력으로 주어진 노드를 지웠을 때, 리프 노드의 개수를 출력한다.



---

> **문제 풀이**

루트 노드부터 `깊이 우선 탐색`을 이용하여 리프 노드를 찾아 카운트 해주면 된다. 
입력 당시에 제거할 노드가 결정되기 때문에, 제거할 노드를 제외하고 인접리스트에 담아 탐색하였다.

주의할 점으로는 루트노드가 제거되는 경우, 트리 전체가 제거되므로 `0`을 리턴해야 한다.


---

> **코드**
> 

```python
import sys

N = int(sys.stdin.readline())
root = -1
count = 0
tree = [[] for _ in range(N)]
route = sys.stdin.readline().split()
cut = int(sys.stdin.readline())

for idx, parent in enumerate(map(int, route)):
    if idx == cut: continue
    if parent == -1:
        root = idx
    else:
        tree[parent].append(idx)


def dfs(node):
    global count
    if not tree[node]:
        count += 1
    else:
        for next_node in tree[node]:
            dfs(next_node)


if root == -1:
    count = 0
else:
    dfs(root)

print(count)
```

---

> **후기**

트리의 특성과 탐색법만 간단히 알면 풀 수 있는 전형적인 물골드 문제.

문제 풀이 시간 : 15분 / 실행시간 : `104ms` / 메모리 : `108080KB` / 코드길이 : `534B`

알고리즘 분류 : 트리, 깊이 우선 탐색, 그래프 이론/탐색