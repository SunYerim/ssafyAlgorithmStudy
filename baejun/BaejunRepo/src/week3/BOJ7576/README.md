<h3 align="center"> 
    📢  [골드5] 백준(토마토) : https://www.acmicpc.net/submit/7576/73123307
</h3>

<br>

## 🚀 문제

철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다. 토마토는 아래의 그림과 같이 격자 모양 상자의 칸에 하나씩 넣어서 창고에 보관한다.
창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다. 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다. 하나의 토마토의 인접한 곳은 왼쪽, 오른쪽, 앞, 뒤 네 방향에 있는 토마토를 의미한다. 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다. 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지, 그 최소 일수를 알고 싶어 한다.
토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램을 작성하라. 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.

---

## 🚦입출력 + 제한사항

- 첫 줄에는 상자의 크기를 나타내는 두 정수 M,N이 주어진다. M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다. 단, 2 ≤ M,N ≤ 1,000 이다. 둘째 줄부터는 하나의 상자에 저장된 토마토들의 정보가 주어진다. 즉, 둘째 줄부터 N개의 줄에는 상자에 담긴 토마토의 정보가 주어진다. 하나의 줄에는 상자 가로줄에 들어있는 토마토의 상태가 M개의 정수로 주어진다. 정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.
- 토마토가 하나 이상 있는 경우만 입력으로 주어진다.
- 여러분은 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다. 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.
---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 1트 : 안될거같긴했는데 일단 완탐으로 풀어봄(2차원 배열 순회) -> 시간초과
- 2트 : 완탐을 dfs로 하려고 함, 짜다가 map key값을 좌표배열로했다가 참조값이 달라 안된다는걸 깨달음 -> 실패
- 3트 : 주변에서 '최소' '최단'키워드면 거의 bfs라는 조언을 듣고 bfs로 접근함 => 성공

- [x] queue를 이용하여 bfs 구현
- [x] class를 통해서 인덱스와 일 수(day) 관리

### 💻코드

```java
class tomato {
	int x;
	int y;
	int day;

	public tomato(int x, int y, int day) {
		this.x = x;
		this.y = y;
		this.day = day;
	}
}

private static void bfs() {
	tomato currentTomato = null;

	while(!queue.isEmpty()) {
		currentTomato = queue.poll();
		for(int i = 0; i < 4; i++) { // 4방탐색
			int nr = currentTomato.x + dx[i];
			int nc = currentTomato.y + dy[i];
			if(nr>=0&&nr<tomatos.length&&nc>=0&&nc<tomatos[0].length) {
				if(tomatos[nr][nc] == 0) {
					tomatos[nr][nc] = 1;
					queue.offer(new tomato(nr, nc, currentTomato.day+1)); //1일 증가
				}
			}
		}
	}
	if (checkTomato()) {
		System.out.println(currentTomato.day);
	} else {
		System.out.println(-1);
	}
}
```

### 🙄 후기
소요시간 : 4시간?5시간?  <br>
후후후.. 이런짓하면 안된다, 적당히 안되면 찾아보고 다시 풀어보고 하는거지, 한문제에 이렇게 시간 잡아먹으면 안됨. 알고리즘만 하는것도 아니고? <br>
그래도 지금은 워낙에 지식이 빈약해서 이런 일이 생기고있는거니까, 하나씩 쌓아가다 보면 이런 일도 줄어들꺼다 <br>
최단최소 키워드면 dfs가 아니라 bfs가 대부분이란걸 이 기회에 확실히 알았으니까 그걸로 오케이 !
