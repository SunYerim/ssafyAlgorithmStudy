package week2.BOJ_2805_나무자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2805_나무자르기 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		int N = Integer.parseInt(s2[0]);
		int M = Integer.parseInt(s2[1]);
		int max  = 0;
		int[] wood = new int[N];
		long sum = 0;
		
		String input = br.readLine();
		String[] input2 = input.split(" ");
		
		for(int i=0;i<N;i++) {
			max = Math.max(max, Integer.parseInt(input2[i]));
			wood[i] = Integer.parseInt(input2[i]);
		}
		
		int mid = max/2;
		int tmp = 0;
		
		while(tmp<mid) {
			for(int i=0;i<N;i++) {
				if(wood[i] - mid<0) continue;
				else {
					sum+=wood[i] - mid;
				}
			}
			
			if(sum == M) {
				break;
			}
			else if(sum<M) {
				mid--;
				sum = 0;
			}
			else {
				tmp = mid;
				mid = (max + mid) /2;
				sum=0;
			}
		}
		
		sb.append(mid);
		System.out.println(sb);
		
	}
}