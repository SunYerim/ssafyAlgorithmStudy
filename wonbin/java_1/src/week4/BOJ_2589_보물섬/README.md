# [BOJ][G5] 보물섬 / (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/2589)]

[문제 내용 전체]

## 문제

보물섬 지도를 발견한 후크 선장은 보물을 찾아나섰다. 보물섬 지도는 아래 그림과 같이 직사각형 모양이며 여러 칸으로 나뉘어져 있다. 각 칸은 육지(L)나 바다(W)로 표시되어 있다. 이 지도에서 이동은 상하좌우로 이웃한 육지로만 가능하며, 한 칸 이동하는데 한 시간이 걸린다. 보물은 서로 간에 최단 거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두 곳에 나뉘어 묻혀있다. 육지를 나타내는 두 곳 사이를 최단 거리로 이동하려면 같은 곳을 두 번 이상 지나가거나, 멀리 돌아가서는 안 된다.

!https://www.acmicpc.net/upload/images/c1bYIsKpI6m317EAx.jpg

예를 들어 위와 같이 지도가 주어졌다면 보물은 아래 표시된 두 곳에 묻혀 있게 되고, 이 둘 사이의 최단 거리로 이동하는 시간은 8시간이 된다.

!https://www.acmicpc.net/upload/images/XqDkWCRUWbzZ.jpg

보물 지도가 주어질 때, 보물이 묻혀 있는 두 곳 간의 최단 거리로 이동하는 시간을 구하는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에는 보물 지도의 세로의 크기와 가로의 크기가 빈칸을 사이에 두고 주어진다. 이어 L과 W로 표시된 보물 지도가 아래의 예와 같이 주어지며, 각 문자 사이에는 빈 칸이 없다. 보물 지도의 가로, 세로의 크기는 각각 50이하이다.

## 출력

첫째 줄에 보물이 묻혀 있는 두 곳 사이를 최단 거리로 이동하는 시간을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음엔 dfs그냥 타서 풀었는데 역시 안되었다.

그래서 최단경로란 키워드를 보고 bfs로 바꿔서 풀기로 하였다.

여러가지 정보를 어떻게 다룰까 고민을 하다가 하나의 클래스로 만들어서 다루면 쉽다고 지환이가 이야기 해줘서 point클래스를 만들어 y값 x값 거리값을 저장하였다.

그 후 bfs를 4방향 탐색으로 타서 max값을 저장후 해결하였다.

---

> **코드**
> 

```java
package week4.BOJ_2589_보물섬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_2589_보물섬 {
	
	static int N;
	static int M;
	static char[][] maze;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int max = 0;
	static int ny = 0;
	static int nx = 0;
	
	static class point{
		int y;
		int x;
		int distance;
		
		public point(int y, int x, int distance) {
			super();
			this.y = y;
			this.x = x;
			this.distance = distance;
		}
		@Override
		public String toString() {
			return "point [y=" + y + ", x=" + x + ", distance=" + distance + "]";
		}
		
		
	}
			
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		
		maze = new char[N][M];
		
		for(int i=0;i<N;i++) {
			s = br.readLine();
			for(int j=0;j<M;j++) {
				maze[i][j] = s.charAt(j);
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(maze[i][j] == 'L') {
					bfs(i,j,0);
				}
			}
		}
		
		bw.append(max+"");
		bw.flush();
		bw.close();
		
	}
	
	public static void bfs(int y, int x, int cnt) {
		
		Queue<point> que = new ArrayDeque<>();
		point p = new point(y,x,cnt);
		int[][] visited = new int[50][50];
		visited[y][x] = 1;
		
		que.offer(p);
		
		while(!que.isEmpty()) {
			
			point p2 = que.poll();
			int tmp = p2.distance;
			cnt = tmp;
			if(max<tmp) max = tmp;
			
			for(int i=0;i<4;i++) {
				ny = dy[i] + p2.y;
				nx = dx[i] + p2.x;
				
				if(ny>=0 && ny<N && nx>=0 && nx<M && visited[ny][nx] == 0 && maze[ny][nx] == 'L') {
					visited[ny][nx] = 1;
					que.offer(new point(ny,nx,cnt+1));
				}
			}
			
		}
	
		
	}
}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 이제 bfs를 어떻게 쓰는지 감이 왔다.
- 클래스를 활용하자

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 한시간 / 508ms / 190440KB / 1969B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [브루트포스 알고리즘](https://www.acmicpc.net/problem/tag/125)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [너비 우선 탐색](https://www.acmicpc.net/problem/tag/126)