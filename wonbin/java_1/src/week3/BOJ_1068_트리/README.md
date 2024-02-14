# [BOJ][G5] 트리/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/1068)]

[문제 내용 전체]

## 문제

트리에서 리프 노드란, 자식의 개수가 0인 노드를 말한다.

트리가 주어졌을 때, 노드 하나를 지울 것이다. 그 때, 남은 트리에서 리프 노드의 개수를 구하는 프로그램을 작성하시오. 노드를 지우면 그 노드와 노드의 모든 자손이 트리에서 제거된다.

예를 들어, 다음과 같은 트리가 있다고 하자.

https://upload.acmicpc.net/560de878-d961-475e-ada4-e1f0774e5a84/-/preview/

현재 리프 노드의 개수는 3개이다. (초록색 색칠된 노드) 이때, 1번을 지우면, 다음과 같이 변한다. 검정색으로 색칠된 노드가 트리에서 제거된 노드이다.

https://upload.acmicpc.net/d46ddf4e-1b82-44cc-8c90-12f76e5bf88f/-/preview/

이제 리프 노드의 개수는 1개이다.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 트리의 노드의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄에는 0번 노드부터 N-1번 노드까지, 각 노드의 부모가 주어진다. 만약 부모가 없다면 (루트) -1이 주어진다. 셋째 줄에는 지울 노드의 번호가 주어진다.

## 출력

첫째 줄에 입력으로 주어진 트리에서 입력으로 주어진 노드를 지웠을 때, 리프 노드의 개수를 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음엔 인접행렬로 풀었었다. 그런데 해결이 안되서 왜 안되지 하는 생각에 여러가지를 시도해 보았는데 뭐해도 안풀렸다.. 고민을 해보니 0을 리프노드로 잡는 경우를 체크를 못한거 같았다.

그래서 초기화를 -1로 하고 풀었더니 바로 해결되었다.

---

> **코드**
> 

```java
package week3.BOJ_1068_트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1068_트리 {

	static int N;
	static int del;
	static int[][] tree;
	static int count = 0;
	static int tmp = 0;
	static int[] spot;
	static int root;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		String[] s2 = s.split(" ");
		del = Integer.parseInt(br.readLine());
		tree = new int[N][N];
		spot = new int[N+1];
		
		for(int i=0;i<N;i++) {
			Arrays.fill(tree[i], -1);
		}
		
		for(int i=0;i<N;i++) {
			if(Integer.parseInt(s2[i]) == -1) {
				root = i;
				continue;
			}
			else if(i==del) {
				continue;
			}
			
			for(int j=0;j<N;j++) {
				if(tree[Integer.parseInt(s2[i])][j] == -1) {
					tree[Integer.parseInt(s2[i])][j] = i;
					break;
				}
			}
		}
		if(root == del) count=0;
		else dfs(root);
		
		sb.append(count);
		System.out.println(sb);
		
	}
	
	public static void dfs(int cnt) {
		
		for(int i=0;i<N;i++) {
			if(tree[cnt][i] != -1) {
				spot[tmp] = cnt;
				cnt = tree[cnt][i];
				tmp++;
				dfs(cnt);
				tmp--;
				if(tmp<0) tmp= 0;
				cnt = spot[tmp];
			}
			else if(i==0 && tree[cnt][i] == -1) {
				count++;
				return;
			}
		}
	}
}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 트리 구현에 대해 좀 알게 되었다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 2시간/124ms/14304KB/1398B

알고리즘 분류

## 알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [트리](https://www.acmicpc.net/problem/tag/120)
- [깊이 우선 탐색](https://www.acmicpc.net/problem/tag/127)