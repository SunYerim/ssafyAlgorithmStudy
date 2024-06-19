<h3 align="center"> 
    📢  [골드4] 백준(빙산) : https://www.acmicpc.net/problem/2573
</h3>

<br>

## 🚀 문제


---

## 🚦입출력 + 제한사항

- 행의 개수와 열의 개수를 나타내는 두 정수 N과 M이 한 개의 빈칸을 사이에 두고 주어진다. N과 M은 3 이상 300 이하이다.
- 배열에서 빙산이 차지하는 칸의 개수, 즉, 1 이상의 정수가 들어가는 칸의 개수는 10,000 개 이하이다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- bfs
- 처음엔 bfs를 두번(빙산 녹이기, 빙산 연결 체크) 해야하나 싶었는데, 풀이 세우다 보니 한번의 bfs안에 과정을 다 담을 수 있을듯하여 그렇게 진행
- bfs를 돌며, 4방 탐색하여 바다라면 1녹이고, 빙산이라면 queue에 추가해가며 연산
- 그룹이 두개 이상으로 나눠지거나, 그룹이 나눠지지 않고 모두 한번에 녹으면(이 경우 문제에선 답을 0으로) 종료

- [x] bfs

### 💻코드

```java
private static void bfs() {
	int curIceBurg = 0; // 이 bfs에서 순회한 빙산 개수
	
	int startX = 0;
	int startY = 0;
	for (Position ele : positions) {
		if(ele != null && isIceBerg[ele.x][ele.y]) {
			startX = ele.x;
			startY = ele.y;
			break;
		}
	}
	
	Queue<Position> queue = new LinkedList<>();
	
	queue.offer(new Position(startX, startY));
	visited[startX][startY] = true;
	curIceBurg++;
	
	while(!queue.isEmpty()) {
		Position curPosition = queue.poll();
		for(int i = 0; i < 4; i++) {
			int nr = curPosition.x + dx[i];
			int nc = curPosition.y + dy[i];
			if(nr>=0&&nr<r&&nc>=0&&nc<c&&map[nr][nc] == 0) { // 여기가 바다면, 현재 빙산을 1 녹인다.
				map[curPosition.x][curPosition.y]--;
				if(map[curPosition.x][curPosition.y] == 0) {
					map[curPosition.x][curPosition.y] = 99999;
					isIceBerg[curPosition.x][curPosition.y] = false; //만약 빙산이 다 녹았으면 바다로 바꿔주기
					removePosition.add(new Position(curPosition.x, curPosition.y)); // 삭제 빙산 리스트에 추가
					removeIceBerg++; // 삭제한 빙산의 개수
				}
			} 
			if(nr>=0&&nr<r&&nc>=0&&nc<c&&!visited[nr][nc]&&isIceBerg[nr][nc]) { // 여기가 빙산이면, queue에 추가
				queue.offer(new Position(nr, nc));
				visited[nr][nc] = true;
				curIceBurg++;
			}
		}
	}
	
	for (Position ele : removePosition) { // 삭제해야될 빙산들 삭제
		map[ele.x][ele.y]= 0; 
	}
	
	if(removePosition.size() == iceBergCnt) { // 한번에 다 녹았다 == 분리되지 못했다 == 0(문제 조건)
		time = 1;
		isOneGroup = true;
	}
	else if(iceBergCnt != curIceBurg) { // 모든 빙산을 탐색하지 않았다 == 분리 됐다
		isOneGroup = true;
	} else {
		iceBergCnt -= removeIceBerg;
	}
}
```

### 🙄 후기
소요시간 : 2시간  <br>
실력 너프 너무 먹었다.. <br>
사소하게 당연히 체크하던 것들 빼먹어서 못하는 경우가 태반.. 감을 올려놓자