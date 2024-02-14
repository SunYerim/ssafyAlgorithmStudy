[백준][G1] 1060 좋은 수 / (AC)
---
> **문제 설명**
>

[[문제 링크](https://www.acmicpc.net/problem/1060)]

정수 집합 S가 주어졌을때, 다음 조건을 만족하는 구간 [A, B]를 좋은 구간이라고 한다.

- A와 B는 양의 정수이고, A < B를 만족한다.
- A ≤ x ≤ B를 만족하는 모든 정수 x가 집합 S에 속하지 않는다.

정수 x를 포함하는 좋은 구간의 개수가 정수 y를 포함하는 좋은 구간의 개수보다 작으면 x는 y보다 더 좋다고 한다. x와 y를 포함하는 좋은 구간의 개수가 같거나, 구간의 개수가 둘 다 무한대와 같은 경우, 작은
수를 더 좋다고 한다.

집합 S가 주어지고, 이를 이용해 전체 정수를 더 좋은 수가 앞으로 오게 정렬했다고 가정하자. 앞에 오는 수 n개를 구해보자.


---

> **제한사항**
>

**입력**

첫째 줄에 집합 S의 크기 L이 주어진다. 둘째 줄에는 집합에 포함된 정수가 주어진다. 셋째 줄에는 n이 주어진다.

**출력**

상위 N개의 수를 공백으로 구분해 출력한다.

**제한**

- 1 ≤ L ≤ 50
- 집합 S에는 중복되는 정수가 없다.
- 집합 S에 포함된 모든 정수는 1보다 크거나 같고, 1,000,000,000보다 작거나 같다.
- 1 ≤ n ≤ 100

---

> **문제 풀이**

문제를 해석하는 것 자체가 어려운데, 임의의 수 `x`에 대해서 `좋은 수` 기준으로 정렬하여 출력하는 문제이다.

`좋은 수`란 다음을 기준으로 한다.

1. `좋은 구간`의 개수가 적을 수록
2. `좋은 구간`의 개수가 서로 같은 수라면, 수가 낮을 수록

여기서 `좋은 구간`이란, 집합 `S`에 포함되지 않는 임의의 두 정수 `A`, `B` (`A < B`)사이에 모든 임의의 수 `x`가 집합 `S`에 포함되지 않는 구간을 말한다.
즉, 정렬된 집합 `S`에서 이웃한 두 수 사이(폐구간)에 존재하는 두 정수 `A`,`B`의 순서쌍을 찾고, 어떤 수 `x`를 포함하는 순서쌍이 몇개인지를 찾는다면 `좋은 구간`의 개수를 판별할 수 있다.

말이 매우 복잡한데, 예를 들어보자.

집합 `S`가 `{1, 3, 6}`이라 하면, `1, 3, 6`은 모두 `좋은 구간`을 가질 수 없는 `x`이므로, 가장 `좋은 수`들 이다. 또, `1`과 `3` 사이의 `A`,`B` 또한 유효한 `좋은 구간`을
만들어 낼 수 없기 때문에, `2` 또한 가장 `좋은 수` 이다.

`3`과 `6` 사이에는 `4`, `5`가 존재하는데 이는 각각 `좋은 구간`을 하나씩 가지므로 앞서 말한 수들 다음으로 `좋은 수` 이다.

`6` 보다 큰 수 `7 ~ ∞`까지는 모두 `좋은 구간`을 무한 개 가지므로, 우선 순위가 가장 낮다.

앞선 예시처럼, 정렬된 집합 `S`에서 각 구간 사이의 수들을 `좋은 구간`의 개수와 함께 자료구조로 저장한 뒤, 문제에서 주어진 정렬 조건을 통해 정렬하여, `L`개 만큼 출력하면 된다. 여기서 `L`은 최대
50까지 주어지므로, 각 구간에서 최대 50개 까지만 추출하여 자료구조로 저장하면 된다.

구간의 시작점을 `s`, 끝점을 `e`라고 할 때, `s + i`(`i>1`)의 `좋은 구간`을 구하는 공식은 `i * (e - s - i) - 1`을 따른다. (`s + i`를 기준으로 나뉘어지는 두 구간 내에서
각각 임의의 `A`, `B`를 뽑는 조합의 개수와 같음)


---

> **코드**
>

```java
package BOJ1060;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();

		int L = Integer.parseInt(in.readLine());
		List<Integer> numbers = new ArrayList<>();
		numbers.add(0);
		String[] split = in.readLine().split(" ");
		for (int i = 0; i < L; i++) {
			numbers.add(Integer.parseInt(split[i]));
		}
		int N = Integer.parseInt(in.readLine());

		Collections.sort(numbers);

		PriorityQueue<Num> queue = new PriorityQueue<>((o1, o2) -> {
			if (o1.length != o2.length) {
				return o1.length > o2.length ? 1 : o1.length < o2.length ? -1 : 0;
			}
			return o1.num - o2.num;
		});
		for (int i = 0; i < L; i++) {
			queue.offer(new Num(numbers.get(i + 1), 0));
		}

		for (int i = 0; i < L; i++) {
			for (Num num : Num.makeNum(numbers.get(i), numbers.get(i + 1))) {
				queue.offer(num);
			}
		}
		while (!queue.isEmpty() && N > 0) {
			N--;
			out.append(queue.poll().num).append(" ");
		}
		if (N > 0) {
			for (int i = 1; i <= N; i++) {
				out.append(numbers.get(L) + i).append(" ");
			}
		}
		System.out.println(out);
	}
}

class Num {
	public int num;
	public long length;

	public Num(int num, long length) {
		this.num = num;
		this.length = length;
	}

	public static List<Num> makeNum(int s, int e) {
		List<Num> result = new ArrayList<>();
		if ((e - s) > 100) {
			for (int i = 1; i <= 50; i++) {
				result.add(new Num(s + i, (long) i * (e - s - i) - 1));
				result.add(new Num(e - i, (long) i * (e - s - i) - 1));
			}
		} else {
			for (int i = 1; i < e - s; i++) {
				result.add(new Num(s + i, i * (e - s - i) - 1));
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return "[n:" + num + ", l:" + length + "]";
	}
}
```

---

> **후기**

`우선순위 큐`에 새로운 `자료구조`를 정의하여 정렬 기준을 두고 구현하였다. 한번만 출력하면 되기 때문에 사실 큐가 아니라 리스트여도 큰 문제는 없을 듯하다.

문제 풀이 시간 : 2시간 / 실행시간 : `136ms` / 메모리 : `14624KB` / 코드길이 : `1877B`

알고리즘 분류 : 수학, 자료구조, 정렬, 우선순위 큐