package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_1253 {
	static int N, cnt, A[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		A = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);

		cnt = 0;
		for (int i = 0; i < N; i++) {
			pro(i);
		}
		System.out.println(cnt);
	}

	static void pro(int idx) {
        int s = 0, e = N-1;
        int target = A[idx];
 
        while (s < e) {
            if(s == idx) s++;
            else if(e == idx) e--;
 
            else {
                if (target > A[s] + A[e]) s++;
                else if (target == A[s] + A[e]) {
                    cnt++;
                    return;
                } else e--;
            }
        }
    }

}
