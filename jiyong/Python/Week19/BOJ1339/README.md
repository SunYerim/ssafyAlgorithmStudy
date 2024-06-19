[백준][G4] 단어 수학 / (AC)
---
> **문제 설명**
>
[[문제 링크](https://www.acmicpc.net/problem/1339)]

민식이는 수학학원에서 단어 수학 문제를 푸는 숙제를 받았다.

단어 수학 문제는 N개의 단어로 이루어져 있으며, 각 단어는 알파벳 대문자로만 이루어져 있다. 이때, 각 알파벳 대문자를 0부터 9까지의 숫자 중 하나로 바꿔서 N개의 수를 합하는 문제이다. 같은 알파벳은 같은
숫자로 바꿔야 하며, 두 개 이상의 알파벳이 같은 숫자로 바뀌어지면 안 된다.

예를 들어, GCF + ACDEB를 계산한다고 할 때, A = 9, B = 4, C = 8, D = 6, E = 5, F = 3, G = 7로 결정한다면, 두 수의 합은 99437이 되어서 최대가 될 것이다.

N개의 단어가 주어졌을 때, 그 수의 합을 최대로 만드는 프로그램을 작성하시오.

---

> **제한사항**

**입력**

첫째 줄에 단어의 개수 N(1 ≤ N ≤ 10)이 주어진다. 둘째 줄부터 N개의 줄에 단어가 한 줄에 하나씩 주어진다. 단어는 알파벳 대문자로만 이루어져있다. 모든 단어에 포함되어 있는 알파벳은 최대 10개이고,
수의 최대 길이는 8이다. 서로 다른 문자는 서로 다른 숫자를 나타낸다.

**출력**

첫째 줄에 주어진 단어의 합의 최댓값을 출력한다.

---

> **문제 풀이**

가장 높은 자리수에 해당하는 알파벳부터 차례로 큰 수를 매겨서 그리디하고 풀면 된다.

---

> **코드**
>

```python
import sys

input = sys.stdin.readline

N = int(input())
words = [input().rstrip() for _ in range(N)]

dic = {}

for word in words:
    cnt = len(word) - 1
    for w in word:
        if w not in dic:
            dic[w] = pow(10, cnt)
        else:
            dic[w] += pow(10, cnt)
        cnt -= 1

dic = sorted(dic.values(), reverse=True)

result = 0
num = 9

for v in dic:
    result += v * num
    num -= 1

print(result)


```

---
