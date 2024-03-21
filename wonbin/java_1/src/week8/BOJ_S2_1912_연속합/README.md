# [BOJ][S2] 연속합/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/1912)]

[문제 내용 전체]

## 문제

n개의 정수로 이루어진 임의의 수열이 주어진다. 우리는 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다. 단, 수는 한 개 이상 선택해야 한다.

예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자. 여기서 정답은 12+21인 33이 정답이 된다.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 정수 n(1 ≤ n ≤ 100,000)이 주어지고 둘째 줄에는 n개의 정수로 이루어진 수열이 주어진다. 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.

## 출력

첫째 줄에 답을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음에는 부분합을 구하면 되는구나 해서 부분합을 구하는 로직을 작성을 했는데 입력값이 100000만이라 전체 탐색을하면 100억번 반복해야해서 다른 방법을 생각해보다가 안떠올라서 공부했던 문제이다. 부분합을 갱신할때 마다 부분합을 적용한 것과 그냥 값을 넣는것을 비교하여 max값을 갱신해주면 풀리는 문제이다.

---

> **코드**
> 

```java
package week8.BOJ_S2_1912_연속합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_S2_1912_연속합 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int answer = 0;
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		for(int i=0;i<N;i++) {
			if(i == 0)
				arr[i] = Integer.parseInt(s2[i]);
			else {
				arr[i] = Math.max(Integer.parseInt(s2[i]) + arr[i-1] ,Integer.parseInt(s2[i]));
			}
			
		}
		
		Arrays.sort(arr);
		
		bw.append(arr[N-1]+"");
		bw.append("\n");
		bw.flush();
		bw.close();
	}

}

```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- dp는 안해보면 진짜 모르겠다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간 / 332ms / 25552KB / 863B

알고리즘 분류

- dp