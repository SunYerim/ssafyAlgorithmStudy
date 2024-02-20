# [BOJ][G4] 게리맨더링/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/17471)]

[문제 내용 전체]

## 문제

백준시의 시장 최백준은 지난 몇 년간 게리맨더링을 통해서 자신의 당에게 유리하게 선거구를 획정했다. 견제할 권력이 없어진 최백준은 권력을 매우 부당하게 행사했고, 심지어는 시의 이름도 백준시로 변경했다. 이번 선거에서는 최대한 공평하게 선거구를 획정하려고 한다.

백준시는 N개의 구역으로 나누어져 있고, 구역은 1번부터 N번까지 번호가 매겨져 있다. 구역을 두 개의 선거구로 나눠야 하고, 각 구역은 두 선거구 중 하나에 포함되어야 한다. 선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 한다. 구역 A에서 인접한 구역을 통해서 구역 B로 갈 수 있을 때, 두 구역은 연결되어 있다고 한다. 중간에 통하는 인접한 구역은 0개 이상이어야 하고, 모두 같은 선거구에 포함된 구역이어야 한다.

아래 그림은 6개의 구역이 있는 것이고, 인접한 구역은 선으로 연결되어 있다.

https://upload.acmicpc.net/08218f4c-2653-4861-a4c1-e7ce808f3a85/-/preview/

공평하게 선거구를 나누기 위해 두 선거구에 포함된 인구의 차이를 최소로 하려고 한다. 백준시의 정보가 주어졌을 때, 인구 차이의 최솟값을 구해보자.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 구역의 개수 N이 주어진다. 둘째 줄에 구역의 인구가 1번 구역부터 N번 구역까지 순서대로 주어진다. 인구는 공백으로 구분되어져 있다.

셋째 줄부터 N개의 줄에 각 구역과 인접한 구역의 정보가 주어진다. 각 정보의 첫 번째 정수는 그 구역과 인접한 구역의 수이고, 이후 인접한 구역의 번호가 주어진다. 모든 값은 정수로 구분되어져 있다.

구역 A가 구역 B와 인접하면 구역 B도 구역 A와 인접하다. 인접한 구역이 없을 수도 있다.

## 출력

첫째 줄에 백준시를 두 선거구로 나누었을 때, 두 선거구의 인구 차이의 최솟값을 출력한다. 두 선거구로 나눌 수 없는 경우에는 -1을 출력한다.

## 제한

- 2 ≤ N ≤ 10
- 1 ≤ 구역의 인구 수 ≤ 100

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음엔 일단 그래프를 구현한 후에 생각을 해보았는데 뭔가 계속 막혀서 어떤 알고리즘을 써야하는지 살짝 보았다.

관련알고리즘에 조합이 있길레 조합을 사용하여 나올수 있는 가짓수를 전부 고르고 그중에 유효한 가짓수만 계산을 해서 합계를 빼주었다.

---

> **코드**
> 

