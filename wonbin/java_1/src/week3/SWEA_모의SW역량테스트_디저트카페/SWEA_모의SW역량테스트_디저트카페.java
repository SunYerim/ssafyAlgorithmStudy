package week3.SWEA_모의SW역량테스트_디저트카페;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_모의SW역량테스트_디저트카페 {
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] dessert;
	
	static int[] dy = {-1,1,1,-1};
	static int[] dx = {1,1,-1,-1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int test_case=1; test_case<=T; test_case++) {
			sb.append("#"+test_case+" ");
			
			N = Integer.parseInt(br.readLine());
			dessert = new int[N][N];
			
		}
		System.out.println(sb);
	}
}
