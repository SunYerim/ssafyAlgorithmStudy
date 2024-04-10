# [BOJ][G5] 상어초등학교/ (WA)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/21608)]

[문제 내용 전체]

## 문제

상어 초등학교에는 교실이 하나 있고, 교실은 N×N 크기의 격자로 나타낼 수 있다. 학교에 다니는 학생의 수는 N2명이다. 오늘은 모든 학생의 자리를 정하는 날이다. 학생은 1번부터 N2번까지 번호가 매겨져 있고, (r, c)는 r행 c열을 의미한다. 교실의 가장 왼쪽 윗 칸은 (1, 1)이고, 가장 오른쪽 아랫 칸은 (N, N)이다.

선생님은 학생의 순서를 정했고, 각 학생이 좋아하는 학생 4명도 모두 조사했다. 이제 다음과 같은 규칙을 이용해 정해진 순서대로 학생의 자리를 정하려고 한다. 한 칸에는 학생 한 명의 자리만 있을 수 있고, |r1 - r2| + |c1 - c2| = 1을 만족하는 두 칸이 (r1, c1)과 (r2, c2)를 인접하다고 한다.

1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.

예를 들어, N = 3이고, 학생 N2명의 순서와 각 학생이 좋아하는 학생이 다음과 같은 경우를 생각해보자.

| 학생의 번호 | 좋아하는 학생의 번호 |
| --- | --- |
| 4 | 2, 5, 1, 7 |
| 3 | 1, 9, 4, 5 |
| 9 | 8, 1, 2, 3 |
| 8 | 1, 9, 3, 4 |
| 7 | 2, 3, 4, 8 |
| 1 | 9, 2, 5, 7 |
| 6 | 5, 2, 3, 4 |
| 5 | 1, 9, 2, 8 |
| 2 | 9, 3, 1, 4 |

가장 먼저, 4번 학생의 자리를 정해야 한다. 현재 교실의 모든 칸은 빈 칸이다. 2번 조건에 의해 인접한 칸 중에서 비어있는 칸이 가장 많은 칸인 (2, 2)이 4번 학생의 자리가 된다.

|  |  |  |
| --- | --- | --- |
|  | 4 |  |
|  |  |  |

다음 학생은 3번이다. 1번 조건을 만족하는 칸은 (1, 2), (2, 1), (2, 3), (3, 2) 이다. 이 칸은 모두 비어있는 인접한 칸이 2개이다. 따라서, 3번 조건에 의해 (1, 2)가 3번 학생의 자리가 된다.

|  | 3 |  |
| --- | --- | --- |
|  | 4 |  |
|  |  |  |

다음은 9번 학생이다. 9번 학생이 좋아하는 학생의 번호는 8, 1, 2, 3이고, 이 중에 3은 자리에 앉아있다. 좋아하는 학생이 가장 많이 인접한 칸은 (1, 1), (1, 3)이다. 두 칸 모두 비어있는 인접한 칸이 1개이고, 행의 번호도 1이다. 따라서, 3번 조건에 의해 (1, 1)이 9번 학생의 자리가 된다.

| 9 | 3 |  |
| --- | --- | --- |
|  | 4 |  |
|  |  |  |

이번에 자리를 정할 학생은 8번 학생이다. (2, 1)이 8번 학생이 좋아하는 학생과 가장 많이 인접한 칸이기 때문에, 여기가 그 학생의 자리이다.

| 9 | 3 |  |
| --- | --- | --- |
| 8 | 4 |  |
|  |  |  |

7번 학생의 자리를 정해보자. 1번 조건을 만족하는 칸은 (1, 3), (2, 3), (3, 1), (3, 2)로 총 4개가 있고, 비어있는 칸과 가장 많이 인접한 칸은 (2, 3), (3, 2)이다. 행의 번호가 작은 (2, 3)이 7번 학생의 자리가 된다.

| 9 | 3 |  |
| --- | --- | --- |
| 8 | 4 | 7 |
|  |  |  |

이런식으로 학생의 자리를 모두 정하면 다음과 같다.

| 9 | 3 | 2 |
| --- | --- | --- |
| 8 | 4 | 7 |
| 5 | 6 | 1 |

이제 학생의 만족도를 구해야 한다. 학생의 만족도는 자리 배치가 모두 끝난 후에 구할 수 있다. 학생의 만족도를 구하려면 그 학생과 인접한 칸에 앉은 좋아하는 학생의 수를 구해야 한다. 그 값이 0이면 학생의 만족도는 0, 1이면 1, 2이면 10, 3이면 100, 4이면 1000이다.

학생의 만족도의 총 합을 구해보자.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 N이 주어진다. 둘째 줄부터 N2개의 줄에 학생의 번호와 그 학생이 좋아하는 학생 4명의 번호가 한 줄에 하나씩 선생님이 자리를 정할 순서대로 주어진다.

