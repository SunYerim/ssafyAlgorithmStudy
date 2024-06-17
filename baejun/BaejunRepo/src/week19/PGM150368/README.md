<h3 align="center"> 
    📢  [레벨2] 프로그래머스(2023 KAKAO BLIND RECRUITMENT / 이모티콘 할인행사) : https://school.programmers.co.kr/learn/courses/30/lessons/150368?language=java#
</h3>

<br>

## 🚀 문제

---

## 🚦입출력 + 제한사항

- 

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 입력값이 짧은거 보고 바로 완탐 떠올렸음
- 이모지 최대 길이가 7이라, 그냥 이모지 할인율 완탐 돌려서 각 경우의 수 마다 유저 이모티콘 구매현황 구해서 max값 갱신하기
- 접근법은 쉬운데.. 오랜만에 하는거라 중복 순열이냐 이게~? 이러면서 코드를 못침 ㅋㅋ

-

### 💻코드

```java
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
```

### 🙄 후기
소요시간 : 1시간  <br>
역시.. 좀만 쉬어도 바로 "나는 바보입니다" 나와버렸음 <br>
꾸준한 문제, 특히 2학기 드가면 더 귀찮아질건데 그때 꾸준히 푸는 연습과 함께, 프로그래머스에 익숙해져야한다.