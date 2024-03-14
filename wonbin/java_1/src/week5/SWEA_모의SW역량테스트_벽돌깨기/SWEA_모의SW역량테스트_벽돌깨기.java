package week5.SWEA_모의SW역량테스트_벽돌깨기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class SWEA_모의SW역량테스트_벽돌깨기 {

	static int N;
	static int W;
	static int H;
	static int[][] map;
	static int[][] map2;
	static int[] path;
	static int min;
	static int answer;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			bw.append("#" + test_case + "" + " ");
			
			String s = br.readLine();
			String[] s2 = s.split(" ");
			min = Integer.MAX_VALUE;
			answer = 0;
			
			N = Integer.parseInt(s2[0]);
			W = Integer.parseInt(s2[1]);
			H = Integer.parseInt(s2[2]);
			map = new int[H+1][W+1];
			map2 = new int[H+1][W+1];
			path = new int[5];
			
			for(int i=1;i<=H;i++) {
				s = br.readLine();
				s2 = s.split(" ");
				for(int j=1;j<=W;j++) {
					map[i][j] = Integer.parseInt(s2[j - 1]);
				}
			}
			
			permutation(0);
			
			bw.append(min+"");
			bw.append("\n");
			bw.flush();
		}
		bw.close();
	}
	
	static void permutation(int cnt) {
		
		if(cnt == N) {
			
			for(int i=1;i<=H;i++) {
				for(int j=1;j<=W;j++) {
					map2[i][j] = map[i][j];
				}
			}
			
			for(int i=0;i<cnt;i++) {
				
				if(path[i] != 0) {
					for(int j = 1;j<=H;j++) {
						if(map2[j][path[i]] != 0) {
							int ny = j;
							int nx = path[i];
							
							if(map2[j][path[i]] == 1) {
								map2[j][path[i]] = 0;
								break;
							}
							
							bfs(ny, nx);
							
							break;
							
						}
					}
					
					blockdown();
				}
			}
			
			for(int i=1;i<=H;i++) {
				for(int j=1;j<=W;j++) {
					if(map2[i][j] != 0)
						answer++;
				}
			}
			
			min = Math.min(answer, min);
			answer = 0;
			return;
			
		}
		
		for(int i=1;i<=W;i++) {
			path[cnt] = i;
			permutation(cnt+1);
		}
	}
	
	static void bfs(int y, int x) {
		
		Queue<int[]> que = new ArrayDeque<>();
		que.add(new int[] {y,x,map2[y][x]});
		map2[y][x] = 0;
		
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			int point = cur[2];
			
			for(int i=1;i<point;i++) {
				for(int j = 0;j<4;j++) {
					int ny = cur[0] + dy[j]*i;
					int nx = cur[1] + dx[j]*i;
					
					if(ny<1 || ny>H || nx<1 || nx>W || map2[ny][nx] == 0)
						continue;
					if(map2[ny][nx] != 0) {
						que.add(new int[] {ny,nx,map2[ny][nx]});
						map2[ny][nx] = 0;
					}
				}
			}
		}
		
	}
	
	static void blockdown() {
		Stack<Integer> s = new Stack<>();
		
		for(int i=1;i<=W;i++) {
			for(int j = 1;j<=H;j++) {
				if(map[j][i] != 0) {
					s.add(map[j][i]);
				}
			}
			
			for(int j = H;j>=1;j--) {
				if(s.isEmpty())
					map[j][i] = 0;
				else
					map[j][i] = s.pop();
			}
		}
	}
	
}
