[백준][P5] 1321 군인 / (AC)
---
> **문제 설명**
>

[[문제 링크](https://www.acmicpc.net/problem/1321)]

캠프 내내 그랬듯이, 여전히 옆 나라와의 전쟁이 한창이다.

전쟁에는 N개의 부대가 투입되었는데, 전쟁이 장기전이 되다 보니 군사의 적절한 배치를 위해 각 부대에 군인이 늘어나기도 하고 줄어들기도 하고 있다.

행정의 편의를 위해 각 군인들에겐 번호가 붙어 있는데, 군인들은 1번 부대부터 군번순서대로 차례차례 배치된다. 예를 들어 1번 부대에 4명, 2번 부대에 3명, 3번 부대에 7명의 군인이 있다면 군번이 6번인 군인은
2번 부대에 배치되게 된다.

문제는 어떤 부대의 인원이 늘어나거나 줄어들었을 때 i번 군인이 어디에 배치되는지 인데, 이럴 때에는 군인도 군번도 처음부터 다시 배치하게 된다. 위의 예에서와 같이 1번 부대에 4명, 2번 부대에 3명, 3번
부대에 7명의 군인이 있었는데, 1번 부대에서 3명의 감원이 일어난다면, 6번 군인은 3번 부대에 재배치 받게 된다.

전쟁 때는 부대의 감원과 증원이 많아 군사 재배치도 자주 일어나게 되는데, 이렇게 자주 배치가 바뀌자 군인들은 자기가 도대체 어떤 부대에 속하는 지 헷갈리게 되었다. 다행히도 바뀐 군번은 다들 정확하게 숙지하고
있다.

부대의 개수 N과 각 부대에 속해 있는 군인의 수가 N개 주어질 때, 부대의 감원과 증원을 한 후, 혹은 그 중에 군번 i번의 군인이 몇 번 부대에 속하는 지를 물어봤을 때, 그 질문에 대답을 해 줄 수 있는
프로그램을 작성하시오.

i번 부대에 증원이나 감원을 할 때엔 "1 i a"의 형태로 명령이 주어지고, 이는 i번 부대에 a명을 더한다는 뜻이다. 감원을 할 때엔 a가 0보다 작은 수로 주어진다. 감원을 해서 부대의 인원수가 0보다 작아지는
입력은 들어오지 않는다. a는 절댓값이 1보다 크거나 같고, 3,000보다 작거나 같은 정수이다.

군번 i번의 군인이 어떤 부대에 배치 받았는지 알고 싶을 때는 "2 i"의 형태로 명령이 주어지고, 이런 명령을 받았을 때는 i번 군인이 몇 번 부대에 배치 받았는지를 출력해야 한다. i는 전체 군인 수보다 작거나
같은 자연수이다.


---

> **제한사항**

**입력**

첫째 줄에 부대의 개수 N(1 ≤ N ≤ 500,000)이 주어지고, 이어서 각 부대의 군사 수를 나타내는 정수가 N개 주어진다. 각 부대의 군사 수는 1000보다 작거나 같은 자연수이다. 그 다음 줄에 명령의 개수
M(1 ≤ M ≤ 10,000)개가 주어지고, 이어서 M줄에 걸쳐 명령이 주어진다.

**출력**

질문한 군인이 몇 번 부대에 배치 받았는지를 한 줄에 하나씩 출력한다.



---

> **문제 풀이**

`i`번 군인이 `N`개의 부대 중 몇 번째에 속하는지를 찾는 문제이다. `1`번부터 번호순 대로 각 부대 정원에 맞게 배치되므로, 이는 `누적합`을 통해서 쉽게 구할 수 있다. 하지만, 누적합의 경우 하나의 원소가
변경되면, 그를 뒤따르는 모든 원소의 누적합도 함께 바뀌어야 해서 `O(n)`만큼 소요된다.

따라서 누적합 배열을 이용한다면, 입력 제한이 `500,000`이기 때문에 최대 `10,000`번 업데이트가 일어난다면 TLE를 피할 수 없을 것이다.

`세그먼트 트리`를 사용하면 임의의 원소가 변경 되었을 때, `구간합`을 업데이트 하는 데에 `O(log n)`이 소요되므로 해당 문제에서는 `세그먼트 트리` 자료구조를 이용하여 구간합을 관리하고, 군인이 속한
부대를 검색할 때에는 `파라메트릭 서치`를 통해 해당 군인이 속한 구간을 찾는다.


---

> **코드**
>

```python
import sys
from math import ceil, log2

input = sys.stdin.readline

# 부대 정보 입력
N = int(input())
nums = [*map(int, input().split())]

seg_tree = [0] * 2 ** (ceil(log2(N)) + 1)


def segmentation(left, right, i=1):
    if left == right:
        seg_tree[i] = nums[left]
        return seg_tree[i]
    mid = (left + right) // 2
    seg_tree[i] = segmentation(left, mid, i * 2) + segmentation(mid + 1, right, i * 2 + 1)
    return seg_tree[i]


def search(start, end, left, right, i=1):
    if end < left or start > right:
        return 0

    if left <= start and end <= right:
        return seg_tree[i]

    mid = (start + end) // 2
    return search(start, mid, left, right, i * 2) + search(mid + 1, end, left, right, i * 2 + 1)


def update(start, end, idx, diff, i=1):
    if start > idx or idx > end:
        return
    seg_tree[i] += diff
    if start != end:
        mid = (start + end) // 2
        update(start, mid, idx, diff, i * 2)
        update(mid + 1, end, idx, diff, i * 2 + 1)


def binary_search(num):
    lp = 0
    rp = N - 1
    while lp <= rp:
        mid = (lp + rp) // 2
        result = search(0, N - 1, 0, mid)
        if result < num:
            lp = mid + 1
        else:
            rp = mid - 1
    return lp + 1


# 세그먼트 트리 초기화
segmentation(0, N - 1)

# 명령 입력
M = int(input())

for _ in range(M):
    query = input().split()
    if query[0] == '1':
        update(0, N - 1, int(query[1]) - 1, int(query[2]))
    else:
        print(binary_search(int(query[1])))
```

---

> **후기**

`세그먼트 트리`를 처음으로 적용해 보았던 문제다. 아직 익숙하지 않아서 `Top-down` 방식의 재귀함수로 구현했지만, 응용면이나 속도면에서 `Bottom-up`방식이 유리하기 때문에 이 또한 숙달 해야겠다.

문제 풀이 시간 : 2시간 / 실행시간 : `676ms` / 메모리 : `190232KB` / 코드길이 : `1531B`

알고리즘 분류 : 자료구조, 세그먼트 트리, 이분 탐색