package week15.BOJ2638;

import java.util.*;
import java.io.*;


public class BOJ2638 {
	static int[][] map;
	static int N;
	static int M;
	static int[][] judgeMap;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		int totalCheezeCnt = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) totalCheezeCnt++;
			}
		}
		
		int totalTime = 0;
	
		while(true) {
			totalTime++;
			judgeMap = new int[N][M];
			visited = new boolean[N][M];
			bfsForjudge();
			visited = new boolean[N][M];
			for(int i = 1; i < N-1; i++) {
				for(int j = 1; j < M-1; j++) {
					if(map[i][j] == 1) {
						int cnt = 0;
						for(int k = 0; k < 4; k++) {
							int nr = i + dx[k];
							int nc = j + dy[k];
							if(nr>=0&&nr<N&&nc>=0&&nc<M&&!visited[nr][nc]&&judgeMap[nr][nc] == 3) {
								cnt++;
								if(cnt == 2) break;
							}
						}
						if(cnt >= 2) {
							map[i][j] = 0;
							totalCheezeCnt--;
						}
					}
				}
			}
			if(totalCheezeCnt <= 0) {
				break;
			}
		}
		System.out.println(totalTime);
	}

	private static void bfsForjudge() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		queue.add(0);
		visited[0][0] = true;
		judgeMap[0][0] = 3;
		
		while(!queue.isEmpty()) {
			int x = queue.poll();
			int y = queue.poll();
			for(int i = 0; i < 4; i++) {
				int nr = x + dx[i];
				int nc = y + dy[i];
				if(nr>=0&&nr<N&&nc>=0&&nc<M&&!visited[nr][nc]) {
					visited[nr][nc] = true;
					if(map[nr][nc] == 0) {
						judgeMap[nr][nc] = 3;
						queue.add(nr);
						queue.add(nc);
					}
				}
			}
		}
	}
}