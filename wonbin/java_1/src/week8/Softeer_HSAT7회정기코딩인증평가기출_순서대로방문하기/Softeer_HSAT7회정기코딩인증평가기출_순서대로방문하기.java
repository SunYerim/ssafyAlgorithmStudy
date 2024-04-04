package week8.Softeer_HSAT7회정기코딩인증평가기출_순서대로방문하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Softeer_HSAT7회정기코딩인증평가기출_순서대로방문하기 {

	static int N;
	static int M;
	static int[][] map;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		map = new int[N][N];
		visited = new int[N][N];
		
		for(int i=0;i<N;i++) {
			s= br.readLine();
			s2 = s.split(" ");
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(s2[j]);
			}
		}
		
		for(int i=0;i<M;i++) {
			s = br.readLine();
			s2 = s.split(" ");
		}
		
	}
}
