package Algorithm.BAEKJOON.BOJ11659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11659 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        // 결과를 한 번에 출력하기 위한 StringBuilder
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        
        /* 입출력 과정 */
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] cumulativeSum = new int[N + 1];
        cumulativeSum[0] = 0;
        
        st = new StringTokenizer(in.readLine());
        /* 첫번째 값부터 ~ i번째 값까지의 누적합을 배열에 저장 */
        int sum = 0;
        for (int i = 1; i <= N; i++) {
        	sum += Integer.parseInt(st.nextToken());
        	cumulativeSum[i] = sum;
        }
        /* prefix 누적합 : i부터 j까지의 합은 (j까지의 누적합 - (i-1)까지의 누적합) */
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(in.readLine());
        	int range1 = Integer.parseInt(st.nextToken());
        	int range2 = Integer.parseInt(st.nextToken());
        	System.out.println(cumulativeSum[range2] - cumulativeSum[range1 - 1]);
        }
	}

}
