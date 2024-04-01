<h3 align="center"> 
    📢  [골드3] 백준(파티) : https://www.acmicpc.net/problem/1238
</h3>

<br>

## 🚀 문제

N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.
어느 날 이 N명의 학생이 X (1 ≤ X ≤ N)번 마을에 모여서 파티를 벌이기로 했다. 이 마을 사이에는 총 M개의 단방향 도로들이 있고 i번째 길을 지나는데 Ti(1 ≤ Ti ≤ 100)의 시간을 소비한다.
각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다. 하지만 이 학생들은 워낙 게을러서 최단 시간에 오고 가기를 원한다.
이 도로들은 단방향이기 때문에 아마 그들이 오고 가는 길이 다를지도 모른다. N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 10,000), X가 공백으로 구분되어 입력된다. 두 번째 줄부터 M+1번째 줄까지 i번째 도로의 시작점, 끝점, 그리고 이 도로를 지나는데 필요한 소요시간 Ti가 들어온다. 시작점과 끝점이 같은 도로는 없으며, 시작점과 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개이다.
- 모든 학생들은 집에서 X에 갈수 있고, X에서 집으로 돌아올 수 있는 데이터만 입력으로 주어진다.
- 첫 번째 줄에 N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 파티장으로 가는 최소경로와, 파티장에서 집으로 가는 최소경로 둘다 구해야함
- 역방향 어쩌구 생각났는데, 파티장 to 집 최소 경로랑, 집별로 집 to 파티장 반복문 돌려도 시간복잡도 충분하길래 그렇게 돌림

- [x] 우선순위큐를 활용한 다잌스트라

### 💻코드

```java
private static void dijkstra(int startIdx) {
	distForGoHome = new int[N+1];
	PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
	Arrays.fill(distForGoHome, Integer.MAX_VALUE);
	
	queue.offer(new Node(startIdx, 0));
	distForGoHome[startIdx] = 0;
	
	while(!queue.isEmpty()) {
		Node curNode = queue.poll();
		
		if(distForGoHome[curNode.idx] < curNode.cost) {
			continue;
		}
		
		for(int i = 0; i < list.get(curNode.idx).size(); i++) {
			Node ntxNode = list.get(curNode.idx).get(i);
			
			if(distForGoHome[ntxNode.idx] > ntxNode.cost + curNode.cost) {
				distForGoHome[ntxNode.idx] = ntxNode.cost + curNode.cost;
				queue.offer(new Node(ntxNode.idx, distForGoHome[ntxNode.idx]));
			}
		}
	}
}
```

### 🙄 후기
소요시간 : 40분 <br>
다익스트라 익힐려고 푼 문제 <br>