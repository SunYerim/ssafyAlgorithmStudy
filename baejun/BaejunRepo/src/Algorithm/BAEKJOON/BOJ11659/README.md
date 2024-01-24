<h3 align="center"> 
    📢  [실버3] 백준(구간 합 구하기 4) : https://www.acmicpc.net/problem/11659
</h3>

<br>

## 🚀 문제

수 N개가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 수의 개수 N과 합을 구해야 하는 횟수 M이 주어진다. 둘째 줄에는 N개의 수가 주어진다. 수는 1,000보다 작거나 같은 자연수이다. 셋째 줄부터 M개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
- 총 M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합을 출력한다.
- 1 ≤ N ≤ 100,000
- 1 ≤ M ≤ 100,000
- 1 ≤ i ≤ j ≤ N

---

### 📜 문제 풀이(기능 목록, 접근법)
- [x] 풀이 자체는 간단함, 구간 별 누적합이니까 배열로 만들어놓고 풀면됨
- [x] 근데 배열탐색으로 누적 합 계산하면 10만*10만 연산이므로 시간초과 이슈
- [x] O(n)으로 가능한 prefix 누적합을 이용하여 제한시간 내 누적합 계산

### 💻코드

```java
/* 첫번째 값부터 ~ i번째 값까지의 누적합을 배열에 저장 */
int sum = 0;
for (int i = 1; i <= N; i++) {
	sum += Integer.parseInt(st.nextToken());
	cumulativeSum[i] = sum;
}
/* prefix 누적합 : i부터 j까지의 합은 (j까지의 누적합 - (i-1)까지의 누적합) */
for (int i = 0; i < M; i++) {
	st = new StringTokenizer(in.readLine());
	int range1 = Integer.parseInt(st.nextToken());
	int range2 = Integer.parseInt(st.nextToken());
	System.out.println(cumulativeSum[range2] - cumulativeSum[range1 - 1]);
}
```

### 🙄 후기

실버3치고 정답률이 낮길래 뭥미 싶어서 풀어봤는데 놓쳤다. <br>
prefix 누적합 방식을 기억해두고 누적합 문제 풀때 시간복잡도를 개선시키자!