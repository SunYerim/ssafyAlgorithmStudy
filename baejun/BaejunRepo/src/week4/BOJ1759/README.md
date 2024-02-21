<h3 align="center"> 
    📢  [골드5] 백준(암호 만들기) : https://www.acmicpc.net/problem/1759
</h3>

<br>

## 🚀 문제

바로 어제 최백준 조교가 방 열쇠를 주머니에 넣은 채 깜빡하고 서울로 가 버리는 황당한 상황에 직면한 조교들은, 702호에 새로운 보안 시스템을 설치하기로 하였다. 이 보안 시스템은 열쇠가 아닌 암호로 동작하게 되어 있는 시스템이다.
암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성되어 있다고 알려져 있다. 또한 정렬된 문자열을 선호하는 조교들의 성향으로 미루어 보아 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열되었을 것이라고 추측된다. 즉, abc는 가능성이 있는 암호이지만 bac는 그렇지 않다.
새 보안 시스템에서 조교들이 암호로 사용했을 법한 문자의 종류는 C가지가 있다고 한다. 이 알파벳을 입수한 민식, 영식 형제는 조교들의 방에 침투하기 위해 암호를 추측해 보려고 한다. C개의 문자들이 모두 주어졌을 때, 가능성 있는 암호들을 모두 구하는 프로그램을 작성하시오.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 두 정수 L, C가 주어진다. (3 ≤ L ≤ C ≤ 15) 다음 줄에는 C개의 문자들이 공백으로 구분되어 주어진다. 주어지는 문자들은 알파벳 소문자이며, 중복되는 것은 없다.
- 각 줄에 하나씩, 사전식으로 가능성 있는 암호를 모두 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 조합.. 접근법은 맞았는데, 아주 되도않는 실수로 너무 시간 잡아먹어서 기록함

- [x] 조합을 이용하여 가능한 경우의 수 추출

### 💻코드

```java
Arrays.sort(alphabet);
combination(0, 0);

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
```

### 🙄 후기
소요시간 : 2시간  <br>
```
combination(start + 1, cnt + 1); // x
combination(i + 1, cnt + 1); // o
```

저거 하나 떄문에 2시간을 버렸다.. 재귀 똑바로 좀 풀자, 안되면 디버깅도 꼼꼼히 하고, ㅏㅡㅏㅡㅏㅡㅏㅡㅏㅡㅏㅡㅏㅡ 답답해