
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Test {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] house = new int[N+1][3];
		int[][] answer = new int[N+1][3];
		
		for(int i=1;i<=N;i++) {
			String s = br.readLine();
			String[] s2 = s.split(" ");
			house[i][0] = Integer.parseInt(s2[0]);
			house[i][1] = Integer.parseInt(s2[1]);
			house[i][2] = Integer.parseInt(s2[2]);
		}
		
		for(int i=1;i<=N;i++) {
			answer[i][0] = Math.min(answer[i-1][1], answer[i-1][2]) + house[i][0];
			answer[i][1] = Math.min(answer[i-1][0], answer[i-1][2]) + house[i][1];
			answer[i][2] = Math.min(answer[i-1][0], answer[i-1][1]) + house[i][2];
		}
		
		
		bw.append(Math.min(Math.min(answer[N][0], answer[N][1]), answer[N][2])+""+"\n");
		bw.flush();
		bw.close();
	}
}
