
[백준][G5] 1548 스티커 붙이기 / (AC)
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

expr = [*sys.stdin.readline().rstrip()]
answer = []
stack = []

for e in expr:
    if e.isalpha():
        answer.append(e)
    else:
        if e == '(':
            stack.append(e)
        elif e == ')':
            while stack and stack[-1] != '(':
                answer += stack.pop()
            stack.pop()
        elif e == '*' or e == '/':
            while stack and (stack[-1] == '*' or stack[-1] == '/'):
                answer += stack.pop()
            stack.append(e)
        elif e == '+' or e == '-':
            while stack and stack[-1] != '(':
                answer += stack.pop()
            stack.append(e)

answer += reversed(stack)

print(''.join(answer))
```

---

> **후기**

괄호가 있는 중위 표현식을 후위 표현식으로 전환하는 문제는 처음해보는 유형이라, 괄호 속 괄호를 어떻게 처리할 지를 많이 고민했었다. 시행착오 끝에 조건을 잘 정리해보니 위의 규칙만 잘 따르면 `올바른 식`에서는 잘 작동한다. (해당 문제의 조건이 항상 올바른 식만 입력되니 더 이상의 예외를 고려할 필요는 없지만, 한 번쯤 정리해두면 좋을 것 같다.)


P.S. `str`타입은 immutable한 객체라서 한 글자씩 리스트에 담아 처리하는 것이 효율적이다.

문제 풀이 시간 : 1시간 / 실행시간 : `116ms` / 메모리 : `108080KB` / 코드길이 : `931B`

알고리즘 분류 : 스택, 문자열