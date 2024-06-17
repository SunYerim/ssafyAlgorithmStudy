package week19.PGM150368;

public class PGM150368 {
	static int[] target = {10, 20, 30, 40};
	static int[] result;
	static int emojiPlusMax = 0;
	static int emojiSellMax = 0;
	static int[][] user;
	static int[] emoji;

	class Solution {

		public int[] solution(int[][] users, int[] emoticons) {
			int[] answer = new int[2];
			result = new int[emoticons.length];
			user = users; // static하게 쓰기 위한 배열 복사
			emoji = emoticons;
			permutation(0);
			answer[0] = emojiPlusMax;
			answer[1] = emojiSellMax;
			return answer;
		}

		private void permutation(int cnt) {
			//종료조건
			if(cnt == result.length) {
				int currentEmojiPlus = 0;
				int currentSellSum = 0;
				/* 고객 전체 순회하며 이모지플러스 가입 및 판매 */
				for(int[] customer : user) {
					int customerSellSum = 0;
					for(int i = 0; i < result.length; i++) {
						if(customer[0] <= result[i]) {
							double value = (double)(100-result[i]) * 0.01;
							customerSellSum += ((double)emoji[i] * value);
							if(customerSellSum >= customer[1]) {
								currentEmojiPlus++;
								customerSellSum = 0;
								break;
							}
						}
					}
					currentSellSum += customerSellSum;
				}
				if(currentEmojiPlus > emojiPlusMax) {
					emojiPlusMax = currentEmojiPlus;
					emojiSellMax = currentSellSum;
				} else if(currentEmojiPlus == emojiPlusMax) {
					if(currentSellSum > emojiSellMax) {
						emojiSellMax = currentSellSum;
					}
				}
				return;
			}
			for(int i = 0; i < 4; i++) {
				result[cnt] = target[i];
				permutation(cnt + 1);
			}
		}
	}
}
