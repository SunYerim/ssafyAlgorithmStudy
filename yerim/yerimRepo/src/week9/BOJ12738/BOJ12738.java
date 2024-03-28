package week9.BOJ12738;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12738 {
    static long n;
    static long[] numbers;
    static long[] lis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new long[(int) n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(st.nextToken());
        }
        lis = new long[(int) n];
        lis[0] = numbers[0];
        long length = 1;

        for (long i = 1; i < n; i++) {
            if (lis[(int) (length - 1)] < numbers[(int) i]) {
                lis[(int) (length++)] = numbers[(int) i];
            } else {
                long idx = bs(numbers[(int) i], 0, length - 1);
                lis[(int) idx] = numbers[(int) i];
            }
        }

        System.out.println(length);
    }

    private static long bs(long value, long start, long end) {
        while (start < end) {
            long mid = (start + end) / 2;
            if (lis[(int) mid] < value) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }
}