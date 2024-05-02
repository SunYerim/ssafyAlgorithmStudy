package week14.BOJ_G5_1717_집합의표현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_G5_1717_집합의표현 {

	static int N;
	static int M;
	static int[] parent;	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		parent = new int[N+1];
		
		for(int i=0;i<=N;i++)
			parent[i] = i;
		
		for(int i=0;i<M;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			
			int c = Integer.parseInt(s2[0]);
			int a = Integer.parseInt(s2[1]);
			int b = Integer.parseInt(s2[2]);
			
			if(c == 0) {
				union(a, b);
			}
			else {
				if(a==b) {
					System.out.println("YES");
					continue;
				}
				a = find(a);
				b = find(b);
				
				if(a==b)
					System.out.println("YES");
				else
					System.out.println("NO");
			}
		}
		
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x >= y)
			parent[x] = y;
		else
			parent[y] = x;
			
	}
	
	static int find(int x) {
		if(parent[x] == x)
			return x;
		
		return parent[x] = find(parent[x]);
	}
}
