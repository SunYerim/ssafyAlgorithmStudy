<h3 align="center"> 
    📢  [실버4] 백준(덱(AC)) : https://www.acmicpc.net/problem/10866
</h3>

<br>

## 🚀 문제

정수를 저장하는 덱(Deque)를 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.
명령은 총 여덟 가지이다.
- push_front X: 정수 X를 덱의 앞에 넣는다.
- push_back X: 정수 X를 덱의 뒤에 넣는다.
- pop_front: 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
- pop_back: 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
- size: 덱에 들어있는 정수의 개수를 출력한다.
- empty: 덱이 비어있으면 1을, 아니면 0을 출력한다.
- front: 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
- back: 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다. 둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다. 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다. 문제에 나와있지 않은 명령이 주어지는 경우는 없다.
- 출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 잦은 삽입삭제 연산이므로 Linked List를 이용하여 구현
- 총 8가지의 케이스가 있으므로 각 동작에 대한 메서드 구현 및 switch case로 기능별 동작

- [x] Linked List 내장 메서드를 이용하여 동작 구현

### 💻코드

```java
private static LinkedList<Integer> deque = new LinkedList<>(); // 잦은 삽입,삭제라 링크드 리스트로 구현

private static void push_front(int element) {
	deque.add(0, element);
}
private static void push_back(int element) {
	deque.add(element);
}
private static void pop_front() {
	int outNumber = (deque.isEmpty()) ? -1 : deque.pollFirst();
	System.out.println(outNumber);
}
private static void pop_back() {
	int outNumber = (deque.isEmpty()) ? -1 : deque.pollLast();
	System.out.println(outNumber);
}
private static void size() {
	System.out.println(deque.size());
}
private static void empty() {
	int outNumber = (deque.isEmpty()) ? 1 : 0;
	System.out.println(outNumber);
}
private static void front() {
	int outNumber = (deque.isEmpty()) ? -1 : deque.peekFirst();
	System.out.println(outNumber);
}
private static void back() {
	int outNumber = (deque.isEmpty()) ? -1 : deque.peekLast();
	System.out.println(outNumber);
}
```

### 🙄 후기
소요시간 : 30분 <br>

문제풀이 자체는 아주 쉬웠다. 다만 Deque 개념자체가 처음이고 생소한거다 보니 직접 구현하면서 한번 구조를 이해하는 시간이였다.<br>
쉽게 느껴지는 자료구조라도 직접 구현해보면 이해도가 확 달라지는 것 같긴하다. 다른 자료구조도 직접 해보자!