# [BOJ][G2] 청소년 상어/ (WA)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/19236)]

[문제 내용 전체]

## 문제

[아기 상어](https://www.acmicpc.net/problem/16236)가 성장해 청소년 상어가 되었다.

4×4크기의 공간이 있고, 크기가 1×1인 정사각형 칸으로 나누어져 있다. 공간의 각 칸은 (x, y)와 같이 표현하며, x는 행의 번호, y는 열의 번호이다. 한 칸에는 물고기가 한 마리 존재한다. 각 물고기는 번호와 방향을 가지고 있다. 번호는 1보다 크거나 같고, 16보다 작거나 같은 자연수이며, 두 물고기가 같은 번호를 갖는 경우는 없다. 방향은 8가지 방향(상하좌우, 대각선) 중 하나이다.

오늘은 청소년 상어가 이 공간에 들어가 물고기를 먹으려고 한다. 청소년 상어는 (0, 0)에 있는 물고기를 먹고, (0, 0)에 들어가게 된다. 상어의 방향은 (0, 0)에 있던 물고기의 방향과 같다. 이후 물고기가 이동한다.

물고기는 번호가 작은 물고기부터 순서대로 이동한다. 물고기는 한 칸을 이동할 수 있고, 이동할 수 있는 칸은 빈 칸과 다른 물고기가 있는 칸, 이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸이다. 각 물고기는 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다. 만약, 이동할 수 있는 칸이 없으면 이동을 하지 않는다. 그 외의 경우에는 그 칸으로 이동을 한다. 물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동한다.

물고기의 이동이 모두 끝나면 상어가 이동한다. 상어는 방향에 있는 칸으로 이동할 수 있는데, 한 번에 여러 개의 칸을 이동할 수 있다. 상어가 물고기가 있는 칸으로 이동했다면, 그 칸에 있는 물고기를 먹고, 그 물고기의 방향을 가지게 된다. 이동하는 중에 지나가는 칸에 있는 물고기는 먹지 않는다. 물고기가 없는 칸으로는 이동할 수 없다. 상어가 이동할 수 있는 칸이 없으면 공간에서 벗어나 집으로 간다. 상어가 이동한 후에는 다시 물고기가 이동하며, 이후 이 과정이 계속해서 반복된다.

https://upload.acmicpc.net/1c7c473e-5e2c-4c45-9c88-b3b7cd06a360/-/preview/

<그림 1>

<그림 1>은 청소년 상어가 공간에 들어가기 전 초기 상태이다. 상어가 공간에 들어가면 (0, 0)에 있는 7번 물고기를 먹고, 상어의 방향은 ↘이 된다. <그림 2>는 상어가 들어간 직후의 상태를 나타낸다.

https://upload.acmicpc.net/8f26df12-6f68-43a3-9f6e-7416144e91dc/-/preview/

<그림 2>

이제 물고기가 이동해야 한다. 1번 물고기의 방향은 ↗이다. ↗ 방향에는 칸이 있고, 15번 물고기가 들어있다. 물고기가 있는 칸으로 이동할 때는 그 칸에 있는 물고기와 위치를 서로 바꿔야 한다. 따라서, 1번 물고기가 이동을 마치면 <그림 3>과 같아진다.

https://upload.acmicpc.net/75315b3c-ee04-4ae8-9422-5b1137f86117/-/preview/

<그림 3>

2번 물고기의 방향은 ←인데, 그 방향에는 상어가 있으니 이동할 수 없다. 방향을 45도 반시계 회전을 하면 ↙가 되고, 이 칸에는 3번 물고기가 있다. 물고기가 있는 칸이니 서로 위치를 바꾸고, <그림 4>와 같아지게 된다.

https://upload.acmicpc.net/7be317c7-b8b5-4b83-becb-ffd8550311fb/-/preview/

<그림 4>

3번 물고기의 방향은 ↑이고, 존재하지 않는 칸이다. 45도 반시계 회전을 한 방향 ↖도 존재하지 않으니, 다시 회전을 한다. ← 방향에는 상어가 있으니 또 회전을 해야 한다. ↙ 방향에는 2번 물고기가 있으니 서로의 위치를 교환하면 된다. 이런 식으로 모든 물고기가 이동하면 <그림 5>와 같아진다.

https://upload.acmicpc.net/a58fbda0-bb64-4773-b5f9-2da0bd3f0fd2/-/preview/

<그림 5>

물고기가 모두 이동했으니 이제 상어가 이동할 순서이다. 상어의 방향은 ↘이고, 이동할 수 있는 칸은 12번 물고기가 있는 칸, 15번 물고기가 있는 칸, 8번 물고기가 있는 칸 중에 하나이다. 만약, 8번 물고기가 있는 칸으로 이동하면, <그림 6>과 같아지게 된다.

https://upload.acmicpc.net/2431d117-fab6-4de9-8d76-2fb41d471ee7/-/crop/651x656/1,12/-/preview/

<그림 6>

상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 구해보자.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄부터 4개의 줄에 각 칸의 들어있는 물고기의 정보가 1번 행부터 순서대로 주어진다. 물고기의 정보는 두 정수 ai, bi로 이루어져 있고, ai는 물고기의 번호, bi는 방향을 의미한다. 방향 bi는 8보다 작거나 같은 자연수를 의미하고, 1부터 순서대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗ 를 의미한다.

## 출력

상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

물고기를 옮기는 작업은 금방 구현했는데 상어를 무한으로 돌리는 방법이 생각보다 까다로워서 해결하지 못하였다. 백트래킹을 좀 더 연습해야겠다.

---

> **코드**
> 

```java
package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class test {

	static shark[][] map;
	static int[][] visited;
	static int[] dir_y = {-1,-1,0,1,1,1,0,-1};
	static int[] dir_x = {0,-1,-1,-1,0,1,1,1};
	static int max;
	static int sum;
	static shark sk;
	static point p;
	
	static class shark {
		int num;
		int dir;
		
		public shark(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "shark [num=" + num + ", dir=" + dir + "]";
		}
		
	}
	
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
		
		map = new shark[4][4];
		visited = new int[4][4];

		for(int i=0;i<4;i++) {
			String s = br.readLine();
			String[] s2 = s.split(" ");
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			for(int j=0;j<4;j++) {
				map[i][j] = new shark(a,b);
			}
		}
		
		visited[0][0] = 1;
		sk = map[0][0];
		p = new point(0,0);
		sum += sk.num;
		max = sum;
		eat(0,0);
		
	}

	private static void move() {
		int cnt = 1;
		
		while(true) {
			
			int flag = 0;
			
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					if(map[i][j].num == cnt) {
						int d_idx = map[i][j].dir - 1;
						int ny = i + dir_y[d_idx];
						int nx = j + dir_x[d_idx];
						if(ny>=0 && ny<4 && nx>=0 && nx<4 && visited[ny][nx] == 0) {
							shark tmp = map[ny][nx];
							map[ny][nx] = map[i][j];
							map[i][j] = tmp;
							flag++;
							break;
						}
						else {
							int idx = map[i][j].dir - 1;
							for(int c=0;c<8;c++) {
								if(idx>=8)
									idx=0;
								ny = i + dir_y[idx];
								nx = j + dir_x[idx];
								if(ny>=0 && ny<4 && nx>=0 && nx<4 && visited[ny][nx] == 0) {
									shark tmp = map[ny][nx];
									map[ny][nx] = map[i][j];
									map[i][j] = tmp;
									flag++;
									break;
								}
								idx++;
							}
						}
					}
				}
				if(flag != 0)
					break;
			}
			
			cnt++;
			if(cnt>16)
				break;
		}
		
	}

	private static void eat(int y, int x) {
		visited[p.y][p.x] = 0;
		map[p.y][p.x] = null;
		
		int ny = y+dir_y[sk.dir-1];
		int nx = x+dir_x[sk.dir-1];
		
		if(ny>=0 && ny<4 && nx>=0 && nx<4 && map[ny][nx] == null) {
			
		}
		
	}

}

```

---

> **후기**
> 

문제에 대한 짧은 코멘트

문제 풀이 시간/ 실행시간/ 메모리/코드길이

알고리즘 분류