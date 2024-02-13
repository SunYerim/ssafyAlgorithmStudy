package week3.BOJ1669;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1669 {
    static int X, Y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        int diff = Y - X;
        long n = 0;

        if (diff == 0) {
            System.out.println(0);
            return;
        }

        while (n * n < diff) n++;

        n = (n * n == diff) ? n : n-1;

        long answer = 2 * n - 1;
        diff -= n * n;

        while (diff > 0) {
            diff -= Math.min(n, diff);
            answer++;
        }

        System.out.println(answer);

    }


}
