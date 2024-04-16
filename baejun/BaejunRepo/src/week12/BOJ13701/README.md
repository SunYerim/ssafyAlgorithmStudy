<h3 align="center"> 
    📢  [골드4] 백준(중복 제거) : https://www.acmicpc.net/problem/13701
</h3>

<br>

## 🚀 문제

문제: N개의 정수 A1, A2, ..., AN 을 읽고, 이들 중에서 반복되는 수를 제외하고 남은 N'개의 수 B1, B2, ..., BN’ 을 입력된 순서대로 출력하시오. 이때,

0 ≤ Ai < 225 = 33554432, i=1,2,…,N.
입력의 개수 N은 1 이상 500만 이하이다.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 A1, A2, ..., AN이 주어진다.
- B1, B2, ..., BN’을 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- Set + StringBuilder

- [x] Set + StringBuilder

### 💻코드

```java
StringBuilder sb = new StringBuilder();
Set<Integer> set = new HashSet<>();

while(st.hasMoreTokens()) {
	int num = Integer.parseInt(st.nextToken());
	if(set.add(num)) {
		sb.append(num).append(" ");
	}
}
System.out.println(sb);
```

### 🙄 후기
소요시간 : 10분  <br>
자바 메모리를 너무 넘치게 줘서,, 그냥 풀어졌다 <br>
지용이한테 물어보니 원래 문제의도는 비트를 이용해서 입력값 메모리를 줄이는것 이었다고 함 ㅋㅋ