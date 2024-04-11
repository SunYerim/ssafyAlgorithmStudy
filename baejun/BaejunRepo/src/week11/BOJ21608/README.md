<h3 align="center"> 
    📢  [골드5] 백준(상어 초등학교) : https://www.acmicpc.net/problem/21608
</h3>

<br>

## 🚀 문제

너무 길허어

---

## 🚦입출력 + 제한사항

- 첫째 줄에 N이 주어진다. 둘째 줄부터 N2개의 줄에 학생의 번호와 그 학생이 좋아하는 학생 4명의 번호가 한 줄에 하나씩 선생님이 자리를 정할 순서대로 주어진다.
- 학생의 번호는 중복되지 않으며, 어떤 학생이 좋아하는 학생 4명은 모두 다른 학생으로 이루어져 있다. 입력으로 주어지는 학생의 번호, 좋아하는 학생의 번호는 N2보다 작거나 같은 자연수이다. 어떤 학생이 자기 자신을 좋아하는 경우는 없다.
- 첫째 줄에 학생의 만족도의 총 합을 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 우선순위를 비교할때 x좌표, y좌표, 인접 좋학생수, 인접 빈칸 수 다 필요하므로 노드 클래스 따로파서 관리했음
- 순번마다 이미 앉은 학생들을 순회하며 노드 클래스를 생성하여 우선순위 큐에 삽입
- 컴패러터 인터페이스 활용하여서 1 -> 2 -> 3 우선순위대로 비교정렬

- [x] 우선순위 큐를 활용하여 정렬

### 💻코드

```java
static class Node implements Comparable<Node> {
	int x;
	int y;
	int adjacencyCnt;
	int likeStu;
	
	Node(int x, int y, int adjacencyCnt, int likeStu) {
		this.x = x;
		this.y = y;
		this.adjacencyCnt = adjacencyCnt;
		this.likeStu = likeStu;
	}

	@Override
	public int compareTo(Node o) {
		if(o.likeStu == this.likeStu) {
			if(this.adjacencyCnt == o.adjacencyCnt) {
				if(this.x == o.x) {
					return Integer.compare(this.y, o.y);
				} else {
					return Integer.compare(this.x, o.x);
				}
			} else {
				return Integer.compare(o.adjacencyCnt, this.adjacencyCnt);
			}
		} else {
			return Integer.compare(o.likeStu, this.likeStu);
		}
	}
}

for(int action = 0; action < N*N; action++) {
	PriorityQueue<Node> queue = new PriorityQueue<>();
	for(int i = 1; i < result.length; i++) {
		for(int j = 1; j < result.length; j++) {
			if(result[i][j] != 0) continue; // 이미 학생이 앉은 경우
			/* 아니라면 해당 칸에 좋아하는 학생 수와 인접 빈칸을 구해서 queue에 삽입 */
			int like = 0;
			int adjacency = 0;
			for(int k = 0; k < 4; k++) {
				int nr = i + dx[k];
				int nc = j + dy[k];
				if(nr>0&&nr<N+1&&nc>0&&nc<N+1) {
					if(result[nr][nc] == 0) adjacency++; //비어있을때
					else {
						for(int q = 0; q < 4; q++) {
							if(list.get(order[action]).get(q) == result[nr][nc]) like++;
						}
					}
				}
			}
			queue.offer(new Node(i, j, adjacency, like));
		}
	}
	Node curNode = queue.poll();
	result[curNode.x][curNode.y] = order[action];
}
```

### 🙄 후기
소요시간 : 1시간 <br>
코드를 좀 더럽게 짰지만, 설계한대로 딱딱 맞아떨어져서 기분좋게 풀 수 있었다 <br>
우선순위 큐와 컴패러블 활용하는 아이디어 아주 좋았으