<h3 align="center"> 
    📢  [A형대비] SWEA(벌꿀채취) : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu&categoryId=AV5V4A46AdIDFAWu&categoryType=CODE&problemTitle=%EB%AA%A8%EC%9D%98&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=2&&&&&&&&&&&&&&&&&&
</h3>

<br>

## 🚀 문제

너무 길어서 설명 보세요

---

## 🚦입출력 + 제한사항

1. 시간제한 : 최대 50개 테스트 케이스를 모두 통과하는데, C/C++/Java 모두 3초.
2. 벌통들의 크기 N은 3 이상 10 이하의 정수이다. (3 ≤ N ≤ 10)
3. 선택할 수 있는 벌통의 개수 M은 1 이상 5 이하의 정수이다. (1 ≤ M ≤ 5)
4. 선택할 수 있는 벌통의 개수 M은 반드시 N 이하로만 주어진다.
5. 꿀을 채취할 수 있는 최대 양 C는 10 이상 30 이하의 정수이다. (10 ≤ C ≤ 30)
6. 하나의 벌통에서 채취할 수 있는 꿀의 양은 1 이상 9 이하의 정수이다.
7. 하나의 벌통에서 일부분의 꿀만 채취할 수 없고, 벌통에 있는 모든 꿀을 한번에 채취해야 한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 조합, 근데 가지 칠 상황이 좀 명확했기때문에 그 부분 잘 체크해서 풀어주면 되겠다 싶었음.
1. 인덱스 기준으로 조합을 돌리는데 가지치기를
1-1. 배열 범위 밖일 경우 가지
1-2. 두 사람이 겹치는 공간을 채취할때 가지
2. 그리고 채취 양 계산하는데 C보다 커질떄
2-1. 부분집합을 구해서 가장 가치있는 경우를 추출(시간 초과 걱정했는데 M이 5이하라서 괜찮겠다 싶었음)
3. 제곱의 합을 구해서 최대 가치를 기록

- [x] 조합과 부분집합을 활용하여 경우의 수 추출

### 💻코드

```java
/* 인덱스를 기준으로 조합을 돌리며 가장 큰 가치창출 경우의수를 추출 */
private static void combination(int start, int cnt) {
	//기저조건
	if(cnt == 2) {
		if(indexs[0][1] + M > N || indexs[1][1] + M > N) return; // 가지1 : 배열 범위 밖까지 가야할 경우 땡
		/* 가지2 : 선택한 두 인덱스가 겹치면 땡 */
		if(indexs[0][0] == indexs[1][0]) {
			boolean[] visit = new boolean[N];
			for(int i = indexs[0][1]; i < N; i++) {
				visit[i] = true;
			}
			for(int i = indexs[1][1]; i < N; i++) {
				if(visit[i]) return;
			}
		}
		int[] honeyA = new int[M];
		int[] honeyB = new int[M];
		int sumA = 0;
		int sumB = 0;
		int totalSum = 0;
		boolean flagA = false;
		boolean flagB = false;
		/* 범위의 꿀 배열로 저장, 꿀들의 합도 같이 기록 */
		for(int i = 0; i < M; i++) {
			honeyA[i] = (arrays[indexs[0][0]][indexs[0][1] + i]);
			honeyB[i] = (arrays[indexs[1][0]][indexs[1][1] + i]);
			sumA += honeyA[i];
			sumB += honeyB[i];
		}
		/* 가져갈 수 있는 양 C를 초과 : 부분집합을 돌려서 가장 가치있게 들고가는 경우의 수를 채택해야됨 */
		if(sumA > C) {
			flagA = true;
			maxForPowerSet = 0;
			visit = new boolean[M];
			powerSet(0, 0, honeyA);
			totalSum += maxForPowerSet;
		}
		if(sumB > C) {
			flagB = true;
			maxForPowerSet = 0;
			visit = new boolean[M];
			powerSet(0, 0, honeyB);
			sumB = maxForPowerSet;
			totalSum += maxForPowerSet;
		}
		/* C를 초과 안했다면 그냥 배열 전체를 제곱해주면서 가치 환산 */
		if(!flagA) {
			for(int e : honeyA) totalSum += e*e;
		}
		if(!flagB) {
			for(int e : honeyB) totalSum += e*e;
		}
		if(totalSum > maxValue) maxValue = totalSum;
		return;
	}
	//유도조건
	for(int i = start; i < N; i++) {
		for(int j = 0; j < N; j++) {
			if(!visited[i][j]) {
				visited[i][j] = true;
				indexs[cnt][0] = i;
				indexs[cnt][1] = j;
				combination(i+1, cnt+1);
				visited[i][j] = false;
			}
		}
	}
}
/* 가장 가치있는 경우(제곱의 합이)를 추출하는 부분집합 */
private static void powerSet(int cnt, int sum, int[] honey) {
	if(cnt == M) {
		if(sum > C) return;
		int a = 0;
		for(int i = 0; i < M; i++) { // 부분집합의 제곱의 합을 구해서 가장 가치있는 경우를 기록
			if(visit[i]) a += (honey[i]*honey[i]);
		}
		if(a > maxForPowerSet) maxForPowerSet = a;
		return;
	}
	visit[cnt] = true;
	powerSet(cnt+1, sum+honey[cnt], honey);
	visit[cnt] = false;
	powerSet(cnt+1, sum, honey);
}
```

### 🙄 후기
소요시간 : 2시간  <br>
뻘 실수로 생각보다 시간을 더 써버렸지만, 이런 문제를 풀떄 사소한 실수를 하는건 실력이라고 생각한다. <br>
그래도 역시 정리를 최대한 해가면서 문제를 푸니까 원인을 찾기 훨씬 수월했던거 같다. <br>
읭 싶으면 빠르게 디버깅 들어가기
