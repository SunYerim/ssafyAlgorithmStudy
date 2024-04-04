# [BOJ][G4] PuyoPuyo / (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/11559)]

[문제 내용 전체]

## 문제

뿌요뿌요의 룰은 다음과 같다.

> 필드에 여러 가지 색깔의 뿌요를 놓는다. 뿌요는 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올 때까지 아래로 떨어진다.
> 
> 
> 뿌요를 놓고 난 후, 같은 색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 연결된 같은 색 뿌요들이 한꺼번에 없어진다. 이때 1연쇄가 시작된다.
> 
> 뿌요들이 없어지고 나서 위에 다른 뿌요들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어지게 된다.
> 
> 아래로 떨어지고 나서 다시 같은 색의 뿌요들이 4개 이상 모이게 되면 또 터지게 되는데, 터진 후 뿌요들이 내려오고 다시 터짐을 반복할 때마다 1연쇄씩 늘어난다.
> 
> 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.
> 

남규는 최근 뿌요뿌요 게임에 푹 빠졌다. 이 게임은 1:1로 붙는 대전게임이라 잘 쌓는 것도 중요하지만, 상대방이 터뜨린다면 연쇄가 몇 번이 될지 바로 파악할 수 있는 능력도 필요하다. 하지만 아직 실력이 부족하여 남규는 자기 필드에만 신경 쓰기 바쁘다. 상대방의 필드가 주어졌을 때, 연쇄가 몇 번 연속으로 일어날지 계산하여 남규를 도와주자!

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

총 12개의 줄에 필드의 정보가 주어지며, 각 줄에는 6개의 문자가 있다.

이때 `.`은 빈공간이고 `.`이 아닌것은 각각의 색깔의 뿌요를 나타낸다.

`R`은 빨강, `G`는 초록, `B`는 파랑, `P`는 보라, `Y`는 노랑이다.

입력으로 주어지는 필드는 뿌요들이 전부 아래로 떨어진 뒤의 상태이다. 즉, 뿌요 아래에 빈 칸이 있는 경우는 없다.

## 출력

현재 주어진 상황에서 몇연쇄가 되는지 출력한다. 하나도 터지지 않는다면 0을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

뿌요뿌요 맵을 작성 한 후 ‘.’이 아닌 문자가 오면 dfs를 타서 개수를 새었다. 그 후 개수가 4개 이상이면 visited 배열을 체크 하였다. 그리고 전부 탐색 한 뒤 체크 된 부분들을 터트리고 밑으로 내리는 작업을 반복하였다. 더 이상 부술 수 있는 구슬이 없으면 탈출 후 정답을 도출했다.

---

> **코드**
> 

```java
package week10.BOJ_G4_11559_PuyoPuyo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G4_11559_PuyoPuyo {

	static char[][] map;
	static int[][] visited;
	static int[][] visited2;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int combo;
	static char target;
	static int cnt;
	static int tmp = 1;
	static int tmp_end;
	static int answer;
	static boolean flag = false;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[12][6];
		visited2 = new int[12][6];
		
		for(int i=0;i<12;i++) {
			String s = br.readLine();
			for(int j=0;j<6;j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		while(true) {
			
			visited2 = new int[12][6];
			visited = new int[12][6];
			flag = false;
			
			for(int i=11;i>=0;i--) {
				for(int j=0;j<6;j++) {
					if(map[i][j] != '.') {
						tmp_end = tmp;
						target = map[i][j];
						visited2[i][j] = 1;
						cnt=1;
						dfs(i, j);
						
						if(cnt>=4) {
							copy();
							visited2 = new int[12][6];
						}
						else
							visited2 = new int[12][6];
					}
				}
			}
			
			change();
			down();
			
			for(int i=0;i<12;i++) {
				for(int j=0;j<6;j++) {
					if(visited[i][j] != 0)
						flag = true;
				}
			}
			
			if(!flag)
				break;
			else {
				combo++;
				answer = Math.max(answer, combo);
			}
			
			
		}
		
		System.out.println(answer);
	}

	private static void dfs(int y, int x) {
		
		for(int i=0;i<4;i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny>=0 && ny<12 && nx>=0 && nx<6 && visited2[ny][nx] == 0 && map[ny][nx] == target) {
				visited2[ny][nx] = 1;
				cnt++;
				dfs(ny, nx);
			}
		}
	}
	
	static void copy() {
		for(int i=0;i<12;i++) {
			for(int j=0;j<6;j++) {
				
				visited[i][j] += visited2[i][j];
			}
		}
	}
	
	static void down() {
		
		for(int i=0;i<6;i++) {
			int check_y = 11;
			int check_x = i;
			
			for(int j=11; j>=0; j--) {
				if(map[j][i] != '.') {
					char c = map[check_y][check_x];
					map[check_y][check_x] = map[j][i];
					map[j][i] = c;
					check_y--;
				}
			}
		}
	}
	
	static void change() {
		for(int i=0;i<12;i++) {
			for(int j=0;j<6;j++) {
				if(visited[i][j] != 0)
					map[i][j] = '.';
			}
		}
	}
}

```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- Flood fill 알고리즘만 사용할 줄 알면 충분히 구현할 만 했다
- 문제를 좀 더 꼼꼼히 읽자

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간? / 128ms / 14480KB / 2272B

알고리즘 분류

- [구현](https://www.acmicpc.net/problem/tag/102)
- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [시뮬레이션](https://www.acmicpc.net/problem/tag/141)
- [너비 우선 탐색](https://www.acmicpc.net/problem/tag/126)