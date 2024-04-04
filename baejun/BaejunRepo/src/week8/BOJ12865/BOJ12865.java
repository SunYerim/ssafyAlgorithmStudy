package week8.BOJ12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ12865 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N+1][2];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken()); // 무게
			arr[i][1] = Integer.parseInt(st.nextToken()); // 가치			
		}
		
		int[][] knapsack = new int[N+1][K+1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= K; j++) {
				if(arr[i][0] > j) { // 담을 수 없는 경우
					knapsack[i][j] = knapsack[i-1][j];
				} else { // 담을 수 있을땐 담은 값과 안 담은 값을 비교해서 더 큰 값 넣기
					knapsack[i][j] = Math.max(knapsack[i-1][j], arr[i][1] + knapsack[i-1][j-arr[i][0]]);
				}
			}	
		}
		System.out.println(knapsack[N][K]);
	}
}
