<h3 align="center"> 
    📢  [골드5] 백준(탑) : https://www.acmicpc.net/problem/2493
</h3>

<br>

## 🚀 문제

KOI 통신연구소는 레이저를 이용한 새로운 비밀 통신 시스템 개발을 위한 실험을 하고 있다. 실험을 위하여 일직선 위에 N개의 높이가 서로 다른 탑을 수평 직선의 왼쪽부터 오른쪽 방향으로 차례로 세우고, 각 탑의 꼭대기에 레이저 송신기를 설치하였다. 모든 탑의 레이저 송신기는 레이저 신호를 지표면과 평행하게 수평 직선의 왼쪽 방향으로 발사하고, 탑의 기둥 모두에는 레이저 신호를 수신하는 장치가 설치되어 있다. 하나의 탑에서 발사된 레이저 신호는 가장 먼저 만나는 단 하나의 탑에서만 수신이 가능하다. 
예를 들어 높이가 6, 9, 5, 7, 4인 다섯 개의 탑이 수평 직선에 일렬로 서 있고, 모든 탑에서는 주어진 탑 순서의 반대 방향(왼쪽 방향)으로 동시에 레이저 신호를 발사한다고 하자. 그러면, 높이가 4인 다섯 번째 탑에서 발사한 레이저 신호는 높이가 7인 네 번째 탑이 수신을 하고, 높이가 7인 네 번째 탑의 신호는 높이가 9인 두 번째 탑이, 높이가 5인 세 번째 탑의 신호도 높이가 9인 두 번째 탑이 수신을 한다. 높이가 9인 두 번째 탑과 높이가 6인 첫 번째 탑이 보낸 레이저 신호는 어떤 탑에서도 수신을 하지 못한다.
탑들의 개수 N과 탑들의 높이가 주어질 때, 각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지를 알아내는 프로그램을 작성하라. 

---

## 🚦입출력 + 제한사항

- 첫째 줄에 탑의 수를 나타내는 정수 N이 주어진다. N은 1 이상 500,000 이하이다. 둘째 줄에는 N개의 탑들의 높이가 직선상에 놓인 순서대로 하나의 빈칸을 사이에 두고 주어진다. 탑들의 높이는 1 이상 100,000,000 이하의 정수이다.
- 첫째 줄에 주어진 탑들의 순서대로 각각의 탑들에서 발사한 레이저 신호를 수신한 탑들의 번호를 하나의 빈칸을 사이에 두고 출력한다. 만약 레이저 신호를 수신하는 탑이 존재하지 않으면 0을 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- stack(LIFO 연산)을 이용해서 뒤에서 부터 연산함. 왼쪽방향으로 높거나 같은 가장 가까운 탑이기때문에
- key, value식의 저장이 필요한데 Node 클래스로 구현함

- [x] Deque를 사용하여 LIFO연산 수행
- [x] Node클래스를 사용하여 index, number pair 저장

### 💻코드

```java
/* Node클래스로 index, value pair 저장 */
class Node {
	int index;
	int number;
	public Node(int index, int number) {
		this.index = index;
		this.number = number;
	}
}

Deque<Node> deque = new ArrayDeque<Node>();
/* deque에 index와 value로 삽입 (Node 클래스), 뒤에서부터 탐색 */
for(int i = koiTop.length - 1; i >= 0; i--) {
	if(deque.isEmpty()) { // 비어있으면 스택에 삽입
		deque.addLast(new Node(i+1, koiTop[i]));
	} else { // 현재 탑의 value가 더 크면 걔가 수신탑이 됨
		while(!deque.isEmpty() && deque.peekLast().number <= koiTop[i]) {
			answer[deque.pollLast().index-1] = i+1;
		}
		deque.addLast(new Node(i+1, koiTop[i]));
	}
}
```

### 🙄 후기
소요시간 : 1시간30분  <br>
![image](https://github.com/SunYerim/ssafyAlgorithmStudy/assets/78029066/60a7e24c-e358-4ecf-9768-2bb2e3fc182a)

맞혔습니다는 뜨는데 시간이 막 3200ms 이러길래, 혹시 stack이랑 deque의 차이인가 싶어 해보니 그건 아니였다(물론 deque가 더 빠르긴했음) <br>
근데 3200ms -> 750ms로 줄어든건 sysout이랑 stringbuilder 차이 하나뿐이였다 <br>
문제의 표본이 크면 클수록 출력 오버헤드 차이가 엄청나지는듯 하다. 그냥 sb로 구현하는걸 습관화하자 !!
