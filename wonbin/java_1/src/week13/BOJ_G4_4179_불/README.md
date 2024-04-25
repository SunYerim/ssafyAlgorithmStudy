# [BOJ][G4] 불!/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/4179)]

[문제 내용 전체]

## 문제

지훈이는 미로에서 일을 한다. 지훈이를 미로에서 탈출하도록 도와주자!

미로에서의 지훈이의 위치와 불이 붙은 위치를 감안해서 지훈이가 불에 타기전에 탈출할 수 있는지의 여부, 그리고 얼마나 빨리 탈출할 수 있는지를 결정해야한다.

지훈이와 불은 매 분마다 한칸씩 수평또는 수직으로(비스듬하게 이동하지 않는다) 이동한다.

불은 각 지점에서 네 방향으로 확산된다.

지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있다.

지훈이와 불은 벽이 있는 공간은 통과하지 못한다.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

입력의 첫째 줄에는 공백으로 구분된 두 정수 R과 C가 주어진다. 단, 1 ≤ R, C ≤ 1000 이다. R은 미로 행의 개수, C는 열의 개수이다.

다음 입력으로 R줄동안 각각의 미로 행이 주어진다.

각각의 문자들은 다음을 뜻한다.

- #: 벽
- .: 지나갈 수 있는 공간
- J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간)
- F: 불이 난 공간

J는 입력에서 하나만 주어진다.

## 출력

지훈이가 불이 도달하기 전에 미로를 탈출 할 수 없는 경우 IMPOSSIBLE 을 출력한다.

지훈이가 미로를 탈출할 수 있는 경우에는 가장 빠른 탈출시간을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음 보자마자 그냥 탐색 2번 돌리면 되겠다 해서 dfs로 처음에 했다가 틀려서 bfs 로 바꾼다음 불먼저 뿌리고 이동하고를 반복했는데 불 번지는 코드를 queue말고 arraylist로 하니까 시간초과가 걸렸다. 그래서 bfs 2번 사용하였다.

1. 좌표 지정 클래스 생성 및 나머지 변수 생성
2. bfs를 돌려서 불먼저 퍼뜨리고 그 이후에 사람이 움직이게 하였다.
3. 사람이 가장자리에 있으면 탈출 탈출못하면 IMPOSSIBLE 출력

---

> **코드**
> 

```java
package week13.BOJ_G4_4179_불;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_G4_4179_불 {

	static int R;
	static int C;
	static char[][] map;
	static Queue<point> fire;
	static int[][] visited;
	static int[][] visited2;
	static int start_x;
	static int start_y;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int cnt;
	static int tmp;
	
	static class point{
		int y;
		int x;
		
		public point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "point [y=" + y + ", x=" + x + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		R = Integer.parseInt(s2[0]);
		C = Integer.parseInt(s2[1]);
		map = new char[R][C];
		visited = new int[R][C];
		visited2 = new int[R][C];
		fire = new ArrayDeque<>();
		
		for(int i=0;i<R;i++) {
			s = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'J') {
					start_y = i;
					start_x = j;
				}
				if(map[i][j] == 'F') {
					visited2[i][j] = 1;
					fire.offer(new point(i, j));
				}
			}
		}
		
		visited[start_y][start_x] = 1;
		bfs(start_y, start_x);
		
		if(tmp == 1) {
			bw.append(cnt+"");
			bw.append("\n");
			bw.close();
		}
		else {
			bw.append("IMPOSSIBLE");
			bw.append("\n");
			bw.close();
		}
	}

	private static void bfs(int y, int x) {
		
		Queue<point> que = new ArrayDeque<>();
		que.offer(new point(y,x));
		
		
		while(!que.isEmpty()) {
			int size = que.size();
			int f_size = fire.size();
			
			while(--f_size>=0) {
				point p = fire.poll();
				
				for(int i=0;i<4;i++) {
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];
					
					if(ny>=0 && ny<R && nx>=0 && nx<C && map[ny][nx] != '#' && map[ny][nx] != 'F') {
						map[ny][nx] = 'F';
						fire.offer(new point(ny, nx));
					}
				}
			}
			
			while(--size>=0) {
				
				point p = que.poll();
				
				int count = 0;
				int count2 = 0;
				
				for(int i=0;i<4;i++) { // 탈출 조건 체크
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];
					
					if(ny < 0 || ny >= R || nx < 0 || nx >= C) {
						cnt++;
						tmp = 1;
						return;
					}
				}
				
				for(int i=0;i<4;i++) {
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];
					
					if(ny>=0 && ny<R && nx>=0 && nx<C && visited[ny][nx] == 0 && map[ny][nx] != '#' && map[ny][nx] != 'F') {
						visited[ny][nx] = 1;
						que.offer(new point(ny, nx));
					}
				}
			}
			
			cnt++;
			
		}
	}
	
	private static void fire() {
		
		while(!fire.isEmpty()) {
			point p = fire.poll();
			
			for(int i=0;i<4;i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				if(ny>=0 && ny<R && nx>=0 && nx<C && map[ny][nx] != '#' && visited2[ny][nx] == 0) {
					map[ny][nx] = 'F';
					visited2[ny][nx] = 1;
					fire.offer(new point(ny, nx));
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

- 속도에 유의해서 풀자

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간? / 684ms / 54944KB / 3316B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [너비 우선 탐색](https://www.acmicpc.net/problem/tag/126)