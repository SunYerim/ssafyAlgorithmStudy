# [BOJ][G4] 최단경로/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/1753)]

[문제 내용 전체]

## 문제

방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오. 단, 모든 간선의 가중치는 10 이하의 자연수이다.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다. (1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000) 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다. 둘째 줄에는 시작 정점의 번호 K(1 ≤ K ≤ V)가 주어진다. 셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다. 이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다. u와 v는 서로 다르며 w는 10 이하의 자연수이다. 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.

## 출력

첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다. 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

다익스트라를 연습할려고 푼 문제이다.

1. arraylist배열을 만들어 graph를 구현했다.
2. 최단거리는 dist배열을 활용하여 저장하였다.
3. 다익스트라를 사용해서 각 경로의 최단거리를 구하였다.

---

> **코드**
> 

```java
package week13.BOJ_G4_1753_최단경로;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_G4_1753_최단경로 {

	static int V;
	static int E;
	static int start;
	static ArrayList<line>[] graph;
	static int[] visited;
	static int[] dist;
	
	
	static class line implements Comparable<line>{
		int v;
		int w;
		
		public line(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(line o) {
			return this.w - o.w;
		}
		
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		V = Integer.parseInt(s2[0]);
		E = Integer.parseInt(s2[1]);
		start = Integer.parseInt(br.readLine());
		graph = new ArrayList[V+1];
		dist = new int[V+1];
		visited = new int[V+1];
		
		Arrays.fill(dist, 30000000);
		
		for(int i=0;i<=V;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0;i<E;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			int c = Integer.parseInt(s2[2]);
			
			graph[a].add(new line(b,c));
			
		}
		
		dist[start] = 0;
		bfs(start);
		
		for(int i=1;i<=V;i++) {
			if(dist[i] == 30000000) {
				bw.append("INF");
				bw.append("\n");
			}
			else {
				bw.append(dist[i]+"");
				bw.append("\n");
			}
		}
		bw.flush();
		bw.close();
	}

	private static void bfs(int start) {
		PriorityQueue<line> que = new PriorityQueue();
		que.offer(new line(start, 0));
		
		while(!que.isEmpty()) {
			line li = que.poll();
			
			if(visited[li.v] == 1)
				continue;
			visited[li.v] = 1;
			
			for(line l : graph[li.v]) {
				if(dist[l.v] > dist[li.v] + l.w) {
					dist[l.v] = dist[li.v] + l.w;
					que.offer(new line(l.v, dist[l.v]));
				}
			}
		}
		
	}
}

```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 다익스트라 기본문제여서 어렵지 않았다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 30분 / 864ms / 132204KB / 2081B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [데이크스트라](https://www.acmicpc.net/problem/tag/22)
- [최단 경로](https://www.acmicpc.net/problem/tag/215)