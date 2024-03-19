<h3 align="center"> 
    📢  [실버2] 백준(부분합) : https://www.acmicpc.net/problem/1912
</h3>

<br>

## 🚀 문제

n개의 정수로 이루어진 임의의 수열이 주어진다. 우리는 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다. 단, 수는 한 개 이상 선택해야 한다.
예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자. 여기서 정답은 12+21인 33이 정답이 된다.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 정수 n(1 ≤ n ≤ 100,000)이 주어지고 둘째 줄에는 n개의 정수로 이루어진 수열이 주어진다. 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.
- 첫째 줄에 답을 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 누적합 배열

### 💻코드

```java
for(int i = 0; i < N; i++) {
	arr[i] = Integer.parseInt(st.nextToken());
}
acc[0] = arr[0];
int maxValue = acc[0];
for(int i = 1; i < N; i++) {
	if(acc[i-1] + arr[i] > arr[i]) {
		acc[i] = acc[i-1] + arr[i];
	} else {
		acc[i] = arr[i];
	}
	maxValue = Math.max(maxValue, acc[i]);
}
System.out.println(maxValue);
```

### 🙄 후기
소요시간 : 30분  <br>
