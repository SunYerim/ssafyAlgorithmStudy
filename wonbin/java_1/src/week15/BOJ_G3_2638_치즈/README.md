# [BOJ][G3] 치즈/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/2638)]

[문제 내용 전체]

## 문제

N×M의 모눈종이 위에 아주 얇은 치즈가 <그림 1>과 같이 표시되어 있다. 단, N 은 세로 격자의 수이고, M 은 가로 격자의 수이다. 이 치즈는 냉동 보관을 해야만 하는데 실내온도에 내어놓으면 공기와 접촉하여 천천히 녹는다. 그런데 이러한 모눈종이 모양의 치즈에서 각 치즈 격자(작 은 정사각형 모양)의 4변 중에서 적어도 2변 이상이 실내온도의 공기와 접촉한 것은 정확히 한시간만에 녹아 없어져 버린다. 따라서 아래 <그림 1> 모양과 같은 치즈(회색으로 표시된 부분)라면 C로 표시된 모든 치즈 격자는 한 시간 후에 사라진다.

https://upload.acmicpc.net/a4998beb-104c-4e37-b3d7-fd91cd81464a/-/preview/

<그림 1>

<그림 2>와 같이 치즈 내부에 있는 공간은 치즈 외부 공기와 접촉하지 않는 것으로 가정한다. 그러므 로 이 공간에 접촉한 치즈 격자는 녹지 않고 C로 표시된 치즈 격자만 사라진다. 그러나 한 시간 후, 이 공간으로 외부공기가 유입되면 <그림 3>에서와 같이 C로 표시된 치즈 격자들이 사라지게 된다.

https://upload.acmicpc.net/e5d519ee-53ea-40a6-b970-710cca0db128/-/preview/

<그림 2>

https://upload.acmicpc.net/a00b876a-86dc-4a82-a030-603a9b1593cc/-/preview/

<그림 3>

모눈종이의 맨 가장자리에는 치즈가 놓이지 않는 것으로 가정한다. 입력으로 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 구하는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에는 모눈종이의 크기를 나타내는 두 개의 정수 N, M (5 ≤ N, M ≤ 100)이 주어진다. 그 다음 N개의 줄에는 모눈종이 위의 격자에 치즈가 있는 부분은 1로 표시되고, 치즈가 없는 부분은 0으로 표시된다. 또한, 각 0과 1은 하나의 공백으로 분리되어 있다.

## 출력

출력으로는 주어진 치즈가 모두 녹아 없어지는데 걸리는 정확한 시간을 정수로 첫 줄에 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

모눈종이의 맨 가장자리에는 치즈가 놓이지 않는 것으로 가정한다고 하니 가장자리인 0,0을 기준으로 flood-fill 알고리즘을 작성한다. 그리고 flood-fill로 잡힌 가장자리를 구분할 수 있게 2로 바꿔주고 각 1번자리마다 2번이 2개 이상있으면 없애도록 하는 로직을 반복하여 횟수를 구현한다.

1. (0,0)을 기준으로 flood-fill을 작성한다.
2. 기존 map을 카피하여 카피된 map에 flood-fill 처리된 부분을 2로 처리한다.
3. 기존 map의 1의 자리마다 상하좌우로 탐색하여 2가 2번이상 탐색되면 0으로 처리한다.
4. 위의 과정을 전부 0이 될때까지 반복하고 횟수를 출력한다.

---

> **코드**
> 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static int N;
	static int M;
	static int[][] map;
	static int[][] map2;
	static int[][] visited;
	static int count;
	static Queue<point> que;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int answer;
	
	static class point {
		int y;
		int x;
		
		public point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String[] s2 = s.split(" ");

		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		map = new int[N][M];
		map2 = new int[N][M];
		que = new ArrayDeque<>();
		
		for(int i=0;i<N;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(s2[j]);
			}
		}
		
		while(true) {
			visited = new int[N][M];
			mapcopy();
			map2[0][0] = 2;
			visited[0][0] = 1;
			dfs(0, 0);
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j] == 1) {
						count = 0;
						for(int k=0;k<4;k++) {
							int ny = i + dy[k];
							int nx = j + dx[k];
							
							if(ny>=0 && ny<N && nx>=0 && nx<M && map2[ny][nx] == 2) {
								count++;
							}
						}
						if(count >= 2)
							que.offer(new point(i, j));
					}
				}
			}
			
			if(que.isEmpty())
				break;
			else {
				while(!que.isEmpty()) {
					point p = que.poll();
					
					map[p.y][p.x] = 0;
				}
				answer++;
			}
			
		}
		
		System.out.println(answer);

	}

	private static void dfs(int y, int x) {
		
		for(int i=0;i<4;i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny>=0 && ny<N && nx>=0 && nx<M && visited[ny][nx] == 0 && map2[ny][nx] == 0) {
				visited[ny][nx] = 1;
				map2[ny][nx] = 2;
				dfs(ny, nx);
			}
		}
	}
	
	private static void mapcopy() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map2[i][j] = map[i][j];
			}
		}
	}

}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 기존 치즈 문제를 풀어봤다면 쉽게 풀린다.
- 외부 공기 처리를 잘 구현했다면 어렵지 않다

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 30분 / 260ms / 21684KB / 2085B

알고리즘 분류

- [구현](https://www.acmicpc.net/problem/tag/102)
- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [시뮬레이션](https://www.acmicpc.net/problem/tag/141)
- [너비 우선 탐색](https://www.acmicpc.net/problem/tag/126)
- [깊이 우선 탐색](https://www.acmicpc.net/problem/tag/127)