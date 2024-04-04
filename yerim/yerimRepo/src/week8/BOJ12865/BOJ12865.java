package week8.BOJ12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865 {
    static int n, k;
    static int[] weight;
    static int[] value;
    // 2차원 dp배열
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게

        weight = new int[101];
        value = new int[101];
        dp = new int[101][100001];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            weight[i] = a;
            value[i] = b;
        }

        // dp 점화식
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j >= weight[i])
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weight[i]] + value[i]);
                else
                    dp[i][j] = dp[i-1][j];
            }
        }


        System.out.println(dp[n][k]);



    }
}