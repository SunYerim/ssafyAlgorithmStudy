package week4.BOJ2589;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class nodelist{
	int x;
	int y;
	int count;
	
	public nodelist(int x, int y, int count) {
		super();
		this.x = x;
		this.y = y;
		this.count = count;
	}
}

public class BOJ2589 {
	static char[][] treasureIsland;
	static boolean[][] visited;
	static int currentDistance;
	static int max = 0;
	static Queue<nodelist> queue = new LinkedList<>();
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		treasureIsland = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = in.readLine();
			for(int j = 0; j < M; j++) {
				treasureIsland[i][j] = str.charAt(j);
			}
		}
		for (int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(treasureIsland[i][j] == 'L') {
					visited = new boolean[N][M];
					currentDistance = 0;
					queue = new LinkedList<>();
					bfs(i, j);
					if(max < currentDistance) max = currentDistance;
				}
			}
		}
		System.out.println(max);
	}

	private static void bfs(int row, int col) {
		visited[row][col] = true;
		int cmax=Integer.MIN_VALUE;
		queue.add(new nodelist(row, col, 0));
		while(!queue.isEmpty()) {
			nodelist get = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nr = get.x + dx[i];
				int nc = get.y + dy[i];
				int c = get.count;
				if(nr>=0&&nr<treasureIsland.length&&nc>=0&&nc<treasureIsland[0].length&&!visited[nr][nc]&&treasureIsland[nr][nc]=='L') {
					visited[nr][nc] = true;
					cmax=Math.max(c+1, cmax);
					queue.add(new nodelist(nr, nc, c+1));
				}
			}
		}
		currentDistance = cmax;
	}
}
