<h3 align="center"> 
    📢  [A형대비] SWEA(보물상자 비밀번호) : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo&categoryId=AWXRUN9KfZ8DFAUo&categoryType=CODE&problemTitle=%EB%AA%A8%EC%9D%98&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
</h3>

<br>

## 🚀 문제

각 변에 다음과 같이 16진수 숫자(0~F)가 적혀 있는 보물상자가 있다.
보물 상자의 뚜껑은 시계방향으로 돌릴 수 있고, 한 번 돌릴 때마다 숫자가 시계방향으로 한 칸씩 회전한다
각 변에는 동일한 개수의 숫자가 있고, 시계방향 순으로 높은 자리 숫자에 해당하며 하나의 수를 나타낸다.
예를 들어 [Fig.1]의 수는 1A3, B54, 8F9, D66이고, [Fig.2]의 수는 61A, 3B5, 48F, 9D6이다.
보물상자에는 자물쇠가 걸려있는데, 이 자물쇠의 비밀번호는 보물 상자에 적힌 숫자로 만들 수 있는 모든 수 중, K번째로 큰 수를 10진 수로 만든 수이다.
N개의 숫자가 입력으로 주어졌을 때, 보물상자의 비밀 번호를 출력하는 프로그램을 만들어보자.
(서로 다른 회전 횟수에서 동일한 수가 중복으로 생성될 수 있다. 크기 순서를 셀 때 같은 수를 중복으로 세지 않도록 주의한다.)

---

## 🚦입출력 + 제한사항

- N은 4의 배수이고, 8이상 28이하의 정수이다. (8 ≤ N ≤ 28)       
- N개의 숫자는 각각 0이상 F이하로 주어진다. (A~F는 알파벳 대문자로만 주어진다.)
- K는 생성 가능한 수의 개수보다 크게 주어지지 않는다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 설명이 직관적이여서 어렵지 않게 접근가능했음
- 16->10 방법이랑, 중복관리, 인덱스 장난질 정도가 포인트?

- [x] Long.parseLong(str, 16)을 통해 16 -> 10 변환
- [x] HashSet 자료구조를 통해 중복관리

### 💻코드

```java
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
```

### 🙄 후기
소요시간 : 40분  <br>
달달하다.. 약간 쉬어가는 코너 <br>
진수 변환할때 그냥 변환하면서 (문자열, 지금 진수(16))로 하면 편하게 되는걸 알수있었다!