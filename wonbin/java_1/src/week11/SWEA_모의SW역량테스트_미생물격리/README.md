# [SWEA][모의SW역량테스트] 미생물격리/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV597vbqAH0DFAVl)]

[문제 내용 전체]

정사각형 구역 안에 K개의 미생물 군집이 있다.

이 구역은 가로 N개, 세로 N개, 총 N * N 개의 동일한 크기의 정사각형 셀들로 이루어져 있다.

미생물들이 구역을 벗어나는걸 방지하기 위해, 가장 바깥쪽 가장자리 부분에 위치한 셀들에는 특수한 약품이 칠해져 있다.

[Fig. 1]은 9개의 군집이 한 변이 7개의 셀로 이루어진 구역에 배치되어 있는 예이다.

가장자리의 빨간 셀은 약품이 칠해져 있는 셀이다.

https://swexpertacademy.com/main/common/fileDownload.do?downloadType=CKEditorImages&fileId=AV599Ik6AH8DFAVl

**[Fig. 1]**

① 최초 각 미생물 군집의 위치와 군집 내 미생물의 수, 이동 방향이 주어진다. 약품이 칠해진 부분에는 미생물이 배치되어 있지 않다. 이동방향은 상, 하, 좌, 우 네 방향 중 하나이다.

② 각 군집들은 1시간마다 이동방향에 있는 다음 셀로 이동한다.

③ 미생물 군집이 이동 후 약품이 칠해진 셀에 도착하면 군집 내 미생물의 절반이 죽고, 이동방향이 반대로 바뀐다.

미생물 수가 홀수인 경우 반으로 나누어 떨어지지 않으므로, 다음과 같이 정의한다.

**살아남은 미생물 수 = 원래 미생물 수를 2로 나눈 후 소수점 이하를 버림 한 값**

따라서 군집에 미생물이 한 마리 있는 경우 살아남은 미생물 수가 0이 되기 때문에, 군집이 사라지게 된다,

④ 이동 후 두 개 이상의 군집이 한 셀에 모이는 경우 군집들이 합쳐지게 된다.

합쳐 진 군집의 미생물 수는 군집들의 미생물 수의 합이며, 이동 방향은 군집들 중 미생물 수가 가장 많은 군집의 이동방향이 된다.

**합쳐지는 군집의 미생물 수가 같은 경우는 주어지지 않으므로 고려하지 않아도 된다.**

M 시간 동안 이 미생물 군집들을 격리하였다. M시간 후 남아 있는 미생물 수의 총합을 구하여라.

시간에 지남에 따라 군집이 변하는 예를 보자.

[Fig. 2]은 최초 군집의 배치를 그림으로 표현한 것이다. 이는 예제 입력 1번과 동일하다. (N = 7, K = 9)

https://swexpertacademy.com/main/common/fileDownload.do?downloadType=CKEditorImages&fileId=AV59-UbaAIIDFAVl

**[Fig. 2]**

1시간 후 [Fig. 3]과 같이 군집들이 이동한다.

A 군집은 약품이 칠해진 구역(가장 자리의 빨간 셀)로 이동하게 되어, 미생물 중3마리만 남고 나머지는 죽는다. 이동 방향은 상에서 하로 바뀐다.

D, E, F 군집은 모두 세로 위치 3, 가로 위치 3에 위치한 셀로 모이게 되며, 합쳐진 군집의 미생물 수는 8 + 14 + 3 = 25가 된다.

E 군집의 미생물 수가 가장 많기 때문에, 합쳐 진 군집의 이동 방향은 E 군집의 이동 방향인 상이 된다.

G, H 군집도 세로 위치 2, 가로 위치 5에 위치한 셀로 모이게 되며, 미생물 수는 108이 되고 이동 방향은 상이 된다.

https://swexpertacademy.com/main/common/fileDownload.do?downloadType=CKEditorImages&fileId=AV59-iS6AIUDFAVl

