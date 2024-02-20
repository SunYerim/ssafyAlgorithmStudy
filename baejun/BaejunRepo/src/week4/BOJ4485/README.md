<h3 align="center"> 
    📢  [골드4] 백준(녹색 옷 입은 애가 젤다지?) : https://www.acmicpc.net/problem/4485
</h3>

<br>

## 🚀 문제

젤다의 전설 게임에서 화폐의 단위는 루피(rupee)다. 그런데 간혹 '도둑루피'라 불리는 검정색 루피도 존재하는데, 이걸 획득하면 오히려 소지한 루피가 감소하게 된다!
링크가 잃을 수밖에 없는 최소 금액은 얼마일까?

---

## 🚦입출력 + 제한사항

- 입력은 여러 개의 테스트 케이스로 이루어져 있다.
-각 테스트 케이스의 첫째 줄에는 동굴의 크기를 나타내는 정수 N이 주어진다. (2 ≤ N ≤ 125) N = 0인 입력이 주어지면 전체 입력이 종료된다.
- 이어서 N개의 줄에 걸쳐 동굴의 각 칸에 있는 도둑루피의 크기가 공백으로 구분되어 차례대로 주어진다. 도둑루피의 크기가 k면 이 칸을 지나면 k루피를 잃는다는 뜻이다. 여기서 주어지는 모든 정수는 0 이상 9 이하인 한 자리 수다.
- 각 테스트 케이스마다 한 줄에 걸쳐 정답을 형식에 맞춰서 출력한다. 형식은 예제 출력을 참고하시오.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 응 다익스트라

- [x] 다익스트라 알고리즘을 사용하여 최단거리 계산

### 💻코드

```java
PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

queue.offer(new Node(0, 0, array[0][0]));
dist[0] = array[0][0];

while(!queue.isEmpty()) {
	Node curNode = queue.poll();

	if(dist[curNode.x*N+curNode.y] < curNode.cost) continue;

	for(int i = 0; i < graph.get(curNode.x*N+curNode.y).size(); i++) {
		Node ntxNode = graph.get(curNode.x*N+curNode.y).get(i);
		if(dist[ntxNode.x*N+ntxNode.y] > curNode.cost + ntxNode.cost) {
			dist[ntxNode.x*N+ntxNode.y] = curNode.cost + ntxNode.cost;
			queue.offer(new Node(ntxNode.x, ntxNode.y, dist[ntxNode.x*N+ntxNode.y]));
		}
	}
}
```

### 🙄 후기
소요시간 : 50분  <br>
아효효 다익스트라 패턴 달달달달다랃ㄹ 할때까지 레스고 <br>