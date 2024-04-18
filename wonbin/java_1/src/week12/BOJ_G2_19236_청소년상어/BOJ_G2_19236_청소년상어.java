package week12.BOJ_G2_19236_청소년상어;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_G2_19236_청소년상어 {

	static shark[][] map;
	static int[][] visited;
	static int[] dir_y = {-1,-1,0,1,1,1,0,-1};
	static int[] dir_x = {0,-1,-1,-1,0,1,1,1};
	static int max;
	static int sum;
	static shark sk;
	static point p;
	
	static class shark {
		int num;
		int dir;
		
		public shark(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "shark [num=" + num + ", dir=" + dir + "]";
		}
		
	}
	
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
		
		map = new shark[4][4];
		visited = new int[4][4];

		for(int i=0;i<4;i++) {
			String s = br.readLine();
			String[] s2 = s.split(" ");
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			for(int j=0;j<4;j++) {
				map[i][j] = new shark(a,b);
			}
		}
		
		visited[0][0] = 1;
		sk = map[0][0];
		p = new point(0,0);
		sum += sk.num;
		max = sum;
		eat(0,0);
		
	}

	private static void move() {
		int cnt = 1;
		
		while(true) {
			
			int flag = 0;
			
			for(int i=0;i<4;i++) {
				for(int j=0;j<4;j++) {
					if(map[i][j].num == cnt) {
						int d_idx = map[i][j].dir - 1;
						int ny = i + dir_y[d_idx];
						int nx = j + dir_x[d_idx];
						if(ny>=0 && ny<4 && nx>=0 && nx<4 && visited[ny][nx] == 0) {
							shark tmp = map[ny][nx];
							map[ny][nx] = map[i][j];
							map[i][j] = tmp;
							flag++;
							break;
						}
						else {
							int idx = map[i][j].dir - 1;
							for(int c=0;c<8;c++) {
								if(idx>=8)
									idx=0;
								ny = i + dir_y[idx];
								nx = j + dir_x[idx];
								if(ny>=0 && ny<4 && nx>=0 && nx<4 && visited[ny][nx] == 0) {
									shark tmp = map[ny][nx];
									map[ny][nx] = map[i][j];
									map[i][j] = tmp;
									flag++;
									break;
								}
								idx++;
							}
						}
					}
				}
				if(flag != 0)
					break;
			}
			
			cnt++;
			if(cnt>16)
				break;
		}
		
	}

	private static void eat(int y, int x) {
		visited[p.y][p.x] = 0;
		map[p.y][p.x] = null;
		
		int ny = y+dir_y[sk.dir-1];
		int nx = x+dir_x[sk.dir-1];
		
		if(ny>=0 && ny<4 && nx>=0 && nx<4 && map[ny][nx] == null) {
			
		}
		
	}
}
