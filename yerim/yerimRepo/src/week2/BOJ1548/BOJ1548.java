package week2.BOJ1548;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1548 {
    static int N;
    static int result = 0;
    static int[] numArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numArr);
        result = N;

        if (N > 2) {
            result = -1;
            for (int start = 0; start < N - 2; start++) {
                for (int end = N - 1; end > start + 1; end--) {
                    if (numArr[start] + numArr[start + 1] > numArr[end]) {
                        result = Math.max(result, end - start + 1);
                        break;
                    }
                }
            }
            if (result == -1) {
                result = 2;
            }
        }
        System.out.println(result);
    }
}
