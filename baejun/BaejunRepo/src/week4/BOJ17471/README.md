<h3 align="center"> 
    📢  [골드4] 백준(게리맨더링) : https://www.acmicpc.net/problem/17471
</h3>

<br>

## 🚀 문제

백준시의 시장 최백준은 지난 몇 년간 게리맨더링을 통해서 자신의 당에게 유리하게 선거구를 획정했다. 견제할 권력이 없어진 최백준은 권력을 매우 부당하게 행사했고, 심지어는 시의 이름도 백준시로 변경했다. 이번 선거에서는 최대한 공평하게 선거구를 획정하려고 한다.
백준시는 N개의 구역으로 나누어져 있고, 구역은 1번부터 N번까지 번호가 매겨져 있다. 구역을 두 개의 선거구로 나눠야 하고, 각 구역은 두 선거구 중 하나에 포함되어야 한다. 선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다. 구역 A에서 인접한 구역을 통해서 구역 B로 갈 수 있을 때, 두 구역은 연결되어 있다고 한다. 중간에 통하는 인접한 구역은 0개 이상이어야 하고, 모두 같은 선거구에 포함된 구역이어야 한다.
아래 그림은 6개의 구역이 있는 것이고, 인접한 구역은 선으로 연결되어 있다.
공평하게 선거구를 나누기 위해 두 선거구에 포함된 인구의 차이를 최소로 하려고 한다. 백준시의 정보가 주어졌을 때, 인구 차이의 최솟값을 구해보자.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 구역의 개수 N이 주어진다. 둘째 줄에 구역의 인구가 1번 구역부터 N번 구역까지 순서대로 주어진다. 인구는 공백으로 구분되어져 있다.
- 셋째 줄부터 N개의 줄에 각 구역과 인접한 구역의 정보가 주어진다. 각 정보의 첫 번째 정수는 그 구역과 인접한 구역의 수이고, 이후 인접한 구역의 번호가 주어진다. 모든 값은 정수로 구분되어져 있다.
- 구역 A가 구역 B와 인접하면 구역 B도 구역 A와 인접하다. 인접한 구역이 없을 수도 있다.
- 첫째 줄에 백준시를 두 선거구로 나누었을 때, 두 선거구의 인구 차이의 최솟값을 출력한다. 두 선거구로 나눌 수 없는 경우에는 -1을 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 하 .. 접근은 바로 쉽게 떠올렸음, 조합+완탐(d-bfs)
- 근데 bfs 구현을 똑바로 못해서 시간을 좀 헤맴

- [x] 조합으로 노드들을 나누고 나뉜 노드가 연결되어있는지 bfs로 확인

### 💻코드

```java
/* 그래프 인덱스를 기준으로 조합 */
private static void combination(int start, int r) {
	//기저조건
	if(r == 0) {
		ArrayList<Integer> pickNumber = new ArrayList<>();
		ArrayList<Integer> nonPickNumber = new ArrayList<>();
		for(int i = 1; i < N+1; i++) {
			if(visited[i]) {
				pickNumber.add(i);
			} else {
				nonPickNumber.add(i);
			}
		}

		if(bfs1(pickNumber) && bfs2(nonPickNumber)) {
			int pickCnt = 0;
			int nonPickCnt = 0;
			for(int e : pickNumber) {
				pickCnt += nodes[e].population;
			}
			for(int e : nonPickNumber) {
				nonPickCnt += nodes[e].population;
			}
			if(Math.abs(pickCnt - nonPickCnt) < minValue) minValue = Math.abs(pickCnt - nonPickCnt);
		}
		return;
	}
	//유도조건
	for(int i = start; i <= N; i++) {
		if(!visited[i]) {
			visited[i] = true;
			combination(i+1, r-1);
			visited[i] = false;
		}
	}
}

/* 조합 된 애들, 안된애들로 나눠서 bfs 진행*/
private static boolean bfs1(ArrayList<Integer> pickNumber) {
	if(pickNumber.size() == 1) return true;
	boolean[] visit = new boolean[N+1];
	int start = pickNumber.get(0);
	Queue<Integer> queue = new LinkedList<>();
	queue.offer(start);
	visit[start] = true;
	
	while(!queue.isEmpty()) {
		int node = queue.poll();
		for(int i = 0; i < nodes[node].list.size(); i++) {
			if(!visit[nodes[node].list.get(i)] && pickNumber.contains(nodes[node].list.get(i))) {
				visit[nodes[node].list.get(i)] = true;
				queue.offer(nodes[node].list.get(i));
			}
		}
	}
	
	for(int e : pickNumber) {
		if(!visit[e]) return false;
	}
	return true;
}

private static boolean bfs2(ArrayList<Integer> nonPickNumber) {
	if(nonPickNumber.size() == 1) return true;
	boolean[] visit = new boolean[N+1];
	int start = nonPickNumber.get(0);
	Queue<Integer> queue = new LinkedList<>();
	queue.offer(start);
	visit[start] = true;
	
	while(!queue.isEmpty()) {
		int node = queue.poll();
		for(int i = 0; i < nodes[node].list.size(); i++) {
			if(!visit[nodes[node].list.get(i)] && nonPickNumber.contains(nodes[node].list.get(i))) {
				visit[nodes[node].list.get(i)] = true;
				queue.offer(nodes[node].list.get(i));
			}
		}
	}
	
	for(int e : nonPickNumber) {
		if(!visit[e]) return false;
	}
	return true;
}
```

### 🙄 후기
소요시간 : 3시간  <br>
지금 거의 연속 3일쨰? <br>
접근 똑바로 해놓고, 그 접근법에 대한 구현 똑바로 못해서 한참헤매다 돌아오고있다. <br>
기본기가 부족하다~