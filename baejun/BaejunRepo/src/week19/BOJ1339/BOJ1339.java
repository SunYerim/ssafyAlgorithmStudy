package week19.BOJ1339;

import java.io.*;
import java.util.*;

public class BOJ1339 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		
		Map<Character, Integer> map = new HashMap<>();
		int N = Integer.parseInt(in.readLine());
		String[] words = new String[N];
		
		for(int i = 0; i < N; i++) {
			words[i] = in.readLine();
		}
		
		// 기댓값 계산
		for(int i = 0; i < N; i++) {
			int num1 = 1;
			for(int j = words[i].length()-1; j >= 0; j--) {
				char c = words[i].charAt(j);
				if(map.get(c) == null) {
					map.put(c, num1);
				} else {
					int value = map.get(c);
					value += num1;
					map.put(c, value);
				}
				num1 *= 10;
			}
		}
		
		// Map 정렬을 위한 keySet List
		List<Character> keys = new ArrayList<>(map.keySet());
		Collections.sort(keys, (o1, o2) -> (map.get(o2).compareTo(map.get(o1))));
		
		// 기댓값으로 산정된 알파벳의 value를 담을 map2
		Map<Character, Integer> map2 = new HashMap<>();
		int expector = 9;
		for (Character key : keys) {
			map2.put(key, expector);
			expector--;
		}

		int sum = 0;
		for(int i = 0; i < N; i++) {
			String wordInteger = "";
			for(int j = 0; j < words[i].length(); j++) {
				wordInteger += map2.get(words[i].charAt(j)).toString();
			}
			sum += Integer.parseInt(wordInteger);
		}
		System.out.println(sum);
	}
}