학생의 번호는 중복되지 않으며, 어떤 학생이 좋아하는 학생 4명은 모두 다른 학생으로 이루어져 있다. 입력으로 주어지는 학생의 번호, 좋아하는 학생의 번호는 N2보다 작거나 같은 자연수이다. 어떤 학생이 자기 자신을 좋아하는 경우는 없다.

## 출력

첫째 줄에 학생의 만족도의 총 합을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

완전 빡구현인거 같아 천천히 구현해 보았는데 상어를 앉히는 곳에서 많이 꼬여 풀지 못하였다. 해답을 찾아보니 hashmap을 활용한 정렬을 통해 구현을 했다.

---

> **코드**
> 

```java
package week11.BOJ_G5_21608_상어초등학교;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

/*
	1. map 순회하면서 앉을 자리체크
	2. 1번 2번 3번 조건 체크
	3. 앉히기
*/

public class BOJ_G5_21608_상어초등학교 {

	static int N;
	static int[][] list;
	static int[][] map;
	static checkplace[][] map2;
	static int[][] map3;
	static int answer;
	static int count; // 빈칸 카운트
	static int count2; // 친구 카운트
	static int maxblank;
	static int maxfriend;
	static int blankcount;
	static int friendcount;
	static int miny = Integer.MAX_VALUE;
	static int minx = Integer.MAX_VALUE;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static class checkplace {
		int f;
		int b;

		public checkplace(int f, int b) {
			this.f = f;
			this.b = b;
		}

		@Override
		public String toString() {
			return "checkplace [f=" + f + ", b=" + b + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		list = new int[N * N][5];
		map = new int[N][N];
		map3 = new int[N][N];

		for (int i = 0; i < N * N; i++) {
			String s = br.readLine();
			String[] s2 = s.split(" ");
			for (int j = 0; j < 5; j++) {
				list[i][j] = Integer.parseInt(s2[j]);
			}
		}

		for (int cnt = 0; cnt < N * N; cnt++) {
			map2 = new checkplace[N][N];
			map3 = new int[N][N];
			maxblank = 0;
			maxfriend = 0;
			blankcount = 0;
			friendcount = 0;
			miny = Integer.MAX_VALUE;
			minx = Integer.MAX_VALUE;
			checkarea(cnt);
			sitdown(cnt);
		}
		
		for(int i=0;i<N*N;i++) {
			int count = calc(i);
			
			if(count == 0)
				answer+=0;
			else if(count == 1)
				answer += 1;
			else if(count == 2)
				answer += 10;
			else if(count == 3)
				answer += 100;
			else
				answer += 1000;
		}

		bw.append(answer + "");
		bw.append("\n");
		bw.flush();
		bw.close();
	}

	private static int calc(int cnt) {
		
		int count = 0;
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] == list[cnt][0]) {
					for(int k=0;k<4;k++) {
						int ny = i + dy[i];
						int nx = j + dx[i];
						
						if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
							for(int d=1;d<5;d++) {
								if(map[ny][nx] == list[cnt][d]) {
									count++;
									break;
								}
							}
						}
					}
				}
			}
		}
		
		return count;
	}

	static void checkarea(int cnt) { // 앉을 자리 체크

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {

					count = 0;
					count2 = 0;

					for (int k = 0; k < 4; k++) {
						int ny = i + dy[k];
						int nx = j + dx[k];

						if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
							if (map[ny][nx] == 0) // 빈칸일 시
								count++;
							else { // 아닐 시
								for(int d=1;d<5;d++) {
									if(map[ny][nx] == list[cnt][d]) {
										count2++;
										break;
									}
								}
							}
						}
					}
					map2[i][j] = new checkplace(count2, count);
					maxblank = Math.max(maxblank, count);
					maxfriend = Math.max(maxfriend, count2);
				}
			}
		}
	}

	static void sitdown(int cnt) {
		
		int fy = 0;
		int fx = 0;
		int by = 0;
		int bx = 0;
		int tmpy = 0;
		int tmpx = 0;
		int cnty = 1;
		int cntx = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 0) {
					if ((map2[i][j].f == maxfriend)) {
						map3[i][j] = 1;
						friendcount++;
						fy = i;
						fx = j;
					}
				}
			}
		}
		
		if(friendcount != 1) { // 친한친구(최대 친한친구의 수)가 있는 자리가 많을때
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map3[i][j] == 1) {
						if ((map2[i][j].b == maxblank)) {
							map3[i][j] = 2;
							blankcount++;
							by = i;
							bx = j;
						}
					}
				}
			}
		}
		else {
			map[fy][fx] = list[cnt][0];
		}
		
		if(blankcount >= 1) {
			if(blankcount == 1) {
				map[by][bx] = list[cnt][0];
			}
			else { // 빈칸(최대 빈칸의 수)의 개수가 많을 때
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if(map3[i][j] == 2) {
							if ((map2[i][j].b == maxblank)) {
								miny = Math.min(miny, i);
								minx = Math.min(minx, j);
								
								if(j >=1 && miny == tmpy) {
									cnty++;
								}
								
								tmpy = miny;
							}
						}
					}
				}
				
				if(cnty > 1) {
					for(int j=0;j<N;j++) {
						if(map[miny][j] == 0 && map2[miny][j].f == maxfriend) {
							map[miny][j] = list[cnt][0];
							break;
						}
					}
				}
				else
					map[miny][minx] = list[cnt][0];
			}
		}
	}
}

```

