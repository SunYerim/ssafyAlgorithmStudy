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