**[Fig. 3]**

시작으로부터 2시간이 지났을 때, [Fig. 4]와 같이 군집들이 이동한다.

A, B 그룹은 이동 중 섞이지 않고 각 그룹의 이동 방향으로 움직이는데, B 그룹은 약품이 칠해진 셀로 이동하므로 미생물 수가 7에서 3으로 반감하고, 이동 방향이 상에서 하로 바뀐다.

https://swexpertacademy.com/main/common/fileDownload.do?downloadType=CKEditorImages&fileId=AV59-wPKAI4DFAVl

**[Fig. 4]**

2시간이 지난 후, 남아 있는 미생물 수는 총 3 + 3 + 5 + 25 + 108 + 1 = 145이다.

---

> **제한사항**
> 

입력/출력 제한사항

**[제약사항]**

1. 시간제한 : 최대 50개 테스트 케이스를 모두 통과하는데, C/C++/Java 모두 5초

2. 구역의 모양은 정사각형으로 주어지며, 한 변의 셀의 개수 N은 5이상 100이하의 정수이다. (5 ≤ N ≤ 100)

3. 최초 배치되어 있는 미생물 군집의 개수 K는 5이상 1,000이하의 정수이다. (5 ≤ K ≤ 1,000)

4. 격리 시간 M은 1이상 1,000이하의 정수이다. (1 ≤ M ≤ 1,000)

5. 최초 약품이 칠해진 가장자리 부분 셀에는 미생물 군집이 배치되어 있지 않다.

6. 최초에 둘 이상의 군집이 동일한 셀에 배치되는 경우는 없다.

7. 각 군집 내 미생물 수는 1 이상 10,000 이하의 정수이다.

8. 군집의 이동방향은 상하좌우 4방향 중 한 방향을 가진다. (상: 1, 하: 2, 좌: 3, 우: 4)

9. 주어진 입력으로 진행하였을 때, 동일한 셀에 같은 미생물 수를 갖는 두 군집이 모이는 경우는 발생하지 않는다.

10.  각 군집의 정보는 세로 위치, 가로 위치, 미생물 수, 이동 방향 순으로 주어진다. 각 위치는 0번부터 시작한다.

**[입력]**

첫 줄에는 총 테스트 케이스의 개수 T가 주어진다.

그 다음 줄부터 T개의 테스트 케이스가 차례대로 주어진다.

각 테스트 케이스의 첫째 줄에는 구역의 한 변에 있는 셀의 개수 N, 격리 시간 M, 미생물 군집의 개수 K가 순서대로 주어지며, 다음 K줄에 걸쳐서 미생물 군집 K개의 정보가 주어진다.

미생물 군집의 정보는 세로 위치, 가로 위치, 미생물 수, 이동 방향 순으로 4개의 정수가 주어진다.

**[출력]**

테스트 케이스의 개수 만큼 T개의 줄에 각 테스트 케이스에 대한 답을 출력한다.

각 줄은 “#x”로 시작하고, 공백을 하나 둔 후 정답을 출력한다. (x는 테스트 케이스의 번호이며, 1번부터 시작한다.)

출력해야 할 정답은 M시간 후 남아 있는 미생물 수의 총 합이다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

보자마자 그냥 구현인거 같아서 하나하나 접근해서 풀었다.

그런데 풀다가 미생물이 연속해서 근접해 있는 경우를 생각하지 않아서 처음부터 다시 풀었다.

하나하나 옮기는게 아니라 전체를 탐색한 후 맵에 미생물이 존재하면 큐에 넣어서 관리를 하고 한번에 옮기는 형식으로 변경해서 풀었다.

---

> **코드**
> 

