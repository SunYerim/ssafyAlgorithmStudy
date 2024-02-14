# [BOJ][11725] 트리의부모찾기/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/11725)]

[문제 내용 전체]

## 문제

루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 노드의 개수 N (2 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N-1개의 줄에 트리 상에서 연결된 두 정점이 주어진다.

## 출력

첫째 줄부터 N-1개의 줄에 각 노드의 부모 노드 번호를 2번 노드부터 순서대로 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음엔 인접행렬을 사용해서 해결을 시도해봤지만 바로 메모리초과가 떴다.

그래서 다른 트리 구현법을 검색해보니 인접리스트를 활용한 구현이 있었다.

그래서 인접리스트를 활용하여 구현을 한 후 탐색을 어떻게 할까 고민을 많이 했는데 그냥 bfs를 때려서 레벨 마다 연결되어 있는 노드들을 전부 체크 한 후 카운트 배열을 활용해서 이전에 중복을 체크해서 해결하였다.

---

> **코드**
> 

```java
package week3.BOJ_11725_트리의부모찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;

import javax.management.Query;

public class BOJ_11725_트리의부모찾기 {
	
	static int N;
	static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
	static StringBuffer sb = new StringBuffer();
	static int[] n_count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int tmp = 1;
		n_count = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {

			String s = br.readLine();
			String[] s2 = s.split(" ");

			tree.get(Integer.parseInt(s2[0])).add(Integer.parseInt(s2[1]));
			tree.get(Integer.parseInt(s2[1])).add(Integer.parseInt(s2[0]));
		}
		
		Queue<Integer> qu = new ArrayDeque<>();
		qu.add(1);
		while(!qu.isEmpty()) {
			tmp = qu.peek();
			
			for(int i=0;i<tree.get(tmp).size();i++) {
				if(n_count[tree.get(tmp).get(i)] == 0) {
					n_count[tree.get(tmp).get(i)] = tmp;
					qu.add(tree.get(tmp).get(i));
				}
				else if(n_count[tree.get(tmp).get(i)] == 1) continue;
				
			}
			qu.poll();
		}

		
		for(int i=2;i<=N;i++) {
			System.out.println(n_count[i]);
		}
	}
}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 인접리스트를 활용해서 해결하면 큰 어려움이 없는 문제였다.
- 처음에 구현도 다르게 해보고 탐색도 어떻게 해야할지 감이 안잡혀서 많이 헤맸다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 2시간 반 / 1512ms / 73592KB / 1429KB

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [트리](https://www.acmicpc.net/problem/tag/120)
- [너비 우선 탐색](https://www.acmicpc.net/problem/tag/126)
- [깊이 우선 탐색](https://www.acmicpc.net/problem/tag/127)