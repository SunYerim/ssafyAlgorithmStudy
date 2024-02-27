<h3 align="center"> 
    📢  [A형대비] SWEA(벽돌 깨기) : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRQm6qfL0DFAUo&categoryId=AWXRQm6qfL0DFAUo&categoryType=CODE&problemTitle=%EB%AA%A8%EC%9D%98&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
</h3>

<br>

## 🚀 문제

너무 길어서 설명 보세요

---

## 🚦입출력 + 제한사항

1. 1 ≤ N ≤ 4
2. 2 ≤ W ≤ 12
3. 2 ≤ H ≤ 15

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
1. 중복순열로 슈팅을 어디로 할지 모든 경우의 수 추출
2. 각 경우의수 단위로 진행하며, bfs와 정렬(0이 생긴 틈 채우기) N회만큼 반복

- [x] bfs를 이용해 완탐, stack을 이용해 정렬

### 💻코드

```java
private static void combination(int cnt) {
		//기저조건
		if(cnt == N) {
			for (int is : comb) {
				numberOfCases.add(is);
			}
			return;
		}

		//유도조건
		for(int i = 0; i < W; i++) {
			comb[cnt] = i;
			combination(cnt+1);
		}
	}


	private static void arrangeArrays() {
		Stack<Integer> stack = new Stack<>();

		for(int i = 0; i < W; i++) {
			for(int j = 0; j < H; j++) {
				if(arraysCopy[j][i] != 0) {
					stack.add(arraysCopy[j][i]);
				}
			}
			for(int j = H-1; j >= 0; j--) {
				if(stack.isEmpty()) arraysCopy[j][i] = 0;
				else arraysCopy[j][i] = stack.pop();
			}
		}
	}


	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 0; i < H; i++) {
			visited[i][start] = true;
			if (arraysCopy[i][start] == 0) continue;
			if (arraysCopy[i][start] == 1) {
				arraysCopy[i][start] = 0;
				return;
			}
			queue.offer(i);
			queue.offer(start);
			break;
		}
		while(!queue.isEmpty()) {
			int x = queue.poll();
			int y = queue.poll();
			if (arraysCopy[x][y] == 1) {
				arraysCopy[x][y] = 0;
				continue;
			}
			for(int i = 0; i < 4; i++) {
				int nr = x + dx[i];
				int nc = y + dy[i];
				if(nr>=0&&nr<H&&nc>=0&&nc<W&&!visited[nr][nc]) {
					visited[nr][nc] = true;
					if(arraysCopy[nr][nc] > 1) {
						queue.offer(nr);
						queue.offer(nc);							
					} else if (arraysCopy[nr][nc] == 1) arraysCopy[nr][nc] = 0;
				}
				for(int j = 1; j < arraysCopy[x][y]-1; j++) {
					if(i == 0) {
						nr += j;
					} else if(i == 1) {
						nr -= j;
					} else if(i == 2) {
						nc += j;
					} else if(i == 3) {
						nc -= j;
					}
					if(nr>=0&&nr<H&&nc>=0&&nc<W&&!visited[nr][nc]) {
						visited[nr][nc] = true;
						if(arraysCopy[nr][nc] > 1) {
							queue.offer(nr);
							queue.offer(nc);							
						} else if (arraysCopy[nr][nc] == 1) arraysCopy[nr][nc] = 0;
					}
					if(i == 0) {
						nr -= j;
					} else if(i == 1) {
						nr += j;
					} else if(i == 2) {
						nc -= j;
					} else if(i == 3) {
						nc += j;
					}
				}
			}
			arraysCopy[x][y] = 0;
		}
	}
```

### 🙄 후기
소요시간 : 4시간 <br>
꺠끗하게 코딩해야한다.. <br>
이거 실제 시험이면 그냥 바로 타임오바 나는건데, 더럽게 풀면 우짤껀데 ~, 최대한 메서드랑 정리 싹 다 해놓고 키보드에 손 올리자 배준아