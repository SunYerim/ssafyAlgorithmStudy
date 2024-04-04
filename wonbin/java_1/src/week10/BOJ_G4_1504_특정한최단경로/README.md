# [BOJ][G4] 특정한최단경로 / (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/1504)]

[문제 내용 전체]

## 문제

방향성이 없는 그래프가 주어진다. 세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다. 또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.

세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다. 하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라. 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 정점의 개수 N과 간선의 개수 E가 주어진다. (2 ≤ N ≤ 800, 0 ≤ E ≤ 200,000) 둘째 줄부터 E개의 줄에 걸쳐서 세 개의 정수 a, b, c가 주어지는데, a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻이다. (1 ≤ c ≤ 1,000) 다음 줄에는 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호 v1과 v2가 주어진다. (v1 ≠ v2, v1 ≠ N, v2 ≠ 1) 임의의 두 정점 u와 v사이에는 간선이 최대 1개 존재한다.

## 출력

첫째 줄에 두 개의 정점을 지나는 최단 경로의 길이를 출력한다. 그러한 경로가 없을 때에는 -1을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

최단경로를 보고 다익스트라겠구나 라고 접근을 하였다. 시작정점과 도착정점을 제공해줘서 다익스트라인것을 확정 지었다. 다익스트라를 구현 하되, 특정한 정점 2곳을 반드시 지나가는 최단 경로를 구하라고 하였다. 그래서 케이스 마다 지나 갈 수 있는 경로들을 지정해주고 다익스트라를 각각 구한다음 최솟값들을 더해서 결과를 도출했다.

---

> **코드**
> 

```java
package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class test {

	static int N;
	static int E;
	static ArrayList<point>[] list;
	static int[] dist;
	static int[] dist2;
	static int[] dist3;
	static int v1;
	static int v2;
	static int root1;
	static int root2;
	static int answer;
	static int[] visited;
	
	static class point implements Comparable<point>{ // 오름차순을 위한 comparable 상속
		int v;
		int cost;
		
		public point(int v, int cost) {
			super();
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(point o) {
			return this.cost - o.cost;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");

		N = Integer.parseInt(s2[0]);
		E = Integer.parseInt(s2[1]);
		list = new ArrayList[N+1];
		dist = new int[N+1];
		dist2 = new int[N+1];
		dist3 = new int[N+1];
		visited = new int[N+1];
		
		for(int i=0;i<N+1;i++) {
			list[i] = new ArrayList<>();
			dist[i] = 10000000;
			dist2[i] = 10000000;
			dist3[i] = 10000000;
		}
		
		for(int i=0;i<E;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			int start = Integer.parseInt(s2[0]);
			int end = Integer.parseInt(s2[1]);
			int cost = Integer.parseInt(s2[2]);
			
			// 양방향 그래프라서 양쪽 다 만들어줌
			list[start].add(new point(end, cost));
			list[end].add(new point(start, cost));
		}
		
		s = br.readLine();
		s2 = s.split(" ");
		
		v1 = Integer.parseInt(s2[0]);
		v2 = Integer.parseInt(s2[1]);
		
		// 최단거리 다익스트라
		dik(1, 0);
		
		if(v2 == N) { // v2가 N이면 v2는 고려 안하고 v1이후 바로 N
			dik2(v1, 0);
			answer = dist[v1] + dist2[N];
		}
		else { // 아닐경우 v1을 먼저 갈 경우와 v2를 먼저 갈 경우로 나누어서 최솟값을 도출
			dik2(v1, 0);
			dik3(v2, 0);
			root1 = dist[v1] + dist2[v2] + dist3[N];
			root2 = dist[v2] + dist3[v1] + dist2[N];
			answer = Math.min(root1, root2);
		}
		
		if(answer >= 10000000)
			System.out.println(-1);
		else
			System.out.println(answer);
	}

	private static void dik(int start, int cost) {
		PriorityQueue<point> pq = new PriorityQueue<>();
		pq.offer(new point(start, cost));
		visited = new int[N+1];
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			
			point p = pq.poll();
			
			if(visited[p.v] == 0) {
				visited[p.v] = 1;
			}
			
			for(point next : list[p.v]) {

				if(visited[next.v] == 0 && dist[next.v] > p.cost + next.cost) {
					dist[next.v] = p.cost + next.cost;
					pq.add(new point(next.v, dist[next.v]));
				}
			}
		}
		
	}
	
	private static void dik2(int start, int cost) {
		PriorityQueue<point> pq = new PriorityQueue<>();
		pq.offer(new point(start, cost));
		visited = new int[N+1];
		dist2[start] = 0;
		
		while(!pq.isEmpty()) {
			
			point p = pq.poll();
			
			if(visited[p.v] == 0) {
				visited[p.v] = 1;
			}
			
			for(point next : list[p.v]) {

				if(visited[next.v] == 0 && dist2[next.v] > p.cost + next.cost) {
					dist2[next.v] = p.cost + next.cost;
					pq.add(new point(next.v, dist2[next.v]));
				}
			}
		}
		
	}
	
	private static void dik3(int start, int cost) {
		PriorityQueue<point> pq = new PriorityQueue<>();
		pq.offer(new point(start, cost));
		visited = new int[N+1];
		dist3[start] = 0;
		
		while(!pq.isEmpty()) {
			
			point p = pq.poll();
			
			if(visited[p.v] == 0) {
				visited[p.v] = 1;
			}
			
			for(point next : list[p.v]) {

				if(visited[next.v] == 0 && dist3[next.v] > p.cost + next.cost) {
					dist3[next.v] = p.cost + next.cost;
					pq.add(new point(next.v, dist3[next.v]));
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

- 다익스트라를 연습 할 수 있어서 좋았다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간 / 908ms / 81512KB / 3498B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [데이크스트라](https://www.acmicpc.net/problem/tag/22)
- [최단 경로](https://www.acmicpc.net/problem/tag/215)