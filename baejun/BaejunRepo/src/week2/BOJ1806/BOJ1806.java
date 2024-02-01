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
		int minLength;
		boolean flag = true;

		int[] input = new int[N];
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < input.length; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		int[] accArray = new int[N];
		accArray[0] = input[0];
		for(int i = 1; i < N; i++) {
			accArray[i] = accArray[i-1] + input[i];
		}
		/* 투포인터 */
		int leftPointer = 0;
		int rightPointer = accArray.length - 1;
		/* 모든수의 합이 S보다 작은 경우 : 그러한 합을 만드는 것이 불가한 상황*/
		if (accArray[rightPointer] < S) {
			System.out.println(0);
			return;
		} else {
			minLength = accArray.length;
		}
		while(leftPointer < rightPointer) {
			if((accArray[rightPointer] - accArray[leftPointer]) >= S) {
				if((rightPointer - leftPointer) < minLength) {
					minLength = rightPointer - leftPointer;	
				}
			}
			leftPointer++;
		}
		leftPointer = 0;
		while(leftPointer < rightPointer) {
			if((accArray[rightPointer] - accArray[leftPointer]) >= S) {
				if((rightPointer - leftPointer) < minLength) {
					minLength = rightPointer - leftPointer;	
				}
			}
			rightPointer--;
		}
		rightPointer = accArray.length - 1;
		while(leftPointer < rightPointer) {
			if((accArray[rightPointer] - accArray[leftPointer]) >= S) {
				if((rightPointer - leftPointer) < minLength) {
					minLength = rightPointer - leftPointer;	
				}
			}
			if((accArray[rightPointer - 1] - accArray[leftPointer]) >= S) {
				if((rightPointer - leftPointer) < minLength) {
					minLength = rightPointer - leftPointer;	
				}
			}
			if((accArray[rightPointer] - accArray[leftPointer + 1]) >= S) {
				if((rightPointer - leftPointer) < minLength) {
					minLength = rightPointer - leftPointer;	
				}
			}
			rightPointer--;
			leftPointer++;
		}
		System.out.println(minLength);
	}
}
