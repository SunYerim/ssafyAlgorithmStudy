package week9.BOJ12015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ12015 {
    // 이분탐색
    static int n;
    static int[] numbers;
    static int[] lis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        lis = new int[n];
        lis[0] = numbers[0];
        int length = 1;

        // 각 원소 i에 대하여 lis배열의 끝 인덱스와 비교하여 i가 더 크다면 뒤에 추가

        // i가 더 작은 경우 bs를 통해 해당 원소가 들어갈 수 있는 자리 탐색하여 해당 자리의 값과 교체

        for (int i = 1; i < n; i++) {
            if (lis[length-1] < numbers[i]) {
                lis[length++] = numbers[i];
            } else {
                int idx = bs(numbers[i] , 0, length-1);
                lis[idx] = numbers[i];
            }
        }

        System.out.println(length);



    }

    private static int bs(int value, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (lis[mid] < value) {
                start = mid+1;
            } else {
                end = mid;
            }
        }
        return end;
    }
}