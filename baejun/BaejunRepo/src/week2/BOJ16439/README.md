<h3 align="center"> 
    📢  [실버4] 백준(치킨치킨치킨) : https://www.acmicpc.net/problem/16439
</h3>

<br>

## 🚀 문제

N명의 고리 회원들은 치킨을 주문하고자 합니다.
치킨은 총 M가지 종류가 있고 회원마다 특정 치킨의 선호도가 있습니다. 한 사람의 만족도는 시킨 치킨 중에서 선호도가 가장 큰 값으로 결정됩니다. 진수는 회원들의 만족도의 합이 최대가 되도록 치킨을 주문하고자 합니다.
시키는 치킨의 종류가 많아질수록 치킨을 튀기는 데에 걸리는 시간도 길어지기 때문에 최대 세 가지 종류의 치킨만 시키고자 합니다.
진수를 도와 가능한 만족도의 합의 최댓값을 구해주세요.

---

## 🚦입출력 + 제한사항

- 첫 번째 줄에 고리 회원의 수 N (1 ≤ N ≤ 30) 과 치킨 종류의 수 M (3 ≤ M ≤ 30) 이 주어집니다.
- 두 번째 줄부터 N개의 줄에 각 회원의 치킨 선호도가 주어집니다.
- i+1번째 줄에는 i번째 회원의 선호도 ai,1, ai,2, ..., ai,M (1 ≤ ai,j ≤ 9) 가 주어집니다.
- 첫 번째 줄에 고리 회원들의 만족도의 합의 최댓값을 출력합니다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 조합으로 완전탐색

### 💻코드

```java
private static void combination(int cnt, int start) {
	//기저조건
	if(cnt == 3) {
		int max = 0;
		for(int i = 0; i < chickens.length; i++) {
			int current = 0;
			for(int j = 0; j < chickens[0].length; j++) {
				if (visited[j]) {
					if (chickens[i][j] > current) current = chickens[i][j];
				}
			}
			max += current;
		}
		if(max > maxValue) maxValue = max;
		return;
	}

	//유도조건
	for(int i = start; i < visited.length; i++) {
		visited[i] = true;
		combination(cnt + 1, i + 1);
		visited[i] = false;
	}
}
```

### 🙄 후기
소요시간 : 30분  <br>

조합으로 쉽게 마무리할 수 있었다. 표본이 적고 완전탐색문제라면, 순열, 조합, 부분집합이 굉장히 많이 쓰이는 듯하다 <br>
뭐 아애 패턴을 외우다 싶이 하는것도 좋을듯함!?
