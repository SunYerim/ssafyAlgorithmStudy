package week2.PGM84512;

public class PGM84512 {
	private static String[] dictionary = {"A", "E", "I", "O", "U"};
	private static int count = 0;
	private static String target;

	public static void main(String[] args) {
		target = "I";
		permutation(0, "");
	}

	private static void permutation(int cnt, String currentWord) {
		// 기저 조건
		if(cnt == 5) { // 모음사전의 최대 길이는 5
			return;
		}
		// 유도 조건
		for(int i = 0; i < 5; i++) {
			currentWord = currentWord.substring(0, cnt) + dictionary[i]; // 자리수 기준으로 자르고 현재 숫자 더해주기
			count++;
			if(currentWord.equals(target)) {
				System.out.println(count);
			}
			permutation(cnt+1, currentWord); // 다음 자리수 재귀호출
		}
	}
}