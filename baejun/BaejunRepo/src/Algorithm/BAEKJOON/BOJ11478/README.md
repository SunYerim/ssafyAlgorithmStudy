<h3 align="center"> 
    📢  [실버3] 백준(서로 다른 부분 문자열의 개수(AC)) : https://www.acmicpc.net/problem/11478
</h3>

<br>

## 🚀 문제

문자열 S가 주어졌을 때, S의 서로 다른 부분 문자열의 개수를 구하는 프로그램을 작성하시오.
부분 문자열은 S에서 연속된 일부분을 말하며, 길이가 1보다 크거나 같아야 한다.
예를 들어, ababc의 부분 문자열은 a, b, a, b, c, ab, ba, ab, bc, aba, bab, abc, abab, babc, ababc가 있고, 서로 다른것의 개수는 12개이다.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 문자열 S가 주어진다. S는 알파벳 소문자로만 이루어져 있고, 길이는 1,000 이하이다.
- 첫째 줄에 S의 서로 다른 부분 문자열의 개수를 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
- [x] 단순히 완전탐색하고 중복 검사 후 집어넣으면 시간초과 이슈가 생김.
- [x] 모든 부분문자열을 탐색하고 추가하는데 hashSet을 사용하여 중복 부분문자열을 관리하자

### 💻코드

```java
/* 모든 부분문자열 추출 */ 
for(int j = 1; j <= inputStr.length(); j++) {
	for(int i = 0; i <= inputStr.length() - j; i++) {
		String a = inputStr.substring(i, i + j);
		stringSet.add(a); //hashSet에 추가(중복제거)
	}	
}
```

### 🙄 후기

Collection 배운김에, HashSet 사용하는 문제 풀어봤다.<br>
Java로 알고리즘 푸는데 Collection 활용은 필수로 느껴지니 앞으로 친하게 지내보자