package week9.BOJ_S2_1654_랜선자르기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class BOJ_S2_1654_랜선자르기 {
	
	static int N;
	static int K;
	static long[] lan;
	static long count;
	static long start;
	static long end;
	static long mid;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		K = Integer.parseInt(s2[0]);
		N = Integer.parseInt(s2[1]);
		lan = new long[K];
		count = 0;
		start = 0;
		end = 0;
		
		
		for(int i=0;i<K;i++) {
			lan[i] = Integer.parseInt(br.readLine());
			end = Math.max(end, lan[i]);
		}
		
		end++;
		
		while(start<end) {
			
			mid = (start+end)/2;
			
			for(int i=0;i<K;i++) {
				count += lan[i]/mid;
			}
			
			if(count<N)
				end = mid;
			else {
				start = mid + 1;
			}
			
			count = 0;
		}
		
		start--;
		
		bw.append(start+"");
		bw.append("\n");
		bw.flush();
		bw.close();
		
	}
}