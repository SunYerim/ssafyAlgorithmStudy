package week1.BOJ_2606_바이러스;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2606_바이러스 {
	
	static int[][] arr = new int[101][101];
	static int[] visited = new int[101];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int computer = Integer.parseInt(br.readLine());
		int c_pair = Integer.parseInt(br.readLine());

		int count = 0;

		for (int i = 0; i < c_pair; i++) {
			String s = br.readLine();
			String[] s2 = s.split(" ");
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);

			arr[a][b] = 1;
			arr[b][a] = 1;
		}

		for(int i= 2;i<=computer;i++) {
			if(arr[1][i] != 0) {
				visited[i] = 1;
				DFS(i,computer);
			}
		}
		
		for(int cnt : visited) {
			if(cnt == 1)
				count++;
		}
		bw.write(count + "" + '\n');
		bw.flush();
		bw.close();
	}
	
	public static void DFS(int y, int computer) {
		
		for(int i=2;i<=computer;i++) {
			if(arr[y][i] == 1 && visited[i] == 0) {
				visited[i] = 1;
				DFS(i, computer);
			}
		}
	}
}