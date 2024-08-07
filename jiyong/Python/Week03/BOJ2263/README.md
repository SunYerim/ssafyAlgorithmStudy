
[백준][G1] 2263 트리의 순회 / (AC)
---
> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/2263)]

n개의 정점을 갖는 이진 트리의 정점에 1부터 n까지의 번호가 중복 없이 매겨져 있다. 이와 같은 이진 트리의 인오더와 포스트오더가 주어졌을 때, 프리오더를 구하는 프로그램을 작성하시오.


---

> **제한사항**
> 

**입력**

첫째 줄에 n(1 ≤ n ≤ 100,000)이 주어진다. 다음 줄에는 인오더를 나타내는 n개의 자연수가 주어지고, 그 다음 줄에는 같은 식으로 포스트오더가 주어진다.

**출력**

첫째 줄에 프리오더를 출력한다.



---

> **문제 풀이**

해당 문제는 트리의 순회법마다의 특성을 이용하여 문제를 `분할 정복`하여 풀 수 있다. 먼저 트리 순회의 특성을 알아보자.

전위 순회(Preorder) : 루트 노드를 기준으로 `루트 노드` - `왼쪽 서브트리` - `오른쪽 서브트리` 순으로 탐색한다.

중위 순회(Inorder) : 루트 노드를 기준으로 `왼쪽 서브트리` - `루트 노드` - `오른쪽 서브트리` 순으로 탐색한다.

후위 순회(Postorder) : 루트 노드를 기준으로 `왼쪽 서브트리` - `오른쪽 서브트리` - `루트 노드` 순으로 탐색한다.

`후위 순회`에서 마지막 방문 노드는 `루트 노드`가 되므로, 이를 `중위 순회`에서 찾아 나누면, 각각 `중위 순회`의 `왼쪽 서브트리`와 `오른쪽 서브트리`를 얻을 수 있다. `중위 순회`의 `왼쪽 서브트리`와 `후위 순회`의 `왼쪽 서브트리`는 길이가 같으므로, `후위 순회`의 `서브트리`들도 각각 구할 수 있다.

여기서 구하고자 하는 것은 `전위 순회`의 방문 순서인데, `전위 순회`는 매 방문하는 트리의 `루트 노드`를 먼저 방문한다. 따라서, 트리의 순회 순서에서 `루트 노드`를 찾아 출력하고 그를 기점으로 `서브트리`로 `분할`하여 해결하면 된다.




---

> **코드**
> 

```python
import sys

input = sys.stdin.readline
n = int(input())
inorder = [*map(int, input().split())]
postorder = [*map(int, input().split())]

answer = []
stack = [[[0, len(inorder)], [0, len(postorder)]]]

pos = [0] * (n + 1)
for i in range(n):
    pos[inorder[i]] = i

while stack:
    in_idx, post_idx = stack.pop()
    root = postorder[post_idx[1] - 1]
    answer.append(root)
    root_idx = pos[root]

    left_in_idx = [in_idx[0], root_idx]
    right_in_idx = [root_idx + 1, in_idx[1]]
    left_post_idx = [post_idx[0], post_idx[0] + (left_in_idx[1] - left_in_idx[0])]
    right_post_idx = [post_idx[0] + (left_in_idx[1] - left_in_idx[0]), post_idx[1] - 1]

    if right_in_idx[0] < right_in_idx[1]:
        stack.append([right_in_idx, right_post_idx])
    if left_in_idx[0] < left_in_idx[1]:
        stack.append([left_in_idx, left_post_idx])

print(*answer, sep=' ')
```

---

> **후기**

`recursive` 하게 구현해야하지만 `재귀함수`로 구현한다면 최대 `100,000`번의 호출이 일어나기 때문에 `스택 오버플로우`가 발생한다는 점을 주의. 또한, `서브트리`를 나눌 때 직접 나누어 생성하면 공간복잡도가 $N^2$가 되기 때문에, 참조할 `트리 순회 순서`는 그대로 유지하고 추상적으로 `인덱스`를 이용하여 트리를 나누는 방식으로 구현하여야 MLE가 발생하지 않는다.  

문제 풀이 시간 : 2시간 / 실행시간 : `204ms` / 메모리 : `136396KB` / 코드길이 : `868B`

알고리즘 분류 : 트리, 분할정복, 재귀