package week5.SWEA5658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SWEA5658 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			StringTokenizer st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String str = in.readLine();
			char[] chars = new char[N];
			for(int i = 0; i < N; i++) {
				chars[i] = str.charAt(i);
			}
			int branch = N / 4; // 2~7
			HashSet<String> set = new HashSet<>(); // 중복관리를 위해 HashSet사용
			for(int i = 0; i < branch; i++) { // branch 이상의 반복은 동일한 값을 뱉으므로 불필요
				for(int j = i; j < N; j+=branch) {
					String s1 = "";
					for(int k = j; k < j+branch; k++) {
						int temp = (k >= N) ? k-N : k; // 회전을 따로 안시키고 그냥 조회할때 값 처리로 회전 대신진행
						s1+=chars[temp];
					}
					set.add(s1);
				}
			}
			Long[] list = new Long[set.size()];
			int listIdx = -1;
			for(String s : set) {
				long decimal = Long.parseLong(s, 16); // 16->10 변환
				list[++listIdx] = decimal;
			}
			Arrays.sort(list);
			sb.append(list[list.length-K]).append("\n");
		}
		System.out.println(sb);
	}
}