참고 코드

```java
import java.util.*;
import java.io.*;

public class Main{
	static int map[][];
	
	static HashMap<Integer,Integer[]> hashMap=new HashMap<>();
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	public static void main(String[] args) throws IOException{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		
		for(int i=0;i<n*n;i++) {
			st=new StringTokenizer(br.readLine());
			int s1 = Integer.parseInt(st.nextToken());
			int s2 = Integer.parseInt(st.nextToken());
			int s3 = Integer.parseInt(st.nextToken());
			int s4 = Integer.parseInt(st.nextToken());
			int s5 = Integer.parseInt(st.nextToken());
			//해시맵에 좋아하는 친구 저장.
			hashMap.put(s1,new Integer[] {s2,s3,s4,s5});
			//현재 학생의 자리를 구함
			putStudent(s1);
		}
		//만족도 조사.
		int sum = 0;
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length;j++) {
				
				int count = 0;
				Integer friends[] = hashMap.get(map[i][j]);
				for(int k=0;k<4;k++) {
					int nextY=i+dy[k];
					int nextX=j+dx[k];
					if(nextY<0||nextX<0||nextY>=map.length||nextX>=map.length)
						continue;
					int now = map[nextY][nextX];
					
					//상,하,좌,우가 친구면 count 증가
					for(int p=0;p<4;p++)
						if(now == friends[p]) count++;
					
				}
				//count 개수에 따라 sum 증가
				switch(count) {
				case 1: sum += 1; break;
				case 2: sum += 10; break;
				case 3: sum += 100; break;
				case 4: sum += 1000; break;
				}
				
				
			}
		}
		
		System.out.println(sum);
		
		

	}
	public static void putStudent(int student) {
		
		Integer friends[] = hashMap.get(student);
		int f1 = friends[0];
		int f2 = friends[1];
		int f3 = friends[2];
		int f4 = friends[3];
		
		
		ArrayList<Integer[]> list=new ArrayList<>();
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map.length;j++) {
				int friendsCount = 0;
				int emptyCount = 0;
				for(int k=0;k<4;k++) {
					int nextY=i+dy[k];
					int nextX=j+dx[k];
					if(nextY<0||nextX<0||nextY>=map.length||nextX>=map.length)
						continue;
					//해당 좌표가 좋아하는 학생이면 친구 count 증가
					//해당 좌표가 빈 공간이면 빈 공간 count 증가
					int now = map[nextY][nextX];
					if(now==f1||now==f2||now==f3||now==f4)
						friendsCount++;
					if(now==0)
						emptyCount++;
					
				}
				list.add(new Integer[] {friendsCount,emptyCount,i,j});
				
			}
		}
		//정렬함. 정렬 기준 -> 선호 학생의 수, 빈 자리의 수, 행, 열
		Collections.sort(list,new Comparator<>() {
			@Override
			public int compare(Integer n1[],Integer n2[]) {
				if(n1[0]<n2[0]) return 1;
				else if(n1[0]==n2[0]) {
					if(n1[1]<n2[1]) return 1;
					else if(n1[1]==n2[1]) {
						if(n1[2]>n2[2]) return 1;
						else if(n1[2]==n2[2]) {
							if(n1[3]>n2[3]) return 1;
						}
						
					}
				}
				return -1;
			}
		});
		
		//0번째 인덱스부터 시작해서 해당 좌표가 0이 아니라면, 그 좌표가 현재 학생의 자리가 됨.
		for(int i=0;i<list.size();i++) {
			int y = list.get(i)[2];
			int x = list.get(i)[3];
			if(map[y][x]==0) {
				map[y][x] = student;
				return;
			}
			
		}
		
		
		
	}

}

```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 자료구조를 확실히 알아야 풀 수 있는거 같다

문제 풀이 시간/ 실행시간/ 메모리/코드길이

알고리즘 분류

- 구현