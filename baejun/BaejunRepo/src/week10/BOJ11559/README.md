<h3 align="center"> 
    📢  [골드3] 백준(파티) : https://www.acmicpc.net/problem/1238
</h3>

<br>

## 🚀 문제

뿌요뿌요의 룰은 다음과 같다.
필드에 여러 가지 색깔의 뿌요를 놓는다. 뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.
뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다. 이때 1연쇄가 시작된다.
뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어지게 된다.
아래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데, 터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때마다 1연쇄씩 늘어난다.
터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.
남규는 최근 뿌요뿌요 게임에 푹 빠졌다. 이 게임은 1:1로 붙는 대전게임이라 잘 쌓는 것도 중요하지만, 상대방이 터뜨린다면 연쇄가 몇 번이 될지 바로 파악할 수 있는 능력도 필요하다. 하지만 아직 실력이 부족하여 남규는 자기 필드에만 신경 쓰기 바쁘다. 상대방의 필드가 주어졌을 때, 연쇄가 몇 번 연속으로 일어날지 계산하여 남규를 도와주자!

---

## 🚦입출력 + 제한사항

- 총 12개의 줄에 필드의 정보가 주어지며, 각 줄에는 6개의 문자가 있다.
- 이때 .은 빈공간이고 .이 아닌것은 각각의 색깔의 뿌요를 나타낸다.
- R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑이다.
- 입력으로 주어지는 필드는 뿌요들이 전부 아래로 떨어진 뒤의 상태이다. 즉, 뿌요 아래에 빈 칸이 있는 경우는 없다.
- 현재 주어진 상황에서 몇연쇄가 되는지 출력한다. 하나도 터지지 않는다면 0을 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 예전에 풀었던 벽돌깨기랑 거의 동일한듯?
- 일단 연쇄를 찾음, BFS를 돌리면서 연쇄가능한애가 있나 찾고, 있다면 터트릴 애들의 좌표를 보관
- 터트리고, stack이용해서 빈공간 채우기 해줌
- 위의 동작을 연쇄 단위로 반복

- [x] bfs + stack이용한 좌표관리

### 💻코드

```java
private static void downSorting() {
	Stack<Character> stack = new Stack<>();
	for(int i = 0; i < 6; i++) {
		for(int j = 0; j < 12; j++) {
			if(field[j][i] != '.') {
				stack.push(field[j][i]);
			}
		}
		for(int j = 11; j >= 0; j--) {
			if(!stack.isEmpty()) {
				field[j][i] = stack.pop();
			} else {
				field[j][i] = '.';
			}
		}
	}
}

private static void BFS(int x, int y) {
	Stack<Integer> stack = new Stack<>(); // 좌표값을 보관하기 위한 stack
	int sameColorCnt = 0; // 같은 수 카운트
	
	Queue<Integer> queue = new LinkedList<>();
	queue.add(x);
	queue.add(y);
	char curColor = field[x][y];
	visited[x][y] = true;
	stack.add(y);
	stack.add(x);
	sameColorCnt++;
	
	while(!queue.isEmpty()) {
		int curX = queue.poll();
		int curY = queue.poll();
		for(int i = 0; i < 4; i++) {
			int nr = curX + dx[i];
			int nc = curY + dy[i];
			if(nr>=0&&nr<12&&nc>=0&&nc<6&&!visited[nr][nc]&&curColor==field[nr][nc]) {
				sameColorCnt++;
				visited[nr][nc] = true;
				queue.add(nr);
				queue.add(nc);
				stack.add(nc); // nc, nr순으로 넣어야 꺼낼떄 nr, nc순이니
				stack.add(nr);
			}
		}
	}
	if(sameColorCnt >= 4) {
		for(int i = 0; i < sameColorCnt; i++) {
			if(!stack.isEmpty()) {
				list.add(stack.pop()); // x좌표
				list.add(stack.pop()); // y좌표
			}
		}
	}
}
```

### 🙄 후기
소요시간 : 1시간 <br>
아 벽돌깨기에서 봤던 stack으로 좌표값 내리는거 바로 떠올리고 활용함~ <br>
뿌듯^_^