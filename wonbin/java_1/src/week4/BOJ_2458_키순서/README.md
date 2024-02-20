# [BOJ][G4] 키순서/ (WA)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/2458)]

[문제 내용 전체]

## 문제

1번부터 N번까지 번호가 붙여져 있는 학생들에 대하여 두 학생끼리 키를 비교한 결과의 일부가 주어져 있다. 단, N명의 학생들의 키는 모두 다르다고 가정한다. 예를 들어, 6명의 학생들에 대하여 6번만 키를 비교하였고, 그 결과가 다음과 같다고 하자.

- 1번 학생의 키 < 5번 학생의 키
- 3번 학생의 키 < 4번 학생의 키
- 5번 학생의 키 < 4번 학생의 키
- 4번 학생의 키 < 2번 학생의 키
- 4번 학생의 키 < 6번 학생의 키
- 5번 학생의 키 < 2번 학생의 키

이 비교 결과로부터 모든 학생 중에서 키가 가장 작은 학생부터 자신이 몇 번째인지 알 수 있는 학생들도 있고 그렇지 못한 학생들도 있다는 사실을 아래처럼 그림을 그려 쉽게 확인할 수 있다. a번 학생의 키가 b번 학생의 키보다 작다면, a에서 b로 화살표를 그려서 표현하였다.

https://upload.acmicpc.net/8f9e2484-a3aa-4b97-b1fa-387df4ae58d0/-/preview/

1번은 5번보다 키가 작고, 5번은 4번보다 작기 때문에, 1번은 4번보다 작게 된다. 그러면 1번, 3번, 5번은 모두 4번보다 작게 된다. 또한 4번은 2번과 6번보다 작기 때문에, 4번 학생은 자기보다 작은 학생이 3명이 있고, 자기보다 큰 학생이 2명이 있게 되어 자신의 키가 몇 번째인지 정확히 알 수 있다. 그러나 4번을 제외한 학생들은 자신의 키가 몇 번째인지 알 수 없다.

학생들의 키를 비교한 결과가 주어질 때, 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇 명인지 계산하여 출력하는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 학생들의 수 N (2 ≤ N ≤ 500)과 두 학생 키를 비교한 횟수 M (0 ≤ M ≤ N(N-1)/2)이 주어진다.

다음 M개의 각 줄에는 두 학생의 키를 비교한 결과를 나타내는 N보다 작거나 같은 서로 다른 양의 정수 a와 b가 주어진다. 이는 번호가 a인 학생이 번호가 b인 학생보다 키가 작은 것을 의미한다.

## 출력

자신이 키가 몇 번째인지 알 수 있는 학생이 모두 몇 명인지를 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

위상 정렬의 원리를 사용하면 풀릴거 같아서 진입 간선 + 나가는 간선 의 합이 N - 1 이면 위치를 알 수 있다는 것을 전제로 두고 진입 간선, 나가는 간선을 구하여 카운트를 해줬고 만약 진입간선의 개수가 0개인 노드가 하나 일 경우 그 노드도 카운트를 해준다.

하지만 이렇게 푸니 메모리 초과가 떴다….

그래서 난 이 이상 해결을 못할 거 같아서 정답을 찾아보니 플로이드 워셜을 사용하는 문제라고 한다

---

> **코드**
> 

```java
package week4.BOJ_2458_키순서;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_2458_키순서 {

	static int N;
	static int M;
	static ArrayList<ArrayList<Integer>> key = new ArrayList<>(); // 그래프 구현 배열
	static int[] in_V;
	static int[] out_V;
	static int count = 0;
	static ArrayList<ArrayList<Integer>> key_in = new ArrayList<>(); // 노드의 진입 간선의 종류를 전부 넣는 배열
	static int[] num_count;
	static int tmp = 0;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		in_V = new int[N+1];
		out_V = new int[N+1];
		num_count = new int[N+1];
		
		for(int i=0;i<N+1;i++) {
			key.add(new ArrayList<>());
		}
		
		for(int i=0;i<N+1;i++) {
			key_in.add(new ArrayList<>());
		}
		
		for(int i=0;i<M;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			key.get(Integer.parseInt(s2[1])).add(Integer.parseInt(s2[0])); // 각 노드가 어떤 노드를 받고 있는지
			out_V[Integer.parseInt(s2[0])]++; // 나가는 간선
		}
		
		for(int i=1;i<N+1;i++) {
			dfs(i, i);
		}
		
		for(int i=1;i<N+1;i++) {
			Arrays.fill(num_count, 0); // 카운트 배열 0으로 초기화
			
			for(int j=0;j<key_in.get(i).size();j++) {
				num_count[key_in.get(i).get(j)]++; // 중복 처리
			}
			for(int j=1;j<N+1;j++) {
				if(num_count[j] != 0)
					in_V[i]++; // 진입 간선
			}
		}
		
		for(int i=1;i<N+1;i++) {
			if(in_V[i] + out_V[i]== N - 1) // 진입 간선 + 나가는 간선이 N - 1이면 위치를 알 수 있음
				count++;
		}
		
		for(int i=1;i<N+1;i++) { // 진입 간선이 없는 경우를 카운트
			if(key.get(i).size() == 0)
				tmp++;
		}
		if(tmp == 1) // 진입 간선이 없는 노드가 하나일 경우 위치를 알 수 있음 (젤 큼)
			count++;
		
		bw.append(count+""+"\n");
		bw.flush();
		bw.close();
	}
	
	static void dfs(int node,int fix) {
		
		for(int i=0;i<key.get(node).size();i++) {
			key_in.get(fix).add(key.get(node).get(i));
			dfs(key.get(node).get(i), fix);
		}
	}
}
```

내 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		boolean[][] check = new boolean[n][n];
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			check[a][b] = true;
		}
		
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(check[i][k] && check[k][j]) {
						check[i][j] = true;
					}
				}
			}
		}
		
		int[] cnt = new int[n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
                if(check[i][j] || check[j][i]) {
					cnt[i] ++;
				}
			}
		}
		
		int res =0;
		for(int num : cnt) {
			if(num == n-1) res++;
		}
		System.out.println(res);
		
	}
}
```

정답 코드

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 새로 알아야 할게 너무 많다
- 플로이드 워셜을 공부해보자
- 메모리 초과만 해결하면 내 코드도 정답이 될거 같다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [깊이 우선 탐색](https://www.acmicpc.net/problem/tag/127)
- [최단 경로](https://www.acmicpc.net/problem/tag/215)
- [플로이드–워셜](https://www.acmicpc.net/problem/tag/31)