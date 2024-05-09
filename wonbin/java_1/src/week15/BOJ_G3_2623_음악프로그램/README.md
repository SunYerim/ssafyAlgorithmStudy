# [BOJ][G3] 음악프로그램/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/2623)]

[문제 내용 전체]

## 문제

인터넷 방송 KOI(Korea Open Internet)의 음악 프로그램 PD인 남일이는 자기가 맡은 프로그램 '뮤직 KOI'에서 가수의 출연 순서를 정하는 일을 매우 골치 아파한다. 순서를 정하기 위해서는 많은 조건을 따져야 한다.

그래서 오늘 출연 예정인 여섯 팀의 가수에 대해서 남일이가 보조 PD 세 명에게 각자 담당한 가수의 출연 순서를 정해오게 하였다. 보조 PD들이 가져온 것은 아래와 같다.

- 1 4 3
- 6 2 5 4
- 2 3

첫 번째 보조 PD는 1번 가수가 먼저, 다음에 4번 가수, 다음에 3번 가수가 출연하기로 순서를 정했다. 두 번째 보조 PD는 6번, 2번, 5번, 4번 순으로 자기 담당 가수들의 순서를 정했다. 한 가수를 여러 보조 PD가 담당할 수도 있다. 마지막으로, 세 번째 보조 PD는 2번 먼저, 다음에 3번으로 정했다.

남일이가 할 일은 이 순서들을 모아서 전체 가수의 순서를 정하는 것이다. 남일이는 잠시 생각을 하더니 6 2 1 5 4 3으로 순서를 정했다. 이렇게 가수 순서를 정하면 세 보조 PD가 정해온 순서를 모두 만족한다. 물론, 1 6 2 5 4 3으로 전체 순서를 정해도 괜찮다.

경우에 따라서 남일이가 모두를 만족하는 순서를 정하는 것이 불가능할 수도 있다. 예를 들어, 세 번째 보조 PD가 순서를 2 3 대신에 3 2로 정해오면 남일이가 전체 순서를 정하는 것이 불가능하다. 이번에 남일이는 우리 나라의 월드컵 4강 진출 기념 음악제의 PD를 맡게 되었는데, 출연 가수가 아주 많다. 이제 여러분이 해야 할 일은 보조 PD들이 가져 온 순서들을 보고 남일이가 가수 출연 순서를 정할 수 있도록 도와 주는 일이다.

보조 PD들이 만든 순서들이 입력으로 주어질 때, 전체 가수의 순서를 정하는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에는 가수의 수 N과 보조 PD의 수 M이 주어진다. 가수는 번호 1, 2,…,N 으로 표시한다. 둘째 줄부터 각 보조 PD가 정한 순서들이 한 줄에 하나씩 나온다. 각 줄의 맨 앞에는 보조 PD가 담당한 가수의 수가 나오고, 그 뒤로는 그 가수들의 순서가 나온다. N은 1이상 1,000이하의 정수이고, M은 1이상 100이하의 정수이다.

## 출력

출력은 N 개의 줄로 이뤄지며, 한 줄에 하나의 번호를 출력한 다. 이들은 남일이가 정한 가수들의 출연 순서를 나타낸다. 답이 여럿일 경우에는 아무거나 하나를 출력 한다. 만약 남일이가 순서를 정하는 것이 불가능할 경우에는 첫째 줄에 0을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음에는 처음 배열을 기준으로 중복되는 값이 있으면 링크드 리스트를 활용하여 끼워넣는 방식을 생각했었는데 그러면 중복처리도 복잡해지고, 답이 안되는 경우도 생각하기 매우 힘들어서 포기를 하였다. 이후 고민을 하다가 위상정렬을 쓰면 되겠는데 라는 생각을 했는데 구현이 안되어서 지용이에게 물어보고 위상정렬 개념을 다시 복습하고 해결하였다.

1. 피디들이 섭외해온 가수 리스트를 받을때 마다 2명씩 끊어서 그래프를 그려준다. (문제 풀이 핵심)
2. 그래프를 그린 후 위상정렬 알고리즘을 수행한다.
3. 만약 비순환 그래프라면 위상정렬된 값을 출력하고 순환 그래프이면 0을 출력한다.

---

> **코드**
> 

```java
package week15.BOJ_G3_2623_음악프로그램;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BOJ_G3_2623_음악프로그램 {

	static int N;
	static int M;
	static int[] count;
	static ArrayList<Integer>[] graph;
	static ArrayList<Integer> answer;
	static int flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String s = br.readLine();
		String[] s2 = s.split(" ");

		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		count = new int[N+1];
		graph = new ArrayList[N+1];
		answer = new ArrayList<>();
		
		for(int i=0;i<=N;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			
			for(int j=1;j<=Integer.parseInt(s2[0]);j++) {
				if(j != Integer.parseInt(s2[0])) {
					graph[Integer.parseInt(s2[j])].add(Integer.parseInt(s2[j+1]));
				}
			}
		}
		
		for(int i=1;i<=N;i++) { // 위상정렬 테이블 채우기
			for(int j=0;j<graph[i].size();j++) {
				count[graph[i].get(j)]++;
			}
		}
		
		TopologicalSort();
		
		for(int i=1;i<=N;i++) {
			if(count[i] != 0) {
				flag++;
				break;
			}
		}
		
		if(flag != 0)
			System.out.println(0);
		else {
			for(int i=0;i<answer.size();i++) {
				bw.append(answer.get(i)+"");
				bw.append("\n");
			}
			
			bw.close();
		}

	}

	private static void TopologicalSort() {
		Queue<Integer> que = new ArrayDeque<>();
		
		for(int i=1;i<=N;i++) {
			if(count[i] == 0)
				que.offer(i);
		}
		
		while(!que.isEmpty()) {
			int tmp = que.poll();
			answer.add(tmp);
			
			for(int i=0;i<graph[tmp].size();i++) {
				count[graph[tmp].get(i)]--;
				if(count[graph[tmp].get(i)] == 0)
					que.offer(graph[tmp].get(i));
			}
		}
		
	}

}

```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 위상정렬을 다시 공부 할 수 있어서 좋았다.
- 그래프를 만드는 방법이 도저히 떠오르지 않았다..

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간 / 160ms / 14972KB / 1966B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [위상 정렬](https://www.acmicpc.net/problem/tag/78)
- [방향 비순환 그래프](https://www.acmicpc.net/problem/tag/213)