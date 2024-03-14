package week5.SWEA_모의SW역량테스트_디저트카페;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class SWEA_모의SW역량테스트_디저트카페 {
	
	static int N;
	static int[][] map;
	static int[][] visited;
	static ArrayList<Integer> list;
	static int[] dy = {-1,1,1,-1};
	static int[] dx = {1,1,-1,-1};
	static int answer;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			bw.append("#" + test_case + "" + " ");
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<>();
			answer = -1;
			
			for(int i=0;i<N;i++) {
				String s = br.readLine();
				String[] s2 = s.split(" ");
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(s2[j]);
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					dfs(i,j);
				}
			}
			
			bw.append(answer+"");
			bw.append("\n");
			bw.flush();
		}
		bw.close();
	}
	
	static void dfs(int y, int x) {
		
		int tmp = 0;
		int tmp2 = 0;
		int ny = y;
		int nx = x;
		int r = 0;
		int c = 0;
		visited = new int[N][N];
		list.clear();
		
		for(int i=0;i<4;i++) {
			tmp2 = 0;
			
			if(i == 0) {
				while(true) {
					ny+=dy[i];
					nx+=dx[i];
					
					if(ny<0 || ny>=N || nx<0 || nx>=N || visited[ny][nx] != 0) {
						if(r==0) {
							tmp++;
							break;
						}
						ny-=dy[i];
						nx-=dx[i];
						break;
					}
					
					for(int j=0;j<list.size();j++) {
						if(map[ny][nx] == list.get(j)) {
							if(r>=2) {
								r--;
								ny-=dy[i];
								nx-=dx[i];
								tmp2++;
								break;
							}
							tmp++;
							break;
						}
					}
					
					if(tmp2 != 0)
						break;
					
					list.add(map[ny][nx]);
					visited[ny][nx]++;
					r++;
				}
			}
			else if(i == 1) {
				while(true) {
					ny+=dy[i];
					nx+=dx[i];
					
					if(ny<0 || ny>=N || nx<0 || nx>=N || visited[ny][nx] != 0) {
						if(c==0) {
							tmp++;
							break;
						}
						ny-=dy[i];
						nx-=dx[i];
						break;
					}
					
					for(int j=0;j<list.size();j++) {
						if(map[ny][nx] == list.get(j)) {
							if(c>=2) {
								c--;
								ny-=dy[i];
								nx-=dx[i];
								tmp2++;
								break;
							}
							tmp++;
							break;
						}
					}
					
					if(tmp2 != 0)
						break;
					
					list.add(map[ny][nx]);
					visited[ny][nx]++;
					c++;
				}
				
			}
			else if(i == 2) {
				
				for(int j=0;j<r;j++) {
					ny+=dy[i];
					nx+=dx[i];
					
					if(ny<0 || ny>=N || nx<0 || nx>=N || visited[ny][nx] != 0) {
						tmp++;
						break;
					}
					
					for(int k=0;k<list.size();k++) {
						if(map[ny][nx] == list.get(k)) {
							tmp++;
							break;
						}
					}
					list.add(map[ny][nx]);
					visited[ny][nx]++;
				}
				
			}
			else {
				for(int j=0;j<c;j++) {
					ny+=dy[i];
					nx+=dx[i];
					
					if(ny<0 || ny>=N || nx<0 || nx>=N || visited[ny][nx] != 0) {
						tmp++;
						break;
					}
					
					for(int k=0;k<list.size();k++) {
						if(map[ny][nx] == list.get(k)) {
							tmp++;
							break;
						}
					}
					list.add(map[ny][nx]);
					visited[ny][nx]++;
				}
			}
			
			if(tmp != 0)
				break;
		}
		
		if(tmp == 0)
			answer = list.size();
		
	}
}
