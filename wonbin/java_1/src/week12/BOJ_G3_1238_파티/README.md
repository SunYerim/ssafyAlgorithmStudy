# [BOJ][G3] 파티/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/1238)]

[문제 내용 전체]

## 문제

N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.

어느 날 이 N명의 학생이 X (1 ≤ X ≤ N)번 마을에 모여서 파티를 벌이기로 했다. 이 마을 사이에는 총 M개의 단방향 도로들이 있고 i번째 길을 지나는데 Ti(1 ≤ Ti ≤ 100)의 시간을 소비한다.

각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다. 하지만 이 학생들은 워낙 게을러서 최단 시간에 오고 가기를 원한다.

이 도로들은 단방향이기 때문에 아마 그들이 오고 가는 길이 다를지도 모른다. N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 10,000), X가 공백으로 구분되어 입력된다. 두 번째 줄부터 M+1번째 줄까지 i번째 도로의 시작점, 끝점, 그리고 이 도로를 지나는데 필요한 소요시간 Ti가 들어온다. 시작점과 끝점이 같은 도로는 없으며, 시작점과 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개이다.

모든 학생들은 집에서 X에 갈수 있고, X에서 집으로 돌아올 수 있는 데이터만 입력으로 주어진다.

## 출력

첫 번째 줄에 N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

문제를 처음보고 다익스트라를 2번 사용하면 되겠다라는 생각을 했다. 그런데 전부 다익스트라를 돌리면 플로이드 워셜과 다를게 없다는 것을 알고 어떻게 사용할까 하다가 방향그래프를 반대로 하나 더 만들어서 X에 대한 다익스트라를 2번 돌리면 풀리겠다고 생각하고 해결 하였다.

1. 방향그래프를 정방향 역방향 2가지로 만든다
2. X에서 정방향, 역방향 그래프로 다익스트라를 돌린다.
3. 2가지 dist 배열에서 나온 값들을 더해서 제일 큰 값을 리턴한다.

---

> **코드**
> 

```java
package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class test {

	static int N;
	static int M;
	static int X;
	static ArrayList<node>[] graph;
	static ArrayList<node>[] graph2;
	static int[] dist;
	static int[] dist2;
	static int[] visited;
	static int max;
	
	static class node implements Comparable<node>{
		int w;
		int cost;
		
		public node(int w, int cost) {
			this.w = w;
			this.cost = cost;
		}
		
		@Override
		public String toString() {
			return "node [w=" + w + ", cost=" + cost + "]";
		}
		
		@Override
		public int compareTo(node o) {
			return this.cost - o.cost;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		X = Integer.parseInt(s2[2]);
		graph = new ArrayList[N+1];
		graph2 = new ArrayList[N+1];
		visited = new int[N+1];
		dist = new int[N+1];
		dist2 = new int[N+1];
		
		for(int i=0;i<=N;i++) {
			graph[i] = new ArrayList<>();
			graph2[i] = new ArrayList<>();
		}
		
		Arrays.fill(dist, 3000000);
		Arrays.fill(dist2, 3000000);
		
		for(int i=0;i<M;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			int c = Integer.parseInt(s2[2]);
			
			graph[a].add(new node(b,c));
			graph2[b].add(new node(a,c));
		}
		
		bfs(X);
		visited = new int[N+1];
		bfs2(X);
		
		for(int i=1;i<=N; i++) {
			if(dist[i] != 3000000 && dist2[i] != 3000000)
				max = Math.max(max, dist[i]+dist2[i]);
		}
		
		bw.append(max+"");
		bw.append("\n");
		bw.close();
		
	}

	private static void bfs(int num) {
		PriorityQueue<node> pq = new PriorityQueue<>();
		pq.offer(new node(num, 0));
		dist[num] = 0;
		
		while(!pq.isEmpty()) {
			node nd = pq.poll();
			
			if(visited[nd.w] == 0)
				visited[nd.w]++;
			else
				continue;
			
			for(node e : graph[nd.w]) {
				if(dist[e.w] > dist[nd.w]+e.cost) {
					dist[e.w] = dist[nd.w]+e.cost;
					pq.offer(new node(e.w, dist[e.w]));
				}
			}
		}
		
	}
	
	private static void bfs2(int num) {
		PriorityQueue<node> pq = new PriorityQueue<>();
		pq.offer(new node(num, 0));
		dist2[num] = 0;
		
		while(!pq.isEmpty()) {
			node nd = pq.poll();
			
			if(visited[nd.w] == 0)
				visited[nd.w]++;
			else
				continue;
			
			for(node e : graph2[nd.w]) {
				if(dist2[e.w] > dist2[nd.w]+e.cost) {
					dist2[e.w] = dist2[nd.w]+e.cost;
					pq.offer(new node(e.w, dist2[e.w]));
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

- 그래프를 조금만 활용할 줄 알면 충분히 풀 수 있다.
- 이게 왜 골3…?

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간 / 228ms / 19468KB / 2948B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [데이크스트라](https://www.acmicpc.net/problem/tag/22)
- [최단 경로](https://www.acmicpc.net/problem/tag/215)