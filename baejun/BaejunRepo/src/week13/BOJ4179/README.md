<h3 align="center"> 
    📢  [골드4] 백준(불!) : https://www.acmicpc.net/problem/4179
</h3>

<br>

## 🚀 문제

지훈이는 미로에서 일을 한다. 지훈이를 미로에서 탈출하도록 도와주자!
미로에서의 지훈이의 위치와 불이 붙은 위치를 감안해서 지훈이가 불에 타기전에 탈출할 수 있는지의 여부, 그리고 얼마나 빨리 탈출할 수 있는지를 결정해야한다.
지훈이와 불은 매 분마다 한칸씩 수평또는 수직으로(비스듬하게 이동하지 않는다) 이동한다.
불은 각 지점에서 네 방향으로 확산된다.
지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있다.
지훈이와 불은 벽이 있는 공간은 통과하지 못한다.

---

## 🚦입출력 + 제한사항

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- BFS 두번 돌림
- 하나는 불이 퍼지는 시간을 기록하기 위한 BFS
- 그리고 지훈이의 탈출 BFS, 이때 불이 퍼진 시간보다 일찍 가야 갈 수 있다는 조건 추가

- [x] BFS

### 💻코드

```java
private static void bfsInjihoon() {
	Queue<Node> queue = new LinkedList<>();
	queue.offer(new Node(startX, startY, 0));
	visited[startX][startY] = true;
	
	while(!queue.isEmpty()) {
		Node curNode = queue.poll();
		for(int i = 0; i < 4; i++) {
			int nr = curNode.x + dx[i];
			int nc = curNode.y + dy[i];
			
			if(nr>=0&&nr<R&&nc>=0&&nc<C&&!visited[nr][nc]&&map[nr][nc]!='#'&&curNode.cost+1<fireMap[nr][nc]) {
				visited[nr][nc] = true;
				queue.offer(new Node(nr, nc, curNode.cost+1));
				if(nr==0||nr==R-1||nc==0||nc==C-1) {
					if(minValue > curNode.cost+1) minValue = curNode.cost+1;
				}
			}
		}
	}
}
```

### 🙄 후기
소요시간 : 30분  <br>
금방 로직 생각하고 풀었는데, 엣지를 못잡았다.. 이거 연습해야된다..