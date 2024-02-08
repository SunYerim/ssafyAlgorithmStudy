<h3 align="center"> 
    📢  [D4] SWEA(정사각형 방) : https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYzxtsg6sH8DFAXz&contestProbId=AV5LtJYKDzsDFAXc&probBoxId=AY1TDG6q3J0DFAWX&type=PROBLEM&problemBoxTitle=%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98&problemBoxCnt=12
</h3>

<br>

## 🚀 문제

N2개의 방이 N×N형태로 늘어서 있다.
위에서 i번째 줄의 왼쪽에서 j번째 방에는 1이상 N2 이하의 수 Ai,j가 적혀 있으며, 이 숫자는 모든 방에 대해 서로 다르다.
당신이 어떤 방에 있다면, 상하좌우에 있는 다른 방으로 이동할 수 있다.
물론 이동하려는 방이 존재해야 하고, 이동하려는 방에 적힌 숫자가 현재 방에 적힌 숫자보다 정확히 1 더 커야 한다.	
처음 어떤 수가 적힌 방에서 있어야 가장 많은 개수의 방을 이동할 수 있는지 구하는 프로그램을 작성하라.

---

## 🚦입출력 + 제한사항

- 첫 번째 줄에 테스트 케이스의 수 T가 주어진다.
- 각 테스트 케이스의 첫 번째 줄에는 하나의 정수 N (1 ≤ N ≤ 103)이 주어진다.
- 다음 N개의 줄에는 i번째 줄에는 N개의 정수 Ai, 1, … , Ai, N (1 ≤ Ai, j ≤ N2) 이 공백 하나로 구분되어 주어진다.
- Ai, j는 모두 서로 다른 수이다.

- 각 테스트 케이스마다 ‘#x’(x는 테스트케이스 번호를 의미하며 1부터 시작한다)를 출력하고,
- 한 칸을 띄운 후, 처음에 출발해야 하는 방 번호와 최대 몇 개의 방을 이동할 수 있는지를 공백으로 구분하여 출력한다.
- 이동할 수 있는 방의 개수가 최대인 방이 여럿이라면 그 중에서 적힌 수가 가장 작은 것을 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 어차피 수는 중복되게 나오지 않고, 오로지 본인 값 + 1로만 움직일 수 있으므로, 그냥 정직하게 4방 탐색하며 갈수있는데 있는지 map으로 기록
- map을 dfs 돌려서(출발지를 다르게해서) 가장 길게 이어지는 친구 추출(같다면 번호가 작은걸로)

- [x]

### 💻코드

```java
for(int[] ele : direction) { // 4방향 현재값+1 인 값이 있는지 탐색
	int nr = i + ele[0];
	int nc = j + ele[1];
	if(nr >= 0 && nr < arr.length && nc >= 0 && nc < arr.length && arr[nr][nc] == (arr[i][j]+1)) {
		map.put(arr[i][j], arr[i][j]+1);
	}
}

private static void DFS(Map map, int key) {
	count++;
	if(map.get(key) != null) {
		DFS(map, (int) map.get(key));
	}
	return;
}


```

### 🙄 후기
소요시간 : 1시간 10분  <br>
dfs 활용 굳굳, 경험이 점점 쌓이고 있다<br>
그리고 문제 제대로 안읽어서 좀 헤맸음(출력조건에 같은 값 처리), 언제나 그렇지만 문제 꼼꼼히 읽고 접근하는건 기본중에 기본이다