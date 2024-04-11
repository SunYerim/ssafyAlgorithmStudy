<h3 align="center"> 
    📢  [골드4] 백준(최단 경로) : https://www.acmicpc.net/problem/1753
</h3>

<br>

## 🚀 문제

방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오. 단, 모든 간선의 가중치는 10 이하의 자연수이다.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다. (1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000) 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다. 둘째 줄에는 시작 정점의 번호 K(1 ≤ K ≤ V)가 주어진다. 셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다. 이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다. u와 v는 서로 다르며 w는 10 이하의 자연수이다. 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.
- 첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다. 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 다잌스트라

- [x] 우선순위큐를 활용한 다잌스트라

### 💻코드

```java
private static void dijkstra(int start2) {
	dist = new int[V+1];
	Arrays.fill(dist, Integer.MAX_VALUE);
	dist[start] = 0;
	PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
	queue.offer(new Node(start, 0));
	
	while(!queue.isEmpty()) {
		Node curNode = queue.poll();
		
		if(curNode.cost > dist[curNode.idx]) continue;
		for(int i = 0; i < list.get(curNode.idx).size(); i++) {
			Node ntxNode = list.get(curNode.idx).get(i);
			
			if(dist[ntxNode.idx] > ntxNode.cost + curNode.cost) {
				dist[ntxNode.idx] = ntxNode.cost + curNode.cost;
				queue.offer(new Node(ntxNode.idx, dist[ntxNode.idx]));
			}
		}
	}
}
```

### 🙄 후기
소요시간 : 30분 <br>
다잌스트라랑 친해지는 중 ~