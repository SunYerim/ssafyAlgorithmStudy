package week2.BOJ1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class BOJ1806 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		/* 입력값 받기 */
		int[] input = new int[N+1];
		st = new StringTokenizer(in.readLine());
		for(int i = 1; i < input.length; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		/* 누적합 배열 생성 */
		int[] accArray = new int[N+1];
		accArray[1] = input[1];
		for(int i = 2; i <= N; i++) {
			accArray[i] = accArray[i-1] + input[i];
		}
		
		/* 투포인터로 돌리기 */
		int leftPointer = 0;
		int rightPointer = 1;
		int minLength = accArray.length - 1;
		/* 모든수의 합이 S보다 작은 경우 : 그러한 합을 만드는 것이 불가한 상황*/
		if (accArray[accArray.length - 1] < S) {
			System.out.println(0);
			return;
		}
		
		while(rightPointer < accArray.length) {
			/* 부분합이 S보다 크다면? minLength 길이 비교하고 갱신*/
			if (accArray[rightPointer] - accArray[leftPointer] >= S) {
				minLength = (minLength > rightPointer - leftPointer) ? rightPointer - leftPointer : minLength;
				leftPointer++; // S보다 큰 지점까지 왔으니, leftPointer를 옮기며 어디까지 길이 단축이 되나 확인
			} else {
				rightPointer++; // S보다 작아지면, 다시 rightPointer를 옮겨서 S보다 커지는 시점 찾기
			}
		}
		System.out.println(minLength);
	}
}
