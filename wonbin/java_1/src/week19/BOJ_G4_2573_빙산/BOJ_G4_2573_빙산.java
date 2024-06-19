package week19.BOJ_G4_2573_빙산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G4_2573_빙산 {

	static int N;
	static int M;
	static int[][] map;
	static int[][] map2;
	static int answer;
	static int[][] visited;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int count;
	static int ck;
	static int mapcount;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		map = new int[N][M];
		map2 = new int[N][M];
		
		for(int i=0;i<N;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(s2[j]);
			}
		}
		
		while(true) {
			visited = new int[N][M];
			visited[0][0] = 1;
			dfs(0,0);
			melt();
			
			visited = new int[N][M];
			mapcount = 0;
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j] == 0) {
						map2[i][j] = 2;
						mapcount++;
					}
					else if(map[i][j] != 0)
						map2[i][j] = 0;
				}
			}
			
			if(mapcount == N*M) {
				answer = 0;
				break;
			}
			
			for(int i=0;i<N;i++) {
				int tmp = 0;
				for(int j=0;j<M;j++) {
					if(map[i][j] != 0) {
						visited[i][j] = 1;
						map2[i][j] = 1;
						check(i,j);
						for(int k=0;k<N;k++) {
							for(int p=0;p<M;p++) {
								if(map2[k][p] == 0) {
									ck = 1;
									break;
								}
							}
						}
						tmp++;
						break;
					}
				}
				if(tmp == 1)
					break;
			}
			
			count++;
			answer = count;
			
			if(ck == 1)
				break;
			
		}
		
		System.out.println(answer);
	}
	
	static void dfs(int y, int x) {
		
		for(int i=0;i<4;i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(map[y][x] != 0) {
				int tmp = 0;
				for(int j=0;j<4;j++) {
					int ny2 = y + dy[j];
					int nx2 = x + dx[j];
					if(map[ny2][nx2] == 0)
						tmp++;
				}
				
				map2[y][x] = tmp;
			}
			
			if(ny>=0 && ny<N && nx>=0 && nx<M && visited[ny][nx] == 0) {
				visited[ny][nx] = 1;
				dfs(ny, nx);
			}
		}
	}
	
	static void melt() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map[i][j] -= map2[i][j];
				if(map[i][j] <= 0)
					map[i][j] = 0;
			}
		}
	}
	
	static void check(int y, int x) {
		
		for(int i=0;i<4;i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny>=0 && ny<N && nx>=0 && nx<M && visited[ny][nx] == 0 && map[ny][nx] != 0) {
				visited[ny][nx] = 1;
				map2[ny][nx] = 1;
				dfs(ny, nx);
			}
		}
	}
}
