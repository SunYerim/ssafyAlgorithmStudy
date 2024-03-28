package week9.BOJ11687;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11687 {
    //2*5가 있어야함.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());

        int left = 1;
        int right = m * 5;

        boolean check = false;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (bs(mid) > m) {
                right = mid - 1;
            } else if (bs(mid) == m) {
                right = mid - 1;
                check = true;
            }
            else {
                left = mid + 1;
            }
        }
        if (check) {
            System.out.println(left);
        } else {
            System.out.println(-1);
        }
    }

    private static int bs(int mid) {
        int cnt = 0;

        for (int i = 5; i <= mid; i*=5) {
            cnt += (mid/i);
        }
        return cnt;
    }
}