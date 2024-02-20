# [BOJ][G4] 녹색옷입은애가젤다지?/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/4485)]

[문제 내용 전체]

## 문제

젤다의 전설 게임에서 화폐의 단위는 루피(rupee)다. 그런데 간혹 '도둑루피'라 불리는 검정색 루피도 존재하는데, 이걸 획득하면 오히려 소지한 루피가 감소하게 된다!

젤다의 전설 시리즈의 주인공, 링크는 지금 도둑루피만 가득한 N x N 크기의 동굴의 제일 왼쪽 위에 있다. [0][0]번 칸이기도 하다. 왜 이런 곳에 들어왔냐고 묻는다면 밖에서 사람들이 자꾸 "젤다의 전설에 나오는 녹색 애가 젤다지?"라고 물어봤기 때문이다. 링크가 녹색 옷을 입은 주인공이고 젤다는 그냥 잡혀있는 공주인데, 게임 타이틀에 젤다가 나와있다고 자꾸 사람들이 이렇게 착각하니까 정신병에 걸릴 위기에 놓인 것이다.

하여튼 젤다...아니 링크는 이 동굴의 반대편 출구, 제일 오른쪽 아래 칸인 [N-1][N-1]까지 이동해야 한다. 동굴의 각 칸마다 도둑루피가 있는데, 이 칸을 지나면 해당 도둑루피의 크기만큼 소지금을 잃게 된다. 링크는 잃는 금액을 최소로 하여 동굴 건너편까지 이동해야 하며, 한 번에 상하좌우 인접한 곳으로 1칸씩 이동할 수 있다.

링크가 잃을 수밖에 없는 최소 금액은 얼마일까?

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

입력은 여러 개의 테스트 케이스로 이루어져 있다.

각 테스트 케이스의 첫째 줄에는 동굴의 크기를 나타내는 정수 N이 주어진다. (2 ≤ N ≤ 125) N = 0인 입력이 주어지면 전체 입력이 종료된다.

이어서 N개의 줄에 걸쳐 동굴의 각 칸에 있는 도둑루피의 크기가 공백으로 구분되어 차례대로 주어진다. 도둑루피의 크기가 k면 이 칸을 지나면 k루피를 잃는다는 뜻이다. 여기서 주어지는 모든 정수는 0 이상 9 이하인 한 자리 수다.

## 출력

각 테스트 케이스마다 한 줄에 걸쳐 정답을 형식에 맞춰서 출력한다. 형식은 예제 출력을 참고하시오.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음엔 dfs로 풀었는데 시간이 너무 오래 걸렸었다.

그래서 주변사람들한테 물어보니 이건 다익스트라 알고리즘을 사용해야 한다고 해서 다익스트라를 따로 찾아 보았다.

하지만 새로 배운 개념이라서 이건 정답을 찾아서 이해해 가면서 풀어보았다.

---

> **코드**
> 

```java
package week4.BOJ_4485_녹색옷입은애가젤다지;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_4485_녹색옷입은애가젤다지 {
	
	static int N;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int[][] rupee;
	static int count = 0;
	static int[][] visited;
	static int min = Integer.MAX_VALUE;
	
	static class zelda implements Comparable<zelda>{
		int x;
		int y;
		int rupee;
		public zelda(int x, int y, int rupee) {
			super();
			this.x = x;
			this.y = y;
			this.rupee = rupee;
		}
		@Override
		public String toString() {
			return "zelda [x=" + x + ", y=" + y + ", rupee=" + rupee + "]";
		}
		
		@Override
		public int compareTo(zelda za) {
			return this.rupee >= za.rupee ? 1: -1;
		}
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tmp = 1;
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0)
				break;
			visited = new int[N][N];
			rupee = new int[N][N];
			min = Integer.MAX_VALUE;
			count = 0;
			
			for(int i=0;i<N;i++) {
				String s = br.readLine();
				String[] s2 = s.split(" ");
				for(int j=0;j<N;j++) {
					rupee[i][j] = Integer.parseInt(s2[j]);
				}
			}
			
			bfs(0,0);
			
			bw.append("Problem " + tmp + ": " + min + "" + "\n");
			tmp++;
		}
		bw.flush();
		bw.close();
		
	}
	
	
	static void bfs(int y, int x) {
		
		PriorityQueue<zelda> que = new PriorityQueue<>();
		zelda z = new zelda(x,y,rupee[y][x]);
		que.offer(z);
		
		while(!que.isEmpty()) {
			zelda nz = que.poll();
			
			for(int i=0;i<4;i++) {
				int ny = dy[i] + nz.y;
				int nx = dx[i] + nz.x;
				
				if(ny>=0 && ny<N && nx>=0 && nx<N && visited[ny][nx] == 0) {
					visited[ny][nx] = 1;
					que.offer(new zelda(nx,ny,rupee[ny][nx]));
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

- 다익스트라도 외워야 겠다.
- 보고나면 이해할만한데 다시 해보라고 하면 잘 모르겠다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 2시간 / 264ms / 20564KB / 2213B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [데이크스트라](https://www.acmicpc.net/problem/tag/22)
- [최단 경로](https://www.acmicpc.net/problem/tag/215)