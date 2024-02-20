# [BOJ][S1] 미로탐색/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/2178)]

[문제 내용 전체]

N×M크기의 배열로 표현되는 미로가 있다.

| 1 | 0 | 1 | 1 | 1 | 1 |
| --- | --- | --- | --- | --- | --- |
| 1 | 0 | 1 | 0 | 1 | 0 |
| 1 | 0 | 1 | 0 | 1 | 1 |
| 1 | 1 | 1 | 0 | 1 | 1 |

미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.

위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 **붙어서** 입력으로 주어진다.

## 출력

첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

bfs를 사용해서 갈수 있는 거리를 구하였고 그중에서도 최단거리를 구했다.

---

> **코드**
> 

```java
package week4.BOJ_2178_미로탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_2178_미로탐색 {
	
	static int N;
	static int M;
	static int[][] maze;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int[][] visited;
	static int answer;
	static int min = Integer.MAX_VALUE;
	
	static class point{
		int x;
		int y;
		int count;
		public point(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}
		
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		maze = new int[N][M];
		visited = new int[N][M];
		
		for(int i=0;i<N;i++) {
			s=br.readLine();
			for(int j=0;j<M;j++) {
				maze[i][j] = s.charAt(j) - '0';
			}
		}
		
		bfs(1);
		bw.append(min + "" + "\n");
		bw.flush();
		bw.close();
	}
	
	
	static void bfs(int cnt) {
		
		Queue<point> que = new ArrayDeque<>();
		point p = new point(0,0,cnt);
		que.offer(p);
		visited[0][0] = 1;
		
		while(!que.isEmpty()) {
			
			point np = que.poll();
			
			for(int i=0;i<4;i++) {
				int ny = dy[i] + np.y;
				int nx = dx[i] + np.x;
				cnt = np.count;
				answer = cnt;
				if(np.y == N -1 && np.x == M -1) {
					if(min>answer)
						min = answer;
				}
				
				if(ny>=0 && ny<N && nx>=0 && nx<M && visited[ny][nx] == 0 && maze[ny][nx] == 1) {
					visited[ny][nx] = 1;
					que.offer(new point(nx,ny,cnt+1));
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

- bfs만 사용할 줄 안다면 매우 쉽게 풀리는 문제이다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 20분 / 164ms / 16336KB / 1780B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [너비 우선 탐색](https://www.acmicpc.net/problem/tag/126)