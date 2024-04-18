package week12.BOJ_G4_14938_서강그라운드;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_G4_14938_서강그라운드 {

	static int n;
	static int m;
	static int r;
	static int[] item;
	static int[][] graph;
	static int[] visited;
	static int[] count;
	static int answer;
	static int tmp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		n = Integer.parseInt(s2[0]);
		m = Integer.parseInt(s2[1]);
		r = Integer.parseInt(s2[2]);
		graph = new int[n+1][n+1];
		item = new int[n+1];
		
		s = br.readLine();
		s2 = s.split(" ");
		
		for(int i=1;i<=n;i++) {
			item[i] = Integer.parseInt(s2[i-1]);
		}
		
		for(int i=0;i<=n;i++) {
			Arrays.fill(graph[i], 150000);
		}
		
		for(int i=0;i<=n;i++) {
			graph[i][i] = 0;
		}
		
		for(int i=0;i<r;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			int c = Integer.parseInt(s2[2]);
			
			graph[a][b] = c;
			graph[b][a] = c;
		}
		
		for(int k=1;k<=n;k++) {
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
				}
			}
		}
		
		for(int i=1;i<=n;i++) {
			tmp = 0;
			for(int j=1;j<=n;j++) {
				if(graph[i][j] <= m)
					tmp += item[j];
			}
			answer = Math.max(answer, tmp);
		}
		
		bw.append(answer+"");
		bw.append("\n");
		bw.close();
		
	}
}
