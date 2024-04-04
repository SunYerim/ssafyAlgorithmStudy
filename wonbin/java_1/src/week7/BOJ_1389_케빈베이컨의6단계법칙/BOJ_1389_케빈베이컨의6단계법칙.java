package week7.BOJ_1389_케빈베이컨의6단계법칙;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BOJ_1389_케빈베이컨의6단계법칙 {
	
	static int N;
	static int M;
	static int[] visited;
	static int[] answer;
	static int result;
	static int[][] map;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		answer = new int[N+1];
		result =0;
		map = new int[N+1][N+1];
		int tmp = Integer.MAX_VALUE;
		
		for(int i=0;i<M;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			
			int start = Integer.parseInt(s2[0]);
			int end = Integer.parseInt(s2[1]);
			
			map[start][end] = 1;
			map[end][start] = 1;
		}
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(i!=j && map[i][j] == 0)
					map[i][j] = 200000;
			}
		}
		
		for(int k=1;k<=N;k++) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				answer[i] += map[i][j];
			}
		}
		
		for(int i=1;i<=N;i++) {
			if(tmp>answer[i]) {
				tmp = answer[i];
				result = i;
			}
		}
		
		bw.append(result+"");
		bw.append("\n");
		bw.flush();
		bw.close();
		
	}
}