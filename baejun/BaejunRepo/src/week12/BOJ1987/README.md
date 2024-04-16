<h3 align="center"> 
    📢  [골드4] 백준(알파벳) : https://www.acmicpc.net/problem/1987
</h3>

<br>

## 🚀 문제

---

## 🚦입출력 + 제한사항

- 첫째 줄에 R과 C가 빈칸을 사이에 두고 주어진다. (1 ≤ R,C ≤ 20)
- 둘째 줄부터 R개의 줄에 걸쳐서 보드에 적혀 있는 C개의 대문자 알파벳들이 빈칸 없이 주어진다.
- 첫째 줄에 말이 지날 수 있는 최대의 칸 수를 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- DFS + 백트래킹
- 알파벳 이력관리를 좀 깔쌈하게 어케할까 하다가, 아스키코드 이용해서 boolean[] 만들어서 int로 인력관리함

- [x] DFS + 이력관리

### 💻코드

```java
private static void DFS(int x, int y, int cnt) {
	if(max < cnt) max = cnt; // 최대값 비교 갱신
	
	for(int i = 0; i < 4; i++) {
		int nr = x + dx[i];
		int nc = y + dy[i];
		if(nr>=0&&nr<R&&nc>=0&&nc<C&&!visited[nr][nc]) {
			int alphabetIndex = (int)map[nr][nc]-64; // 해당 알파벳을 썼는지 확인하기 위해 알파벳 인덱스로 변환
			if(!alphabetVisited[alphabetIndex]) { // 해당 인덱스를 방문했는지 여부와 해당 알파벳을 사용했는지를 백트래킹
				visited[nr][nc] = true;
				alphabetVisited[alphabetIndex] = true;
				DFS(nr, nc, cnt+1);
				visited[nr][nc] = false;
				alphabetVisited[alphabetIndex] = false;					
			}
		}
	}
}
```

### 🙄 후기
소요시간 : 25분  <br>
생각보다 너무 금방 풀려서 기부니가 좋았다. easy~