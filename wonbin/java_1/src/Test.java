import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Test {
	
	static int N;
	static int M;
	static int[] path;
	static int[] visited;
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		path = new int[N];
		visited = new int[N+1];
		
		permutation(0, 1);
		
		System.out.println(sb);
	}
	
	static void permutation(int cnt, int start) {
		
		if(cnt == M) {
			for(int i=0;i<cnt;i++) {
				if(path[i] != 0) {
					sb.append(path[i]+" ");
				}
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start;i<=N;i++) {
			path[cnt] = i;
			visited[i]++;
			permutation(cnt+1, i);
			path[cnt] = 0;
		}
	}
}