```java
package week11.SWEA_모의SW역량테스트_미생물격리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class SWEA_모의SW역량테스트_미생물격리 {
	
	static int N;
	static int M;
	static int K;
	static ms[][] map;
	static ms[][] map2; // 더할때 비교하기 용 맵
	static long answer;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int[][] visited;
	static Queue<ms> que = new ArrayDeque<>();
	
	static class ms{
		int y;
		int x;
		long value;
		int d;
		
		public ms(int y, int x, long value, int d) {
			this.y = y;
			this.x = x;
			this.value = value;
			this.d = d;
		}

		@Override
		public String toString() {
			return "ms [value=" + value + ", d=" + d + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			String s = br.readLine();
			String[] s2 = s.split(" ");
			
			N = Integer.parseInt(s2[0]);
			M = Integer.parseInt(s2[1]);
			K = Integer.parseInt(s2[2]);
			map = new ms[N][N];
			map2 = new ms[N][N];
			answer = 0;
			
			for(int i=0;i<K;i++) {
				s = br.readLine();
				s2 = s.split(" ");
				int y = Integer.parseInt(s2[0]);
				int x = Integer.parseInt(s2[1]);
				long bio = Long.parseLong(s2[2]);
				int d = Integer.parseInt(s2[3]);
				
				if(d==1)
					d--;
				else if(d==4)
					d-=3;
				
				map[y][x] = new ms(y, x, bio, d); // 방향 인덱스가 0부터 시작함
			}
			
			for(int k=0;k<M;k++) {
				
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(map[i][j] != null) {
							que.offer(map[i][j]);
							map[i][j] = null;
						}
					}
				}
				
				while(!que.isEmpty()) {
					ms next = que.poll();
					int ny = next.y + dy[next.d];
					int nx = next.x + dx[next.d];
					
					if(ny == 0 || ny == N-1 || nx == 0 || nx == N-1) { // 도착지가 약물이면
						if(map[ny][nx] != null) { // 도착지에 미생물이 존재하면
							int d = next.d;
							ms tmp = map[ny][nx];
							
							if(d==0 || d==1)
								d+=2;
							else
								d-=2;
							
							next = new ms(next.y, next.x, next.value/2, d);
							
							if(map2[ny][nx].value > next.value) { // 기존 미생물이 더 많으면
								map[ny][nx] = new ms(ny, nx, map[ny][nx].value + next.value, map[ny][nx].d);
							}
							else {
								map2[ny][nx] = new ms(ny, nx, next.value, next.d);
								map[ny][nx] = new ms(ny, nx, map[ny][nx].value + next.value, next.d);
							}
						}
						else { // 존재하지 않으면
							int d = next.d;
							
							if(d==0 || d==1)
								d+=2;
							else
								d-=2;
							
							map[ny][nx] = new ms(ny, nx, next.value/2, d);
							map2[ny][nx] = new ms(ny, nx, next.value/2, d);
						}
					}
					else { // 도착지가 약물이 아니면
						if(map[ny][nx] != null) { // 도착지에 미생물이 존재하면
							
							if(map2[ny][nx].value > next.value) { // 기존 미생물이 더 많으면
								map[ny][nx] = new ms(ny, nx, map[ny][nx].value + next.value, map[ny][nx].d);
							}
							else {
								map2[ny][nx] = new ms(ny, nx, next.value, next.d);
								map[ny][nx] = new ms(ny, nx, map[ny][nx].value + next.value, next.d);
							}
						}
						else { // 존재하지 않으면
							
							map[ny][nx] = new ms(ny, nx, next.value, next.d);
							map2[ny][nx] = new ms(ny, nx, next.value, next.d);
						}
					}
				}
			}
			
			calcmap();
			
			bw.append("#"+tc+""+" "+answer+"");
			bw.append("\n");
		}
		
		bw.flush();
		bw.close();
	}

	private static void calcmap() {

		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] != null)
					answer += map[i][j].value;
			}
		}
	}

}

```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 초반에 잘못풀어서 생각보다 오래 결렸다.
- 구현을 풀때는 최대한 모든 경우를 생각해보자

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간 40분  / 487 ms / 113,996 kb / 3,831

알고리즘 분류

- 구현