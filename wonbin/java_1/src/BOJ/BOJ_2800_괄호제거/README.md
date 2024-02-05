# [백준][G5] 괄호 제거/ (WA)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/2800)]

어떤 수식이 주어졌을 때, 괄호를 제거해서 나올 수 있는 서로 다른 식의 개수를 계산하는 프로그램을 작성하시오.

이 수식은 괄호가 올바르게 쳐져 있다. 예를 들면, 1+2, (3+4), (3+4*(5+6))와 같은 식은 괄호가 서로 쌍이 맞으므로 올바른 식이다.

하지만, 1+(2*3, ((2+3)*4 와 같은 식은 쌍이 맞지 않는 괄호가 있으므로 올바른 식이 아니다.

괄호를 제거할 때는, 항상 쌍이 되는 괄호끼리 제거해야 한다.

예를들어 (2+(2*2)+2)에서 괄호를 제거하면, (2+2*2+2), 2+(2*2)+2, 2+2*2+2를 만들 수 있다. 하지만, (2+2*2)+2와 2+(2*2+2)는 만들 수 없다. 그 이유는 쌍이 되지 않는 괄호를 제거했기 때문이다.

어떤 식을 여러 쌍의 괄호가 감쌀 수 있다.

---

> **제한사항**
> 

첫째 줄에 음이 아닌 정수로 이루어진 수식이 주어진다. 이 수식은 괄호가 올바르게 쳐져있다. 숫자, '+', '*', '-', '/', '(', ')'로만 이루어져 있다. 수식의 길이는 최대 200이고, 괄호 쌍은 적어도 1개, 많아야 10개이다. / 올바른 괄호 쌍을 제거해서 나올 수 있는 서로 다른 식을 사전 순으로 출력한다.

---

> **문제 풀이**
> 

처음에는 ‘(’ 의 개수를 카운트 하여 1쌍 제거 되었을때 2쌍 제거 되었을때… 이런식의 집합으로 분리하여 해결하려고 하였다. 하지만 풀이가 난잡해지고 코드도 엄청 길어져서 이 방법은 포기하였다.

두번째 방법은 stack으로 문장을 받아서 괄호쌍이 될때마다 괄호를 지우고 그 가짓수를 나열하는(?) 방법을 생각했었는데 구현이 쉽지 않았다. 이 아이디어는 생각해낸지 조금 오래 되어서 정확히 기억이 나지 않는다.

세번째로 괄호의 인덱스값의 쌍을 2차원 배열에 넣어서 그 인덱스 값을 이용하여 재귀함수로 괄호를 지우는 방법을 생각해 내었다. 지금 와서 보니 이 방법이 틀린 방법이 아니라 맞는 방법이었다. 하지만 아직 내가 문자열과 리스트를 다루는 실력이 많이 안좋은거 같다. 아이디어는 좋았지만 구현을 하지 못하였다. 

그래서 답을 찾아서 참고를 하여 비교를 해보았는데 이를 통해서 문자열을 다루는 여러가지 방법도 알게 되었고 싸피에서 이번주에 배운 재귀에 대해서도 좀 더 공부를 할 수 있었다.

---

> **코드**
> 

내 코드

```java
package BOJ.BOJ_2800_괄호제거;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_2800_괄호제거 {
	
	private static int N; // N개 중
	private static int[] numbers;
	static String s;
	static LinkedList<Character> list = new LinkedList<>();
	static ArrayList<String> list_f;
	static int[][] d_idx;
	static HashSet<String> hash = new HashSet<>();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		s = br.readLine();
		int count = 0;
		int tmp = 0;
		
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i) == '(') {
				count++;
			}
		}
		
		d_idx = new int[count][2];
		numbers = new int[count];
		
		for(int i =0 ;i<s.length();i++) {
			if(s.charAt(i) == ')') {
				tmp--;
				d_idx[tmp][1] = i;
			}
			list.add(s.charAt(i));
			if(s.charAt(i) == '(') {
				d_idx[tmp][0] = i;
				tmp++;
			}
		}
		
		for(int i=0;i<count;i++) {
			numbers[i] = d_idx[i][1];
		}
		
		for(int i=0;i<count;i++) {
			N = i+1;
			combi(0,0);
		}
		
		list_f = new ArrayList<>(hash);
		
		Collections.sort(list_f);
		
		for(String li : list_f) {
			bw.write(li + '\n');
		}
		
		bw.flush();
		bw.close();
		
	}

	private static void combi(int cnt, int start) {

		if (cnt == N) {
			for(int i=0;i<cnt;i++) {
				String s2=s;
				s2.replace(s2.charAt(d_idx[i][0]) + "", "");
				s2.replace(s2.charAt(d_idx[i][1]) + "", "");
				hash.add(s2);
			}
			
			return;
		}

		// 시작점부터 가능한 끝까지 선택하는 시도
		for (int i = 0; i < N; i++) {
			combi(cnt + 1, i + 1); // 현재 선택한 수의 다음부터 선택하도록 시작점 주기!
		}
	}
}
```

참고한 코드

```java
package BOJ.BOJ_2800_괄호제거;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair {
	int left, right;

	public Pair(int left, int right) {
		this.left = left;
		this.right = right;
	}
}

public class BOJ_2800_괄호제거 {
	static List<Pair> pairs;
	static int pairCount;
	static Set<String> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		Stack<Integer> stk = new Stack<>();
		set = new TreeSet<>();

		pairs = new ArrayList<>();

		int inputSize = input.length();
		for (int i = 0; i < inputSize; i++) {
			char cur = input.charAt(i);
			if (cur == '(') {
				stk.add(i);
			} else if (cur == ')') {
				pairs.add(new Pair(stk.pop(), i));
			}
		}

		pairCount = pairs.size();
		makeString(0, input + "");

		// 입력값은 제거
		set.remove(input);

		StringBuilder answer = new StringBuilder();
		for (String s : set) {
			answer.append(s).append("\n");
		}

		System.out.print(answer);
	}

	static void makeString(int idx, String str) {
		if (idx == pairCount) {
			// 공백 제거 후 set에 add
			set.add(str.replaceAll(" ", ""));
			return;
		}

		// 현재 괄호를 제거
		Pair pair = pairs.get(idx);
		StringBuilder sb = new StringBuilder(str);
		sb.setCharAt(pair.left, ' ');
		sb.setCharAt(pair.right, ' ');
		makeString(idx + 1, sb.toString());

		// 제거 안함
		makeString(idx + 1, str);
	}
}
```

---

> **후기**
> 

참고 사이트

- [https://velog.io/@jh5253/백준-2800-괄호-제거-Java자바](https://velog.io/@jh5253/%EB%B0%B1%EC%A4%80-2800-%EA%B4%84%ED%98%B8-%EC%A0%9C%EA%B1%B0-Java%EC%9E%90%EB%B0%94)

문제에 대한 짧은 코멘트

- 이번 기회로 문자열을 다루는 여러가지 방법을 알게되었다.
- 아직 너무 많이 부족한 거 같다 더욱 공부를 해야겠다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이 : 풀이 실패

알고리즘 분류

- 스택
- 재귀
- 문자열