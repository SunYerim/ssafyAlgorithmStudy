# [BOJ][G5] 부분삼각수열/ (WA)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/1548)]

[문제 내용 전체]

## 문제

세 수 x, y, z가 x+y>z, x+z>y, y+z>x의 관계를 만족하면, 세 수는 삼각관계에 있다고 한다.

마찬가지로 길이가 N인 수열 B(b[0], b[1], ..., b[n-1])의 모든 b[i], b[j], b[k]가 삼각관계에 있으면 이 수열은 삼각 수열이라고 한다. 이때, i, j, k는 모두 다른 값이다.

수열 A가 주어졌을 때, 이 수열에서 적절히 몇 개의 원소를 빼서 이 수열을 삼각 수열로 만들려고 한다. 삼각 수열의 최대 길이를 구하는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 수열의 크기 N이 주어진다. 둘째 줄에 수열 A에 들어있는 수가 공백을 사이에 두고 주어진다. N은 최대 50이고, A에 들어있는 수는 109보다 작거나 같은 자연수이다.

## 출력

첫째 줄에 가장 긴 부분 삼각 수열의 길이를 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

뭔가 번뜩하는 아이디어가 떠오르지 않아서 그냥 전부 탐색해보자 라는 마인드로 구현해 보았다.

triangle이라는 3개의 변수를 저장하는 클래스를 작성하여 각 a, b, c의 인덱스를 저장할 수 있는 모든 경우의 수를 저장한 후 만약 같은 부분이 없으면 카운팅을 하는 방식으로 시도해 보았다.

하지만 내가 생각한거 보다 훨씬 많은 경우의 수가 카운팅이 되었다. 디버깅을 해보았지만 해결할 방법이 떠오르지 않아 현재의 상태에 머물러 있다.

---

> **코드**
> 

```java
package week2.BOJ_1548_부분삼각수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class triangle{
	int a1;
	int b1;
	int c1;
	
	triangle(int a1, int b1, int c1){
		this.a1 = a1;
		this.b1 = b1;
		this.c1 = c1;
	}

	@Override
	public String toString() {
		return "triangle [a1=" + a1 + ", b1=" + b1 + ", c1=" + c1 + "]";
	}
	
}

public class BOJ_1548_부분삼각수열 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		String[] s2 = s.split(" ");
		int answer = 0;
		long a;
		long b;
		long c;
		int tmp =0;
		List<triangle> list = new ArrayList<>();
		long[] arr = new long[N];
		
		for(int i=0;i<N;i++) {
			arr[i] = Long.parseLong(s2[i]);
		}
		
		if(N == 1) answer =1;
		else if(N==2) answer =2;
		else {
			for(int i=0;i<N;i++) {
				c = arr[i];
				arr[i] = 0;
				for(int j=0;j<N - 1;j++) {
					a=arr[j];
					if(arr[j] == 0) continue;
					for(int k=j+1;k<N;k++) {
						b = arr[k];
						if(arr[k] == 0) continue;
						else {
							if(a+b>c) {
								if(list.isEmpty()) {
									list.add(new triangle(j,k,i));
									list.add(new triangle(k,j,i));
									list.add(new triangle(j,i,k));
									list.add(new triangle(k,i,j));
									list.add(new triangle(i,j,k));
									list.add(new triangle(i,k,j));
									answer++;
								}
								else {
									for(triangle tri : list) {
										if(new triangle(j,k,i).equals(tri)) {
											tmp =1;
											break;
										}
									}
									if(tmp == 0) {
										list.add(new triangle(j,k,i));
										list.add(new triangle(k,j,i));
										list.add(new triangle(j,i,k));
										list.add(new triangle(k,i,j));
										list.add(new triangle(i,j,k));
										list.add(new triangle(i,k,j));
										answer++;
									}
									else tmp=0;
								}
							}
						}
					}
				}
				arr[i] = c;
			}
		}
		
		sb.append(answer);
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