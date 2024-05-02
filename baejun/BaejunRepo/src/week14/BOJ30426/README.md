<h3 align="center"> 
    📢  [골드4] 백준(Rebirth) : https://www.acmicpc.net/problem/30426
</h3>

<br>

## 🚀 문제


---

## 🚦입출력 + 제한사항


---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- DP, 가장 마지막 인덱스부터 가능한 경우의 수를 파라미터로 넘겨가며, 시작 위치까지 진행
- 시작위치가, 가능한 경우의 수인지 체크

- [x] DP

### 💻코드

```java
Arrays.sort(dontGoList); // 이분탐색 위함

ArrayList<Integer> paramList = new ArrayList<>();
paramList.add(0); // 마지막 -> 첫번째로 가는데, 마지막엔 0이어야 utopia라서 초기값 0
for(int i = K-1; i >=0; i--) {
	ArrayList<Integer> newList = new ArrayList<>();
	boolean[] visited = new boolean[N*2]; // 중복방지

	for(int j = 0; j < paramList.size(); j++) {
		int num1 = (map[i][0] % N)*(-1);
		if(num1 < 0) num1 += N;
		int C1 = paramList.get(j) + num1;
		if(C1 >= N) C1 = C1%N; // C(현위치)는 N보다 클 수 없음(문제에 명시)
		if(!visited[C1] && Arrays.binarySearch(dontGoList, C1) < 0) {
			visited[C1] = true;
			newList.add(C1);
		}
		
		int num2 = (map[i][1] % N)*(-1);
		if(num2 < 0) num2 += N;
		int C2 = paramList.get(j) + num2;
		if(C2 >= N) C2 = C2%N;
		if(!visited[C2] && Arrays.binarySearch(dontGoList, C2) < 0) {
			visited[C2] = true;
			newList.add(C2);
		}
	}
	paramList = newList;
}
/* 시작할때 현재위치가, 가능한 경우의 수인지 체크해서 답 출력 */
if(paramList.indexOf(M) != -1) {
	System.out.println("utopia");
} else {
	System.out.println("dystopia");
}
```

### 🙄 후기
소요시간 : 2시간  <br>
DP 쥐약인데, 풀어서 기분좋다 ㅎㅋㅎㅋㅎ <br>
그리고 식 구할 때, moduler 연산을 넘겨서 음수라면, *(-1)을 하는게 아니라, +N (N은 moduler하는 수)라는것도 새로 알았다 <br>
내부적으로 moduler 연산을 하는게 -N -N -N 해가면서 나머지를 찾는다는걸 처음 알았음!! (지용이 작품)