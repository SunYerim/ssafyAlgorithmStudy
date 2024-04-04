package week8.BOJ1912;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912 {
    // 연속적으로 선택한 값이 최대
    static int n, max;
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n];
        StringTokenizer  st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 초기화
        dp[0] = arr[0];
        max = arr[0];
        // 시작
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(arr[i], dp[i-1] + arr[i]);

            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}
