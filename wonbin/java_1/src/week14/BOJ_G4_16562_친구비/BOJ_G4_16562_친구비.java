package week14.BOJ_G4_16562_친구비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G4_16562_친구비 {

	
	static int N;
	static int M;
	static int K;
	static int[] fm;
	static int[] parent;
	static int[] check;
	static int answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		K = Integer.parseInt(s2[2]);
		parent = new int[N+1];
		check = new int[N+1];
		fm = new int[N+1];
		
		for(int i=0;i<=N;i++)
			parent[i] = i;
		
		s = br.readLine();
		s2 = s.split(" ");
		
		for(int i=0;i<N;i++) {
			fm[i+1] = Integer.parseInt(s2[i]);
		}
		
		
		for(int i=0;i<M;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			if(a>b)
				union(b,a);
			else
				union(a,b);
		}
		
		for(int i=1;i<=N;i++) {
			int m =find(i);
			
			if(check[m] == 0) {
				answer += fm[m];
				check[m] = 1;
			}
		}
		
		if(answer > K)
			System.out.println("Oh no");
		else
			System.out.println(answer);
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		
		if(fm[x] > fm[y]) parent[x] = y;
		else parent[y] = x;
	}
	
    // find 연산
	public static int find(int x) {
		if(parent[x] == x) return x;
		return find(parent[x]);
	}
}
