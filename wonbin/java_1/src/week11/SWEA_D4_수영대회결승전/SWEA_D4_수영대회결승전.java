package week11.SWEA_D4_수영대회결승전;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class SWEA_D4_수영대회결승전 {

	static int N;
	static int[][] map;
	static int[][] visited;
	static int answer;
	static int[] start = new int[2];
	static int[] end = new int[2];
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	
	static class point{
		int y;
		int x;
		int cnt;
		
		public point(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "point [y=" + y + ", x=" + x + ", cnt=" + cnt + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new int[N][N];
			answer = 0;
			
			for(int i=0;i<N;i++) {
				String s = br.readLine();
				String[] s2 = s.split(" ");
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(s2[j]);
				}
			}
			
			String s = br.readLine();
			String[] s2 = s.split(" ");
			
			start[0] = Integer.parseInt(s2[0]); // y좌표
			start[1] = Integer.parseInt(s2[1]); // x좌표
			
			s = br.readLine();
			s2 = s.split(" ");
			
			end[0] = Integer.parseInt(s2[0]); // y좌표
			end[1] = Integer.parseInt(s2[1]); // x좌표
			
			bfs(start[0], start[1], 0);
			
			if(answer == 0)
				answer = -1;
			
			bw.append("#"+tc+""+" "+answer+"");
			bw.append("\n");
		}
		bw.flush();
		bw.close();
	}

	private static void bfs(int y, int x, int cnt) {
		
		Queue<point> que = new ArrayDeque<>();
		visited[y][x] = 1;
		que.offer(new point(y, x, 0));
		
		while(!que.isEmpty()) {
			
			point p = que.poll();
			
			if(p.y == end[0] && p.x == end[1]) {
				answer = p.cnt;
				return;
			}
			
			for(int i=0;i<4;i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				if(ny>=0 && ny<N && nx>=0 && nx<N && visited[ny][nx] == 0 && map[ny][nx] != 1) {
					if(map[ny][nx] == 2) {
						if(p.cnt % 3 == 2) {
							que.offer(new point(ny , nx, p.cnt+1));
							visited[ny][nx] = 1;
						}
						else
							que.offer(new point(p.y, p.x, p.cnt+1));
					}
					else {
						que.offer(new point(ny, nx, p.cnt+1));
						visited[ny][nx] = 1;
					}
				}
			}
			
		}
		
	}
}
