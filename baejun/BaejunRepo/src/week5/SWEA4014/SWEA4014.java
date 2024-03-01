package week5.SWEA4014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA4014 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

//		int T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= 1; test_case++) {
//			sb.append("#" + test_case + " ");
			st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int[][] array = new int[N][N];

			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0; j < N; j++) {
					array[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[] arrayRowCol = new int[N];
			int[] accArray = new int[N];
			int cnt = 0;

			// 가로방향 순회
			for(int i = 0; i < N; i++) {
				/* 배열을 행 또는 열 별로 구분하여 처리하고 초기화(누적합도) */
				for(int j = 0; j < N; j++) {
					arrayRowCol[j] = array[i][j];
					if(j == 0) {
						accArray[j] = 1;
					} else {
						accArray[j] = (arrayRowCol[j]==arrayRowCol[j-1]) ? accArray[j-1]+1 : 1;
					}
				}
				/* 배열이 모든 같은 값이라면 무조건 가능 */
				if(accArray[accArray.length-1] == accArray.length) {
					cnt++;
					continue;
				}
				boolean flag = false;
				/* index별로 돌면서 경사로 설치가 불가능, 또는 활주가 불가능한 상황 만날시 가지치기 하기, 안당하고 끝까지 가면 활주가능으로 판단 */
				for(int k = 1; k < N; k++) {
					if(flag) break;
					if(Math.abs(arrayRowCol[k] - arrayRowCol[k-1]) > 1) { 
						flag = true; 
						break;
					}
					/* 누적합 배열에 -1을 대입할떄, 이미 활주로가 설치 되어 있는지 확인(visited 역할)해서 그렇다면 경사로 중복 설치는 불가능 하므로 안되는 상황임 */
					else if(Math.abs(arrayRowCol[k] - arrayRowCol[k-1]) == 1) {
						if(arrayRowCol[k] > arrayRowCol[k-1]) {
							if(accArray[k-1] < X) {
								flag = true; 
								break;
							}
							for(int q = k-1; q > k-1-X; q--) {
								if(accArray[q] == -1) {
									flag = true;
									break;
								}
								accArray[q] = -1;
							}
						} else {
							if(k+X-1 >= N) {
								flag = true;
								break;
							}
							if(accArray[k+X-1] != X) {
								flag = true;
								break;
							}
							for(int q = k; q < k+X; q++) {
								if(accArray[q] == -1) {
									flag = true;
									break;
								}
								accArray[q] = -1;
							}
						}
					}
				}
				if(!flag) cnt++;
			}
			
			// 세로방향 순회
			for(int i = 0; i < N; i++) {
				/* 배열을 행 또는 열 별로 구분하여 처리하고 초기화(누적합도) */
				for(int j = 0; j < N; j++) {
					arrayRowCol[j] = array[j][i];
					if(j == 0) {
						accArray[j] = 1;
					} else {
						accArray[j] = (arrayRowCol[j]==arrayRowCol[j-1]) ? accArray[j-1]+1 : 1;
					}
				}
				/* 배열이 모든 같은 값이라면 무조건 가능 */
				if(accArray[accArray.length-1] == accArray.length) {
					cnt++;
					continue;
				}
				boolean flag = false;
				/* index별로 돌면서 경사로 설치가 불가능, 또는 활주가 불가능한 상황 만날시 가지치기 하기, 안당하고 끝까지 가면 활주가능으로 판단 */
				for(int k = 1; k < N; k++) {
					if(flag) break;
					if(Math.abs(arrayRowCol[k] - arrayRowCol[k-1]) > 1) { 
						flag = true; 
						break;
					}
					/* 누적합 배열에 -1을 대입할떄, 이미 활주로가 설치 되어 있는지 확인(visited 역할)해서 그렇다면 경사로 중복 설치는 불가능 하므로 안되는 상황임 */
					else if(Math.abs(arrayRowCol[k] - arrayRowCol[k-1]) == 1) {
						if(arrayRowCol[k] > arrayRowCol[k-1]) {
							if(accArray[k-1] < X) {
								flag = true; 
								break;
							}
							for(int q = k-1; q > k-1-X; q--) {
								if(accArray[q] == -1) {
									flag = true;
									break;
								}
								accArray[q] = -1;
							}
						} else {
							if(k+X-1 >= N) {
								flag = true;
								break;
							}
							if(accArray[k+X-1] != X) {
								flag = true;
								break;
							}
							for(int q = k; q < k+X; q++) {
								if(accArray[q] == -1) {
									flag = true;
									break;
								}
								accArray[q] = -1;
							}
						}
					}
				}
				if(!flag) cnt++;
			}
			sb.append(cnt).append("\n");
		}	
		System.out.println(sb);
	}
}