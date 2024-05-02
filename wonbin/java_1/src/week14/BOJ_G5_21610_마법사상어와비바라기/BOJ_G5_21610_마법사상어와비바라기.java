package week14.BOJ_G5_21610_마법사상어와비바라기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_G5_21610_마법사상어와비바라기 {

	static int N;
	static int M;
	static int[][] map;
	static ArrayList<point> cloud;
	static ArrayList<point> cloud2;
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dx = { -1, -1, 0, 1, 1, 1, 1, 0, -1 };
	static int[] dy2 = { -1, -1, 1, 1 };
	static int[] dx2 = { -1, 1, 1, -1 };
	static int answer;
	static int tmp;

	static class point {
		int y;
		int x;

		public point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public String toString() {
			return "point [y=" + y + ", x=" + x + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String[] s2 = s.split(" ");

		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		map = new int[N + 1][N + 1];
		cloud = new ArrayList<>();
		cloud2 = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			s = br.readLine();
			s2 = s.split(" ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(s2[j - 1]);
			}
		}

		cloud.add(new point(N, 1));
		cloud.add(new point(N, 2));
		cloud.add(new point(N - 1, 1));
		cloud.add(new point(N - 1, 2));

		for (int i = 0; i < M; i++) {

			s = br.readLine();
			s2 = s.split(" ");

			int a = Integer.parseInt(s2[0]) - 1; // 구름 이동 방향
			int b = Integer.parseInt(s2[1]); // 구름 움직이는 횟수

			for (int j = 0; j < b; j++) {

				for (int k = 0; k < cloud.size(); k++) {
					if (cloud.get(k).y + dy[a] > N) {
						cloud.get(k).y = 1;
					} else if (cloud.get(k).y + dy[a] < 1) {
						cloud.get(k).y = N;
					} else {
						cloud.get(k).y += dy[a];
					}
				}

				for (int k = 0; k < cloud.size(); k++) {
					if (cloud.get(k).x + dx[a] > N) {
						cloud.get(k).x = 1;
					} else if (cloud.get(k).x + dx[a] < 1) {
						cloud.get(k).x = N;
					} else {
						cloud.get(k).x += dx[a];
					}
				}
			}

			for (int j = 0; j < cloud.size(); j++) {
				map[cloud.get(j).y][cloud.get(j).x]++;
			}

			for (int k = 0; k < cloud.size(); k++) { // 물복사 버그
				for (int ab = 0; ab < 4; ab++) {
					if (cloud.get(k).y + dy2[ab] >= 1 && cloud.get(k).y + dy2[ab] <= N && cloud.get(k).x + dx2[ab] >= 1
							&& cloud.get(k).x + dx2[ab] <= N) {
						if (map[cloud.get(k).y + dy2[ab]][cloud.get(k).x + dx2[ab]] != 0) {
							map[cloud.get(k).y][cloud.get(k).x]++;
						}
					}
				}
			}

			for (int k = 1; k <= N; k++) {
				for (int p = 1; p <= N; p++) {
					if (map[k][p] >= 2) {
						for (int c = 0; c < cloud.size(); c++) {
							if (cloud.get(c).y == k && cloud.get(c).x == p) {
								tmp = 1;
								break;
							}
						}
						if (tmp == 1) {
							tmp = 0;
							continue;
						}
						map[k][p] -= 2;
						cloud2.add(new point(k, p));
					}
				}
			}

			cloud.clear();
			cloud = (ArrayList<point>) cloud2.clone();
			cloud2.clear();
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				answer += map[i][j];
			}
		}

		System.out.println(answer);
	}
}
