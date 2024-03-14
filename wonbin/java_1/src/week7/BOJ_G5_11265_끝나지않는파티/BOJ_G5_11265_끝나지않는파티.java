package week7.BOJ_G5_11265_끝나지않는파티;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_G5_11265_끝나지않는파티 {

	static int N;
	static int M;
	static int[][] map;
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		map = new int[N+1][N+1]; // 위치 구하기 위해서
		
		for(int i=1;i<=N;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			for(int j=1;j<=N;j++) {
				map[i][j] = Integer.parseInt(s2[j-1]);
			}
		}
		
		for(int k=1;k<=N;k++) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++)
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]); // 플로이드 워셜
			}
		}
		
		for(int i=0;i<M;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			
			int start = Integer.parseInt(s2[0]);
			int end = Integer.parseInt(s2[1]);
			int count = Integer.parseInt(s2[2]);
			if(count>=map[start][end]) {
				bw.append("Enjoy other party");
				bw.append("\n");
			}
			else {
				bw.append("Stay here");
				bw.append("\n");
			}
		}
		
		bw.flush();
		bw.close();
	}
}
