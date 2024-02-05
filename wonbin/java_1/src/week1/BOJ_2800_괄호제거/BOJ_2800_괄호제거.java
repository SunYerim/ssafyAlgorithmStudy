package week1.BOJ_2800_괄호제거;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_2800_괄호제거 {
	
	private static int N; // N개 중
	private static int[] numbers;
	static String s;
	static LinkedList<Character> list = new LinkedList<>();
	static ArrayList<String> list_f;
	static int[][] d_idx;
	static HashSet<String> hash = new HashSet<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		s = br.readLine();
		int count = 0;
		int tmp = 0;
		
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i) == '(') {
				count++;
			}
		}
		
		d_idx = new int[count][2];
		numbers = new int[count];
		
		for(int i =0 ;i<s.length();i++) {
			if(s.charAt(i) == ')') {
				tmp--;
				d_idx[tmp][1] = i;
			}
			list.add(s.charAt(i));
			if(s.charAt(i) == '(') {
				d_idx[tmp][0] = i;
				tmp++;
			}
		}
		
		for(int i=0;i<count;i++) {
			numbers[i] = d_idx[i][1];
		}
		
		for(int i=0;i<count;i++) {
			N = i+1;
			combi(0,0);
		}
		
		list_f = new ArrayList<>(hash);
		
		Collections.sort(list_f);
		
		for(String li : list_f) {
			bw.write(li + '\n');
		}
		
		bw.flush();
		bw.close();
		
	}


	private static void combi(int cnt, int start) {

		if (cnt == N) {
			for(int i=0;i<cnt;i++) {
				String s2=s;
				s2.replace(s2.charAt(d_idx[i][0]) + "", "");
				s2.replace(s2.charAt(d_idx[i][1]) + "", "");
				hash.add(s2);
			}
			
			return;
		}

		// 시작점부터 가능한 끝까지 선택하는 시도
		for (int i = 0; i < N; i++) {
			combi(cnt + 1, i + 1); // 현재 선택한 수의 다음부터 선택하도록 시작점 주기!
		}
	}
}