package week8.BOJ_S2_11053_가장긴증가하는부분수열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_S2_11053_가장긴증가하는부분수열 {

	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int answer = 0;
		int[] length = new int[n];
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(s2[i]);
		}
		
		for(int k=0;k<n;k++) {
			length[k] = 1;
			for(int i=0;i<k;i++) {
				if(arr[k] > arr[i]) {
					length[k] = Math.max(length[k], length[i]+1);
				}
			}
		}
		
		Arrays.sort(length);
		
		bw.append(length[n-1]+"");
		bw.append("\n");
		bw.flush();
		bw.close();
	}
}
