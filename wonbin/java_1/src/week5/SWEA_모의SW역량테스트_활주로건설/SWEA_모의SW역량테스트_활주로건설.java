package week5.SWEA_모의SW역량테스트_활주로건설;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_모의SW역량테스트_활주로건설 {
	
	static int N;
	static int X;
	static int[][] map;
	static int count;
	static int count2;
	static int[][] visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			bw.append("#" + test_case + "" + " ");
			
			String s = br.readLine();
			String[] s2 = s.split(" ");
			N = Integer.parseInt(s2[0]);
			X = Integer.parseInt(s2[1]);
			map = new int[N][N];
			count = 0;
			int tmp = 0;
			
			for(int i=0;i<N;i++) {
				s = br.readLine();
				s2 = s.split(" ");
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(s2[j]);
				}
			}
			
			for(int i=0;i<N;i++) {
				tmp = 0;
				visited = new int[N][N];
				for(int j=0;j<N - 1;j++) {
					
					if(map[i][j] - map[i][j+1] == 1) {
						for(int k=j+1;k<j+1+X;k++) {
							if(k>=N || visited[i][k] != 0) {
								tmp++;
								break;
							}
							
							if(map[i][k] == map[i][j] - 1) {
								visited[i][k]++;
							}
							else {
								tmp++;
								break;
							}
						}
						
						if(tmp != 0)
							break;
					}
					else if(map[i][j] - map[i][j+1] == -1) {
						for(int k=j+1-X;k<j+1;k++) {
							if(k<0 || visited[i][k] != 0) {
								tmp++;
								break;
							}
							
							if(map[i][k] == map[i][j+1] - 1) {
								visited[i][k]++;
							}
							else {
								tmp++;
								break;
							}
						}
						
						if(tmp != 0)
							break;
					}
					else if(Math.abs(map[i][j] - map[i][j+1]) >= 2) {
						tmp++;
						break;
					}
					
				}
				
				if(tmp == 0)
					count++;
			}
			
			for(int i=0;i<N;i++) {
				tmp = 0;
				visited = new int[N][N];
				for(int j=0;j<N - 1;j++) {
					
					if(map[j][i] - map[j+1][i] == 1) {
						for(int k=j+1;k<j+1+X;k++) {
							if(k>=N || visited[k][i] != 0) {
								tmp++;
								break;
							}
							
							if(map[k][i] == map[j][i] - 1) {
								visited[k][i]++;
							}
							else {
								tmp++;
								break;
							}
						}
						
						if(tmp != 0)
							break;
					}
					else if(map[j][i] - map[j+1][i] == -1) {
						for(int k=j+1-X;k<j+1;k++) {
							if(k<0 || visited[k][i] != 0) {
								tmp++;
								break;
							}
							
							if(map[k][i] == map[j+1][i] - 1) {
								visited[k][i]++;
							}
							else {
								tmp++;
								break;
							}
						}
						
						if(tmp != 0)
							break;
					}
					else if(Math.abs(map[j][i] - map[j+1][i]) >= 2) {
						tmp++;
						break;
					}
					
				}
				
				if(tmp == 0)
					count++;
			}
			
			bw.append(count+"");
			bw.append("\n");
			bw.flush();
			
		}
		bw.close();
	}
	
}
