package week20.BOJ_G5_2668_숫자고르기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class BOJ_G5_2668_숫자고르기 {
	
	static int N;
	static int[] numarr;
	static int[] visited;
	static ArrayList<Integer> checkidx;
	static ArrayList<Integer> checknum;
	static int idx;
	static int answer;
	static TreeSet<Integer> answerarr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		numarr = new int[N+1];
		answerarr = new TreeSet<Integer>();
		
		for(int i=1;i<=N;i++) {
			int tmp = Integer.parseInt(br.readLine());
			numarr[i] = tmp;
		}
		
		for(int i=1;i<=N;i++) {
			checkidx = new ArrayList<Integer>();
			checknum = new ArrayList<Integer>();
			visited = new int[N+1];
			checkidx.add(i);
			checknum.add(numarr[i]);
			visited[i] = 1;
			idx = numarr[i];
			if(checkidx.get(0) == checknum.get(0)) {
				answerarr.add(i);
				continue;
			}
			while(true) {
				int tmp = 0;
				if(visited[idx] == 1) {
					Collections.sort(checkidx);
					Collections.sort(checknum);
					
					for(int j=0;j<checkidx.size();j++) {
						if(checkidx.get(j) != checknum.get(j)) {
							tmp = 1;
							break;
						}
					}
					if(tmp == 1)
						break;
					else {
						for(int p : checkidx) {
							answerarr.add(p);
						}
						break;
					}
				}
				else {
					visited[idx] = 1;
					checkidx.add(idx);
					checknum.add(numarr[idx]);
					idx = numarr[idx];
				}
			}
		}
		
		bw.append(answerarr.size()+"");
		bw.append("\n");
		for(int p: answerarr) {
			bw.append(p+"");
			bw.append("\n");
		}
		
		bw.flush();
		bw.close();
		
	}
}

