<h3 align="center"> 
    📢  [A형대비] SWEA(홈 방범 서비스) : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V61LqAf8DFAWu&categoryId=AV5V61LqAf8DFAWu&categoryType=CODE&problemTitle=%EB%AA%A8%EC%9D%98&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=2
</h3>

<br>

## 🚀 문제

N*N 크기의 도시에 홈방범 서비스를 제공하려고 한다.
홈방범 서비스는 운영 상의 이유로 [Fig. 1]의 파란색 부분과 같이 마름모 모양의 영역에서만 제공된다
또한, 홈방범 서비스를 제공하기 위해서는 운영 비용이 필요하다.
[Fig. 2]와 같이 서비스 영역의 크기 K 가 커질수록 운영 비용이 커진다.
운영 비용은 서비스 영역의 면적과 동일하며, 아래와 같이 구할 수 있다.
운영 비용 = K * K + (K - 1) * (K - 1)
운영 영역의 크기 K 는 1 이상의 정수이다.

 - K = 1 일 때, 운영 비용은 1 이다.

 - K = 2 일 때, 운영 비용은 5 이다.

 - K = 3 일 때, 운영 비용은 13 이다.

 - K = 4 일 때, 운영 비용은 25 이다.
[Fig. 3]과 같이 도시를 벗어난 영역에 서비스를 제공해도 운영 비용은 변경되지 않는다.
[Fig. 3]의 경우 K = 3 이므로, 운영 비용은 13 이다.
홈방범 서비스를 제공받는 집들은 각각 M의 비용을 지불할 수 있어, 보안회사에서는 손해를 보지 않는 한 최대한 많은 집에 홈방범 서비스를 제공하려고 한다.
도시의 크기 N과 하나의 집이 지불할 수 있는 비용 M, 도시의 정보가 주어진다.
이때, 손해를 보지 않으면서 홈방범 서비스를 가장 많은 집들에 제공하는 서비스 영역을 찾고,
그 때의 홈방범 서비스를 제공 받는 집들의 수를 출력하는 프로그램을 작성하라.

---

## 🚦입출력 + 제한사항

- 1. 시간제한 : 최대 50개 테스트 케이스를 모두 통과하는데, C/C++/Java 모두 3초
- 2. 도시의 크기 N은 5 이상 20 이하의 정수이다. (5 ≤ N ≤ 20)
- 3. 하나의 집이 지불할 수 있는 비용 M은 1 이상 10 이하의 정수이다. (1 ≤ M ≤ 10)
- 4. 홈방범 서비스의 운영 비용은 서비스 영역의 면적과 동일하다.
- 5. 도시의 정보에서 집이 있는 위치는 1이고, 나머지는 0이다
- 6. 도시에는 최소 1개 이상의 집이 존재한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 별수없다.. 그냥 돌려야된다..
- 근데 그냥 쭉 다 도는건 시간초과 떠서, 가지치기나 예외처리를 떠올려내야함

- [x] 존나리 완탐

### 💻코드

```java
// 예외처리 1 : N*N 이 싹다 1이고 인당 비용 2이상이면 답이 N*N임
if(cntExsitHome == N*N && M > 1) {
	sb.append(N*N).append("\n");
	continue;
}
// 가지치기 1 : 회사의 비용이 집이 내는 비용 최대값보다 크면 애초에 설치 못함
int costK = K * K + (K - 1) * (K - 1);
if(costK > maxCost) break;

for(int i = 0; i < N; i++) {
	for(int j = 0; j < N; j++) {
		visited = new boolean[N][N];
		ArrayList<Integer> list = new ArrayList<>();
		visited[i][j] = true;
		list.add(i);
		list.add(j);
		int homeCount = (city[i][j] == 1) ? 1 : 0;

		for(int q = K-1; q > 0; q--) {
			int listSize = list.size();
			for(int li = 0; li < listSize; li+=2) {
				for(int direc = 0; direc < 4; direc++) {
					int nr = list.get(li) + dx[direc];
					int nc = list.get(li+1) + dy[direc];
					if(nr>=0&&nr<N&&nc>=0&&nc<N&&!visited[nr][nc]) {
						visited[nr][nc] = true;
						if(city[nr][nc] == 1) homeCount++;
						list.add(nr);
						list.add(nc);
					}
				}
			}
		}
		if(costK <= homeCount*M) { // 등호주의.. 이걸로 30분 헤맸다
			if(maxHomeCount < homeCount) maxHomeCount = homeCount;
		}
	}
}
```

### 🙄 후기
소요시간 : 2시간반  <br>
정신병 걸릴거같애 ~ <br>
이런 문제일수록 디버깅 철저하게 하고, 꼼꼼하게 하나하나 중간체크 해가면서 풀어야된다, 머리 아프다고 놓아버리면 절대 못품