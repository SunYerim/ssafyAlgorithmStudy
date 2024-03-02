<h3 align="center"> 
    📢  [A형대비] SWEA(수영장) : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq&categoryId=AV5PpFQaAQMDFAUq&categoryType=CODE&problemTitle=%EB%AA%A8%EC%9D%98&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=2&&&&&&&&&
</h3>

<br>

## 🚀 문제

---

## 🚦입출력 + 제한사항

1. 시간 제한 : 최대 50개 테스트 케이스를 모두 통과하는 데 C/C++/Java 모두 3초
2. 모든 종류의 이용권 요금은 10 이상 3,000 이하의 정수이다.
3. 각 달의 이용 계획은 각 달의 마지막 일자보다 크지 않다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 1년권은 그냥 1년권 한장 사는경우가 최소값이므로 그거 띵
- 글고 이제 3달권을 기준으로 잡음, 3달권을 0개~4개 사는 경우로 나눠서
- 조합으로(12C1~3) 3달권에 포함되는 달을 구하고, 거기에 포함되지 않는 애들은 1달권 내지 1일권으로 채움
- 1달권 쓸지 1일권 쓸지 정하는 기준은, 그냥 사용일수*일일권이 한달권보다 싸면 그렇게, 아니면 일일권 쓰면됨


### 💻코드

```java
minCost = year; // 1년권 사용한 경우
// 3달권 0개 사용한 경우
int sum = 0;
for(int e : monthCost) {
	sum += e;
}
if(sum < minCost) minCost = sum;
// 3달권 4개 사용한경우
if(minCost > (threeMonth * 4)) minCost = (threeMonth * 4);
// 3달권 1개~3개 사용한 경우
for(int i = 1; i < 4; i++) {
	visited = new boolean[13];
	usingThreeMonth = new int[i];
	combination(1, 0, i);
}

private static void combination(int start, int cnt, int r) {
	// 기저조건
	if(cnt == r) {
		visitedForCalcurate = new boolean[13];
		calcuration();
		return;
	}
	
	// 유도조건
	for(int i = start; i < 13; i++) {
		if(!visited[i]) {
			visited[i] = true;
			usingThreeMonth[cnt] = i;
			combination(i+1, cnt+1, r);
			visited[i] = false;
		}
	}
}

private static void calcuration() {
	int sum = 0;
	sum += (threeMonth * usingThreeMonth.length);
	for(int e : usingThreeMonth) {
		for(int i = 0; i < 3; i++) {
			if(e+i < 13) visitedForCalcurate[e+i] = true;
		}
	}
	for(int i = 1; i < 13; i++) {
		if(!visitedForCalcurate[i]) sum += monthCost[i];
	}
	minCost = Math.min(sum, minCost);
}
```

### 🙄 후기
소요시간 : 35분  <br>
달달하다.. 약간 쉬어가는 코너 <br>