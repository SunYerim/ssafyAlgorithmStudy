package week2.BOJ16439;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16439 {
	private static boolean[] visited;
	private static int maxValue;
	private static int[][] chickens;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		visited = new boolean[M];
		chickens = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) {
				chickens[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		maxValue = 0;
		combination(0, 0);
		System.out.println(maxValue);
	}

	private static void combination(int cnt, int start) {
		//기저조건
		if(cnt == 3) {
			int max = 0;
			for(int i = 0; i < chickens.length; i++) {
				int current = 0;
				for(int j = 0; j < chickens[0].length; j++) {
					if (visited[j]) {
						if (chickens[i][j] > current) current = chickens[i][j];
					}
				}
				max += current;
			}
			if(max > maxValue) maxValue = max;
			return;
		}

		//유도조건
		for(int i = start; i < visited.length; i++) {
			visited[i] = true;
			combination(cnt + 1, i + 1);
			visited[i] = false;
		}
	}
}
