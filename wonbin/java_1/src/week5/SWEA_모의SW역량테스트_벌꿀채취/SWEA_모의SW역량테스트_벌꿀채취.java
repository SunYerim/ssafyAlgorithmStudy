package week5.SWEA_모의SW역량테스트_벌꿀채취;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class SWEA_모의SW역량테스트_벌꿀채취 {

	static int N; // 벌집 크기
	static int M; // 선택할 꿀통 개수
	static int C; // 꿀통의 최대양
	static int[][] bees;
	static int[][] visited;
	static int[] honey1;
	static int[] honey2;
	static int[] path = new int[5];
	static int sum;
	static int max1 = Integer.MIN_VALUE;
	static int max2 = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			bw.append("#" + test_case + "" + " ");

			String s = br.readLine();
			String[] s2 = s.split(" ");

			N = Integer.parseInt(s2[0]);
			M = Integer.parseInt(s2[1]);
			C = Integer.parseInt(s2[2]);
			bees = new int[N][N];
			int tmp = 0;
			int tmp2 = 0;
			int result = 0;

			for (int i = 0; i < N; i++) {
				s = br.readLine();
				s2 = s.split(" ");
				for (int j = 0; j < N; j++) {
					bees[i][j] = Integer.parseInt(s2[j]);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited = new int[N][N];
					honey1 = new int[N];
					max1 = 0;
					max2 = 0;
					tmp = 0;
					for (int k = j; k < j+M; k++) {
						if (k >= N) {
							tmp++;
							break;
						}
						honey1[k] = bees[i][k];
						visited[i][k]++;
					}

					if (tmp != 0) {
						tmp = 0;
						break;
					}

					for (int k = 1; k <= M; k++) {
						comb(0, 0, k);
					}

					for (int k = 0; k < N; k++) {
						for (int p = 0; p < N; p++) {
							honey2 = new int[N];
							for (int aa = p; aa < p+M; aa++) {
								if (aa >= N) {
									tmp2++;
									break;
								}
								if(visited[k][aa] != 0) {
									tmp2++;
									break;
								}
								
								honey2[aa] = bees[k][aa];
							}

							if (tmp2 != 0) {
								tmp2 = 0;
								continue;
							}

							for (int aa = 1; aa <= M; aa++) {
								comb2(0, 0, aa);
							}
							
							result = Math.max(result, max1+max2);
						}
					}
				}
			}
			

			bw.append(result + "");
			bw.append("\n");
			bw.flush();

		}

		bw.close();
	}

	static void comb(int cnt, int start, int lev) {

		if (cnt == lev) {
			sum = 0;

			for (int i = 0; i < cnt; i++) {
				sum += path[i];
			}

			if (sum <= C) {
				int tmp = 0;
				for(int i=0;i<cnt;i++) {
					tmp += path[i]*path[i];
				}
				max1 = Math.max(tmp, max1);
			}
		}

		for (int i = start; i < N; i++) {
			if (honey1[i] == 0)
				continue;
			path[cnt] = honey1[i];
			comb(cnt + 1, i + 1, lev);
		}

	}

	static void comb2(int cnt, int start, int lev) {

		if (cnt == lev) {
			sum = 0;

			for (int i = 0; i < cnt; i++) {
				sum += path[i];
			}

			if (sum <= C) {
				int tmp = 0;
				for(int i=0;i<cnt;i++) {
					tmp += path[i]*path[i];
				}
				max2 = Math.max(tmp, max2);
			}
		}

		for (int i = start; i < N; i++) {
			if (honey2[i] == 0)
				continue;
			path[cnt] = honey2[i];
			comb2(cnt + 1, i + 1, lev);
		}

	}

}
