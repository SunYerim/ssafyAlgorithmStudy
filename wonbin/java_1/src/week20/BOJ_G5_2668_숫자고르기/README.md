# [BOJ][G5] 숫자고르기 / (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/2668)]

[문제 내용 전체]

## 문제

세로 두 줄, 가로로 N개의 칸으로 이루어진 표가 있다. 첫째 줄의 각 칸에는 정수 1, 2, …, N이 차례대로 들어 있고 둘째 줄의 각 칸에는 1이상 N이하인 정수가 들어 있다. 첫째 줄에서 숫자를 적절히 뽑으면, 그 뽑힌 정수들이 이루는 집합과, 뽑힌 정수들의 바로 밑의 둘째 줄에 들어있는 정수들이 이루는 집합이 일치한다. 이러한 조건을 만족시키도록 정수들을 뽑되, 최대로 많이 뽑는 방법을 찾는 프로그램을 작성하시오. 예를 들어, N=7인 경우 아래와 같이 표가 주어졌다고 하자.

!https://www.acmicpc.net/upload/images/u5JZnfExdtFXjmR.png

이 경우에는 첫째 줄에서 1, 3, 5를 뽑는 것이 답이다. 첫째 줄의 1, 3, 5밑에는 각각 3, 1, 5가 있으며 두 집합은 일치한다. 이때 집합의 크기는 3이다. 만약 첫째 줄에서 1과 3을 뽑으면, 이들 바로 밑에는 정수 3과 1이 있으므로 두 집합이 일치한다. 그러나, 이 경우에 뽑힌 정수의 개수는 최대가 아니므로 답이 될 수 없다.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에는 N(1≤N≤100)을 나타내는 정수 하나가 주어진다. 그 다음 줄부터는 표의 둘째 줄에 들어가는 정수들이 순서대로 한 줄에 하나씩 입력된다.

## 출력

첫째 줄에 뽑힌 정수들의 개수를 출력하고, 그 다음 줄부터는 뽑힌 정수들을 작은 수부터 큰 수의 순서로 한 줄에 하나씩 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음엔 접근자체를 해매었는데 알고보니까 윗줄의 숫자가 고정되어있는건 인덱스가 정해져있다는 것을 알고 dfs로 해결하였다.

1. 각 인덱스의 값을 다음 인덱스로 저장하고 계속 타고 들어가면서 값을 지정했다.
2. 모든 순환이 완료되면 정답을 출력한다.
3. 정답의 개수가 최대가 되기 위해 모든 경우를 다해보고 경우의 수를 더한다.

---

> **코드**
> 

```java
package week20.BOJ_G5_2668_숫자고르기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class BOJ_G5_2668_숫자고르기 {
	
	static int N;
	static int[] numarr;
	static int[] visited;
	static ArrayList<Integer> checkidx;
	static ArrayList<Integer> checknum;
	static int idx;
	static int answer;
	static TreeSet<Integer> answerarr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		numarr = new int[N+1];
		answerarr = new TreeSet<Integer>();
		
		for(int i=1;i<=N;i++) {
			int tmp = Integer.parseInt(br.readLine());
			numarr[i] = tmp;
		}
		
		for(int i=1;i<=N;i++) {
			checkidx = new ArrayList<Integer>();
			checknum = new ArrayList<Integer>();
			visited = new int[N+1];
			checkidx.add(i);
			checknum.add(numarr[i]);
			visited[i] = 1;
			idx = numarr[i];
			if(checkidx.get(0) == checknum.get(0)) {
				answerarr.add(i);
				continue;
			}
			while(true) {
				int tmp = 0;
				if(visited[idx] == 1) {
					Collections.sort(checkidx);
					Collections.sort(checknum);
					
					for(int j=0;j<checkidx.size();j++) {
						if(checkidx.get(j) != checknum.get(j)) {
							tmp = 1;
							break;
						}
					}
					if(tmp == 1)
						break;
					else {
						for(int p : checkidx) {
							answerarr.add(p);
						}
						break;
					}
				}
				else {
					visited[idx] = 1;
					checkidx.add(idx);
					checknum.add(numarr[idx]);
					idx = numarr[idx];
				}
			}
		}
		
		bw.append(answerarr.size()+"");
		bw.append("\n");
		for(int p: answerarr) {
			bw.append(p+"");
			bw.append("\n");
		}
		
		bw.flush();
		bw.close();
		
	}
}

```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 어렵지 않은 문제였는데 오랜만에 풀어서 그런가 조금 해매었다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간반 / 14376KB / 132ms / 1919B

알고리즘 분류

- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [깊이 우선 탐색](https://www.acmicpc.net/problem/tag/127)