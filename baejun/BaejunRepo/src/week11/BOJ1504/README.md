<h3 align="center"> 
    📢  [골드4] 백준(특정한 최단 경로) : https://www.acmicpc.net/problem/1504
</h3>

<br>

## 🚀 문제

방향성이 없는 그래프가 주어진다. 세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다. 또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.
세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다. 하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라. 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 정점의 개수 N과 간선의 개수 E가 주어진다. (2 ≤ N ≤ 800, 0 ≤ E ≤ 200,000) 둘째 줄부터 E개의 줄에 걸쳐서 세 개의 정수 a, b, c가 주어지는데, a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻이다. (1 ≤ c ≤ 1,000) 다음 줄에는 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호 v1과 v2가 주어진다. (v1 ≠ v2, v1 ≠ N, v2 ≠ 1) 임의의 두 정점 u와 v사이에는 간선이 최대 1개 존재한다.
- 첫째 줄에 두 개의 정점을 지나는 최단 경로의 길이를 출력한다. 그러한 경로가 없을 때에는 -1을 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 다잌스트라 2번 돌려서 start -> v1 -> v2 -> N 하나랑 start -> v2 -> v1 -> N을 비교

- [x] 우선순위큐를 활용한 다잌스트라

### 💻코드

```java
private static void djikstra(int start) {
	Arrays.fill(dist, Integer.MAX_VALUE);

	dist[start] = 0;
	PriorityQueue<Node> queue = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
	queue.offer(new Node(start, dist[start]));

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
소요시간 : 1시간 반 <br>
다잌스트라는 초기화 하는 과정이 암만해도 눈에 익지가 않는다.. <br>