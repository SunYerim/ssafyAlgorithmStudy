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

		int T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
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

			// 가로방향 먼저 순회
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					arrayRowCol[j] = array[i][j];
					if(j == 0) {
						accArray[j] = 1;
					} else {
						accArray[j] = (arrayRowCol[j]==arrayRowCol[j-1]) ? accArray[j-1]+1 : 1;
					}
				}
				if(accArray[accArray.length-1] == accArray.length) {
					cnt++;
					continue;
				}
				boolean[] visited = new boolean[N];
				boolean flag = true;
				for(int j = 1; j < N; j++) {
					if(arrayRowCol[j] == arrayRowCol[j-1]+1) {
						if(accArray[j-1] < X) { 
							flag = false;
						}
						else {
							for(int q = j-1; q > j-1-X; q--) {
								if(visited[q] = true) {
									flag = false;
									break;
								}
								visited[q] = true;
								if(accArray[q] == 1) break;
							}
						}
					} else if(arrayRowCol[j] == arrayRowCol[j-1]-1) {
						int pointer = j+1;
						for(int k = pointer; k < N; k++) {
							if(k == N-1) {
								if(accArray[k] < X) {
									flag = false;
									break;
								} else {

								}
							} else {
								for(int q = k; q > k-X; q--) {
									if(visited[q] = true) {
										flag = false;
										break;
									}
									visited[q] = true;
									if(accArray[q] == 1) break;
								}
							}
							if(accArray[k] == 1) {
								if(accArray[k-1] < X) flag = false;
								else {
									for(int q = k-1; q > k-1-X; q--) {
										if(visited[q] = true) {
											flag = false;
											break;
										}
										visited[q] = true;
										if(accArray[q] == 1) break;
									}
								}
							}
						}
					}
					if(!flag) break;
				}
				if(flag) cnt++;
			}

			// 가로방향 먼저 순회
			// 가로방향 먼저 순회
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					arrayRowCol[j] = array[i][j];
					if(j == 0) {
						accArray[j] = 1;
					} else {
						accArray[j] = (arrayRowCol[j]==arrayRowCol[j-1]) ? accArray[j-1]+1 : 1;
					}
				}
				if(accArray[accArray.length-1] == accArray.length) {
					cnt++;
					continue;
				}
				boolean[] visited = new boolean[N];
				boolean flag = true;
				for(int j = 1; j < N; j++) {
					if(arrayRowCol[j] == arrayRowCol[j-1]+1) {
						if(accArray[j-1] < X) { 
							flag = false;
						}
						else {
							for(int q = j-1; q > j-1-X; q--) {
								if(visited[q] = true) {
									flag = false;
									break;
								}
								visited[q] = true;
								if(accArray[q] == 1) break;
							}
						}
					} else if(arrayRowCol[j] == arrayRowCol[j-1]-1) {
						int pointer = j+1;
						for(int k = pointer; k < N; k++) {
							if(k == N-1) {
								if(accArray[k] < X) {
									flag = false;
									break;
								} else {

								}
							} else {
								for(int q = k; q > k-X; q--) {
									if(visited[q] = true) {
										flag = false;
										break;
									}
									visited[q] = true;
									if(accArray[q] == 1) break;
								}
							}
							if(accArray[k] == 1) {
								if(accArray[k-1] < X) flag = false;
								else {
									for(int q = k-1; q > k-1-X; q--) {
										if(visited[q] = true) {
											flag = false;
											break;
										}
										visited[q] = true;
										if(accArray[q] == 1) break;
									}
								}
							}
						}
					}
					if(!flag) break;
				}
				if(flag) cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}
