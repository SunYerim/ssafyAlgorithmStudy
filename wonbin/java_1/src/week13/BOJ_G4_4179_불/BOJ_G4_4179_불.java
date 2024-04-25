package week13.BOJ_G4_4179_불;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_G4_4179_불 {

	static int R;
	static int C;
	static char[][] map;
	static Queue<point> fire;
	static int[][] visited;
	static int[][] visited2;
	static int start_x;
	static int start_y;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int cnt;
	static int tmp;
	
	static class point{
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
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		R = Integer.parseInt(s2[0]);
		C = Integer.parseInt(s2[1]);
		map = new char[R][C];
		visited = new int[R][C];
		visited2 = new int[R][C];
		fire = new ArrayDeque<>();
		
		for(int i=0;i<R;i++) {
			s = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'J') {
					start_y = i;
					start_x = j;
				}
				if(map[i][j] == 'F') {
					visited2[i][j] = 1;
					fire.offer(new point(i, j));
				}
			}
		}
		
		visited[start_y][start_x] = 1;
		bfs(start_y, start_x);
		
		if(tmp == 1) {
			bw.append(cnt+"");
			bw.append("\n");
			bw.close();
		}
		else {
			bw.append("IMPOSSIBLE");
			bw.append("\n");
			bw.close();
		}
	}

	private static void bfs(int y, int x) {
		
		Queue<point> que = new ArrayDeque<>();
		que.offer(new point(y,x));
		
		
		while(!que.isEmpty()) {
			int size = que.size();
			int f_size = fire.size();
			
			while(--f_size>=0) {
				point p = fire.poll();
				
				for(int i=0;i<4;i++) {
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];
					
					if(ny>=0 && ny<R && nx>=0 && nx<C && map[ny][nx] != '#' && map[ny][nx] != 'F') {
						map[ny][nx] = 'F';
						fire.offer(new point(ny, nx));
					}
				}
			}
			
			while(--size>=0) {
				
				point p = que.poll();
				
				int count = 0;
				int count2 = 0;
				
				for(int i=0;i<4;i++) { // 탈출 조건 체크
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];
					
					if(ny < 0 || ny >= R || nx < 0 || nx >= C) {
						cnt++;
						tmp = 1;
						return;
					}
				}
				
				for(int i=0;i<4;i++) {
					int ny = p.y + dy[i];
					int nx = p.x + dx[i];
					
					if(ny>=0 && ny<R && nx>=0 && nx<C && visited[ny][nx] == 0 && map[ny][nx] != '#' && map[ny][nx] != 'F') {
						visited[ny][nx] = 1;
						que.offer(new point(ny, nx));
					}
				}
			}
			
			cnt++;
			
		}
	}
	
	private static void fire() {
		
		while(!fire.isEmpty()) {
			point p = fire.poll();
			
			for(int i=0;i<4;i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				if(ny>=0 && ny<R && nx>=0 && nx<C && map[ny][nx] != '#' && visited2[ny][nx] == 0) {
					map[ny][nx] = 'F';
					visited2[ny][nx] = 1;
					fire.offer(new point(ny, nx));
				}
			}
		}
		
	}
}
