package week9.BOJ1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1654 {
    static int N, K;

    static long[] lanCable;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 잘라야되는 갯수

        lanCable = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            lanCable[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(lanCable); // 정렬 -> 이분탐색 위해서

        long start = 1;
        long end = lanCable[N-1];
        long result = 0;


        while (start <= end) {
            long middle = (start + end) / 2;
            long count = 0;

            for (long cable : lanCable) {
                count += cable / middle;
            }

            if (count >= K) {
                start = middle + 1;
                result = middle;
            } else{
                end = middle - 1;
            }

        }

        System.out.println(result);


    }
}
