<h3 align="center"> 
    📢  [골드4] 백준(이모티콘) : https://www.acmicpc.net/problem/14226
</h3>

<br>

## 🚀 문제

---

## 🚦입출력 + 제한사항

- 

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- BFS

### 💻코드

```java
private static int bfs() {
    Queue<Node> queue = new LinkedList<>();
    queue.offer(new Node(1, 0, 0));

    boolean[][] visited = new boolean[1001][1001];

    while (!queue.isEmpty()) {
        Node curNode = queue.poll();
        if (curNode.count == N) {
            min = curNode.cost;
            break;
        }

        // 클립보드에 붙여넣고 나서
        queue.offer(new Node(curNode.count, curNode.cost + 1, curNode.count));
        // 그러고 -1이랑 붙여넣기 한번씩
        if (curNode.clipBoard != 0 && curNode.count + curNode.clipBoard <= N && !visited[curNode.count + curNode.clipBoard][curNode.clipBoard]) {
            queue.offer(new Node(curNode.count + curNode.clipBoard, curNode.cost + 1, curNode.clipBoard));
            visited[curNode.count + curNode.clipBoard][curNode.clipBoard] = true;
        }
        if (curNode.count >= 1 && !visited[curNode.count-1][curNode.clipBoard]) {
            queue.offer(new Node(curNode.count - 1, curNode.cost + 1, curNode.clipBoard));
            visited[curNode.count-1][curNode.clipBoard] = true;
        }
    }

    return min;
}
```

### 🙄 후기
소요시간 : 2시간  <br>
몬하게따