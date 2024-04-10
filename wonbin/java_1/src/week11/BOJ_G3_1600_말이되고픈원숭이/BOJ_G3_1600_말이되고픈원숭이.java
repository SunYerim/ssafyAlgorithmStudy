package week11.BOJ_G3_1600_말이되고픈원숭이;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ_G3_1600_말이되고픈원숭이 {

	static int K;
	static int W;
	static int H;
	static int[][] map;
	static int[][][] visited;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int[] hy = {-2,-1,1,2,2,1,-1,-2}; // 말 이동 y좌표
	static int[] hx = {1,2,2,1,-1,-2,-2,-1}; // 말 이동 x좌표
	static int answer;
	static boolean flag;
	
	static class monkey{
		int y;
		int x;
		int k;
		
		public monkey(int y, int x, int k) {
			this.y = y;
			this.x = x;
			this.k = k;
		}

		@Override
		public String toString() {
			return "monkey [y=" + y + ", x=" + x + ", k=" + k + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		K = Integer.parseInt(br.readLine());
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		W = Integer.parseInt(s2[0]);
		H = Integer.parseInt(s2[1]);
		map = new int[H][W];
		visited = new int[H][W][K+1]; // 말로 뛴 경우와 걷는 경우를 구분하기 위해 3차원 방문배열 만들기
		flag = false;
		
		for(int i=0;i<H;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			for(int j=0;j<W;j++) {
				map[i][j] = Integer.parseInt(s2[j]);
			}
		}
		
		bfs(0,0,0);
		
		if(!flag)
			answer = -1;
		
		bw.append(answer+"");
		bw.append("\n");
		bw.flush();
		bw.close();
	}

	private static void bfs(int y, int x, int cnt) {

		Queue<monkey> que = new ArrayDeque<>();
		visited[y][x][cnt] = 1;
		que.offer(new monkey(y,x,cnt));
		
		while(!que.isEmpty()) {
			
			int size = que.size();
			
			while(--size>=0) {
				monkey m = que.poll();
				
				if(m.y == H-1 && m.x == W-1) {
					flag = true;
					return;
				}
				
				for(int i=0;i<8;i++) { // 말로 뛰는 경우
					int ny = m.y + hy[i];
					int nx = m.x + hx[i];
					
					if(m.k < K) {
						if(ny>=0 && ny<H && nx>=0 && nx<W && visited[ny][nx][m.k+1] == 0 && map[ny][nx] == 0) {
							que.offer(new monkey(ny, nx, m.k+1));
							visited[ny][nx][m.k+1] = 1;
						}
					}
				}
				
				for(int i=0;i<4;i++) { // 그냥 걷는 경우
					int ny = m.y + dy[i];
					int nx = m.x + dx[i];
					
					if(ny>=0 && ny<H && nx>=0 && nx<W && visited[ny][nx][m.k] == 0 && map[ny][nx] == 0) {
						que.offer(new monkey(ny, nx, m.k));
						visited[ny][nx][m.k] = 1;
					}
				}
			}
			
			answer++;
		}
	}
}
