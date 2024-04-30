<h3 align="center"> 
    📢  [골드4] 백준(좋다) : https://www.acmicpc.net/problem/1253
</h3>

<br>

## 🚀 문제

N개의 수 중에서 어떤 수가 다른 수 두 개의 합으로 나타낼 수 있다면 그 수를 “좋다(GOOD)”고 한다.
N개의 수가 주어지면 그 중에서 좋은 수의 개수는 몇 개인지 출력하라.
수의 위치가 다르면 값이 같아도 다른 수이다.

---

## 🚦입출력 + 제한사항

- 첫째 줄에는 수의 개수 N(1 ≤ N ≤ 2,000), 두 번째 줄에는 i번째 수를 나타내는 Ai가 N개 주어진다. (|Ai| ≤ 1,000,000,000, Ai는 정수)
- 좋은 수의 개수를 첫 번째 줄에 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 각 인덱스 순회
- 안에서 또 인덱스를 순회하며 해당 인덱스 하나와 조건을 만족하는 target이 있는지를 이분탐색

- [x] 이분탐색

### 💻코드

```java
for(int i = 0; i < N; i++) {
	long val1 = map[i];
	boolean flag = false;
	for(int j = 0; j < N; j++) {
		if(i == j) continue;
		int left = 0;
		int right = N-1;
		while(left <= right) {
			int mid = (left+right) / 2;
			long target = val1 - map[j];
			if(map[mid] == target && mid != i && mid != j) {
				flag = true;
				break;
			}

			if(map[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
	}
	if(flag) answer++;
}
```

### 🙄 후기
소요시간 : 2시간 <br>
개같다..