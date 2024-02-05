# [Programmers][Level2] 프로세스 / (AC)

---

> **문제 설명**
>

[[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/42587)]
운영체제의 역할 중 하나는 컴퓨터 시스템의 자원을 효율적으로 관리하는 것입니다. 이 문제에서는 운영체제가 다음 규칙에 따라 프로세스를 관리할 경우 특정 프로세스가 몇 번째로 실행되는지 알아내면 됩니다.

1. 실행 대기 큐(Queue)에서 대기중인 프로세스 하나를 꺼냅니다.
2. 큐에 대기중인 프로세스 중 우선순위가 더 높은 프로세스가 있다면 방금 꺼낸 프로세스를 다시 큐에 넣습니다.
3. 만약 그런 프로세스가 없다면 방금 꺼낸 프로세스를 실행합니다.
   3.1 한 번 실행한 프로세스는 다시 큐에 넣지 않고 그대로 종료됩니다.
   예를 들어 프로세스 4개 [A, B, C, D]가 순서대로 실행 대기 큐에 들어있고, 우선순위가 [2, 1, 3, 2]라면 [C, D, A, B] 순으로 실행하게 됩니다.

현재 실행 대기 큐(Queue)에 있는 프로세스의 중요도가 순서대로 담긴 배열 priorities와, 몇 번째로 실행되는지 알고싶은 프로세스의 위치를 알려주는 location이 매개변수로 주어질 때, 해당 프로세스가 몇 번째로 실행되는지 return 하도록 solution 함수를 작성해주세요.

---
> **제한사항**

- priorities의 길이는 1 이상 100 이하입니다.
- priorities의 원소는 1 이상 9 이하의 정수입니다.
- priorities의 원소는 우선순위를 나타내며 숫자가 클 수록 우선순위가 높습니다.
- location은 0 이상 (대기 큐에 있는 프로세스 수 - 1) 이하의 값을 가집니다.
- priorities의 가장 앞에 있으면 0, 두 번째에 있으면 1 … 과 같이 표현합니다.

### 입력<br>

### 출력<br>

---

> **후기**

- 첫번째 접근 시도 방법 -> priorityQueue사용해서 compareTo 오버라이드해서 코드를 짰으나,
  - 우선순위가 동일한 것을 또 정렬 기준 세우는 것이 안된다는 것을 알게됨.
  ```java
    public class Node implements Comparable<Node> {
        int position;
        int priority;
        
        public Node(int position, int priority) {
            this.position = position;
            this.priority = priority;
        }
        
        @Override
        public int compareTo(Node o) {
            return o.priority - this.priority;
        }
    }
- 두 번째 접근 시도 방법 -> Queue와 함께 사용해보자
  - PriorityQueue는 우선순위에 따라 요소를 정렬하지만, 동일한 우선순위를 가진 요소들 사이의 순서는 보장하지 않는다.
  - Queue를 함께 사용하여 이를 해결하였음.
  - PriorityQueue를 사용하여 가장 높은 우선순위를 가진 요소를 찾고, 그 요소의 위치를 기준으로 Queue에서 해당 요소 이전의 요소들을 뒤로 보내는 방법으로 해결!
- 우선순위 큐를 오랜만에 건들여봤는데 다시 복기할 수 있어서 좋았고! 정렬기준 세울 수 없는 것도 잊고있었는데 다시 복기할 수 있었다
--- 
> **간단한 설명**
- Queue에서 요소를 하나씩 꺼내면서 그 요소의 우선순위가 현재 PriorityQueue의 가장 높은 우선순위와 같다면 출력, 그렇지 않다면 다시 Queue의 뒤로 보내는 방식

