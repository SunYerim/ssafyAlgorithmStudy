package week4.BOJ1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1759 {
	static char[] alphabet;
	static int L;
	static int C;
	static boolean[] visited;
	static String result = "";

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alphabet = new char[C];
		visited = new boolean[C];
		st = new StringTokenizer(in.readLine());

		for(int i = 0; i < C; i++) {
			alphabet[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(alphabet);
		combination(0, 0);
	}

	private static void combination(int start, int cnt) {
		// 기저조건
		if(result.length() == L) {
			int aeiou = 0;
			int another = 0;
			for(int i = 0; i < result.length(); i++) {
				if(result.charAt(i) == 'a' || result.charAt(i) == 'e' || result.charAt(i) == 'i' || result.charAt(i) == 'o' || result.charAt(i) == 'u') {
					aeiou++;
				} else {
					another++;
				}
			}
			if(aeiou > 0 && another > 1) {
				System.out.println(result);
			}
			return;
		}
		// 유도조건
		for(int i = start; i < C; i++) {
			visited[i] = true;
			result += alphabet[i];
			combination(i + 1, cnt + 1);
			result = result.substring(0, (result.length() - 1));
			visited[i] = false;
		}
	}
}