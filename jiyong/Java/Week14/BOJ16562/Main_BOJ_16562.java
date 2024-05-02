package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_BOJ_16562 {

	static int N, M, k;
	static int[] A, link, cost;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		A = new int[N + 1];
		link = new int[N + 1];
		cost = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			link[i] = i;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			union(v, w);
		}

		for (int i = 1; i <= N; i++) {
			find(i);
			if (cost[link[i]] == 0) {
				cost[link[i]] = A[i];
			} else if (cost[link[i]] > A[i]) {
				cost[link[i]] = A[i];
			}
		}
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			sum += cost[i];
		}
		System.out.println(sum <= k ? sum : "Oh no");
	}

	public static int find(int x) {
		if (link[x] == x) {
			return x;
		} else {
			return link[x] = find(link[x]);
		}
	}

	public static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) {
			return;
		}
		if (aRoot > bRoot) {
			link[bRoot] = link[aRoot];
		} else {
			link[aRoot] = link[bRoot];
		}
	}
}
