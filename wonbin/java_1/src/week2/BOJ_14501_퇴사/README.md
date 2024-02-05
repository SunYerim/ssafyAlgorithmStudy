# [백준][S3] 퇴사 / (AC)

---

> **문제 설명**
> 

[[문제 링크](https://www.acmicpc.net/problem/14501)]

[문제 내용 전체]

상담원으로 일하고 있는 백준이는 퇴사를 하려고 한다.

오늘부터 N+1일째 되는 날 퇴사를 하기 위해서, 남은 N일 동안 최대한 많은 상담을 하려고 한다.

백준이는 비서에게 최대한 많은 상담을 잡으라고 부탁을 했고, 비서는 하루에 하나씩 서로 다른 사람의 상담을 잡아놓았다.

각각의 상담은 상담을 완료하는데 걸리는 기간 Ti와 상담을 했을 때 받을 수 있는 금액 Pi로 이루어져 있다.

N = 7인 경우에 다음과 같은 상담 일정표를 보자.

|  | 1일 | 2일 | 3일 | 4일 | 5일 | 6일 | 7일 |
| --- | --- | --- | --- | --- | --- | --- | --- |
| Ti | 3 | 5 | 1 | 1 | 2 | 4 | 2 |
| Pi | 10 | 20 | 10 | 20 | 15 | 40 | 200 |

1일에 잡혀있는 상담은 총 3일이 걸리며, 상담했을 때 받을 수 있는 금액은 10이다. 5일에 잡혀있는 상담은 총 2일이 걸리며, 받을 수 있는 금액은 15이다.

상담을 하는데 필요한 기간은 1일보다 클 수 있기 때문에, 모든 상담을 할 수는 없다. 예를 들어서 1일에 상담을 하게 되면, 2일, 3일에 있는 상담은 할 수 없게 된다. 2일에 있는 상담을 하게 되면, 3, 4, 5, 6일에 잡혀있는 상담은 할 수 없다.

또한, N+1일째에는 회사에 없기 때문에, 6, 7일에 있는 상담을 할 수 없다.

퇴사 전에 할 수 있는 상담의 최대 이익은 1일, 4일, 5일에 있는 상담을 하는 것이며, 이때의 이익은 10+20+15=45이다.

상담을 적절히 했을 때, 백준이가 얻을 수 있는 최대 수익을 구하는 프로그램을 작성하시오.

---

> **제한사항**
> 

입력/출력 제한사항

## 입력

첫째 줄에 N (1 ≤ N ≤ 15)이 주어진다.

둘째 줄부터 N개의 줄에 Ti와 Pi가 공백으로 구분되어서 주어지며, 1일부터 N일까지 순서대로 주어진다. (1 ≤ Ti ≤ 5, 1 ≤ Pi ≤ 1,000)

## 출력

첫째 줄에 백준이가 얻을 수 있는 최대 이익을 출력한다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

처음에는 첫번째 일감부터 하나 하나 더하면서 나오는 최대의 페이를 계산하는 방식으로 코드를 작성하였다.

하지만 그렇게 하니 지금의 일정을 소화하지 않고 다음날의 일정을 소화하는 경우의 가짓수를 계산하지 못하였다.

그래서 고민을 하다가 이전에 배웠던 부분함수 재귀가 생각나서 그 방법으로 풀면 완전탐색이 가능하여 해결이 될거 같아 시도하였다.

하지만 내가 원하는 방식대로 흘러가지 않았다. 여러 방법으로 계속 재시도를 해보고 공책에 적어가며 진행해 보았지만 뭔가 내방식이 매끄럽게 흘러가지 않았다 방법은 맞는거 같은데 머리가 멍청한건지 계속 시도를 해도 안되어서 풀이를 참고 해 보았다.

---

> **코드**
> 

내가 시도한 코드

```java
package week2.BOJ_14501_퇴사;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

class Pair{
	int ti;
	int pi;
	
	Pair(int ti, int pi){
		this.ti = ti;
		this.pi = pi;
	}
}

public class BOJ_14501_퇴사 {
	
	static int tmp;
	static int day = 0;
	static int pay = 0;
	static int max = Integer.MIN_VALUE;
	static List<Pair> pairs;
	static int N;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine()); // 일하는 날짜 수 입력
		pairs = new ArrayList<>();
		
		for(int i=0; i<N;i++) {
			String s = br.readLine();
			String[] s2 = s.split(" ");
			Pair pr = new Pair(Integer.parseInt(s2[0]), Integer.parseInt(s2[1]));
			pairs.add(pr);
		} // 각 요일마다 일하는 기간 및 얻는 돈의 양 입력 pair에 저장
		
		run(0,0);
		
		bw.write(max+""+"\n");
		bw.flush();
		bw.close();
	}
	
	public static void run(int cnt, int money) {
		if(cnt>=N -1) {
			if(cnt == N -1) {
				if(pairs.get(cnt).ti == 1) money += pairs.get(cnt).pi;
				pay = money;
				max = Math.max(pay, max);
				return;
			}
			else {
				pay = money;
				max = Math.max(pay, max);
				return;
			}
		}
		
		money += pairs.get(cnt).pi;
		cnt += pairs.get(cnt).ti;
		run(cnt, money);
		cnt -= pairs.get(cnt).ti;
		money -= pairs.get(cnt).pi;
		run(++cnt, money);
	}
}
```

참고한 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n, answer = 0;
    private static int[] T, P;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        T = new int[n];
        P = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        System.out.println(answer);
    }

    private static void dfs(int index, int value) {
        if (index >= n) {
            answer = Math.max(answer, value);
            return;
        }

        // 해당 index를 포함
        if (index + T[index] <= n) dfs(index + T[index], value + P[index]);
        else dfs(index + T[index], value); // n을 넘어가면 value 합치지 않음

        dfs(index + 1, value); // 해당 index 미포함
    }
}
```

출처:

https://leveloper.tistory.com/91

[꾸준하게:티스토리]

수정한 코드

```java
package week2.BOJ_14501_퇴사;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

