package week10.BOJ_G4_11559_PuyoPuyo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G4_11559_PuyoPuyo {

	static char[][] map;
	static int[][] visited;
	static int[][] visited2;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int combo;
	static char target;
	static int cnt;
	static int tmp = 1;
	static int tmp_end;
	static int answer;
	static boolean flag = false;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new char[12][6];
		visited2 = new int[12][6];
		
		for(int i=0;i<12;i++) {
			String s = br.readLine();
			for(int j=0;j<6;j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		while(true) {
			
			visited2 = new int[12][6];
			visited = new int[12][6];
			flag = false;
			
			for(int i=11;i>=0;i--) {
				for(int j=0;j<6;j++) {
					if(map[i][j] != '.') {
						tmp_end = tmp;
						target = map[i][j];
						visited2[i][j] = 1;
						cnt=1;
						dfs(i, j);
						
						if(cnt>=4) {
							copy();
							visited2 = new int[12][6];
						}
						else
							visited2 = new int[12][6];
					}
				}
			}
			
			change();
			down();
			
			for(int i=0;i<12;i++) {
				for(int j=0;j<6;j++) {
					if(visited[i][j] != 0)
						flag = true;
				}
			}
			
			if(!flag)
				break;
			else {
				combo++;
				answer = Math.max(answer, combo);
			}
			
			
		}
		
		System.out.println(answer);
	}

	private static void dfs(int y, int x) {
		
		for(int i=0;i<4;i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny>=0 && ny<12 && nx>=0 && nx<6 && visited2[ny][nx] == 0 && map[ny][nx] == target) {
				visited2[ny][nx] = 1;
				cnt++;
				dfs(ny, nx);
			}
		}
	}
	
	static void copy() {
		for(int i=0;i<12;i++) {
			for(int j=0;j<6;j++) {
				
				visited[i][j] += visited2[i][j];
			}
		}
	}
	
	static void down() {
		
		for(int i=0;i<6;i++) {
			int check_y = 11;
			int check_x = i;
			
			for(int j=11; j>=0; j--) {
				if(map[j][i] != '.') {
					char c = map[check_y][check_x];
					map[check_y][check_x] = map[j][i];
					map[j][i] = c;
					check_y--;
				}
			}
		}
	}
	
	static void change() {
		for(int i=0;i<12;i++) {
			for(int j=0;j<6;j++) {
				if(visited[i][j] != 0)
					map[i][j] = '.';
			}
		}
	}
}
