# [BOJ][G4] 서강그라운드 / (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/14938)]

[문제 내용 전체]

## 문제

예은이는 요즘 가장 인기가 있는 게임 서강그라운드를 즐기고 있다. 서강그라운드는 여러 지역중 하나의 지역에 낙하산을 타고 낙하하여, 그 지역에 떨어져 있는 아이템들을 이용해 서바이벌을 하는 게임이다. 서강그라운드에서 1등을 하면 보상으로 치킨을 주는데, 예은이는 단 한번도 치킨을 먹을 수가 없었다. 자신이 치킨을 못 먹는 이유는 실력 때문이 아니라 아이템 운이 없어서라고 생각한 예은이는 낙하산에서 떨어질 때 각 지역에 아이템 들이 몇 개 있는지 알려주는 프로그램을 개발을 하였지만 어디로 낙하해야 자신의 수색 범위 내에서 가장 많은 아이템을 얻을 수 있는지 알 수 없었다.

각 지역은 일정한 길이 l (1 ≤ l ≤ 15)의 길로 다른 지역과 연결되어 있고 이 길은 양방향 통행이 가능하다. 예은이는 낙하한 지역을 중심으로 거리가 수색 범위 m (1 ≤ m ≤ 15) 이내의 모든 지역의 아이템을 습득 가능하다고 할 때, 예은이가 얻을 수 있는 아이템의 최대 개수를 알려주자.

https://upload.acmicpc.net/ef3a5124-833a-42ef-a092-fd658bc8e662/-/preview/

주어진 필드가 위의 그림과 같고, 예은이의 수색범위가 4라고 하자. ( 원 밖의 숫자는 지역 번호, 안의 숫자는 아이템 수, 선 위의 숫자는 거리를 의미한다) 예은이가 2번 지역에 떨어지게 되면 1번,2번(자기 지역), 3번, 5번 지역에 도달할 수 있다. (4번 지역의 경우 가는 거리가 3 + 5 = 8 > 4(수색범위) 이므로 4번 지역의 아이템을 얻을 수 없다.) 이렇게 되면 예은이는 23개의 아이템을 얻을 수 있고, 이는 위의 필드에서 예은이가 얻을 수 있는 아이템의 최대 개수이다.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에는 지역의 개수 n (1 ≤ n ≤ 100)과 예은이의 수색범위 m (1 ≤ m ≤ 15), 길의 개수 r (1 ≤ r ≤ 100)이 주어진다.

둘째 줄에는 n개의 숫자가 차례대로 각 구역에 있는 아이템의 수 t (1 ≤ t ≤ 30)를 알려준다.

세 번째 줄부터 r+2번째 줄 까지 길 양 끝에 존재하는 지역의 번호 a, b, 그리고 길의 길이 l (1 ≤ l ≤ 15)가 주어진다.

지역의 번호는 1이상 n이하의 정수이다. 두 지역의 번호가 같은 경우는 없다.

## 출력

예은이가 얻을 수 있는 최대 아이템 개수를 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음에 보고 최단경로 사용하면 되겠다 싶어서 다익스트라로 구현했는데 실패하였다. 아마 다익스트라를 제대로 구현 안하고 입맛대로 바꾸었다가 실패 한거 같다. 그래서 플로이드 워셜을 사용해서 해결하였다.

1. 플로이드 워셜을 사용할 테이블을 초기화 해준다.(본인은 0, 입력값 넣어주고 나머지 최댓값)
2. 플로이드 워셜을 사용하여 전체 가짓수에 대한 최단 경로를 구한다.
3. 이제 각 한줄씩 읽어서 m보다 경로의 cost가 작으면 그에대한 인덱스를 활용해 item배열에서 값을 더한다.
4. 3번의 과정을 반복하고 최댓값을 갱신하여 출력한다.

---

> **코드**
> 

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

	static int n;
	static int m;
	static int r;
	static int[] item;
	static int[][] graph;
	static int[] visited;
	static int[] count;
	static int answer;
	static int tmp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		n = Integer.parseInt(s2[0]);
		m = Integer.parseInt(s2[1]);
		r = Integer.parseInt(s2[2]);
		graph = new int[n+1][n+1];
		item = new int[n+1];
		
		s = br.readLine();
		s2 = s.split(" ");
		
		for(int i=1;i<=n;i++) {
			item[i] = Integer.parseInt(s2[i-1]);
		}
		
		for(int i=0;i<=n;i++) {
			Arrays.fill(graph[i], 150000);
		}
		
		for(int i=0;i<=n;i++) {
			graph[i][i] = 0;
		}
		
		for(int i=0;i<r;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			int c = Integer.parseInt(s2[2]);
			
			graph[a][b] = c;
			graph[b][a] = c;
		}
		
		for(int k=1;k<=n;k++) {
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					graph[i][j] = Math.min(graph[i][k] + graph[k][j], graph[i][j]);
				}
			}
		}
		
		for(int i=1;i<=n;i++) {
			tmp = 0;
			for(int j=1;j<=n;j++) {
				if(graph[i][j] <= m)
					tmp += item[j];
			}
			answer = Math.max(answer, tmp);
		}
		
		bw.append(answer+"");
		bw.append("\n");
		bw.close();
		
	}

}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 다익스트라를 아직 제대로 활용을 못하는게 한심하다
- 플로이드나 다익스트라 둘중 하나만 사용할 줄 알면 금방 해결하는 문제이다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간 / 160ms / 15148KB / 1800B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [데이크스트라](https://www.acmicpc.net/problem/tag/22)
- [최단 경로](https://www.acmicpc.net/problem/tag/215)
- [플로이드–워셜](https://www.acmicpc.net/problem/tag/31)