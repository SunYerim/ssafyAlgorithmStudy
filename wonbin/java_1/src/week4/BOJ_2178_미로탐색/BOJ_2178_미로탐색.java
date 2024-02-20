package week4.BOJ_2178_미로탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_2178_미로탐색 {
	
	static int N;
	static int M;
	static int[][] maze;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int[][] visited;
	static int answer;
	static int min = Integer.MAX_VALUE;
	
	static class point{
		int x;
		int y;
		int count;
		public point(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}
		
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		maze = new int[N][M];
		visited = new int[N][M];
		
		for(int i=0;i<N;i++) {
			s=br.readLine();
			for(int j=0;j<M;j++) {
				maze[i][j] = s.charAt(j) - '0';
			}
		}
		
		bfs(1);
		bw.append(min + "" + "\n");
		bw.flush();
		bw.close();
	}
	
	
	static void bfs(int cnt) {
		
		Queue<point> que = new ArrayDeque<>();
		point p = new point(0,0,cnt);
		que.offer(p);
		visited[0][0] = 1;
		
		while(!que.isEmpty()) {
			
			point np = que.poll();
			
			for(int i=0;i<4;i++) {
				int ny = dy[i] + np.y;
				int nx = dx[i] + np.x;
				cnt = np.count;
				answer = cnt;
				if(np.y == N -1 && np.x == M -1) {
					if(min>answer)
						min = answer;
				}
				
				if(ny>=0 && ny<N && nx>=0 && nx<M && visited[ny][nx] == 0 && maze[ny][nx] == 1) {
					visited[ny][nx] = 1;
					que.offer(new point(nx,ny,cnt+1));
				}
			}
		}
		
	}
}