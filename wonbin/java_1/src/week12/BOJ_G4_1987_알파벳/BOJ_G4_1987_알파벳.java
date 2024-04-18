package week12.BOJ_G4_1987_알파벳;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class BOJ_G4_1987_알파벳 {

	static int R;
	static int C;
	static char[][] map;
	static int[][] visited;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static HashSet<Character> hset;
	static int answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		R = Integer.parseInt(s2[0]);
		C = Integer.parseInt(s2[1]);
		map = new char[R][C];
		visited = new int[R][C];
		hset = new HashSet<>();
		
		for(int i=0;i<R;i++) {
			s = br.readLine();
			for(int j=0;j<C;j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		visited[0][0] = 1;
		hset.add(map[0][0]);
		dfs(0,0,1);
		
		bw.append(answer+"");
		bw.append("\n");
		bw.flush();
		bw.close();
	}

	private static void dfs(int y, int x, int cnt) {
		
		answer = Math.max(answer, cnt);

		for(int i=0;i<4;i++) {
			
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if(ny>=0 && ny<R && nx>=0 && nx<C && visited[ny][nx] == 0 && hset.add(map[ny][nx])) {
				visited[ny][nx] = 1;
				dfs(ny, nx, cnt+1);
				visited[ny][nx] = 0;
				hset.remove(map[ny][nx]);
			}
		}
	}
}
