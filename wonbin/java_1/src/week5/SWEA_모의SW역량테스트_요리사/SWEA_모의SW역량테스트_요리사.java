package week5.SWEA_모의SW역량테스트_요리사;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class SWEA_모의SW역량테스트_요리사 {

	static int N;
	static int[][] map;
	static int[] path;
	static ArrayList<Integer> list;
	static int min;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			bw.append("#" + test_case + "" + " ");
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			path = new int[N];
			min = Integer.MAX_VALUE;
			
			for(int i=0;i<N;i++) {
				String s = br.readLine();
				String[] s2 = s.split(" ");
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(s2[j]);
				}
			}
			
			comb(0,0);
			
			bw.append(min+"");
			bw.append("\n");
			bw.flush();
		}
		bw.close();
	}
	
	static void comb(int cnt, int start) {
		
		list = new ArrayList<>();
		
		for(int i=0;i<N;i++)
			list.add(i);
		
		int sum1 = 0;
		int sum2 = 0;
		
		if(cnt == N/2) {
			
			for(int i=0;i<cnt;i++) {
				int tmp = list.size();
				for(int j=0;j<tmp;j++) {
					if(path[i] == list.get(j)) {
						list.remove(Integer.valueOf(path[i]));
						break;
					}
				}
			}
			
			for(int i=0;i<cnt;i++) {
				for(int j=0;j<cnt;j++) {
					if(j == i)
						continue;
					sum1 += map[path[i]][path[j]];
				}
			}
			
			for(int i=0;i<list.size();i++) {
				for(int j=0;j<list.size();j++) {
					if(j == i)
						continue;
					sum2 += map[list.get(i)][list.get(j)];
				}
			}
			
			min = Math.min(min, Math.abs(sum1 - sum2));
		}
		
		for(int i=start;i<N;i++) {
			path[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
}
