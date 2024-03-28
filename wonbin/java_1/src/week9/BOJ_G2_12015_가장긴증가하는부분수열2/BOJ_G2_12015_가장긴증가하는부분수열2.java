package week9.BOJ_G2_12015_가장긴증가하는부분수열2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_G2_12015_가장긴증가하는부분수열2 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];  // 수열의 수들
		int[] C = new int[N];  // 동적테이블 C[k] : 해당(k) 길이를 만족하는 자리(k자리)에 오는 수의 최솟값

		String[] split = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(split[i]);
		}

		/**
		 * 2. 알고리즘 풀기
		 */
		int size = 0;
		for (int i = 0; i < N; i++) {

			// binarySearch 리턴 값
			// 찾는 값이 존재하면 해당 인덱스 번호 리턴
			// 찾는 값이 존재하지 않으면 (삽입 되어야 하는 위치 인덱스 번호 + 1) * -1 값을 리턴, +1 하는 이유는 인덱스 번호 0번 때문에 
			// binarySearch 실행 전에는 C 배열이 정렬된 상태여야 하는데
			// C 배열은 항상 정렬된 상태이므로 특별히 정렬할 필요는 없다.
			int pos = Arrays.binarySearch(C, 0, size, arr[i]);
			if (pos >= 0) continue;  // 찾은 경우는 해당 위치에 값을 넣어도 변화 없기 때문에 넘어간다.

			int insertPos = Math.abs(pos) - 1;  // 삽입 위치 인덱스 값 계산 (맨 뒤 또는 기존 원소 대체 자리)
			C[insertPos] = arr[i];  // insertPos 자리에 값을 update 하면 그 의미는 
			// 0인덱스 위치부터 insertPos 위치까지의 원소 갯수가 insertPos 위치에 저장된 그 값을 마지막으로 하는 LIS 길이가 됨
			// 배열의 원소가 LIS를 이루는 구성요소와는 관계가 없다.

			if (insertPos == size) size++;  // 삽입 위치의 인덱스와 크기가 같으면(결국, 마지막이 삽입 위치라는 얘기임) 크기 1 늘림
		}
		
		System.out.println(size);
		
	}
}
