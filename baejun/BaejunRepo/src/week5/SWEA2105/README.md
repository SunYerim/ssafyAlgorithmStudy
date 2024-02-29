<h3 align="center"> 
    📢  [A형대비] SWEA(디저트 카페) : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5VwAr6APYDFAWu
</h3>

<br>

## 🚀 문제

너무 길어서 설명 보세요

---

## 🚦입출력 + 제한사항

1. 시간제한 : 최대 50개 테스트 케이스를 모두 통과하는 데 C/C++/Java 모두 3초
2. 디저트 카페가 모여있는 지역의 한 변의 길이 N은 4 이상 20 이하의 정수이다. (4 ≤ N ≤ 20)
3. 디저트 종류를 나타나는 수는 1 이상 100 이하의 정수이다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- DFS 돌릴 생각했음
- 근데 어차피 대각방향 시계방향으로 도나, 반시계로 도나 결과는 똑같으므로, 시계방향으로만 돌았음
- 같은 종류의 디저트 먹거나 하는 가지치기 해줬음
- 시계방향으로 사각형을 그리면서 도므로, 현재 방향에서 같은 방향 유지하는 경우 한번, 45도 트는 방향 한번의 경우만 해주면 됨

- [x] DFS로 탐색

### 💻코드

```java
private static void DFS(int cnt, int prevDirec, int x, int y, int destX, int destY) {
	//기저조건
	if(cnt != 0 && x == destX && y == destY) { // 출발지로 돌아왔으면 디저트 몇개 먹었는지 체크
		if(maxValue < cnt) maxValue = cnt;
		return;
	}
	//유도조건
	if(cnt == 0) { // 출발지에선 같은 방향으로 한칸 나아감(제자리 돌기 방지)
		visitedForDessert[map[x][y].variation] = true;
		int nr = x+dx[prevDirec];
		int nc = y+dy[prevDirec];
		if(nr>=0&&nr<N&&nc>=0&&nc<N) {
			if(!visitedForDessert[map[nr][nc].variation]) {
				visitedForDessert[map[nr][nc].variation] = true;
				DFS(cnt+1, prevDirec, nr, nc, destX, destY);
				visitedForDessert[map[nr][nc].variation] = false;
			}
		}
	} else { // 같은 방향으로 가는거 한번, 방향 45도 틀어서 한번(시계방향으로) -> 이때, 방향은 같이갈땐 유지, 틀땐 변경해줘야함
		int nr = x+dx[prevDirec];
		int nc = y+dy[prevDirec];
		if(nr == destX && nc == destY) {
			DFS(cnt+1, prevDirec, nr, nc, destX, destY);
			return;
		}
		else if(nr>=0&&nr<N&&nc>=0&&nc<N) {
			if(!visitedForDessert[map[nr][nc].variation]) {
				visitedForDessert[map[nr][nc].variation] = true;
				DFS(cnt+1, prevDirec, nr, nc, destX, destY);
				visitedForDessert[map[nr][nc].variation] = false;
			}
		}
		if(prevDirec+1 == 4) return; // 방향이 3일땐 3으로 가는 경우만 구하면 됨.(트는 순간 사각형 어그러짐)
		nr = x+dx[prevDirec+1];
		nc = y+dy[prevDirec+1];
		if(nr == destX && nc == destY) { // 목적지에 도착했으면 바로 호출
			DFS(cnt+1, prevDirec+1, nr, nc, destX, destY);
		}
		else if(nr>=0&&nr<N&&nc>=0&&nc<N) {
			if(!visitedForDessert[map[nr][nc].variation]) {
				visitedForDessert[map[nr][nc].variation] = true;
				DFS(cnt+1, prevDirec+1, nr, nc, destX, destY);
				visitedForDessert[map[nr][nc].variation] = false;
			}
		}
	}
}
```

### 🙄 후기
소요시간 : 2시간 <br>
오.. 확실히 최대한 생각 많이하고, 문제 꼼꼼히 읽고, 최대한 코드를 그려놓고 손가락 두들기니까 과정이 훨씬 간결해졌다 <br>
이 폼 그대로 쭉 풀어보자