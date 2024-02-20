package week4.BOJ_2589_보물섬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_2589_보물섬 {
	
	static int N;
	static int M;
	static char[][] maze;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int max = 0;
	static int ny = 0;
	static int nx = 0;
	
	static class point{
		int y;
		int x;
		int distance;
		
		public point(int y, int x, int distance) {
			super();
			this.y = y;
			this.x = x;
			this.distance = distance;
		}
		@Override
		public String toString() {
			return "point [y=" + y + ", x=" + x + ", distance=" + distance + "]";
		}
		
		
	}
			
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		
		maze = new char[N][M];
		
		for(int i=0;i<N;i++) {
			s = br.readLine();
			for(int j=0;j<M;j++) {
				maze[i][j] = s.charAt(j);
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(maze[i][j] == 'L') {
					bfs(i,j,0);
				}
			}
		}
		
		bw.append(max+"");
		bw.flush();
		bw.close();
		
	}
	
	public static void bfs(int y, int x, int cnt) {
		
		Queue<point> que = new ArrayDeque<>();
		point p = new point(y,x,cnt);
		int[][] visited = new int[50][50];
		visited[y][x] = 1;
		
		que.offer(p);
		
		while(!que.isEmpty()) {
			
			point p2 = que.poll();
			int tmp = p2.distance;
			cnt = tmp;
			if(max<tmp) max = tmp;
			
			for(int i=0;i<4;i++) {
				ny = dy[i] + p2.y;
				nx = dx[i] + p2.x;
				
				if(ny>=0 && ny<N && nx>=0 && nx<M && visited[ny][nx] == 0 && maze[ny][nx] == 'L') {
					visited[ny][nx] = 1;
					que.offer(new point(ny,nx,cnt+1));
				}
			}
			
		}
	
		
	}
}