```java
package week4.BOJ_17471_게리맨더링;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class BOJ_17471_게리맨더링 {
	
	static int N;
	static int[] path = new int[11];
	static int zero_count = 0;
	static int answer;
	static int[][] graph = new int[11][11];
	static int min = Integer.MAX_VALUE;
	static ArrayList<gaeri> list = new ArrayList<>();
	static int sum = 0;
	static int sum2 = 0;
	static int[] num_count = new int[11];
	
	static class gaeri{
		int idx;
		int value;
		public gaeri(int idx, int value) {
			super();
			this.idx = idx;
			this.value = value;
		}
		@Override
		public String toString() {
			return "gaeri [idx=" + idx + ", value=" + value + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		for(int i=1;i<=N;i++) { // 선거지역 번호 및 인원수 객체 저장
			list.add(new gaeri(i, Integer.parseInt(s2[i-1])));
		}
		
		for(int i=1;i<=N;i++) { // 선거지도 그래프 그리기
			s = br.readLine();
			s2 = s.split(" ");
			if(Integer.parseInt(s2[0]) == 0)
				zero_count++;
			else {
				for(int j = 0;j<s2.length - 1;j++) {
					graph[i][j] = Integer.parseInt(s2[j+1]);
				}
			}
		}
		
		
		if(N == 2) {
			answer = Math.abs(list.get(0).value - list.get(1).value);
		}
		else if(N - zero_count < 2) // 선거구를 2가지로 나눌 수 없는 경우
			answer = -1;
		else {
			for(int i=1;i<N;i++) {
				comb(1,0,i);
			}
			answer = min;
		}
		
		if(answer == Integer.MAX_VALUE)
			answer = -1;
		
		bw.append(answer + "" + "\n");
		bw.flush();
		bw.close();
		
	}
	
	static void comb(int start, int cnt, int R) {
		
		if(cnt == R) {
			ArrayList<Integer> arr = new ArrayList<>();
			int tmp = 0;
			int tmp2 = 0;
			sum = 0;
			sum2 = 0;
			for(int i=1;i<=N;i++) { // 1~6 저장
				arr.add(i);
			}
			
			for(int i=1;i<11;i++)
				if(path[i] != 0)
					arr.remove(Integer.valueOf(path[i])); // 조합된 수와 분리
			
			Arrays.fill(num_count, 0);
			for(int i=0;i<arr.size();i++) {
				num_count[arr.get(i)]++;
			}
			for(int i=1;i<10;i++) { // 조합된 수 무엇과 연결되었는지 검사
				if(path[i] != 0) {
					num_count[path[i]]++;
					dfs(path[i]); // 하나만 검사하면 되니 하나 검사하고 break;
					break;
				}
			}
			
			for(int i=1;i<11;i++) { // 조합된 수랑 체크된거랑 수량이 같은지 검사 = 연결이 잘 되어 있는가
				if(num_count[i] != 0) {
					for(int j=1;j<11;j++) {
						if(path[j] != 0 && path[j] == i)
							tmp++;
					}
				}
			}
			
			if(tmp == cnt) // 연결이 잘 되어 있다면 합 연산
			{
				for(int i=1;i<11;i++) {
					if(path[i] != 0) {
						sum += list.get(path[i] - 1).value;
					}
				}
			}
			else
				return;
			
			Arrays.fill(num_count, 0);
			for(int i=1;i<11;i++) {
				if(path[i] != 0)
					num_count[path[i]]++;
			}
			dfs(arr.get(0));
			
			for(int i=1;i<11;i++) {
				if(num_count[i] != 0) {
					for(int j=0;j<arr.size();j++) {
						if(i == arr.get(j))
							tmp2++;
					}
				}
			}
			
			if(tmp2 == arr.size())
			{
				for(int i=0;i<arr.size();i++) {
					sum2 += list.get(arr.get(i) - 1).value;
				}
			}
			else return;
			
			if(min>Math.abs(sum - sum2))
				min = Math.abs(sum - sum2);
			
			return;
		}
		
		for(int i=start;i<=N;i++) {
			path[i] = i;
			comb(i+1, cnt+1, R);
			path[i] = 0;
		}
		
	}
	
	static void dfs(int cnt) {
		
		
		
		for(int i=0;i<11;i++) {
			if(graph[cnt][i] != 0 && num_count[graph[cnt][i]] == 0) {
				num_count[graph[cnt][i]]++;
				dfs(graph[cnt][i]);
			}
			
		}
		
	}
}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- ㅋㅋ 내가 이겼다
- 조합을 활용해서 브루트포스를 할 생각만 하면 그 이후는 구현 싸움인거 같다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 2시간 / 184ms / 19472KB / 3884B

알고리즘 분류

- [수학](https://www.acmicpc.net/problem/tag/124)
- [그래프 이론](https://www.acmicpc.net/problem/tag/7)
- [브루트포스 알고리즘](https://www.acmicpc.net/problem/tag/125)
- [그래프 탐색](https://www.acmicpc.net/problem/tag/11)
- [너비 우선 탐색](https://www.acmicpc.net/problem/tag/126)
- [조합론](https://www.acmicpc.net/problem/tag/6)
- [깊이 우선 탐색](https://www.acmicpc.net/problem/tag/127)