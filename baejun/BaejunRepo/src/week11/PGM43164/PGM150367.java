package week11.PGM43164;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class PGM150367 {
	public static void main(String[] args) throws IOException {
	}
}

class Solution {
	static boolean answerFlag;

	public int[] solution(long[] numbers) {
		int[] answer = new int[numbers.length];
		for(int i = 0; i < numbers.length; i++) {
			/* 1. 2진수 변환, 높이 파악 후 포화 이진트리가 되도록 앞에 0 붙여주기 */
			String binaryNumber = Long.toBinaryString(numbers[i]);
			long binaryTreeHeight = (binaryNumber.length() == 1) ? 1 : 2;
			long squreTwo = 2;
			// System.out.print(binaryNumber + " ");
			while(binaryTreeHeight != 1 && binaryTreeHeight < binaryNumber.length()) {
				squreTwo *= 2;
				binaryTreeHeight = squreTwo - 1;
			}
			String zeroSum = "";
			for(int j = 0; j < binaryTreeHeight - binaryNumber.length(); j++) {
				zeroSum += "0";
			}
			binaryNumber = zeroSum + binaryNumber;
			// System.out.println(binaryNumber);
			/* 문자열 DFS */
			// System.out.print(binaryNumber + " ");
			answerFlag = false;
			DFS(binaryNumber);

			if(answerFlag) {
				answer[i] = 0;
			} else {
				answer[i] = 1;
			}
		}
		return answer;
	}

	public void DFS(String str) {
		// 기저조건(1이거나(끝까지갔) 이미 답이 구해졌거나)
		if(str.length() <= 1 || answerFlag) {
			return;
		}
		// 유도조건(mid 구해서 문제 없나 체크하고, 자식들 다시 DFS 보내기)
		int mid = (str.length()-1) / 2;
		char root = str.charAt(mid);
		if(root == '0') {
			for(int i = 0; i < str.length(); i++) {
				if(str.charAt(i) == '1') {
					answerFlag = true;
					break;
				}
			}
			String left = "";
			String right = "";
			for(int i = 0; i < mid; i++) {
				left += str.charAt(i);
			}
			for(int i = mid+1; i < str.length(); i++) {
				right += str.charAt(i);
			}
			DFS(left);
			DFS(right);
		} else {
			String left = "";
			String right = "";
			for(int i = 0; i < mid; i++) {
				left += str.charAt(i);
			}
			for(int i = mid+1; i < str.length(); i++) {
				right += str.charAt(i);
			}
			DFS(left);
			DFS(right);
		}
	}
}