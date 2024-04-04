package week7.BOJ2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178 {
	
	static class Node {
		int value;
		int cost;
		
		Node(int value) {
			this.value = value;
			this.cost = 0;
		}
	}
	
	static int N, M, cnt;
	static Node[][] map;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Node[N][M];
		
		for(int i = 0; i < N; i++) {
			String str = in.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = new Node(str.charAt(j) - '0');
			}
		}
		visited = new boolean[N][M];
		cnt = 0;
		bfs();
		System.out.println(map[N-1][M-1].cost);
	}

	private static void bfs() {
		visited[0][0] = true;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(0);
		queue.offer(0);
		map[0][0].cost = 1;
		
		while(!queue.isEmpty()) {
			int x = queue.poll();
			int y = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = x + dx[i];
				int nc = y + dy[i];
				
				if(nr>=0&&nr<N&&nc>=0&&nc<M&&!visited[nr][nc]&&map[nr][nc].value == 1) {
					visited[nr][nc] = true;
					queue.offer(nr);
					queue.offer(nc);
					map[nr][nc].cost = map[x][y].cost+1;
				}
			}
		}
	}
}
