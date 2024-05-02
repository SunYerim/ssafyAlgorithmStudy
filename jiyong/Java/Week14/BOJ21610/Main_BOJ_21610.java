package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_BOJ_21610 {
	static int N, M, A[][];
	static boolean[][] visited;
	static List<Cloud> clouds;
	// ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	static int[][] delta = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };

	static class Cloud {
		int r, c;

		public Cloud(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public void Move(int d, int s) {
			this.r = (this.r + delta[d - 1][0] * s + 50 * N) % N;
			this.c = (this.c + delta[d - 1][1] * s + 50 * N) % N;
			A[this.r][this.c] += 1;
			visited[this.r][this.c] = true;
		}

		public void copyBug() {
			int nr, nc;
			for (int i = 1; i < 8; i += 2) {
				nr = this.r + delta[i][0];
				nc = this.c + delta[i][1];
				if (isIn(nr, nc) && A[nr][nc] > 0) {
					A[this.r][this.c] += 1;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		clouds = new ArrayList<>();
		clouds.add(new Cloud(N - 1, 0));
		clouds.add(new Cloud(N - 1, 1));
		clouds.add(new Cloud(N - 2, 0));
		clouds.add(new Cloud(N - 2, 1));

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			visited = new boolean[N][N];
			for (Cloud cloud : clouds) {
				cloud.Move(d, s);
			}
			for (Cloud cloud : clouds) {
				cloud.copyBug();
			}
			clouds.clear();
			createCloud();
		}
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				cnt += A[r][c];
			}
		}
		System.out.println(cnt);
	}

	private static void createCloud() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c] && A[r][c] >= 2) {
					clouds.add(new Cloud(r, c));
					A[r][c] -= 2;
				}
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}

}
