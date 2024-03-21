package week8.BOJ_G5_12865_평범한배낭;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_G5_12865_평범한배낭 {

	static int N; // 물품의 수
	static int K; // 배낭의 최대 무게
	static int[][] dp; // dp 배열
	static bag[] values; // 각 물건의 밸류 배열

	static class bag { // 무게 와 밸류값을 하나로 저장할 클래스
		int W;
		int V;

		public bag(int w, int v) {
			super();
			W = w;
			V = v;
		}

		@Override
		public String toString() {
			return "bag [W=" + W + ", V=" + V + "]";
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String s = br.readLine();
		String[] s2 = s.split(" ");

		N = Integer.parseInt(s2[0]);
		K = Integer.parseInt(s2[1]);
		values = new bag[N]; // 물품의 수 만큼 초기화
		dp = new int[K + 1][N]; // K번 인덱스를 사용하기 위해 K+1, 세로열이 각 배낭의 무게 가로열이 탐색한 물건의 수

		for (int i = 0; i < N; i++) { // 물건들 밸류 및 무게 저장
			s = br.readLine();
			s2 = s.split(" ");
			values[i] = new bag(Integer.parseInt(s2[0]), Integer.parseInt(s2[1]));
		}

		for (int i = 0; i < N; i++) { // 0번 가로열 0으로 초기화
			dp[0][i] = 0;
		}

		for (int i = 1; i < K + 1; i++) { // 세로열 첫번째(0번 인덱스) 값 초기화
			if (i >= values[0].W)
				dp[i][0] = values[0].V;
			else
				dp[i][0] = 0;
		}

		if (N > 1) {// 물건이 2개 이상 있을 경우 dp 실행
			for (int i = 1; i < N; i++) {
				for (int k = 1; k < K + 1; k++) {
					if (values[i].W > k) {
						dp[k][i] = dp[k][i - 1]; // 만약 배낭 무게보다 물건의 무게가 더 무거울 경우 안넣는다. -> 이전에 저장해 놓은 무게를 가져옴
					} else {
						dp[k][i] = Math.max(dp[k][i - 1], values[i].V + dp[k - values[i].W][i - 1]);
					} // 만약 배낭의 무게가 물건의 무게보다 클 경우 -> 물건을 넣었을 경우와 안넣었을 경우를 비교해서 더 큰값을 저장한다.
				}
			}
		}

		bw.append(dp[K][N - 1] + "");
		bw.append("\n");
		bw.flush();
		bw.close();

	}
}
