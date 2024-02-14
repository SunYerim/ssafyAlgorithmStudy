
[백준][G5] 1548 부분 삼각 수열 / (AC)
---
> **문제 설명**
> 
[[문제 링크](https://www.acmicpc.net/problem/1548)]

세 수 x, y, z가 x+y>z, x+z>y, y+z>x의 관계를 만족하면, 세 수는 삼각관계에 있다고 한다.

마찬가지로 길이가 N인 수열 B(b[0], b[1], ..., b[n-1])의 모든 b[i], b[j], b[k]가 삼각관계에 있으면 이 수열은 삼각 수열이라고 한다. 이때, i, j, k는 모두 다른 값이다.

수열 A가 주어졌을 때, 이 수열에서 적절히 몇 개의 원소를 빼서 이 수열을 삼각 수열로 만들려고 한다. 삼각 수열의 최대 길이를 구하는 프로그램을 작성하시오.



---

> **제한사항**
> 

**입력**

첫째 줄에 수열의 크기 N이 주어진다. 둘째 줄에 수열 A에 들어있는 수가 공백을 사이에 두고 주어진다. N은 최대 50이고, A에 들어있는 수는 $10^9$보다 작거나 같은 자연수이다. 

**출력**

첫째 줄에 가장 긴 부분 삼각 수열의 길이를 출력한다.

---

> **문제 풀이**



---

> **코드**
> 

```python
import sys

N = int(sys.stdin.readline().rstrip())
seq = [*map(int, sys.stdin.readline().rstrip().split())]
seq.sort()

length = 2

if len(seq) < 3:
    print(len(seq))
else:
    lp, rp = 0, 2
    while rp < len(seq):
        if seq[lp + 1] - seq[lp] < seq[rp] < seq[lp + 1] + seq[lp]:
            length = max(length, rp - lp + 1)
        else:
            lp += 1
        rp += 1
    print(length)
```

---

> **후기**




문제 풀이 시간 : 30분 / 실행시간 : `108ms` / 메모리 : `108080KB` / 코드길이 : `399B`

알고리즘 분류 : 그리디, 정렬, 브루트포스