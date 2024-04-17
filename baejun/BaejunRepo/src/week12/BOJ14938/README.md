<h3 align="center"> 
    📢  [골드4] 백준(서강 그라운드) : https://www.acmicpc.net/problem/14938
</h3>

<br>

## 🚀 문제

---

## 🚦입출력 + 제한사항

- 첫째 줄에는 지역의 개수 n (1 ≤ n ≤ 100)과 예은이의 수색범위 m (1 ≤ m ≤ 15), 길의 개수 r (1 ≤ r ≤ 100)이 주어진다.
- 둘째 줄에는 n개의 숫자가 차례대로 각 구역에 있는 아이템의 수 t (1 ≤ t ≤ 30)를 알려준다.
- 세 번째 줄부터 r+2번째 줄 까지 길 양 끝에 존재하는 지역의 번호 a, b, 그리고 길의 길이 l (1 ≤ l ≤ 15)가 주어진다.
- 지역의 번호는 1이상 n이하의 정수이다. 두 지역의 번호가 같은 경우는 없다.
- 예은이가 얻을 수 있는 최대 아이템 개수를 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 각 지점을 출발지로 선정하여서 다익스트라 돌리고, 갈수있는 애들의 item value를 합해서 max값 비교

- [x] Dijkstra

### 💻코드

```java
private static void dijkstra(int start) {
	dist = new int[N];
	Arrays.fill(dist, Integer.MAX_VALUE);
	PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
	
	dist[start] = 0;
	queue.offer(new Node(start, 0));
	
	while(!queue.isEmpty()) {
		Node curNode = queue.poll();
		
		if(dist[curNode.idx] < curNode.weight) continue;
		
		for(int i = 0; i < map.get(curNode.idx).size(); i++) {
			Node ntxNode = map.get(curNode.idx).get(i);
			if(dist[ntxNode.idx] > ntxNode.weight + curNode.weight) {
				dist[ntxNode.idx] = ntxNode.weight + curNode.weight;
				queue.offer(new Node(ntxNode.idx, dist[ntxNode.idx]));
			}
		}
	}
}
```

### 🙄 후기
소요시간 : 20분  <br>
자율이랑 선정을 계속 다익스트라로 정한 이유가 <br>
해당 유형을 계속 파바바박 구현하지 못하고 삐걱 댔었고, 한번에 파악하지 못해서 연습하고자 다익스트라로 선정했었는데 <br>
드디어 좀 보자마자 파바바ㅂ바바바ㅏ바박 구현이 되는 것 같아 뿌듯하다 ㅎㅋㅎㅋ