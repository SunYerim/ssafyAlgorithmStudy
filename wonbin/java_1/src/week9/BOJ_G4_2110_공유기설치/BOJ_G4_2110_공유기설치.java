package week9.BOJ_G4_2110_공유기설치;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_G4_2110_공유기설치 {
	
	static int N;
	static int C;
	static int iptime;
	static int[] house;
	static int answer;
	static int start;
	static int end;
	static int mid;
	static int cnt;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		C = Integer.parseInt(s2[1]);
		house = new int[N];
		iptime = 2;
		
		for(int i=0;i<N;i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(house);
		start = house[0];
		end = house[N-1];
		
		if(C == 2) {
			answer = house[N-1] - house[0];
			bw.append(answer+"");
			bw.append("\n");
		}
		else {
			binarysearch(0, N - 1);
			bw.append(answer+"");
			bw.append("\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	static void binarysearch(int s, int e) {
		
		while(iptime<C) {
			mid = (s + e) / 2;
			
			if((house[mid] - house[s]) <= (house[e] - house[mid])) {
				answer = house[mid] - house[s];
				e = mid;
			}
			else {
				answer = house[e] - house[mid];
				s = mid + 1;
			}
			
			iptime++;
			
		}
	}

}
