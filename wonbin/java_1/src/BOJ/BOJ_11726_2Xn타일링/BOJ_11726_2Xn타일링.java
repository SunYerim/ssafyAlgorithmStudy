package BOJ.BOJ_11726_2Xn타일링;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_11726_2Xn타일링 {
	
	private static long[] fibo_arr = new long[1004];

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		long result = 0;
		
		fibo_arr[0] = 1;
		fibo_arr[1] = 1;
		
		result  = fibo(n);
		
		bw.write(result +"" + '\n');
		bw.flush();
		bw.close();
		
	}
	
	private static long fibo(int n) {
		
		if(fibo_arr[n] != 0) {
			return fibo_arr[n];
		}
		else if(n < 2) {
			return 1;
		}
		else {
			fibo_arr[n] =  (fibo(n-2) + fibo(n-1)) % 10007;
		}
		
		return fibo_arr[n];
	}
}
