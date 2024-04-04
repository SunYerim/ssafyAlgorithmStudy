[프로그래머스][Lv.3] N으로 표현 / (AC)
---
> **문제 설명**
>
[[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/42895)]

아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.

12 = 5 + 5 + (5 / 5) + (5 / 5)
12 = 55 / 5 + 5 / 5
12 = (55 + 5) / 5

5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.


---

> **제한사항**
>

- N은 1 이상 9 이하입니다.
- number는 1 이상 32,000 이하입니다.
- 수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
- 최솟값이 8보다 크면 -1을 return 합니다.

---

> **문제 풀이**

어떠한 수 { `N`을 `k`번 사용하여 만들 수 있는 수 }는 { `k-i`번 사용하여 만들 수 있는 수 } 와 { `k-j`번 사용하여 만들 수 있는 수 } , 두 집합 간의 사칙연산을 통해 나올 수 있는 모든 수와 N을 k번 이어 붙인 수라는 공식을 이용하여 중복 부분 문제로 풀면 된다.(단, `i + j = k`) 

---

> **코드**
>

```python
def solution(N, number):
    if number == 1:
        return 1
    set_list = []

    for cnt in range(1, 9):
        partial_set = set()
        partial_set.add(int(str(N) * cnt))
        for i in range(cnt - 1):
            for op1 in set_list[i]:
                for op2 in set_list[-i - 1]:
                    partial_set.add(op1 + op2)
                    partial_set.add(op1 * op2)
                    partial_set.add(op1 - op2)
                    if op2 != 0:
                        partial_set.add(op1 / op2)
        if number in partial_set:
            return cnt
        set_list.append(partial_set)
    return -1

```

---

> **후기**

N을 을 최대 8번까지 사용하는 경우까지만 보면 된다는 힌트를 잘 이용 해야한다.

문제 풀이 시간 : ??분 (결국 풀이를 봤다..)

알고리즘 분류 : 다이나믹 프로그래밍
