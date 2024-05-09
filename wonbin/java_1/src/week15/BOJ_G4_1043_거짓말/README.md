# [BOJ][G4] 거짓말/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/1043)]

[문제 내용 전체]

## 문제

지민이는 파티에 가서 이야기 하는 것을 좋아한다. 파티에 갈 때마다, 지민이는 지민이가 가장 좋아하는 이야기를 한다. 지민이는 그 이야기를 말할 때, 있는 그대로 진실로 말하거나 엄청나게 과장해서 말한다. 당연히 과장해서 이야기하는 것이 훨씬 더 재미있기 때문에, 되도록이면 과장해서 이야기하려고 한다. 하지만, 지민이는 거짓말쟁이로 알려지기는 싫어한다. 문제는 몇몇 사람들은 그 이야기의 진실을 안다는 것이다. 따라서 이런 사람들이 파티에 왔을 때는, 지민이는 진실을 이야기할 수 밖에 없다. 당연히, 어떤 사람이 어떤 파티에서는 진실을 듣고, 또다른 파티에서는 과장된 이야기를 들었을 때도 지민이는 거짓말쟁이로 알려지게 된다. 지민이는 이런 일을 모두 피해야 한다.

사람의 수 N이 주어진다. 그리고 그 이야기의 진실을 아는 사람이 주어진다. 그리고 각 파티에 오는 사람들의 번호가 주어진다. 지민이는 모든 파티에 참가해야 한다. 이때, 지민이가 거짓말쟁이로 알려지지 않으면서, 과장된 이야기를 할 수 있는 파티 개수의 최댓값을 구하는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 사람의 수 N과 파티의 수 M이 주어진다.

둘째 줄에는 이야기의 진실을 아는 사람의 수와 번호가 주어진다. 진실을 아는 사람의 수가 먼저 주어지고 그 개수만큼 사람들의 번호가 주어진다. 사람들의 번호는 1부터 N까지의 수로 주어진다.

셋째 줄부터 M개의 줄에는 각 파티마다 오는 사람의 수와 번호가 같은 방식으로 주어진다.

N, M은 50 이하의 자연수이고, 진실을 아는 사람의 수는 0 이상 50 이하의 정수, 각 파티마다 오는 사람의 수는 1 이상 50 이하의 정수이다.

## 출력

첫째 줄에 문제의 정답을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

유니온 파인드를 사용해서 각 그룹에서 연관된 그룹을 지정해줬고 다시 한번 탐색을해서 연관된 그룹인지 판정을하고 전부 다 탐색을 한 뒤 연관된 그룹이 아니면 answer값을 ++ 해줬다.

1. 유니온파인드를 활용해 연관된 그룹인지 판별.
2. 각 그룹마다 전체 탐색을 해서 연관된 그룹인지 판별.
3. 전체 탐색 후 연관된 그룹이 아니면 answer++
4. answer 출력 만약 연관된 사람이 0명이면 M 출력

---

> **코드**
> 

```java
package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class test {

	static int N;
	static int M;
	static int[] parents;
	static ArrayList<Integer> np;
	static ArrayList<Integer>[] party;
	static int[] lie;
	static int tmp;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String[] s2 = s.split(" ");

		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		parents = new int[N+1];
		party = new ArrayList[M];
		np = new ArrayList<>();
		lie = new int[M];
		
		for(int i=0;i<=N;i++) {
			parents[i] = i;
		}
		
		for(int i=0;i<M;i++) {
			party[i] = new ArrayList<>();
		}
		
		s = br.readLine();
		s2 = s.split(" ");
		
		if(Integer.parseInt(s2[0]) != 0) {
			for(int i=1;i<=Integer.parseInt(s2[0]);i++) {
				np.add(Integer.parseInt(s2[i]));
			}
		}
		
		for(int i=0;i<M;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			for(int j=1;j<=Integer.parseInt(s2[0]);j++) {
				if(np.size() != 0 && j != Integer.parseInt(s2[0])) {
					union(Integer.parseInt(s2[j]), Integer.parseInt(s2[j+1]));
				}
				party[i].add(Integer.parseInt(s2[j]));
			}
		}
		
		if(np.size() == 0) {
			System.out.println(M);
		}
		else {
			for(int p=0;p<np.size();p++) {
				for(int i=0;i<M;i++) {
					for(int j=0;j<party[i].size();j++) {
						int a = find(np.get(p));
						int b = find(party[i].get(j));
						
						if(a == b) { // 소문이 퍼지는 그룹이면 tmp++
							tmp++;
							break;
						}
					}
					if(tmp == 1) {
						lie[i] = 1;
					}
					tmp = 0;
				}
			}
			
			for(int i=0;i<M;i++) { // 소문이 안퍼졌으면 answer++
				if(lie[i] == 0)
					answer++;
			}
			
			System.out.println(answer);
		}
		

	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x >= y)
			parents[x] = y;
		else
			parents[y] = x;
	}
	
	
	static int find(int x) {
		if(parents[x] == x)
			return x;
		else
			return find(parents[x]);
	}
	
}

```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 유니온 파인드를 연습할 수 있는 문제였다.
- 건실한 청년.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 2시간? / 124ms / 14288KB / 2081B

알고리즘 분류

- [자료 구조](https://www.acmicpc.net/problem/tag/175)
- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [분리 집합](https://www.acmicpc.net/problem/tag/81)