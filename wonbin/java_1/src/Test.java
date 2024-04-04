
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Test {

	static int N;
	static int[][] sea;
	static int[][] visited;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int start_y;
	static int start_x;
	static int end_y;
	static int end_x;
	static int count;

	static class point {
		int y;
		int x;
		int cnt;
		public point(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
		@Override
		public String toString() {
			return "point [y=" + y + ", x=" + x + ", cnt=" + cnt + "]";
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			sea = new int[N][N];
			visited = new int[N][N];

			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				String[] s2 = s.split(" ");
				for (int j = 0; j < N; j++) {
					sea[i][j] = Integer.parseInt(s2[j]);
				}
			}

			String s = br.readLine();
			String[] s2 = s.split(" ");

			start_y = Integer.parseInt(s2[0]);
			start_x = Integer.parseInt(s2[1]);

			s = br.readLine();
			s2 = s.split(" ");

			end_y = Integer.parseInt(s2[0]);
			end_x = Integer.parseInt(s2[1]);

			bfs(start_y, start_x, 0);
			
			bw.append("#"+test_case+" "+count+"");
			bw.append("\n");
		}
		bw.flush();
		bw.close();
	}

	static void bfs(int y, int x, int cnt) {
		Queue<point> que = new ArrayDeque<>();
		que.offer(new point(y, x, cnt));
		visited[y][x] = 1;

		while (!que.isEmpty()) {
			point p = que.poll();
			cnt = p.cnt;

			if (p.y == end_y && p.x == end_x) {
				count = cnt;
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];

				if (ny >= 0 && ny < N && nx >= 0 && nx < N && visited[ny][nx] == 0 && sea[ny][nx] != 1) {
					if (sea[ny][nx] == 2) {
						if(cnt % 3 == 2) {
							visited[ny][nx] = 1;
							que.offer(new point(ny, nx, cnt+1));
						}
						else
							que.offer(new point(p.y, p.x, cnt+1));
						
					}
					else {
						visited[ny][nx] = 1;
						que.offer(new point(ny, nx, cnt+1));
					}
				}
			}
		}
		
		count = -1;
	}
}
