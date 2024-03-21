<h3 align="center"> 
    📢  [실버2] 백준(가장 긴 증가하는 부분 수열) : https://www.acmicpc.net/problem/11053
</h3>

<br>

## 🚀 문제

수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.
- 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
- 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.


---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 끝에서부터 부분 수열 길이의 최대값을 구해서 그 값을 이용

### 💻코드

```java
int maxValue = 0;
for(int i = N-1; i >= 0; i--) {
	int maxCnt = 1;
	for(int j = i+1; j < N; j++) {
		if(arr[j] > arr[i]) {
			if(maxCnt < acc[j]+1) maxCnt = acc[j]+1;
		}
	}
	acc[i] = maxCnt;
	maxValue = Math.max(maxValue, acc[i]);
}
System.out.println(maxValue);
```

### 🙄 후기
소요시간 : 20분  <br>
easy~