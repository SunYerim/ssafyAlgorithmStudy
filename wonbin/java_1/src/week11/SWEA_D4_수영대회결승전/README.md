# [SWEA][D4] 수영대회결승전(AC)

---

> **문제 설명**
> 

[[문제 링크](https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AWKaG6_6AGQDFARV)]

[문제 내용 전체]

예선전에서 승리한 삼성이는 결승전 까지 진출하게 되었다.

결승전인 만큼 수영장이 아닌 바다에서 진행되었다.

바다 전체를 사용 할 수 없기에 가로 N 세로 N만큼의 공간만 사용하여 진행하도록 하였다.

이 공간을 벗어나면 실격처리가 되므로 공간안에서 가장 빠른 길을 찾아야 한다.

이 공간에는 섬과 같은 지나갈 수 없는 장애물과, 주기적으로 사라졌다 나타나는 소용돌이 같은 장애물이 존재한다.

( 섬과 같은 장애물은 지도에서 1로 표시, 소용돌이 같은 장애물은 2로 표시 )

소용돌이는 생성되고 2초동안 유지되다가 1초동안 잠잠해진다.

예를들어, 0초에 생성된 소용돌이는 0초, 1초까지 유지되고 2초에 사라지게된다. 또한 3초, 4초에는 생성되고 5초에 사라진다.

(단 ,한번 통과한 소용돌이 위에서는 머물러 있을 수 있다 )

이런 바다에서 삼성이를 우승시키려면 어떤 경로로 보내야 될까?

똑똑한 여러분들은 한번에 그 경로를 찾을 수 있었다. 해당 경로로 수영을 했을때 삼성이는 몇초만에 골인 할 수 있을까?

```java
5 //N
0 0 0 0 0
0 0 0 1 0
0 0 0 1 0
2 2 1 1 0
0 0 0 0 0
4 0 //시작점
2 0 //도착점
```

# **EX)**

이 경우에는 (4,0) 에서 시작, 소용돌이가 존재하므로 이동하지 않는다 ( 0초 )

(4,0) 아직 소용돌이가 사라지지 않았으므로 제자리에 있다 ( 1초)

(4,0) 이제 소용돌이가 사라지는 것을 보았고 건너려고한다 ( 2초)

(3,0) 소용돌이를 통과하였고 바다위를 수영하고 있다 (3초)

(2,0) 도착지에 도착하였다 (4초)

---

> **제한사항**
> 

입력/출력 제한사항

# **입력**

첫 번째 줄에 테스트 케이스의 수 T가 주어진다.

각 테스트 케이스의 첫 번째 줄에는 수영장의 크기 N  ( 2<=N<=15 )

다음 N개의 줄의 i번째 줄에는 수영장의 모양이 공백으로 구분되어 주어진다. ( 0 : 지나갈 수 있는 곳 , 1 : 장애물 , 2: 주기가 2초인 소용돌이)

다음으로 시작위치 A,B가 주어지고 ( 0<=A,B<=N-1)

마지막 줄에 도착위치 C, D가 주어진다 ( 0 <=C,D<=N-1) ( 도착점과 시작점은 소용돌이가 아니다 )

# 출력

각 테스트 케이스마다 테스트 케이스의 번호와 이동시간을 공백을 두고 표시한다

도착 할 수 없다면 -1을 출력한다.

(Ex) #1 4

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

bfs를 활용해서 최단거리를 구하는 문제인것을 파악하고 bfs를 활용하여 해결하였다.

1. bfs 배열을 활용해서 최단거리를 구하는 로직을 작성하였다.
2. 소용돌이로 갈 경우에 소용돌이가 없어지기 직전이면 갈 수 있도록 하고 아니면 그대로 있도록 구현하였다.

---

> **코드**
> 

```java
package week11.SWEA_D4_수영대회결승전;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class SWEA_D4_수영대회결승전 {

	static int N;
	static int[][] map;
	static int[][] visited;
	static int answer;
	static int[] start = new int[2];
	static int[] end = new int[2];
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	
	static class point{
		int y;
		int x;
		int cnt;
		
		public point(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "point [y=" + y + ", x=" + x + ", cnt=" + cnt + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new int[N][N];
			answer = 0;
			
			for(int i=0;i<N;i++) {
				String s = br.readLine();
				String[] s2 = s.split(" ");
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(s2[j]);
				}
			}
			
			String s = br.readLine();
			String[] s2 = s.split(" ");
			
			start[0] = Integer.parseInt(s2[0]); // y좌표
			start[1] = Integer.parseInt(s2[1]); // x좌표
			
			s = br.readLine();
			s2 = s.split(" ");
			
			end[0] = Integer.parseInt(s2[0]); // y좌표
			end[1] = Integer.parseInt(s2[1]); // x좌표
			
			bfs(start[0], start[1], 0);
			
			if(answer == 0)
				answer = -1;
			
			bw.append("#"+tc+""+" "+answer+"");
			bw.append("\n");
		}
		bw.flush();
		bw.close();
	}

	private static void bfs(int y, int x, int cnt) {
		
		Queue<point> que = new ArrayDeque<>();
		visited[y][x] = 1;
		que.offer(new point(y, x, 0));
		
		while(!que.isEmpty()) {
			
			point p = que.poll();
			
			if(p.y == end[0] && p.x == end[1]) {
				answer = p.cnt;
				return;
			}
			
			for(int i=0;i<4;i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				if(ny>=0 && ny<N && nx>=0 && nx<N && visited[ny][nx] == 0 && map[ny][nx] != 1) {
					if(map[ny][nx] == 2) {
						if(p.cnt % 3 == 2) {
							que.offer(new point(ny , nx, p.cnt+1));
							visited[ny][nx] = 1;
						}
						else
							que.offer(new point(p.y, p.x, p.cnt+1));
					}
					else {
						que.offer(new point(ny, nx, p.cnt+1));
						visited[ny][nx] = 1;
					}
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

- bfs를 사용할 줄 알면 충분히 풀 수 있는 문제이다

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 30분 / 102 ms / 23,492 kb / 2,555

알고리즘 분류

- bfs