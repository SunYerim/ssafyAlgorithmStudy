# [BOJ][G4] 빙산/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/2573)]

[문제 내용 전체]

지구 온난화로 인하여 북극의 빙산이 녹고 있다. 빙산을 그림 1과 같이 2차원 배열에 표시한다고 하자. 빙산의 각 부분별 높이 정보는 배열의 각 칸에 양의 정수로 저장된다. 빙산 이외의 바다에 해당되는 칸에는 0이 저장된다. 그림 1에서 빈칸은 모두 0으로 채워져 있다고 생각한다.

|  |  |  |  |  |  |  |
| --- | --- | --- | --- | --- | --- | --- |
|  | 2 | 4 | 5 | 3 |  |  |
|  | 3 |  | 2 | 5 | 2 |  |
|  | 7 | 6 | 2 | 4 |  |  |
|  |  |  |  |  |  |  |

그림 1. 행의 개수가 5이고 열의 개수가 7인 2차원 배열에 저장된 빙산의 높이 정보

빙산의 높이는 바닷물에 많이 접해있는 부분에서 더 빨리 줄어들기 때문에, 배열에서 빙산의 각 부분에 해당되는 칸에 있는 높이는 일년마다 그 칸에 동서남북 네 방향으로 붙어있는 0이 저장된 칸의 개수만큼 줄어든다. 단, 각 칸에 저장된 높이는 0보다 더 줄어들지 않는다. 바닷물은 호수처럼 빙산에 둘러싸여 있을 수도 있다. 따라서 그림 1의 빙산은 일년후에 그림 2와 같이 변형된다.

그림 3은 그림 1의 빙산이 2년 후에 변한 모습을 보여준다. 2차원 배열에서 동서남북 방향으로 붙어있는 칸들은 서로 연결되어 있다고 말한다. 따라서 그림 2의 빙산은 한 덩어리이지만, 그림 3의 빙산은 세 덩어리로 분리되어 있다.

|  |  |  |  |  |  |  |
| --- | --- | --- | --- | --- | --- | --- |
|  |  | 2 | 4 | 1 |  |  |
|  | 1 |  | 1 | 5 |  |  |
|  | 5 | 4 | 1 | 2 |  |  |
|  |  |  |  |  |  |  |

그림 2

|  |  |  |  |  |  |  |
| --- | --- | --- | --- | --- | --- | --- |
|  |  |  | 3 |  |  |  |
|  |  |  |  | 4 |  |  |
|  | 3 | 2 |  |  |  |  |
|  |  |  |  |  |  |  |

그림 3

한 덩어리의 빙산이 주어질 때, 이 빙산이 두 덩어리 이상으로 분리되는 최초의 시간(년)을 구하는 프로그램을 작성하시오. 그림 1의 빙산에 대해서는 2가 답이다. 만일 전부 다 녹을 때까지 두 덩어리 이상으로 분리되지 않으면 프로그램은 0을 출력한다.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫 줄에는 이차원 배열의 행의 개수와 열의 개수를 나타내는 두 정수 N과 M이 한 개의 빈칸을 사이에 두고 주어진다. N과 M은 3 이상 300 이하이다. 그 다음 N개의 줄에는 각 줄마다 배열의 각 행을 나타내는 M개의 정수가 한 개의 빈 칸을 사이에 두고 주어진다. 각 칸에 들어가는 값은 0 이상 10 이하이다. 배열에서 빙산이 차지하는 칸의 개수, 즉, 1 이상의 정수가 들어가는 칸의 개수는 10,000 개 이하이다. 배열의 첫 번째 행과 열, 마지막 행과 열에는 항상 0으로 채워진다.

## 출력

첫 줄에 빙산이 분리되는 최초의 시간(년)을 출력한다. 만일 빙산이 다 녹을 때까지 분리되지 않으면 0을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

이전에 풀었던 치즈와 유사한 문제인거 같아 접근이 쉬웠다.

하지만 땅이 분리된 경우를 체크하는 것이 치즈와 다른 경우여서 그 케이스를 체크 하는게 포인트 인거 같다.

1. Flood_Fill 알고리즘을 사용해서 땅이 분리될때를 찾는다.
2. 분리된 경우를 찾으면 그때의 녹은 횟수를 반환한다.

---

> **코드**
> 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int M;
	static int[][] map;
	static int[][] map2;
	static int answer;
	static int[][] visited;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int count;
	static int ck;
	static int mapcount;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		map = new int[N][M];
		map2 = new int[N][M];
		
		for(int i=0;i<N;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(s2[j]);
			}
		}
		
		while(true) {
			visited = new int[N][M];
			visited[0][0] = 1;
			dfs(0,0);
			melt();
			
			visited = new int[N][M];
			mapcount = 0;
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j] == 0) {
						map2[i][j] = 2;
						mapcount++;
					}
					else if(map[i][j] != 0)
						map2[i][j] = 0;
				}
			}
			
			if(mapcount == N*M) {
				answer = 0;
				break;
			}
			
			for(int i=0;i<N;i++) {
				int tmp = 0;
				for(int j=0;j<M;j++) {
					if(map[i][j] != 0) {
						visited[i][j] = 1;
						map2[i][j] = 1;
						check(i,j);
						for(int k=0;k<N;k++) {
							for(int p=0;p<M;p++) {
								if(map2[k][p] == 0) {
									ck = 1;
									break;
								}
							}
						}
						tmp++;
						break;
					}
				}
				if(tmp == 1)
					break;
			}
			
			count++;
			answer = count;
			
			if(ck == 1)
				break;
			
		}
		
		System.out.println(answer);
	}
	
	static void dfs(int y, int x) {
		
		for(int i=0;i<4;i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(map[y][x] != 0) {
				int tmp = 0;
				for(int j=0;j<4;j++) {
					int ny2 = y + dy[j];
					int nx2 = x + dx[j];
					if(map[ny2][nx2] == 0)
						tmp++;
				}
				
				map2[y][x] = tmp;
			}
			
			if(ny>=0 && ny<N && nx>=0 && nx<M && visited[ny][nx] == 0) {
				visited[ny][nx] = 1;
				dfs(ny, nx);
			}
		}
	}
	
	static void melt() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map[i][j] -= map2[i][j];
				if(map[i][j] <= 0)
					map[i][j] = 0;
			}
		}
	}
	
	static void check(int y, int x) {
		
		for(int i=0;i<4;i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny>=0 && ny<N && nx>=0 && nx<M && visited[ny][nx] == 0 && map[ny][nx] != 0) {
				visited[ny][nx] = 1;
				map2[ny][nx] = 1;
				check(ny, nx);
			}
		}
	}
}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- dfs 또는 bfs를 잘 사용할 줄 알면 쉽게 풀 수 있는 문제이다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간 / 207860KB / 1196MB / 2573B

알고리즘 분류

- [구현](https://www.acmicpc.net/problem/tag/102)
- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [너비 우선 탐색](https://www.acmicpc.net/problem/tag/126)
- [깊이 우선 탐색](https://www.acmicpc.net/problem/tag/127)