class Pair{
	int ti;
	int pi;
	
	Pair(int ti, int pi){
		this.ti = ti;
		this.pi = pi;
	}
}

public class BOJ_14501_퇴사 {
	
	
	static int tmp;
	static int day = 0;
	static int pay = 0;
	static int max = Integer.MIN_VALUE;
	static List<Pair> pairs;
	static int N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine()); // 일하는 날짜 수 입력
		pairs = new ArrayList<>();
		
		for(int i=0; i<N;i++) {
			String s = br.readLine();
			String[] s2 = s.split(" ");
			Pair pr = new Pair(Integer.parseInt(s2[0]), Integer.parseInt(s2[1]));
			pairs.add(pr);
		} // 각 요일마다 일하는 기간 및 얻는 돈의 양 입력 pair에 저장
		
		run(0,0);
		
		bw.write(max+""+"\n");
		bw.flush();
		bw.close();
	}
	
	public static void run(int cnt, int money) {
		if(cnt>=N) {
			max = Math.max(max, money);
			return;
		}
		
		if(cnt + pairs.get(cnt).ti<=N) run(cnt + pairs.get(cnt).ti, money + pairs.get(cnt).pi); // N을 넘지 않으면 계속 더함
		else run(cnt + pairs.get(cnt).ti, money); // 만약 넘으면 money 합치지 않음
		
		run(cnt+1, money); // 해당 index 미포함
	}

}
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- 아니 진짜 진짜 거의 다 했는데 이렇게 답지를 참고해서 너무 억울하다
- 내가 못한거니까 이번기회에 브루트포스를 다시 공부해야겠다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 아이디어를 떠올리는건 20분? 풀이는 답지 참조/ 160ms / 16160KB / 1505B

알고리즘 분류

- [다이나믹 프로그래밍](https://www.acmicpc.net/problem/tag/25)
- [브루트포스 알고리즘](https://www.acmicpc.net/problem/tag/125)