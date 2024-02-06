package week2.BOJ9934;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ9934 {
    static int K;
    static int[] numArr;
    static List<Integer>[] levels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        int size = (int) Math.pow(2, K) - 1;

        numArr = new int[size];
        levels = new ArrayList[K];
        for (int i = 0; i < K; i++) {
            levels[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, size -1, 0);

        for (int i = 0; i < K; i++) {
            for (int j : levels[i]) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    static void solve(int start, int end, int level) {
        if (start > end) return;
        int mid = (start + end) / 2;
        levels[level].add(numArr[mid]);
        solve(start, mid-1, level+1);
        solve(mid+1, end, level+1);
    }

}
