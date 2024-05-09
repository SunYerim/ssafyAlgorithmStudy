package week15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BOJ_2638 {
	static int N, M, cnt, map[][], count[][];
	static int[][] delta = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		count = new int[N][M];
		cnt = 0;

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					cnt++;
				}
			}
		}
		int i = 0;
		while (cnt > 0) {
			floodFill();
			meltCheese();
			i++;
		}
		System.out.println(i);

	}

	private static void meltCheese() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (count[r][c] >= 2) {
					map[r][c] = 0;
					cnt--;
				}
				count[r][c] = 0;
			}
		}

	}

	private static void floodFill() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(0, 0));
		boolean[][] visited = new boolean[N][M];
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			Node now = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = now.r + delta[d][0];
				int nc = now.c + delta[d][1];

				if (isIn(nr, nc)) {
					if (map[nr][nc] == 1) {
						count[nr][nc]++;
					} else if (!visited[nr][nc]) {
						visited[nr][nc] = true;
						queue.offer(new Node(nr, nc));
					}
				}
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
