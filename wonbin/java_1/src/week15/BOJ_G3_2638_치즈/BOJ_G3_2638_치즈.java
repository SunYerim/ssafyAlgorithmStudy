package week15.BOJ_G3_2638_치즈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_G3_2638_치즈 {

	static int N;
	static int M;
	static int[][] map;
	static int[][] map2;
	static int[][] visited;
	static int count;
	static Queue<point> que;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int answer;

	static class point {
		int y;
		int x;

		public point(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String[] s2 = s.split(" ");

		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		map = new int[N][M];
		map2 = new int[N][M];
		que = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			s = br.readLine();
			s2 = s.split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(s2[j]);
			}
		}

		while (true) {
			visited = new int[N][M];
			mapcopy();
			map2[0][0] = 2;
			visited[0][0] = 1;
			dfs(0, 0);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						count = 0;
						for (int k = 0; k < 4; k++) {
							int ny = i + dy[k];
							int nx = j + dx[k];

							if (ny >= 0 && ny < N && nx >= 0 && nx < M && map2[ny][nx] == 2) {
								count++;
							}
						}
						if (count >= 2)
							que.offer(new point(i, j));
					}
				}
			}

			if (que.isEmpty())
				break;
			else {
				while (!que.isEmpty()) {
					point p = que.poll();

					map[p.y][p.x] = 0;
				}
				answer++;
			}

		}

		System.out.println(answer);

	}

	private static void dfs(int y, int x) {

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny >= 0 && ny < N && nx >= 0 && nx < M && visited[ny][nx] == 0 && map2[ny][nx] == 0) {
				visited[ny][nx] = 1;
				map2[ny][nx] = 2;
				dfs(ny, nx);
			}
		}
	}

	private static void mapcopy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map2[i][j] = map[i][j];
			}
		}
	}
}
