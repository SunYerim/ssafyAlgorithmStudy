<h3 align="center"> 
    📢  [A형대비] SWEA(보호 필름) : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu&categoryId=AV5V1SYKAaUDFAWu&categoryType=CODE&problemTitle=%EB%AA%A8%EC%9D%98&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=2&&&&&&&&&
</h3>

<br>

## 🚀 문제

너무 길어서 설명 보세요

---

## 🚦입출력 + 제한사항

1. 시간제한 : 최대 50개 테스트 케이스를 모두 통과하는데, C/C++/Java 모두 5초
2. 보호 필름의 두께 D는 3이상 13이하의 정수이다. (3≤D≤13)
3. 보호 필름의 가로크기 W는 1이상 20이하의 정수이다. (1≤W≤20)
4. 합격기준 K는 1이상 D이하의 정수이다. (1≤K≤D)
5. 셀이 가질 수 있는 특성은 A, B 두 개만 존재한다.


---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 약품투입 기준으로 조합 한번(최소값 기준), 그리고 고른 공간에 A넣을지 B넣을지 고르는 조합 한번, 두번 조합써서 완탐
- 글고 조합의 경우마다 성능체크해서 되면 바로 쫑내서, 최소값부터 구해서 바로 쫑내면 시간 될줄 알았는데 계속 초과가남(5000ms제한인데 알고보니 계속 5015ms였던..)
- 뭐 더 좋은 아이디어가 있겠지만, 지금 내 아이디어에서 시간초과를 안당하는 법은, 배열 복사의 시간을 줄이는 것
- 조합의 경우마다 배열을 복사했었는데 이게 생각보다 컸음, 그래서 복사본을 하나 전역으로 만들어놓고, 원본 배열을 바꾸고, 다시 복사본을 이용해서 복원하고 하는 방식으로 진행함
- 원래 복사본 만들어서 걜 다루면 계속 row*col 만큼 연산했는데, 이제 조합에 해당하는 부분만 건드려서 그 부분에서 시간이 절약됨

- [x] 조합을 활용하여 경우의 수 추출

### 💻코드

```java
/* 약품투입할 곳 정하는 조합 */
private static void combination(int start, int cnt, int r) {
	if(answer != Integer.MAX_VALUE) return;
	// 기저조건
	if(cnt == r) {
		for(int i = 0; i <= cnt; i++) {
			visitedForInserting = new boolean[cnt];
			combForInserting(0, 0, i, picked);
		}
		for(int i = 0; i < picked.length; i++) {
			for(int j = 0; j < W; j++) {
				cells[picked[i]][j] = copyCells[picked[i]][j];
			}
		}
		return;
	}
	// 유도조건
	for(int i = start; i < D; i++) {
		if(!visited[i]) {
			visited[i] = true;
			picked[cnt] = i;
			combination(i+1, cnt+1, r);
			visited[i] = false;
		}
	}
}
/* A넣을지 B넣을지 정하는 조합 */
private static void combForInserting(int start, int cnt, int r, int[] picked) {
	if(answer != Integer.MAX_VALUE) return;
	// 기저조건
	if(cnt == r) {
		for(int i = 0; i < picked.length; i++) {
			if(visitedForInserting[i]) {
				for(int j = 0; j < W; j++) {
					cells[picked[i]][j] = 1;
				}
			} else {
				for(int j = 0; j < W; j++) {
					cells[picked[i]][j] = 0;
				}
			}
		}
		if(capacityTesting(cells)) {
			answer = cnt;
		}
		return;
	}
	// 유도조건
	for(int i = start; i < picked.length; i++) {
		if(!visitedForInserting[i]) {
			visitedForInserting[i] = true;
			combForInserting(i+1, cnt+1, r, picked);
			visitedForInserting[i] = false;
		}
	}
}
```

### 🙄 후기
소요시간 : 1시간   <br>
화아아악실히, 키보드에 손 최대한 늦게 갖다대기 전략이 먹히고 있다 <br>
최대한 설계하고, 가능한 한 최대한 꼼꼼히 코드를 정해놓고 실제 코딩을 진행하는게, 사실 오히려 시간적으로도 절약이 되고, 무엇보다 되지 않을때 이유를 찾는 속도가 훨 빨라졌다