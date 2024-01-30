# [BOJ][S3] 2Xn타일링 / (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/11726)]

[문제 내용 전체]

2×n 크기의 직사각형을 1×2, 2×1 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.

아래 그림은 2×5 크기의 직사각형을 채운 한 가지 방법의 예이다.

!https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/11726/1.png

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)

## 출력

첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음에는 이게 무슨 문제인가 싶어서 테스트케이스를 1번 부터 손코딩을 해보았는데 결과값을 적다보니 피보나치 수열과 매우 매우 흡사하게 생겨서 계산을 해보니까 피보나치 수열이 맞았다.

그래서 처음엔 재귀 형태로 피보나치를 구현해서 돌려봤는데 숫자가 100 정도 까지만 가도 시간이 너무 오래 걸려서 풀리지가 않았다. 그래서 더 빠르게 피보나치를 구현할 수 없나 생각하던 도중 점화식을 찾아서 문제를 해결하는 dp 해결법을 본 기억이 났었다. 그래서 찾아서 문제를 풀었는데 실패가 떠서 무슨 반례가 있나.. 찾아봤는데 long은 생각보다 어어엄청 큰 수를 담지는 못하는거 같았다. 그래서 계산 할때 마다 10007을 나누어서 오버플로우가 나는것을 방지하였다.

---

> **코드**
> 

```java
package BOJ.BOJ_11726_2Xn타일링;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_11726_2Xn타일링 {
	
	private static long[] fibo_arr = new long[1004];

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		long result = 0;
		
		fibo_arr[0] = 1;
		fibo_arr[1] = 1;
		
		result  = fibo(n);
		
		bw.write(result +"" + '\n');
		bw.flush();
		bw.close();
		
	}
	
	private static long fibo(int n) {
		
		if(fibo_arr[n] != 0) {
			return fibo_arr[n];
		}
		else if(n < 2) {
			return 1;
		}
		else {
			fibo_arr[n] =  (fibo(n-2) + fibo(n-1)) % 10007;
		}
		
		return fibo_arr[n];
	}
}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 문제 접근방식만 찾으면 진짜 쉬운 문제인거 같다
- 메모라이즈 기법을 배웠다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 30분/144ms/15948KB/869B

알고리즘 분류

• [다이나믹 프로그래밍](https://www.acmicpc.net/problem/tag/25)