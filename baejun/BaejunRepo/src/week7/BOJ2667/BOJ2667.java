package week7.BOJ2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2667 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Integer> list;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static Queue<Integer> queue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String str = in.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		visited = new boolean[N][N];
		list = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					bfs(i, j);
				}
			}
		}
		
		sb.append(list.size()).append("\n");
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		});
		for(int e : list) {
			sb.append(e).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs(int x, int y) {
		int cnt = 0;
		
		visited[x][y] = true;
		queue = new LinkedList<>();
		queue.offer(x);
		queue.offer(y);
		
		while(!queue.isEmpty()) {
			int x1 = queue.poll();
			int y1 = queue.poll();
			cnt++;
			for(int i = 0; i < 4; i++) {
				int nr = x1 + dx[i];
				int nc = y1 + dy[i];
				if(nr>=0&&nr<N&&nc>=0&&nc<N&&!visited[nr][nc]&&map[nr][nc] == 1) {
					visited[nr][nc] = true;
					queue.offer(nr);
					queue.offer(nc);
				}
			}
		}
		
		if(cnt > 0) list.add(cnt);
	}
}
