# [BOJ][G5] 뱀과사다리게임 / (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/16928)]

[문제 내용 전체]

## 문제

[뱀과 사다리 게임](https://en.wikipedia.org/wiki/Snakes_and_Ladders)을 즐겨 하는 큐브러버는 어느 날 궁금한 점이 생겼다.

> 주사위를 조작해 내가 원하는 수가 나오게 만들 수 있다면, 최소 몇 번만에 도착점에 도착할 수 있을까?
> 

게임은 정육면체 주사위를 사용하며, 주사위의 각 면에는 1부터 6까지 수가 하나씩 적혀있다. 게임은 크기가 10×10이고, 총 100개의 칸으로 나누어져 있는 보드판에서 진행된다. 보드판에는 1부터 100까지 수가 하나씩 순서대로 적혀져 있다.

플레이어는 주사위를 굴려 나온 수만큼 이동해야 한다. 예를 들어, 플레이어가 i번 칸에 있고, 주사위를 굴려 나온 수가 4라면, i+4번 칸으로 이동해야 한다. 만약 주사위를 굴린 결과가 100번 칸을 넘어간다면 이동할 수 없다. 도착한 칸이 사다리면, 사다리를 타고 위로 올라간다. 뱀이 있는 칸에 도착하면, 뱀을 따라서 내려가게 된다. 즉, 사다리를 이용해 이동한 칸의 번호는 원래 있던 칸의 번호보다 크고, 뱀을 이용해 이동한 칸의 번호는 원래 있던 칸의 번호보다 작아진다.

게임의 목표는 1번 칸에서 시작해서 100번 칸에 도착하는 것이다.

게임판의 상태가 주어졌을 때, 100번 칸에 도착하기 위해 주사위를 굴려야 하는 횟수의 최솟값을 구해보자.

## 입력

첫째 줄에 게임판에 있는 사다리의 수 N(1 ≤ N ≤ 15)과 뱀의 수 M(1 ≤ M ≤ 15)이 주어진다.

둘째 줄부터 N개의 줄에는 사다리의 정보를 의미하는 x, y (x < y)가 주어진다. x번 칸에 도착하면, y번 칸으로 이동한다는 의미이다.

다음 M개의 줄에는 뱀의 정보를 의미하는 u, v (u > v)가 주어진다. u번 칸에 도착하면, v번 칸으로 이동한다는 의미이다.

1번 칸과 100번 칸은 뱀과 사다리의 시작 또는 끝이 아니다. 모든 칸은 최대 하나의 사다리 또는 뱀을 가지고 있으며, 동시에 두 가지를 모두 가지고 있는 경우는 없다. 항상 100번 칸에 도착할 수 있는 입력만 주어진다.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 게임판에 있는 사다리의 수 N(1 ≤ N ≤ 15)과 뱀의 수 M(1 ≤ M ≤ 15)이 주어진다.

둘째 줄부터 N개의 줄에는 사다리의 정보를 의미하는 x, y (x < y)가 주어진다. x번 칸에 도착하면, y번 칸으로 이동한다는 의미이다.

다음 M개의 줄에는 뱀의 정보를 의미하는 u, v (u > v)가 주어진다. u번 칸에 도착하면, v번 칸으로 이동한다는 의미이다.

1번 칸과 100번 칸은 뱀과 사다리의 시작 또는 끝이 아니다. 모든 칸은 최대 하나의 사다리 또는 뱀을 가지고 있으며, 동시에 두 가지를 모두 가지고 있는 경우는 없다. 항상 100번 칸에 도착할 수 있는 입력만 주어진다.

## 출력

100번 칸에 도착하기 위해 주사위를 최소 몇 번 굴려야 하는지 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

hashmap을 2개를 사용하여 사다리의 (위치, 도착지) / (도착지, 위치) 쌍의 맵을 2개만들어 비교를 하면서 반복을 돌렸다.

하지만 주사위 거리 안에 사다리를 탄것보다 다음 주사위 거리안에서 사다리를 탄것이 더 빠를 경우를 체크하지 못하였다.

그래서 제일 멀리가는 사다리의 도착값을 저장한 후 6으로 나누어 max가 달라질때 마다 비교를 하여  값이 더 큰 쪽으로 가도록 유도하고 결과를 도출하는 코드를 작성하였는데 시간초과가 떴다.

더 고민을 해보다가 이번에 배운 bfs가 최단거리를 구하는데 사용된다는 생각이 나서 관련해서 다시 공부해보니 해결이 되었다.

---

> **코드**
> 

```java
package week2.BOJ_16928_뱀과사다리게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16928_뱀과사다리게임 {

	// 결과를 한 번에 출력하기 위한 StringBuilder
		private static StringBuilder sb = new StringBuilder();
		static int[] arr;

		public static void main(String[] args) throws IOException {

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			String s = br.readLine();
			String[] s2 = s.split(" ");
			int ladder = Integer.parseInt(s2[0]);
			int snake = Integer.parseInt(s2[1]);
			arr = new int[101];
			int answer;
			
			for(int i=1;i<arr.length;i++) {
				arr[i] = i;
			}
			
			for(int i=0;i<ladder;i++) {
				String input = br.readLine();
				String[] input2 = input.split(" ");
				arr[Integer.parseInt(input2[0])] =  Integer.parseInt(input2[1]);
			}
			
			for(int i=0;i<snake;i++) {
				String input = br.readLine();
				String[] input2 = input.split(" ");
				arr[Integer.parseInt(input2[0])] = Integer.parseInt(input2[1]);
			}
				
			answer = bfs(1);	
			
			sb.append(answer);
			
			System.out.println(sb);
		}
		
		private static int bfs(int startNode) {

			int[] check = new int[101]; // 방문 순서를 기록하기 위한 배열 생성. 
			Queue<Integer> q = new LinkedList<>();
			q.offer(startNode); //처음에 인덱스 1이 들어간다.
			check[startNode] = 0; 

			while (true) {
				int visitedNum = q.poll();
				//주사위 1~6이 나오는 경우를 살피기.
				for (int i = 1; i < 7; i++) {            	
					int newNode = visitedNum + i;
	                
	                // board의 범위를 넘기면 무시하기
	                // - check 배열에 IndexOutOfBoundsException을 발생시킬 수 있기 때문
					if (newNode > 100) { 
						continue;
					}
	                
	                // check되어있는 경우(방문을 한적이 있는 경우)는 무시한다는 것을 전제로 함.
					if (check[arr[newNode]] == 0) { 
						q.offer(arr[newNode]);
						check[arr[newNode]] = check[visitedNum] + 1;
					}
					if (arr[newNode] == 100) {
						return check[100];
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

- 처음에 너무 어렵게 생각해서 더 오래 걸린것 같다.
- bfs, dfs에 차차 적응을 해봐야겠다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 3시간 이상..? 한 3일 고민한거 같다. / 128ms / 14312KB / 2340B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [너비 우선 탐색](https://www.acmicpc.net/problem/tag/126)