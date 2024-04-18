# [BOJ][G4] 알파벳/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/1987)]

[문제 내용 전체]

## 문제

세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (1행 1열) 에는 말이 놓여 있다.

말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.

좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 R과 C가 빈칸을 사이에 두고 주어진다. (1 ≤ R,C ≤ 20) 둘째 줄부터 R개의 줄에 걸쳐서 보드에 적혀 있는 C개의 대문자 알파벳들이 빈칸 없이 주어진다.

## 출력

첫째 줄에 말이 지날 수 있는 최대의 칸 수를 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

중복 처리를 hashset에 저장을하면서 dfs탐색을 하였다.

1. 2차원 문자 맵 생성
2. dfs 구현
3. 각 방문마다 hset에 넣어보면서 null이 안뜨면 진행 아니면 아웃으로 작성하였다.
4. 모든 경우를 보기 위해서 방문후 방문 취소를 진행해 주었다.
5. 가장 큰 cnt값을 answer에 저장하고 출력하였다.

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
import java.util.HashSet;

public class test {

	static int R;
	static int C;
	static char[][] map;
	static int[][] visited;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static HashSet<Character> hset;
	static int answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		R = Integer.parseInt(s2[0]);
		C = Integer.parseInt(s2[1]);
		map = new char[R][C];
		visited = new int[R][C];
		hset = new HashSet<>();
		
		for(int i=0;i<R;i++) {
			s = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		visited[0][0] = 1;
		hset.add(map[0][0]);
		dfs(0,0,1);
		
		bw.append(answer+"");
		bw.append("\n");
		bw.flush();
		bw.close();
	}

	private static void dfs(int y, int x, int cnt) {
		
		answer = Math.max(answer, cnt);

		for(int i=0;i<4;i++) {
			
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny>=0 && ny<R && nx>=0 && nx<C && visited[ny][nx] == 0 && hset.add(map[ny][nx])) {
				visited[ny][nx] = 1;
				dfs(ny, nx, cnt+1);
				visited[ny][nx] = 0;
				hset.remove(map[ny][nx]);
			}
		}
	}
}

```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 중복처리하는 방법만 잘 떠올리면 금방푸는 문제였다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 30분 / 2616ms / 300836KB / 1444B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [깊이 우선 탐색](https://www.acmicpc.net/problem/tag/127)
- [백트래킹](https://www.acmicpc.net/problem/tag/5)