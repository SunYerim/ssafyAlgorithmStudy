package week18.BOJ_G3_10986_나머지합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G3_10986_나머지합 {

	static int N;
	static int M;
	static int[] arr;
	static int idx1;
	static int idx2;
	static long sum;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String[] s2 = s.split(" ");

		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		arr = new int[N];

		s = br.readLine();
		s2 = s.split(" ");

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(s2[i]);
		}

		sum = arr[0];
		
		if(sum % M == 0)
			answer++;

		for(int i=1;i<N;i++) {
			sum+=arr[i];
			if(sum % M == 0)
				answer++;
		}
		
		for(int i=0;i<N-1;i++) {
			sum-=arr[i];
			if(sum % M == 0)
				answer++;
		}

		System.out.println(answer);
	}
}
