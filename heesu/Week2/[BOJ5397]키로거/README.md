# [Silver II] 키로거 - 5397 

[문제 링크](https://www.acmicpc.net/problem/5397) 

### 성능 요약

메모리: 54156 KB, 시간: 1880 ms

### 분류

자료 구조, 연결 리스트, 스택

### 제출 일자

2024년 1월 31일 16:20:59

### 문제 설명

<p>창영이는 강산이의 비밀번호를 훔치기 위해서 강산이가 사용하는 컴퓨터에 키로거를 설치했다. 며칠을 기다린 끝에 창영이는 강산이가 비밀번호 창에 입력하는 글자를 얻어냈다.</p>

<p>키로거는 사용자가 키보드를 누른 명령을 모두 기록한다. 따라서, 강산이가 비밀번호를 입력할 때, 화살표나 백스페이스를 입력해도 정확한 비밀번호를 알아낼 수 있다. </p>

<p>강산이가 비밀번호 창에서 입력한 키가 주어졌을 때, 강산이의 비밀번호를 알아내는 프로그램을 작성하시오. 강산이는 키보드로 입력한 키는 알파벳 대문자, 소문자, 숫자, 백스페이스, 화살표이다.</p>

### 입력 

 <p>첫째 줄에 테스트 케이스의 개수가 주어진다. 각 테스트 케이스는 한줄로 이루어져 있고, 강산이가 입력한 순서대로 길이가 L인 문자열이 주어진다. (1 ≤ L ≤ 1,000,000) 강산이가 백스페이스를 입력했다면, '-'가 주어진다. 이때 커서의 바로 앞에 글자가 존재한다면, 그 글자를 지운다. 화살표의 입력은 '<'와 '>'로 주어진다. 이때는 커서의 위치를 움직일 수 있다면, 왼쪽 또는 오른쪽으로 1만큼 움직인다. 나머지 문자는 비밀번호의 일부이다. 물론, 나중에 백스페이스를 통해서 지울 수는 있다. 만약 커서의 위치가 줄의 마지막이 아니라면, 커서 및 커서 오른쪽에 있는 모든 문자는 오른쪽으로 한 칸 이동한다.</p>

### 출력 

 <p>각 테스트 케이스에 대해서, 강산이의 비밀번호를 출력한다. 비밀번호의 길이는 항상 0보다 크다.</p>

### 접근 방법
처음에는 스택 하나 사용해서 인덱스 값을 가지고 커서 위치를 처리하려고 함. <br>
```python
import sys
t = int(sys.stdin.readline())
for _ in range(t):
    stack = []
    keys = sys.stdin.readline()
    index = 0
    for key in keys:
        if key == '-' and index > 0:
            del stack[index-1]
            index-=1
        elif key == ">":
            if index < len(stack):
                index += 1
        elif key == "<":
            if index > 0:
                index -= 1
        else:
            stack.insert(index, key)
            index+=1
    for i in stack:
        print(i, end="")
```
정답을 출력하기는 하는데 시간초과 이슈. 아무리 생각해도 시간 더 줄일 방법이 생각 안 나서 인터넷 검색

<br><br>

**해결 방법: 스택을 2개 사용!**
> 커서를 기준으로 좌측, 우측을 관리하는 스택을 각각 2개 관리해서 시간을 단축
```Python
import sys
input = sys.stdin.readline
n = int(input())

for _ in range(n):
    keys = list(input().strip())
    left, right = [], []
    for key in keys:
        if key == '-':
            if left:
                left.pop()
        elif key == '>':
            if right:
                left.append(right.pop())
        elif key == '<':
            if left:
                right.append(left.pop())
        else:
            left.append(key)
    for i in left+right[::-1]:
        print(i, end="")
    print()
```


### 배운 점!
커서를 기준으로 스택을 2개 사용하는 건 생각도 못 해봤다. 아는 만큼 보인다니,, 한참을 헤맸다<br>
다양한 문제를 풀어보면서 다양한 접근 방법을 생각하는 힘을 길러야겠다!!