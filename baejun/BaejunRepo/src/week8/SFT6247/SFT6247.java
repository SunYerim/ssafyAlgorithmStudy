package week8.SFT6247;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class SFT6247 {
	static int n;
	static long q; // 연비 1,000,000,000, 혹시 모르니 long타입
	static Long[] fuelEfficiency; // 연비 1,000,000,000, 혹시 모르니 long타입

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		q = (long) Integer.parseInt(st.nextToken());
		fuelEfficiency = new Long[n];
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < n; i++) {
			fuelEfficiency[i] = (long) Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(fuelEfficiency); // 이분 탐색을 위한 정렬
		
		for(int i = 0; i < q; i++) {
			long curMiddleValue = (long)Integer.parseInt(in.readLine());
			long idx = binarySearch(0, n-1, curMiddleValue); // 이분탐색
			if(idx < 1) { // 없거나 제일 첫번째 자리에 있을땐 그 수가 중앙값일 경우가 없음
				sb.append(0).append("\n");
				continue;
			}
			long cnt = idx * ((n-1) - idx); // 인덱스 위치로 걔보다 (작은경우C1 * 큰경우C1)
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}


	private static long binarySearch(int low, int high, long findValue) {
		int mid;
		
		if(low <= high) {
			mid = (low+high) / 2; // 중앙값 설정
			
			if(fuelEfficiency[mid] == findValue) { // 찾았다!
				return mid;
			} else if (fuelEfficiency[mid] > findValue) { // 찾는 값보다 크면 오른쪽은 다 날리고 재탐색
				return binarySearch(low, mid-1, findValue);
			} else { // 찾는 값 보다 작으면 왼쪽은 다 날리고 재탐색
				return binarySearch(mid+1, high, findValue);
			}
		}
		return -1;
	}
}
