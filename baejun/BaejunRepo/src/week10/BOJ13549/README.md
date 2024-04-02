<h3 align="center"> 
    📢  [골드5] 백준(숨바꼭질3) : https://www.acmicpc.net/problem/13549
</h3>

<br>

## 🚀 문제

수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다.
수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

---

## 🚦입출력 + 제한사항

- 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
- 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 다잌스트라

- [x] 우선순위큐를 활용한 다잌스트라

### 💻코드

```java
int[] dist = new int[100001];
Arrays.fill(dist, 100000);
PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
queue.offer(new Node(start, 0));
dist[start] = 0;

while(!queue.isEmpty()) {
	Node curNode = queue.poll();
	
	if(dist[curNode.idx] < curNode.cost) { // 더 비싼경우 = 의미없음
		continue;
	}
	
	for(int i = 0; i < graph.get(curNode.idx).size(); i++) { // 연결된 친구들의 최소값 갱신
		Node ntxNode = graph.get(curNode.idx).get(i);
		
		if(dist[ntxNode.idx] > ntxNode.cost + curNode.cost) {
			dist[ntxNode.idx] = ntxNode.cost + curNode.cost;
			queue.add(new Node(ntxNode.idx, dist[ntxNode.idx]));
		}
	}
}
```

### 🙄 후기
소요시간 : 1시간 반 <br>
다잌스트라는 초기화 하는 과정이 암만해도 눈에 익지가 않는다.. <br>
0-1BFS라는 새로운 유형도 알게되었다!