package week19.BOJ_G4_1339_단어수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_G4_1339_단어수학 {
	
	static int N;
	static int[] alpha;
	static String[] arr;
	static int[] point;
	static int[] count;
	static long answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int idx = 0;
		int check = 0;
		int number = 9;
		arr = new String[N];
		point = new int[N];
		count = new int[N];
		alpha = new int[100];
		
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			arr[i] = s;
		}
		
		for(int i=0;i<N;i++) {
			int size = arr[i].length();
			int tmp = size;
			
			for(int j=0;j<size;j++) {
				double len = Math.pow(10, tmp-1);
				idx = arr[i].charAt(j);
				alpha[idx] += (int)len;
				tmp--;
				if(tmp<=0)
					tmp = 0;
			}
		}
		
		Arrays.sort(alpha);
		
		for(int i=99;i>=0;i--) {
			if(alpha[i] == 0)
				break;
			else {
				answer += alpha[i] * number;
				number--;
			}
		}
		
		System.out.println(answer);
	}
}
