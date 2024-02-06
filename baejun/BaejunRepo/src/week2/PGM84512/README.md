<h3 align="center"> 
    📢  [level2] 프로그래머스(모음 사전) : https://school.programmers.co.kr/learn/courses/30/lessons/84512
</h3>

<br>

## 🚀 문제

사전에 알파벳 모음 'A', 'E', 'I', 'O', 'U'만을 사용하여 만들 수 있는, 길이 5 이하의 모든 단어가 수록되어 있습니다. 사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA"이며, 마지막 단어는 "UUUUU"입니다.
단어 하나 word가 매개변수로 주어질 때, 이 단어가 사전에서 몇 번째 단어인지 return 하도록 solution 함수를 완성해주세요.

---

## 🚦입출력 + 제한사항

- word의 길이는 1 이상 5 이하입니다.
- word는 알파벳 대문자 'A', 'E', 'I', 'O', 'U'로만 이루어져 있습니다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 중복 순열일거라 생각했음
- 돌려주는데, cnt로 재귀 탈출 조건 설정도 하지만, 단어의 길이설정도 해주면서(1~5) 진행
- 중복순열 그대로 돌리면서 타겟 단어 나오면 출력해주기

- [x] permutation함수 재귀호출을 통해 중복순열 구현

### 💻코드

```java
/* 중복순열 도는 함수 */
private static void permutation(int cnt, String currentWord) {
	// 기저 조건
	if(cnt == 5) { // 모음사전의 최대 길이는 5
		return;
	}
	// 유도 조건
	for(int i = 0; i < 5; i++) {
		currentWord = currentWord.substring(0, cnt) + dictionary[i]; // 자리수 기준(cnt)로 자르고 현재 숫자 더해주기
		count++;
		if(currentWord.equals(target)) {
			System.out.println(count);
		}
		permutation(cnt+1, currentWord); // 다음 자리수 재귀호출
	}
}
```

### 🙄 후기
소요시간 : 40분  <br>
아 발전이 느껴져서 기분좋다 !! <br>
옛날 같았으면 무조건 다중 for문 죽음의 노가다였을껀데, 그래도 순조부 배우고 바로 중복순열 생각해내고 써먹은게 고무적이다. <br>
앞으로도 더 효율적인, 좋은 방법을 찾는 연습과 경험을 쌓아야한다. 물론 안되면 죽음의 6중 for문이라도 돌려야하지만.. 그건 최후의 수단이다! ㅋㅋㅋ
