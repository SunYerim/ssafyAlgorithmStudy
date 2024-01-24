package Algorithm.Programmers.PGM42839;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class PGM42839 {
	static int count = 0; // 집계값
	static ArrayList<Integer> checkingDuplication = new ArrayList<Integer>(); // 중복체크용 리스트

	/* 소수 판별하는 메소드 */
	static boolean checkPrimeNumber(int number) {
		boolean flag = true;
		if(number == 1 || number == 0) flag = false;
		for(int i = 2; i <= Math.sqrt(number); i++) {
			if(number % i  == 0) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	/* 순열을 구하고(모든 경우의 수) 소수 판별하여 집계 */
	static void permutation(int[] arr, int depth, int n, int r) {
		if(depth == r) {
			String str = toString(arr, r);
			int num = Integer.parseInt(str);
			if(!checkingDuplication.contains(num)) {
				checkingDuplication.add(num);
				if(checkPrimeNumber(num)) count++;
			}
			return;
		}
		for(int i = depth; i < n; i++) {
			swap(arr, depth, i);
			permutation(arr, depth + 1, n, r);
			swap(arr, depth, i);
		}
	}
	/* 자리변경 */
	static void swap(int[] arr, int depth, int i) {
		int temp = arr[depth];
		arr[depth] = arr[i];
		arr[i] = temp;
	}
	/* 숫자 이어붙이기 */
	static String toString(int[] arr, int r) {
		String str = "";
		for(int i = 0; i < r; i++)
			str += arr[i];
		return str;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        // 결과를 한 번에 출력하기 위한 StringBuilder
        StringBuilder sb = new StringBuilder();
        
        String numbers = in.readLine();
        
        int[] numbersInteger = new int[numbers.length()];
        
        for(int i = 0; i < numbers.length(); i++) {
        	numbersInteger[i] = numbers.charAt(i) - '0';
        }
        for(int i = 1; i <= numbersInteger.length; i++) {
        	permutation(numbersInteger, 0, numbersInteger.length, i);
        }
        sb.append(count);
        System.out.println(sb);
	}
}
