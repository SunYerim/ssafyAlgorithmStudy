package week11.SWEA_모의SW역량테스트_미생물격리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class SWEA_모의SW역량테스트_미생물격리 {
	
	static int N;
	static int M;
	static int K;
	static ms[][] map;
	static ms[][] map2; // 더할때 비교하기 용 맵
	static long answer;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int[][] visited;
	static Queue<ms> que = new ArrayDeque<>();
	
	static class ms{
		int y;
		int x;
		long value;
		int d;
		
		public ms(int y, int x, long value, int d) {
			this.y = y;
			this.x = x;
			this.value = value;
			this.d = d;
		}

		@Override
		public String toString() {
			return "ms [value=" + value + ", d=" + d + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			String s = br.readLine();
			String[] s2 = s.split(" ");
			
			N = Integer.parseInt(s2[0]);
			M = Integer.parseInt(s2[1]);
			K = Integer.parseInt(s2[2]);
			map = new ms[N][N];
			map2 = new ms[N][N];
			answer = 0;
			
			for(int i=0;i<K;i++) {
				s = br.readLine();
				s2 = s.split(" ");
				int y = Integer.parseInt(s2[0]);
				int x = Integer.parseInt(s2[1]);
				long bio = Long.parseLong(s2[2]);
				int d = Integer.parseInt(s2[3]);
				
				if(d==1)
					d--;
				else if(d==4)
					d-=3;
				
				map[y][x] = new ms(y, x, bio, d); // 방향 인덱스가 0부터 시작함
			}
			
			for(int k=0;k<M;k++) {
				
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(map[i][j] != null) {
							que.offer(map[i][j]);
							map[i][j] = null;
						}
					}
				}
				
				while(!que.isEmpty()) {
					ms next = que.poll();
					int ny = next.y + dy[next.d];
					int nx = next.x + dx[next.d];
					
					if(ny == 0 || ny == N-1 || nx == 0 || nx == N-1) { // 도착지가 약물이면
						if(map[ny][nx] != null) { // 도착지에 미생물이 존재하면
							int d = next.d;
							ms tmp = map[ny][nx];
							
							if(d==0 || d==1)
								d+=2;
							else
								d-=2;
							
							next = new ms(next.y, next.x, next.value/2, d);
							
							if(map2[ny][nx].value > next.value) { // 기존 미생물이 더 많으면
								map[ny][nx] = new ms(ny, nx, map[ny][nx].value + next.value, map[ny][nx].d);
							}
							else {
								map2[ny][nx] = new ms(ny, nx, next.value, next.d);
								map[ny][nx] = new ms(ny, nx, map[ny][nx].value + next.value, next.d);
							}
						}
						else { // 존재하지 않으면
							int d = next.d;
							
							if(d==0 || d==1)
								d+=2;
							else
								d-=2;
							
							map[ny][nx] = new ms(ny, nx, next.value/2, d);
							map2[ny][nx] = new ms(ny, nx, next.value/2, d);
						}
					}
					else { // 도착지가 약물이 아니면
						if(map[ny][nx] != null) { // 도착지에 미생물이 존재하면
							
							if(map2[ny][nx].value > next.value) { // 기존 미생물이 더 많으면
								map[ny][nx] = new ms(ny, nx, map[ny][nx].value + next.value, map[ny][nx].d);
							}
							else {
								map2[ny][nx] = new ms(ny, nx, next.value, next.d);
								map[ny][nx] = new ms(ny, nx, map[ny][nx].value + next.value, next.d);
							}
						}
						else { // 존재하지 않으면
							
							map[ny][nx] = new ms(ny, nx, next.value, next.d);
							map2[ny][nx] = new ms(ny, nx, next.value, next.d);
						}
					}
				}
			}
			
			calcmap();
			
			bw.append("#"+tc+""+" "+answer+"");
			bw.append("\n");
		}
		
		bw.flush();
		bw.close();
	}

	private static void calcmap() {

		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j] != null)
					answer += map[i][j].value;
			}
		}
	}

}
