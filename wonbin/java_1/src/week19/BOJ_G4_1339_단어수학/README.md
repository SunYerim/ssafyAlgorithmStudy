# [BOJ][G4] 단어수학 / (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/1339)]

[문제 내용 전체]

민식이는 수학학원에서 단어 수학 문제를 푸는 숙제를 받았다.

단어 수학 문제는 N개의 단어로 이루어져 있으며, 각 단어는 알파벳 대문자로만 이루어져 있다. 이때, 각 알파벳 대문자를 0부터 9까지의 숫자 중 하나로 바꿔서 N개의 수를 합하는 문제이다. 같은 알파벳은 같은 숫자로 바꿔야 하며, 두 개 이상의 알파벳이 같은 숫자로 바뀌어지면 안 된다.

예를 들어, GCF + ACDEB를 계산한다고 할 때, A = 9, B = 4, C = 8, D = 6, E = 5, F = 3, G = 7로 결정한다면, 두 수의 합은 99437이 되어서 최대가 될 것이다.

N개의 단어가 주어졌을 때, 그 수의 합을 최대로 만드는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 단어의 개수 N(1 ≤ N ≤ 10)이 주어진다. 둘째 줄부터 N개의 줄에 단어가 한 줄에 하나씩 주어진다. 단어는 알파벳 대문자로만 이루어져있다. 모든 단어에 포함되어 있는 알파벳은 최대 10개이고, 수의 최대 길이는 8이다. 서로 다른 문자는 서로 다른 숫자를 나타낸다.

## 출력

첫째 줄에 주어진 단어의 합의 최댓값을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음에는 문제를 잘못이해하고 풀어서 좀 해맸었다 ㅋㅋ

어떻게 숫자를 부여하면 좋을지 생각해보았는데 글자 길이가 큰 거부터 숫자를 부여하면 될거 같아서 시도를 해보았는데 구현하다가 막혔었다. 그래서 힌트를 좀 보았는데 굳이 정렬을 할 필요 없이 각 문장 마다 점수를 부여해서 그 점수를 합산 후 점수가 큰 순서대로 숫자를 부여해주면 되는 거였다.

1. 알파벳 배열을 만들어서 각 문장의 위치에 있는 알파벳 인덱스 값에 점수를 부여한다 → 10^(문장 길이 -1)
2. 점수 부여가 끝나면 정렬을 한다.
3. 그 후 높은 순서대로 숫자를 곱해서 해답을 도출한다.

---

> **코드**
> 

```java
package week19.BOJ_G4_1339_단어수학;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_G4_1339_단어수학 {
	
	static int N;
	static int[] alpha;
	static String[] arr;
	static int[] point;
	static int[] count;
	static long answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int idx = 0;
		int check = 0;
		int number = 9;
		arr = new String[N];
		point = new int[N];
		count = new int[N];
		alpha = new int[100];
		
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			arr[i] = s;
		}
		
		for(int i=0;i<N;i++) {
			int size = arr[i].length();
			int tmp = size;
			
			for(int j=0;j<size;j++) {
				double len = Math.pow(10, tmp-1);
				idx = arr[i].charAt(j);
				alpha[idx] += (int)len;
				tmp--;
				if(tmp<=0)
					tmp = 0;
			}
		}
		
		Arrays.sort(alpha);
		
		for(int i=99;i>=0;i--) {
			if(alpha[i] == 0)
				break;
			else {
				answer += alpha[i] * number;
				number--;
			}
		}
		
		System.out.println(answer);
	}
}

```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 생각하는 힘을 기르는거 같아 좋았다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 2시간 이상..? ㅎㅎ; / 14356KB / 128ms / 1132KB

알고리즘 분류
• [그리디 알고리즘](https://www.acmicpc.net/problem/tag/33)