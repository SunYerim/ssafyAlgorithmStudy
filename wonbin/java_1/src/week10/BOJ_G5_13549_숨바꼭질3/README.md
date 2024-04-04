# [BOJ][G5] 숨바꼭질3 / (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/13549)]

[문제 내용 전체]

## 문제

수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다.

수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

## 출력

수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음에는 접근 자체를 잘 못해서 dp느낌으로 풀었는데 너무 안풀려서 고민을 하다가 이전에 풀었던 뱀과사다리게임 문제가 떠올라서 1차원 bfs느낌으로 풀면 되겠구나 해서 접근을 했다.

하지만 이번 문제는 가중치가 0과 1로 이루어져 있어서 가중치에 따른 큐배열을 관리해줘야 했다. 여러번 시도했지만 해결이 안되서 알고리즘을 알아보니 0-1 bfs라는 알고리즘이 따로 있어서 찾아서 해결했다. Queue를 Deque로 만들어서 가중치가 0인 객체를 앞으로 넣어줘서 관리를 하니까 문제가 해결되었다.

---

> **코드**
> 

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

	static int N;
	static int K;
	static int[] visited;
	static int count;
	
	static class person{
		int p;
		int cnt;
		
		public person(int p, int cnt) {
			this.p = p;
			this.cnt = cnt;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		K = Integer.parseInt(s2[1]);
		visited = new int[500000];
		count = Integer.MAX_VALUE;
		
		bfs(N);
		
		System.out.println(count);
	}

	private static void bfs(int n) {
		Deque<person> que = new ArrayDeque<>();
		que.offer(new person(n, 0));
		int tmp = 0;
		visited[n] = 1;
		
		while(!que.isEmpty()) {
			
			person point = que.poll();
			
			
			if(point.p == K) {
				count = point.cnt;
				return;
			}
			
			
			if(0 <= point.p * 2 && point.p * 2 <= 100000 && visited[point.p * 2] == 0) {
				que.addFirst(new person(point.p * 2, point.cnt));
				visited[point.p * 2] = 1;
			}
			
			if(0 <= point.p - 1 && point.p - 1 <= 100000 && visited[point.p - 1] == 0) {
				que.offer(new person(point.p - 1, point.cnt + 1));
				visited[point.p - 1] = 1;
			}
			
			if(0 <= point.p + 1 && point.p + 1 <= 100000 && visited[point.p + 1] == 0) {
				que.offer(new person(point.p + 1, point.cnt + 1));
				visited[point.p + 1] = 1;
			}
			
			
		}
	}

}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 다익스트라로도 풀 수 있다는데 일차원 다익스트라는 뭔가 접근이 잘안되네;;
- 0-1 bfs를 알고가서 좋았다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 거진 3일 고민 ㅋㅋㅋㅋ.. / 156ms / 18376KB / 1663B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [너비 우선 탐색](https://www.acmicpc.net/problem/tag/126)
- [데이크스트라](https://www.acmicpc.net/problem/tag/22)
- [최단 경로](https://www.acmicpc.net/problem/tag/215)
- [0-1 너비 우선 탐색](https://www.acmicpc.net/problem/tag/176)