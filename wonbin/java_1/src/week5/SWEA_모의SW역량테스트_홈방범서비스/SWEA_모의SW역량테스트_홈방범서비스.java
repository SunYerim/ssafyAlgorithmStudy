package week5.SWEA_모의SW역량테스트_홈방범서비스;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_모의SW역량테스트_홈방범서비스 {

	static int N;
	static int M;
	static int[][] home;

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
			home = new int[N][N];
			int K = 1;
			int cost = 0;
			int tmp = 0;
			int flag = 1;
			int answer = 0;
			int answer2 = 0;
			int max = 0;

			for (int i = 0; i < N; i++) {
				s = br.readLine();
				s2 = s.split(" ");

				for (int j = 0; j < N; j++) {
					home[i][j] = Integer.parseInt(s2[j]);
					if(home[i][j] == 1)
						answer2++;
				}
			}

			while (K<=N+1) {
				cost = K * K + (K - 1) * (K - 1);
				int up_y = 0;
				int down_y = 0;

				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						up_y = i - 1;
						down_y = i + 1;
						tmp = 0;
						answer = 0;
						if (K == 1) {
							tmp += M;
							answer++;
						} else {
							for (int ab = 0; ab < K - 1; ab++) { // 운영비용 위쪽 구하기
								for (int ac = j - (K - 2) + ab ; ac <= j + (K - 2) - ab; ac++) {
									if (up_y >= 0 && up_y < N && ac >= 0 && ac < N && home[up_y][ac] == 1) {
										tmp += M;
										answer++;
									}
								}
								up_y--;
							}

							for (int ac = j - (K - 1); ac <= j + (K - 1); ac++) { // 중앙 운영비용 구하기
								if (i >= 0 && i < N && ac >= 0 && ac < N && home[i][ac] == 1) {
									tmp += M;
									answer++;
								}
							}

							for (int ab = 0; ab < K - 1; ab++) { // 운영비용 아래쪽 구하기
								for (int ac = j - (K - 2) + ab ; ac <= j + (K - 2) - ab; ac++) {
									if (down_y >= 0 && down_y < N && ac >= 0 && ac < N && home[down_y][ac] == 1) {
										tmp += M;
										answer++;
									}
								}
								down_y++;
							}

							if (cost < tmp) {
								max = Math.max(max, answer);
							}
						}
					}
				}

				K++;
			}
			
			//max = Math.max(answer2, max);

			bw.append(max + "");
			bw.append("\n");
			bw.flush();

		}

		bw.close();
	}
}
