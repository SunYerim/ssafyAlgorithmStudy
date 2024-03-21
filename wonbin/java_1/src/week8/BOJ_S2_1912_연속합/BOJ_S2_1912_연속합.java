package week8.BOJ_S2_1912_연속합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_S2_1912_연속합 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int answer = 0;
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		for(int i=0;i<N;i++) {
			if(i == 0)
				arr[i] = Integer.parseInt(s2[i]);
			else {
				arr[i] = Math.max(Integer.parseInt(s2[i]) + arr[i-1] ,Integer.parseInt(s2[i]));
			}
			
		}
		
		Arrays.sort(arr);
		
		bw.append(arr[N-1]+"");
		bw.append("\n");
		bw.flush();
		bw.close();
	}

}
