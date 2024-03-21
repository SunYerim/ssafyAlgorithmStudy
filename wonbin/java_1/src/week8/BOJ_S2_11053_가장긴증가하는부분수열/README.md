# [BOJ][S2] 가장긴증가하는부분수열/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/11053)]

[문제 내용 전체]

## 문제

수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {**10**, **20**, 10, **30**, 20, **50**} 이고, 길이는 4이다.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.

둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)

## 출력

첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

부분증가수열 알고리즘이 따로존재하는 dp 문제이다. 길이 배열을 따로 만들어서 각 길이마다 최대 길이를 비교해가면서 max 값을 도출하는 문제이다. 현재 방식으로 풀면 시간 복잡도가 O(N2제곱))만큼 걸려 효율이 좋지 않다 다른 방법으로는 이분탐색을 활용하는 방식이 있는데 그 방식을 사용하면 시간복잡도를 (Nlogn)까지 줄일 수 있다.

---

> **코드**
> 

```java
package week8.BOJ_S2_11053_가장긴증가하는부분수열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_S2_11053_가장긴증가하는부분수열 {

	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int answer = 0;
		int[] length = new int[n];
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(s2[i]);
		}
		
		for(int k=0;k<n;k++) {
			length[k] = 1;
			for(int i=0;i<k;i++) {
				if(arr[k] > arr[i]) {
					length[k] = Math.max(length[k], length[i]+1);
				}
			}
		}
		
		Arrays.sort(length);
		
		bw.append(length[n-1]+"");
		bw.append("\n");
		bw.flush();
		bw.close();
	}
}

```

이분탐색을 활용한 LIS

```java
import java.util.Scanner;

public class LIS_DP2 {
	static int N;
	static int[] arr, dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		// dp에 실질적으로 저장된 원소의 길이 = LIS인 1차원 dp테이블을 만든다.
		// 해당 dp에 저장된 원소(0이 아닌 값)들은 이후 조사하는 원소들이 부분 수열을 늘릴 수 있을지에 대한 정보를 제공한다.
		dp = new int[N];
		// 처음에 저장된 원소는 없으므로, LIS = 0이다.
		int LIS = 0;

		// 첫 번째 원소부터 N번째 원소까지 dp 테이블의 값을 채워 나간다.
		for (int i = 0; i < N; i++) {
			// 이분 탐색을 활용하여 dp테이블에 저장된 원소를 탐색하며 현재 선택된 숫자가 dp테이블의 어떤 위치에 포함될지 파악한다.
			int idx = BinarySearch(arr[i], 0, LIS, LIS + 1);
			
			// 찾지 못한 경우
			if(idx == -1) {
				// 가장 마지막 위치에 원소를 삽입하고 LIS의 길이를 늘린다.
				dp[LIS++] = arr[i];
			}
			// 찾은 경우
			else {
				// 해당 위치에 현재 값을 삽입하여 갱신한다.
				dp[idx] = arr[i];
			}
		}
		
		// LIS의 길이를 출력한다.
		System.out.println(LIS);

		sc.close();
	}

	private static int BinarySearch(int num, int start, int end, int size) {
		int res = 0;
		while (start <= end) {
			// 중앙 값을 찾는다.
			int mid = (start + end) / 2;

			// 만일 현재 선택된 원소가 해당 원소보다 작거나 같다면, 앞 부분을 탐색한다.
			if (num <= dp[mid]) {
				// 해당 원소의 위치를 기억해둔다.
				res = mid;
				end = mid - 1;
			}
			// 만일 현재 선택된 원소가 해당 원소보다 크다면, 뒷 부분을 탐색한다.
			else {
				start = mid + 1;
			}
		}

		// dp테이블에서 삽입될 위치를 찾지 못한 경우(즉, 모든 수들보다 큰 경우).
		if (start == size) {
			return -1;
		}
		// dp테이블에서 삽입될 위치를 찾은 경우.
		else {
			return res;
		}
	}
}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 처음 보는 유형이라 공부를 더 해봐야 겠다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간 / 148ms / 15468KB / 946B

알고리즘 분류

- dp