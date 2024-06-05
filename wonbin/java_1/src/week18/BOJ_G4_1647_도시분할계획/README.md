# [BOJ][G4] 도시분할계획 / (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/1647)]

[문제 내용 전체]

## 문제

동물원에서 막 탈출한 원숭이 한 마리가 세상구경을 하고 있다. 그러다가 평화로운 마을에 가게 되었는데, 그곳에서는 알 수 없는 일이 벌어지고 있었다.

마을은 N개의 집과 그 집들을 연결하는 M개의 길로 이루어져 있다. 길은 어느 방향으로든지 다닐 수 있는 편리한 길이다. 그리고 각 길마다 길을 유지하는데 드는 유지비가 있다. 임의의 두 집 사이에 경로가 항상 존재한다.

마을의 이장은 마을을 두 개의 분리된 마을로 분할할 계획을 가지고 있다. 마을이 너무 커서 혼자서는 관리할 수 없기 때문이다. 마을을 분할할 때는 각 분리된 마을 안에 집들이 서로 연결되도록 분할해야 한다. 각 분리된 마을 안에 있는 임의의 두 집 사이에 경로가 항상 존재해야 한다는 뜻이다. 마을에는 집이 하나 이상 있어야 한다.

그렇게 마을의 이장은 계획을 세우다가 마을 안에 길이 너무 많다는 생각을 하게 되었다. 일단 분리된 두 마을 사이에 있는 길들은 필요가 없으므로 없앨 수 있다. 그리고 각 분리된 마을 안에서도 임의의 두 집 사이에 경로가 항상 존재하게 하면서 길을 더 없앨 수 있다. 마을의 이장은 위 조건을 만족하도록 길들을 모두 없애고 나머지 길의 유지비의 합을 최소로 하고 싶다. 이것을 구하는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 집의 개수 N, 길의 개수 M이 주어진다. N은 2이상 100,000이하인 정수이고, M은 1이상 1,000,000이하인 정수이다. 그 다음 줄부터 M줄에 걸쳐 길의 정보가 A B C 세 개의 정수로 주어지는데 A번 집과 B번 집을 연결하는 길의 유지비가 C (1 ≤ C ≤ 1,000)라는 뜻이다.

임의의 두 집 사이에 경로가 항상 존재하는 입력만 주어진다.

## 출력

첫째 줄에 없애고 남은 길 유지비의 합의 최솟값을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

1. MST 사용 (프림을 썼습니다.)
2. 그 후 그중에서 간선의 길이가 가장 긴 간선을 제거
3. 그러면 간선의 길이가 가장 짧은 두 마을이 나옴

---

> **코드**
> 

```java
package week18.BOJ_G4_1647_도시분할계획;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class BOJ_G4_1647_도시분할계획 {

	static int N;
	static int M;
	static int load;
	static ArrayList<node>[] graph;
	static int[] visited;
	static int maxcost;
	static int answer;
	
	static class node implements Comparable<node> { // 우선순위 큐를 사용하기 위해 Comparable 사용
		int w;
		int cost;
		
		public node(int w, int cost) {
			this.w = w;
			this.cost = cost;
		}

		@Override
		public int compareTo(node o) {
			return this.cost - o.cost;
		}

		@Override
		public String toString() {
			return "node [w=" + w + ", cost=" + cost + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		visited = new int[N+1];
		graph = new ArrayList[N+1];
		
		for(int i=0;i<=N;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			s = br.readLine();
			s2= s.split(" ");
			
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			int c = Integer.parseInt(s2[2]);
			
			graph[a].add(new node(b, c));
			graph[b].add(new node(a, c));
			
		}
		
		Prim(1);
		answer -= maxcost;
		
		System.out.println(answer);
		
	}
	
	static void Prim(int start) { // MST 구현
		
		PriorityQueue<node> pq = new PriorityQueue<>();
		pq.offer(new node(start, 0));
		
		while(!pq.isEmpty()) {
			node e = pq.poll();
			
			int v = e.w;
			int cost = e.cost;
			
			if(visited[v] == 1)
				continue;
			
			visited[v] = 1;
			maxcost = Math.max(maxcost, cost);
			answer += cost;
			
			for(node n : graph[v]) {
				if(visited[n.w] == 0)
					pq.offer(n);
			}
			
			
		}
		
		
	}
}

```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- MST 연습을 할 수 있었습니다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간 / 2144ms / 343832KB / 1807B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [최소 스패닝 트리](https://www.acmicpc.net/problem/tag/49)