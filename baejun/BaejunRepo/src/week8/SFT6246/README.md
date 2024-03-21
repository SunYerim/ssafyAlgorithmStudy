<h3 align="center"> 
    📢  [레벨3] 소프티어(순서대로 방문하기) : https://softeer.ai/practice/6246
</h3>

<br>

## 🚀 문제

너무 길어헝
---

## 🚦입출력 + 제한사항

[조건 1] 2 ≤ n ≤ 4
[조건 2] 2 ≤ m ≤ n2

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 읽고나선 easy한데? 했음
- 그냥 일반 DFS 도는건데, 1(벽)안가는 제약추가와 목적지 도착 시 필수방문코스 다 돌았는지 체크만 하면된다 생각함
- 진짜 조금씩만 틀리길래 '?' 띄우다가, 문제를 똑바로 안읽은걸 깨달음
- 출발 / 도착만 보장되면 되는게 아니라, 그냥 조건 싹다 순서대로 방문해야된다는걸 알게됨
- 변수 하나를 둬서 방문한곳이 현재 순번의 방문지인지를 계속 체크해서 해결!

- [x] DFS + 백트래킹

### 💻코드

```java
/* m개의 첫번째 위치에서 출발해서 4방 탐색인데 벽 조건 추가, 기저조건에서 필수 위치(순서에 맞게) 다 갔는지 확인하고 +1*/
private static void dfs(int row, int col) {
	// 기저조건
	if(row == checked[m-1][0] && col == checked[m-1][1]) {
		if(checkingVisit == m) answer++; // 순서에 맞게 다 돌았으면 정답
		return;
	}
	// 유도조건
	for(int i = 0; i < 4; i++) {
		int nr = row + dx[i];
		int nc = col + dy[i];
		if(nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc] && array[nr][nc] == 0) {
			visited[nr][nc] = true;
			/* 백트래킹 : 순번에 맞게 방문해야하므로 필수방문의 현재 순번과 같다면 ++해줌*/
			if(nr == checked[checkingVisit][0] && nc == checked[checkingVisit][1]) checkingVisit++;
			dfs(nr, nc);
			if(nr == checked[checkingVisit-1][0] && nc == checked[checkingVisit-1][1]) checkingVisit--;
			visited[nr][nc] = false;
		}
	}
}
```

### 🙄 후기
소요시간 : 50분  <br>
아이디어 떠올리는덴 어렵지 않았는데 문제를 똑바로 안읽었다 <br>
아주 잘못된 태도야~