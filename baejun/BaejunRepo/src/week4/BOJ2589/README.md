<h3 align="center"> 
    📢  [골드5] 백준(보물섬) : https://www.acmicpc.net/problem/2589
</h3>

<br>

## 🚀 문제

보물섬 지도를 발견한 후크 선장은 보물을 찾아나섰다. 보물섬 지도는 아래 그림과 같이 직사각형 모양이며 여러 칸으로 나뉘어져 있다. 각 칸은 육지(L)나 바다(W)로 표시되어 있다. 이 지도에서 이동은 상하좌우로 이웃한 육지로만 가능하며, 한 칸 이동하는데 한 시간이 걸린다. 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다. 육지를 나타내는 두 곳 사이를 최단 거리로 이동하려면 같은 곳을 두 번 이상 지나가거나, 멀리 돌아가서는 안 된다.

예를 들어 위와 같이 지도가 주어졌다면 보물은 아래 표시된 두 곳에 묻혀 있게 되고, 이 둘 사이의 최단 거리로 이동하는 시간은 8시간이 된다.

보물 지도가 주어질 때, 보물이 묻혀 있는 두 곳 간의 최단 거리로 이동하는 시간을 구하는 프로그램을 작성하시오.


---

## 🚦입출력 + 제한사항

- 첫째 줄에는 보물 지도의 세로의 크기와 가로의 크기가 빈칸을 사이에 두고 주어진다. 이어 L과 W로 표시된 보물 지도가 아래의 예와 같이 주어지며, 각 문자 사이에는 빈 칸이 없다. 보물 지도의 가로, 세로의 크기는 각각 50이하이다.
- 첫째 줄에 보물이 묻혀 있는 두 곳 사이를 최단 거리로 이동하는 시간을 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 아니 최단거리인거 보고 BFS인줄은 알았는데, 4방탐색에 가는길 여러개일때 BFS처리를 어떻게..? 하면서
- 일단 DFS로 풀어봄, 답은 잘 나오는데 2% 시간초과 컷 당함
- 이건 내가 경험 못해본거다 싶어서 상범이한테 물어봄
- BFS진행할때 QUEUE에 cost를 같이 달아주면 된다는 아이디어 획득!
- 그러고 품

- [x] BFS를 이용하여 최단거리 찾기

### 💻코드

```java
/* 전체 탐색해서 L만날때마다 BFS 돌려줌 */
for (int i = 0; i < N; i++) {
	for(int j = 0; j < M; j++) {
		if(treasureIsland[i][j] == 'L') {
			visited = new boolean[N][M];
			currentDistance = 0;
			queue = new LinkedList<>();
			bfs(i, j);
			if(max < currentDistance) max = currentDistance;
		}
	}
}

private static void bfs(int row, int col) {
	visited[row][col] = true;
	int cmax=Integer.MIN_VALUE;
	queue.add(new nodelist(row, col, 0));
	while(!queue.isEmpty()) {
		nodelist get = queue.poll();
		for(int i = 0; i < 4; i++) {
			int nr = get.x + dx[i];
			int nc = get.y + dy[i];
			int c = get.count;
			if(nr>=0&&nr<treasureIsland.length&&nc>=0&&nc<treasureIsland[0].length&&!visited[nr][nc]&&treasureIsland[nr][nc]=='L') {
				visited[nr][nc] = true;
				cmax=Math.max(c+1, cmax);
				queue.add(new nodelist(nr, nc, c+1));
			}
		}
	}
	currentDistance = cmax;
}
```

### 🙄 후기
소요시간 : 2시간  <br>
역시.. 안 겪어본 유형은 죽어도 못 떠올린다 <br>
이제 한번 겪어봤고, 이런 유형 많이 나오니까 다음에 보면 패줄께 <br>
그리고 번외로, 직사각형인데, nc 길이 체크할때 nr길이 기준으로 체크했다가, outofbounds로 한 40분은 찾아헤맸다, 평소에 직사각형 배열 잘 챙겼으면서.. 자나꺠나 불조심하자
