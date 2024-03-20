<h3 align="center"> 
    📢  [골드5] 백준(평범한 배낭) : https://www.acmicpc.net/problem/12865
</h3>

<br>

## 🚀 문제

이 문제는 아주 평범한 배낭에 관한 문제이다.

한 달 후면 국가의 부름을 받게 되는 준서는 여행을 가려고 한다. 세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다.

준서가 여행에 필요하다고 생각하는 N개의 물건이 있다. 각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다. 아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다. 준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.


---

## 🚦입출력 + 제한사항

- 첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다. 두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.
- 입력으로 주어지는 모든 수는 정수이다.
- 한 줄에 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**

### 💻코드

```java
int[][] arr = new int[N+1][2];

for(int i = 1; i <= N; i++) {
	st = new StringTokenizer(in.readLine());
	arr[i][0] = Integer.parseInt(st.nextToken()); // 무게
	arr[i][1] = Integer.parseInt(st.nextToken()); // 가치			
}

int[][] knapsack = new int[N+1][K+1];
for(int i = 1; i <= N; i++) {
	for(int j = 1; j <= K; j++) {
		if(arr[i][0] > j) { // 담을 수 없는 경우
			knapsack[i][j] = knapsack[i-1][j];
		} else { // 담을 수 있을땐 담은 값과 안 담은 값을 비교해서 더 큰 값 넣기
			knapsack[i][j] = Math.max(knapsack[i-1][j], arr[i][1] + knapsack[i-1][j-arr[i][0]]);
		}
	}	
}
System.out.println(knapsack[N][K]);
```

### 🙄 후기
소요시간 : 30분  <br>
엄두가 안나서 걍 보고했다. 근데 그래도 모르겠다>?