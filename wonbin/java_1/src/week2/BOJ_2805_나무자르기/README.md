# [BOJ][S2] 나무자르기 / (TLE)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/2805)]

[문제 내용 전체]

## 문제

상근이는 나무 M미터가 필요하다. 근처에 나무를 구입할 곳이 모두 망해버렸기 때문에, 정부에 벌목 허가를 요청했다. 정부는 상근이네 집 근처의 나무 한 줄에 대한 벌목 허가를 내주었고, 상근이는 새로 구입한 목재절단기를 이용해서 나무를 구할것이다.

목재절단기는 다음과 같이 동작한다. 먼저, 상근이는 절단기에 높이 H를 지정해야 한다. 높이를 지정하면 톱날이 땅으로부터 H미터 위로 올라간다. 그 다음, 한 줄에 연속해있는 나무를 모두 절단해버린다. 따라서, 높이가 H보다 큰 나무는 H 위의 부분이 잘릴 것이고, 낮은 나무는 잘리지 않을 것이다. 예를 들어, 한 줄에 연속해있는 나무의 높이가 20, 15, 10, 17이라고 하자. 상근이가 높이를 15로 지정했다면, 나무를 자른 뒤의 높이는 15, 15, 10, 15가 될 것이고, 상근이는 길이가 5인 나무와 2인 나무를 들고 집에 갈 것이다. (총 7미터를 집에 들고 간다) 절단기에 설정할 수 있는 높이는 양의 정수 또는 0이다.

상근이는 환경에 매우 관심이 많기 때문에, 나무를 필요한 만큼만 집으로 가져가려고 한다. 이때, 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 구하는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 나무의 수 N과 상근이가 집으로 가져가려고 하는 나무의 길이 M이 주어진다. (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)

둘째 줄에는 나무의 높이가 주어진다. 나무의 높이의 합은 항상 M보다 크거나 같기 때문에, 상근이는 집에 필요한 나무를 항상 가져갈 수 있다. 높이는 1,000,000,000보다 작거나 같은 양의 정수 또는 0이다.

## 출력

적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최댓값을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음엔 안될걸 알면서도 일단 이중 포문을 작성하여 시도를 해봤다. 

하지만 역시 시간초과가 떠서 다른 방법으로 접근을 시도해 보았다.

두번째 방법은 최댓값에서 m값을 뺀 수에서 최대값까지만 탐색을 해보는 거였는데 이것 또한 시간초과가 떴다.

그래서 내머리로는 답이 없어 접근 방법을 알아보았는데 이분탐색을 활용 해야 한다고 적혀있었다.

그래서 이분탐색을 구현하여 해보았지만 내가 sum이 m이랑 똑같을 경우에만 값을 도출하게 작성하여 무한루프가 걸리는 구간이 있었다. 이를 해결하기 위해 범위의 차가 1 이하로 날 경우 그중 큰 값을 도출하게 했는데 실패가 떴다. 뭔가 놓치고 있는게 있는거 같다.

sum이 m보다 낮으면 기준점을 낮추고 이전 기준값보다 작아지면 리턴하는 방식으로 해봤는데 다시 무한반복이 뜨는거 같다..

아니 이거 반례 다 맞는데 왜 흠 허 진짜 느린건가

---

> **코드**
> 

```java
package week2.BOJ_2805_나무자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2805_나무자르기 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		int N = Integer.parseInt(s2[0]);
		int M = Integer.parseInt(s2[1]);
		int max  = 0;
		int[] wood = new int[N];
		long sum = 0;
		
		String input = br.readLine();
		String[] input2 = input.split(" ");
		
		for(int i=0;i<N;i++) {
			max = Math.max(max, Integer.parseInt(input2[i]));
			wood[i] = Integer.parseInt(input2[i]);
		}
		
		int mid = max/2;
		int tmp = 0;
		
		while(tmp<mid) {
			for(int i=0;i<N;i++) {
				if(wood[i] - mid<0) continue;
				else {
					sum+=wood[i] - mid;
				}
			}
			
			if(sum == M) {
				break;
			}
			else if(sum<M) {
				mid--;
				sum = 0;
			}
			else {
				tmp = mid;
				mid = (max + mid) /2;
				sum=0;
			}
		}
		
		sb.append(mid);
		System.out.println(sb);
		
	}
}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

문제 풀이 시간/ 실행시간/ 메모리/코드길이

알고리즘 분류