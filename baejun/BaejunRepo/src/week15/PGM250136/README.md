<h3 align="center"> 
    📢  [레벨2] 프로그래머스([PCCP 기출문제] 2번 / 석유 시추) : https://school.programmers.co.kr/learn/courses/30/lessons/250136
</h3>

<br>

## 🚀 문제

---

## 🚦입출력 + 제한사항

- 가로, 세로 최대 500*500

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- BFS인데, 행별로 어차피 집계해야되니까, BFS돌릴때 한 덩어리의 수랑 그 덩어리가 포함된 세로인덱스를 같이 체크함
- 그래서 BFS Queue돌리기 끝내고, 포함된 세로에 세로덩어리 만큼 더해줘서 세로인덱스별로 합계를 들고있음

-

### 💻코드

```java
public void bfs(int startX, int startY, int[][] land) {
    int cnt = 0;
    boolean[] visitForCol = new boolean[col];
    
    Queue<Integer> queue = new LinkedList<>();
    queue.add(startX);
    queue.add(startY);
    visited[startX][startY] = true;
    visitForCol[startY] = true;
    
    while(!queue.isEmpty()) {
        int x = queue.poll();
        int y = queue.poll();
        cnt++;
        
        for(int i = 0; i < 4; i++) {
            int nr = x + dx[i];
            int nc = y + dy[i];
            if(nr>=0&&nr<row&&nc>=0&&nc<col&&!visited[nr][nc]&&land[nr][nc] == 1) {
                visited[nr][nc] = true;
                visitForCol[nc] = true;
                queue.add(nr);
                queue.add(nc);
            }
        }
    }
    for(int idx = 0; idx < col; idx++) {
        if(visitForCol[idx]) {
            colScore[idx] += cnt;
        }
    }
}
```

### 🙄 후기
소요시간 : 1시간  <br>
재밌네~