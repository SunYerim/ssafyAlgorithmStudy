package week5.SWEA_모의SW역량테스트_보물상자비밀번호;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SWEA_모의SW역량테스트_보물상자비밀번호 {

	static int N;
	static int K;
	static char[] line;
	static String[] pw;
	static ArrayList<Integer> answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1;test_case<=T;test_case++) {
			bw.append("#"+test_case+""+" ");
			
			String s = br.readLine();
			String[] s2 = s.split(" ");
			
			N = Integer.parseInt(s2[0]);
			K = Integer.parseInt(s2[1]);
			line = new char[N];
			pw = new String[N];
			int tmp = 0;
			int count = 0;
			char cmp = ' ';
			answer = new ArrayList<>();
			int div = N / 4;
			
			String input = br.readLine();
			for(int i=0;i<N;i++)
				line[i] = input.charAt(i);
			
			String sp = "";
			
			for(int i=0;i<div;i++)
			{
				sp = "";
				for(int j = 0;j<N;j++) {
					sp += line[j];
					if(j%div == div - 1) {
						count = 0;
						for(int k=0;k<pw.length;k++) {
							if(pw[k] != null && pw[k].equals(sp))
								count++;
						}
						if(count == 0) {
							pw[tmp] = sp;
							tmp++;
						}
						sp = "";
					}
				}
				
				cmp = line[N - 1];
				for(int j=N - 2;j>=0;j--) {
					line[j+1] = line[j];
				}
				line[0] = cmp;
			}
			
			for(int i=0;i<N;i++) {
				if(pw[i] != null) {
					answer.add(Integer.parseInt(pw[i], 16));
				}
			}
			
			Collections.sort(answer, Collections.reverseOrder());
			
			bw.append(answer.get(K-1)+"");
			bw.append("\n");
			bw.flush();
		}
		
		bw.close();
	}
}
