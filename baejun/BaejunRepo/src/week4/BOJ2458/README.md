<h3 align="center"> 
    📢  [골드3] 백준(줄 세우기) : https://www.acmicpc.net/problem/2458
</h3>

<br>

## 🚀 문제

1번부터 N번까지 번호가 붙여져 있는 학생들에 대하여 두 학생끼리 키를 비교한 결과의 일부가 주어져 있다. 단, N명의 학생들의 키는 모두 다르다고 가정한다. 예를 들어, 6명의 학생들에 대하여 6번만 키를 비교하였고, 그 결과가 다음과 같다고 하자.
- 1번 학생의 키 < 5번 학생의 키
- 3번 학생의 키 < 4번 학생의 키
- 5번 학생의 키 < 4번 학생의 키
- 4번 학생의 키 < 2번 학생의 키
- 4번 학생의 키 < 6번 학생의 키
- 5번 학생의 키 < 2번 학생의 키
이 비교 결과로부터 모든 학생 중에서 키가 가장 작은 학생부터 자신이 몇 번째인지 알 수 있는 학생들도 있고 그렇지 못한 학생들도 있다는 사실을 아래처럼 그림을 그려 쉽게 확인할 수 있다. a번 학생의 키가 b번 학생의 키보다 작다면, a에서 b로 화살표를 그려서 표현하였다.
1번은 5번보다 키가 작고, 5번은 4번보다 작기 때문에, 1번은 4번보다 작게 된다. 그러면 1번, 3번, 5번은 모두 4번보다 작게 된다. 또한 4번은 2번과 6번보다 작기 때문에, 4번 학생은 자기보다 작은 학생이 3명이 있고, 자기보다 큰 학생이 2명이 있게 되어 자신의 키가 몇 번째인지 정확히 알 수 있다. 그러나 4번을 제외한 학생들은 자신의 키가 몇 번째인지 알 수 없다.
학생들의 키를 비교한 결과가 주어질 때, 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇 명인지 계산하여 출력하는 프로그램을 작성하시오.
---

## 🚦입출력 + 제한사항

- 첫째 줄에 학생들의 수 N (2 ≤ N ≤ 500)과 두 학생 키를 비교한 횟수 M (0 ≤ M ≤ N(N-1)/2)이 주어진다. 
- 다음 M개의 각 줄에는 두 학생의 키를 비교한 결과를 나타내는 N보다 작거나 같은 서로 다른 양의 정수 a와 b가 주어진다. 이는 번호가 a인 학생이 번호가 b인 학생보다 키가 작은 것을 의미한다.
- 자신이 키가 몇 번째인지 알 수 있는 학생이 모두 몇 명인지를 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 후... 십숑키 상당히 오랜시간을 투자했다
- 처음에 그래프를 만들고, 나보다 더 큰 노드와 더 작은 노드를 분류하여 구한다음 그 두개의 합이 N-1(자기자신)이면 되지않나?했다
- 결론적으로 그 접근이 맞긴한데, 메모리초과가 계속 떴다. 알고보니 dfs 코드를 조금 잘못짰다.
- 아이디어가 틀렸나싶어 딴걸로가보고 그래도 안되고, 암만봐도 저게 맞아서 저걸로 했고, dfs가 잘못된걸 알고 좀 수정헀다
- 시간초과가 나서 보니, 어차피 정렬순서를 구하는것도 아니고, 되냐 안되냐만 판단하면 되기때문에, 직접 큰노드 작은노드를 관리하지않고 그냥 카운팅만 해줬다

- [x] dfs를 이용하여 탐색

### 💻코드

```java
/* 큰거랑 작은거 차례로 삽입 */
for(int i = 0; i < M; i++) {
	st = new StringTokenizer(in.readLine());
	int idx1 = Integer.parseInt(st.nextToken());
	int idx2 = Integer.parseInt(st.nextToken());
	graph[idx1].biggerThanV.add(idx2);
	graph[idx2].smallerThanV.add(idx1);
}
/* 자기보다 큰거 순회하기(순회가 됐다면, 순서를 알 수있단 소리임) */
for(int i = 1; i < N+1; i++) {
	visited = new boolean[N+1];
	biggerThanVDFS(i, i);
}
/* 자기보다 작은 거 순회하기 */
for(int i = 1; i < N+1; i++) {
	visited = new boolean[N+1];
	smallerThanVBFS(i, i);
}
/* Big과 Small의 합이 N-1이라면(자기자신 제외) 모두의 순서를 정할 수 있단 소리임 */
for(int i = 1; i < N+1; i++) {
	if(countBig[i] + countSmall[i] == N-1) {
		answer++;
	}
}
private static void smallerThanVBFS(int node, int originalNode) {
	visited[node] = true;
	for(int i = 0; i < graph[node].smallerThanV.size(); i++) {
		int idx = graph[node].smallerThanV.get(i);
		if(!visited[idx]) {
			countSmall[originalNode]++;
			smallerThanVBFS(idx, originalNode);
		}
	}
}
private static void biggerThanVDFS(int node, int originalNode) {
	visited[node] = true;
	for(int i = 0; i < graph[node].biggerThanV.size(); i++) {
		int idx = graph[node].biggerThanV.get(i);
		if(!visited[idx]) {
			countBig[originalNode]++;
			biggerThanVDFS(idx, originalNode);
		}
	}
}
```

### 🙄 후기
소요시간 : 4시간 30분?  <br>
후... 십숑키 아주 괴롭혔다 <br>
자료구조에 조금 더 초점을 두는것과, 테케 띡이 아닌 확실한 디버깅을 해야된다는 교훈을 얻었다. <br>
그리고 이런 되냐 안되냐만 판단하는, 이런 문제 대부분이 그걸 직접 담는거까지 필요한게 아니라 그냥 카운팅만 하면 되는 경우가 많더라 <br>
이 문제 같은 경우도 직접 배열에 담아서 개수를 세버리면 시간초과가 나서, 그런 부분 좀 더 유의해주면 좋을 것 같다