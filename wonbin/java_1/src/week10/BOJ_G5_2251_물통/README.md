# [BOJ][G5] 물통/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/2251)]

[문제 내용 전체]

## 문제

각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다. 처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다. 이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데, 이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다. 이 과정에서 손실되는 물은 없다고 가정한다.

이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 물의 양이 변할 수도 있다. 첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을 모두 구해내는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 세 정수 A, B, C가 주어진다.

## 출력

첫째 줄에 공백으로 구분하여 답을 출력한다. 각 용량은 오름차순으로 정렬한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음에는 테케의 가짓수만 전부 구해서 그 가짓수대로만 동작하도록 구현했는데 당연히 틀렸고 다른 방법이 있나 고민을 하다가 아 그리디한 방법으로는 불가능 하겠구나를 깨닫고 완탐으로 생각을 돌려 보았다. 그런데도 방법이 떠오르지 않아서 결국 해답을 찾아보았다…. 그냥 A→B,C / B→A,C / C→A, B 로 가는 모든 가짓수를 처리하면 되는 거였다. 여기서 포인트는 방문처리를 하는건데 물통이 3개여서 3차원 배열을 활용해서 방문처리를 했다.

---

> **코드**
> 

```java
package week10.BOJ_G5_2251_물통;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeSet;

public class BOJ_G5_2251_물통 {
	
	static TreeSet<Integer> list = new TreeSet<>();
	static int capaA;
	static int capaB;
	static int capaC;
	static boolean[][][] visited = new boolean[201][201][201];
	
	static class water{
		int a;
		int b;
		int c;
		
		public water(int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		capaA = Integer.parseInt(s2[0]);
		capaB = Integer.parseInt(s2[1]);
		capaC = Integer.parseInt(s2[2]);
		
		bfs();
		
		for(int a : list) {
			bw.append(a+""+" ");
		}
		bw.append("\n");
		bw.flush();
		bw.close();
	}
	
	static void bfs() {
		Queue<water> que = new ArrayDeque<>();
		que.add(new water(0,0,capaC));
		
		while(!que.isEmpty()) {
			water wt = que.poll();
			
			int a = wt.a;
			int b = wt.b;
			int c = wt.c;
			
			if(visited[a][b][c] == true)
				continue;
			
			visited[a][b][c] = true;
			
			if(a==0)
				list.add(c);
			
			// A -> B
			if(a+b >= capaB) // 넘치는 경우
				que.add(new water(a-(capaB-b), capaB, c));
			else
				que.add(new water(0, a+b, c));
			
			// A -> C
			if(a+c >= capaC) // 넘치는 경우
				que.add(new water(a-(capaC-c), b, capaC));
			else
				que.add(new water(0, b, a+c));
			
			// B -> C
			if(b+c >= capaC) // 넘치는 경우
				que.add(new water(a, b - (capaC - c), capaC));
			else
				que.add(new water(a, 0, b+c));
			
			// B -> A
			if(a+b >= capaA) // 넘치는 경우
				que.add(new water(capaA, b - (capaA - a), c));
			else
				que.add(new water(a+b, 0, c));
			
			// C -> B
			if(b+c >= capaB) // 넘치는 경우
				que.add(new water(a, capaB, c - (capaB - b)));
			else
				que.add(new water(a, b+c, 0));
			
			// C -> A
			if(a+c >= capaA) // 넘치는 경우
				que.add(new water(capaA, b, c - (capaA - a)));
			else
				que.add(new water(a+c, b, 0));
		}
		
	}
}

```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 뭔가 완탐이란건 알았는데 방법을 못떠올리겠다..

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 이것도 3일 이상 고민한듯.. / 148ms / 24892KB / 2322B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [너비 우선 탐색](https://www.acmicpc.net/problem/tag/126)
- [깊이 우선 탐색](https://www.acmicpc.net/problem/tag/127)