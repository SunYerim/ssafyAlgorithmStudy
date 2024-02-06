<h3 align="center"> 
    📢  [실버2] 백준(너비 우선 탐색2) : https://www.acmicpc.net/problem/24445
</h3>

<br>

## 🚀 문제

오늘도 서준이는 너비 우선 탐색(BFS) 수업 조교를 하고 있다. 아빠가 수업한 내용을 학생들이 잘 이해했는지 문제를 통해서 확인해보자.
N개의 정점과 M개의 간선으로 구성된 무방향 그래프(undirected graph)가 주어진다. 정점 번호는 1번부터 N번이고 모든 간선의 가중치는 1이다. 정점 R에서 시작하여 너비 우선 탐색으로 노드를 방문할 경우 노드의 방문 순서를 출력하자.
너비 우선 탐색 의사 코드는 다음과 같다. 인접 정점은 내림차순으로 방문한다.
```
bfs(V, E, R) {  # V : 정점 집합, E : 간선 집합, R : 시작 정점
    for each v ∈ V - {R}
        visited[v] <- NO;
    visited[R] <- YES;  # 시작 정점 R을 방문 했다고 표시한다.
    enqueue(Q, R);  # 큐 맨 뒤에 시작 정점 R을 추가한다.
    while (Q ≠ ∅) {
        u <- dequeue(Q);  # 큐 맨 앞쪽의 요소를 삭제한다.
        for each v ∈ E(u)  # E(u) : 정점 u의 인접 정점 집합.(정점 번호를 내림차순으로 방문한다)
            if (visited[v] = NO) then {
                visited[v] <- YES;  # 정점 v를 방문 했다고 표시한다.
                enqueue(Q, v);  # 큐 맨 뒤에 정점 v를 추가한다.
            }
    }
}
```

---

## 🚦입출력 + 제한사항

- 첫째 줄에 정점의 수 N (5 ≤ N ≤ 100,000), 간선의 수 M (1 ≤ M ≤ 200,000), 시작 정점 R (1 ≤ R ≤ N)이 주어진다.
- 다음 M개 줄에 간선 정보 u v가 주어지며 정점 u와 정점 v의 가중치 1인 양방향 간선을 나타낸다. (1 ≤ u < v ≤ N, u ≠ v) 모든 간선의 (u, v) 쌍의 값은 서로 다르다.
- 첫째 줄부터 N개의 줄에 정수를 한 개씩 출력한다. i번째 줄에는 정점 i의 방문 순서를 출력한다. 시작 정점의 방문 순서는 1이다. 시작 정점에서 방문할 수 없는 경우 0을 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 문제 제목에서 대놓고 BFS를 주기때문에 접근법은 딱히 없다
- 다만 오름차순으로  정렬 후 접근해야하기 때문에, Map에 List를 삽입하여 입력값을 정리하고, 오름차순으로 접근한다

- [x] Queue를 사용하여 bfs 수행

### 💻코드

```java
/* bfs 풀이 */
private static void bfs(int R) {
	Queue<Integer> queue = new LinkedList<>();
	queue.offer(R);
	
	while(!queue.isEmpty()) {
		int key = queue.poll();
		if(!visited[key]) {
			visited[key] = true;
			order[key] = ++rank;
			if(map.containsKey(key)) {
				for (int e : map.get(key)) {
					if(!visited[e]) {
						queue.offer(e);
					}
				}
			}
		}
	}
}

/* map의 list(value) 오름차순 정렬 */
map.forEach((key, value) -> {
	Collections.sort(value);
});


map.forEach((key, value) -> {
	Collections.reverse(value);
});
```

### 🙄 후기
소요시간 : 1시간  <br>
bfs 처음 풀어보는데 재밌었음 ! 이번 주차 bfs dfs 나갈때 최대한 문제 많이 풀어서 아주 외우듯이 해버리자<br>
