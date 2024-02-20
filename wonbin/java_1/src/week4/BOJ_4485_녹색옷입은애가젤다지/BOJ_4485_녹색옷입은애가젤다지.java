package week4.BOJ_4485_녹색옷입은애가젤다지;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;


public class BOJ_4485_녹색옷입은애가젤다지 {
	
	static int N;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int[][] rupee;
	static int answer = 0;
	
	static class zelda implements Comparable<zelda>{
		int x;
		int y;
		int rupee;
		public zelda(int x, int y, int rupee) {
			super();
			this.x = x;
			this.y = y;
			this.rupee = rupee;
		}
		@Override
		public String toString() {
			return "zelda [x=" + x + ", y=" + y + ", rupee=" + rupee + "]";
		}
		@Override
		public int compareTo(zelda o) {
			return this.rupee - o.rupee;
		}
		
		
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tmp = 1;
		
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0)
				break;
			rupee = new int[N][N];
			
			for(int i=0;i<N;i++) {
				String s = br.readLine();
				String[] s2 = s.split(" ");
				for(int j=0;j<N;j++) {
					rupee[i][j] = Integer.parseInt(s2[j]);
				}
			}
			
			bfs();
			
			bw.append("Problem " + tmp + ": " + answer + "" + "\n");
			tmp++;
		}
		bw.flush();
		bw.close();
		
	}
	
	static void bfs() {
		
		PriorityQueue<zelda> que = new PriorityQueue<>();
		int[][] move = new int[N][N]; //가중치 저장 배열
		
		for(int i=0;i<N;i++) {
			Arrays.fill(move[i], Integer.MAX_VALUE);
		}
		
		que.offer(new zelda(0,0,rupee[0][0]));
		move[0][0] = rupee[0][0];
		
		while(!que.isEmpty()) {
			zelda z = que.poll();
			int zx = z.x;
			int zy = z.y;
			int cost = z.rupee;
			
			if(zx == N - 1 && zy == N - 1)
				answer = cost;
			
			for(int i=0;i<4;i++) {
				int ny = dy[i] + z.y;
				int nx = dx[i] + z.x;
				
				if(ny>=0 && ny<N && nx>=0 && nx<N) {
					if(cost + rupee[ny][nx] < move[ny][nx]) {
						move[ny][nx] = cost+rupee[ny][nx];
						que.offer(new zelda(nx,ny,cost+rupee[ny][nx]));
					}
				}
			}
		}
		
	}
	
}