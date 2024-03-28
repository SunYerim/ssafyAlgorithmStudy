# [BOJ][S2] 랜선자르기/ (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/1654)]

[문제 내용 전체]

## 문제

집에서 시간을 보내던 오영식은 박성원의 부름을 받고 급히 달려왔다. 박성원이 캠프 때 쓸 N개의 랜선을 만들어야 하는데 너무 바빠서 영식이에게 도움을 청했다.

이미 오영식은 자체적으로 K개의 랜선을 가지고 있다. 그러나 K개의 랜선은 길이가 제각각이다. 박성원은 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶었기 때문에 K개의 랜선을 잘라서 만들어야 한다. 예를 들어 300cm 짜리 랜선에서 140cm 짜리 랜선을 두 개 잘라내면 20cm는 버려야 한다. (이미 자른 랜선은 붙일 수 없다.)

편의를 위해 랜선을 자르거나 만들 때 손실되는 길이는 없다고 가정하며, 기존의 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다고 가정하자. 그리고 자를 때는 항상 센티미터 단위로 정수길이만큼 자른다고 가정하자. N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다. 이때 만들 수 있는 최대 랜선의 길이를 구하는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에는 오영식이 이미 가지고 있는 랜선의 개수 K, 그리고 필요한 랜선의 개수 N이 입력된다. K는 1이상 10,000이하의 정수이고, N은 1이상 1,000,000이하의 정수이다. 그리고 항상 K ≦ N 이다. 그 후 K줄에 걸쳐 이미 가지고 있는 각 랜선의 길이가 센티미터 단위의 정수로 입력된다. 랜선의 길이는 231-1보다 작거나 같은 자연수이다.

## 출력

첫째 줄에 N개를 만들 수 있는 랜선의 최대 길이를 센티미터 단위의 정수로 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음에는 일반적인 이분탐색을 활용해서 풀었는데 중복되는 값들을 처리하는 방법이 떠오르지 않았다. 고민을 하다가 안풀려서 결국 정답을 봤는데 upper_bound 형식으로 처리를 하는 방법이었다. 이분탐색을 하지만 하는 과정에서 중복되는 값들이 있으면 그중 최대의 값. 즉 답이 될 수 있는 최대한의 수를 구하는 방식을 활용해서 문제를 해결하였다. 주의할점은 end 값을 최댓값 보다 1을 더 주어서 0으로 나누는 경우를 방지해야 한다.

---

> **코드**
> 

```java
package samsung;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class example {
	
	static int N;
	static int K;
	static long[] lan;
	static long count;
	static long start;
	static long end;
	static long mid;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		K = Integer.parseInt(s2[0]);
		N = Integer.parseInt(s2[1]);
		lan = new long[K];
		count = 0;
		start = 0;
		end = 0;
		
		
		for(int i=0;i<K;i++) {
			lan[i] = Integer.parseInt(br.readLine());
			end = Math.max(end, lan[i]);
		}
		
		end++;
		
		while(start<end) {
			
			mid = (start+end)/2;
			
			for(int i=0;i<K;i++) {
				count += lan[i]/mid;
			}
			
			if(count<N)
				end = mid;
			else {
				start = mid + 1;
			}
			
			count = 0;
		}
		
		start--;
		
		bw.append(start+"");
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

- 이분탐색은 다 알았다고 생각했는데 아직 알아야 할게 많다고 생각했다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 1시간 반 / 192ms / 17596KB / 1235B

알고리즘 분류

- [이분 탐색](https://www.acmicpc.net/problem/tag/12)
- [매개 변수 탐색](https://www.acmicpc.net/problem/tag/170)