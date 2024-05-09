package week15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_1043 {
	static int N, M, L, cnt;
	static int[] link, knew;
	static boolean[] possible;
	static List<Integer>[] parties;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cnt = 0;
		possible = new boolean[N + 1];
		parties = new ArrayList[M];

		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());

		if (L > 0) {
			knew = new int[L];
		}

		for (int i = 0; i < L; i++) {
			knew[i] = Integer.parseInt(st.nextToken());
		}

		makeSet();

		for (int i = 0; i < M; i++) {
			parties[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			parties[i].add(a);
			for (int j = 0; j < K - 1; j++) {
				int b = Integer.parseInt(st.nextToken());
				parties[i].add(b);
				union(a, b);
			}
		}

		for (int i = 0; i < L; i++) {
			find(knew[i]);
			possible[link[knew[i]]] = true;
		}

		for (int i = 0; i < M; i++) {
			boolean flag = true;
			for (int num : parties[i]) {
				find(num);
				if (possible[link[num]]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				cnt++;
			}
		}

		System.out.println(cnt);

	}

	private static void makeSet() {
		link = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			link[i] = i;
		}
	}

	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (link[aRoot] == link[bRoot]) {
			return;
		}
		link[bRoot] = aRoot;
	}

	private static int find(int x) {
		if (link[x] == x) {
			return x;
		}
		return link[x] = find(link[x]);
	}
}
