package week3.BOJ_1068_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1068_트리 {

	static int N;
	static int del;
	static int[][] tree;
	static int count = 0;
	static int tmp = 0;
	static int[] spot;
	static int root;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		String[] s2 = s.split(" ");
		del = Integer.parseInt(br.readLine());
		tree = new int[N][N];
		spot = new int[N+1];
		
		for(int i=0;i<N;i++) {
			Arrays.fill(tree[i], -1);
		}
		
		for(int i=0;i<N;i++) {
			if(Integer.parseInt(s2[i]) == -1) {
				root = i;
				continue;
			}
			else if(i==del) {
				continue;
			}
			
			for(int j=0;j<N;j++) {
				if(tree[Integer.parseInt(s2[i])][j] == -1) {
					tree[Integer.parseInt(s2[i])][j] = i;
					break;
				}
			}
		}
		if(root == del) count=0;
		else dfs(root);
		
		sb.append(count);
		System.out.println(sb);
		
	}
	
	public static void dfs(int cnt) {
		
		for(int i=0;i<N;i++) {
			if(tree[cnt][i] != -1) {
				spot[tmp] = cnt;
				cnt = tree[cnt][i];
				tmp++;
				dfs(cnt);
				tmp--;
				if(tmp<0) tmp= 0;
				cnt = spot[tmp];
			}
			else if(i==0 && tree[cnt][i] == -1) {
				count++;
				return;
			}
		}
	}
}