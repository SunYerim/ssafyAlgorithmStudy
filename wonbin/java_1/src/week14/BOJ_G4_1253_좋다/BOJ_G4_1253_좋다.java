package week14.BOJ_G4_1253_좋다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BOJ_G4_1253_좋다 {

	static int[] array;
	static int N;
	static int answer;
	static int tmp;
	static int tmp2;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		array = new int[N];
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		for(int i=0;i<N;i++) {
			array[i] = Integer.parseInt(s2[i]);
		}
		
		Arrays.sort(array);
		
		for(int i=0;i<N;i++) {
			
			twopointer(0, N-1, i);
		}
		
		System.out.println(answer);
	}

	private static void twopointer(int left, int right, int c) {
		
		while(left < right) {
			tmp = array[left];
			tmp2 = array[right];
			
			if(left == c) {
				left++;
				continue;
			}
			else if(right == c) {
				right--;
				continue;
			}
			
			if(tmp + tmp2 == array[c]) {
				answer++;
				break;
			}
			else if(tmp + tmp2 > array[c]) {
				right--;
			}
			else if(tmp + tmp2 < array[c]) {
				left++;
			}
		}
		
	}
}
