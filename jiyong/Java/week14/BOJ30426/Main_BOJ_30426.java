package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_BOJ_30426 {
	static int N, M, K, L;
	static boolean[] X;
	static int[][] D;
	static Set<Integer>[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		D = new int[K][];
		X = new boolean[N];
		dp = new Set[K + 1];

		for (int i = 0; i <= K; i++) {
			dp[i] = new HashSet<>();
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			D[i] = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		}

		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			X[Integer.parseInt(br.readLine())] = true;
		}

		dp[K].add(0);
		for (int i = K - 1; i >= 0; i--) {
			for (int num : dp[i + 1]) {
				int correct = (num - D[i][0] + N) % N;
				int incorrect = (num - D[i][1] + N) % N;
				if (!X[correct])
					dp[i].add(correct);
				if (!X[incorrect] && correct != incorrect)
					dp[i].add(incorrect);
			}
		}

		System.out.println(!dp[0].add(M) ? "utopia" : "dystopia");

	}
}
