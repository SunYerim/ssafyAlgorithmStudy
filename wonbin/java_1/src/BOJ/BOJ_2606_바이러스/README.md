# [BOJ][실버3] 바이러스/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/2606)]

[문제 내용 전체]

신종 바이러스인 웜 바이러스는 네트워크를 통해 전파된다. 한 컴퓨터가 웜 바이러스에 걸리면 그 컴퓨터와 네트워크 상에서 연결되어 있는 모든 컴퓨터는 웜 바이러스에 걸리게 된다.

예를 들어 7대의 컴퓨터가 <그림 1>과 같이 네트워크 상에서 연결되어 있다고 하자. 1번 컴퓨터가 웜 바이러스에 걸리면 웜 바이러스는 2번과 5번 컴퓨터를 거쳐 3번과 6번 컴퓨터까지 전파되어 2, 3, 5, 6 네 대의 컴퓨터는 웜 바이러스에 걸리게 된다. 하지만 4번과 7번 컴퓨터는 1번 컴퓨터와 네트워크상에서 연결되어 있지 않기 때문에 영향을 받지 않는다.

!https://www.acmicpc.net/upload/images/zmMEZZ8ioN6rhCdHmcIT4a7.png

어느 날 1번 컴퓨터가 웜 바이러스에 걸렸다. 컴퓨터의 수와 네트워크 상에서 서로 연결되어 있는 정보가 주어질 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력하는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에는 컴퓨터의 수가 주어진다. 컴퓨터의 수는 100 이하인 양의 정수이고 각 컴퓨터에는 1번 부터 차례대로 번호가 매겨진다. 둘째 줄에는 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수가 주어진다. 이어서 그 수만큼 한 줄에 한 쌍씩 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍이 주어진다.

## 출력

1번 컴퓨터가 웜 바이러스에 걸렸을 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 첫째 줄에 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음에는 생긴게 linkedList 처럼 생겨서 linkedList 활용법을 찾아보면서 풀려고 하였다.

그런데 풀다보니 2차원 배열이 필요할거 같아서 linkedList, ArrayList 를 활용해서 2차원 배열을 만들고 사용해 볼려고 했지만 생소하고 활용하기 어려워서 일반 2차원 배열을 만들어서 시작하였다.

처음 탐색은 1번 인덱스에 있는 배열들만 탐색해서 각 변수의 인덱스에 접근해서 풀었는데 그렇게 하니 1번 인덱스가 아닌 3, 5 / 7, 4 등 중간에 들어갔는데 연결이 되어 있는 요소들은 탐색을 못했다.

그래서 틀린 후 반례를 찾아보다가 DFS라는 키워드를 봐버려서 DFS탐색을 위주로 생각을 해보았다.

1, 2 같은 위치가 있으면 2를 타고 탐색을 다시하는 재귀 DFS를 만들어서 결국 전부 탐색을 해서 결과를 도출하였다.

---

> **코드**
> 

```java
package BOJ_2606_바이러스;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2606_바이러스 {
	
	static int[][] arr = new int[101][101];
	static int[] visited = new int[101];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int computer = Integer.parseInt(br.readLine());
		int c_pair = Integer.parseInt(br.readLine());

		int count = 0;

		for (int i = 0; i < c_pair; i++) {
			String s = br.readLine();
			String[] s2 = s.split(" ");
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);

			arr[a][b] = 1;
			arr[b][a] = 1;
		}

		for(int i= 2;i<=computer;i++) {
			if(arr[1][i] != 0) {
				visited[i] = 1;
				DFS(i,computer);
			}
		}
		
		for(int cnt : visited) {
			if(cnt == 1)
				count++;
		}
		bw.write(count + "" + '\n');
		bw.flush();
		bw.close();
	}
	
	public static void DFS(int y, int computer) {
		
		for(int i=2;i<=computer;i++) {
			if(arr[y][i] == 1 && visited[i] == 0) {
				visited[i] = 1;
				DFS(i, computer);
			}
		}
	}
}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 생각을 더 해봤으면 짧은 시간안에 풀었을거 같다
- 날먹 마인드를 줄여야 겠다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간 20분 정도 / 152ms / 15820 KB / 1223 B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [너비 우선 탐색](https://www.acmicpc.net/problem/tag/126)
- [깊이 우선 탐색](https://www.acmicpc.net/problem/tag/127)