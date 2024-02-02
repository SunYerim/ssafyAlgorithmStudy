<h3 align="center"> 
    📢  [골드4] 백준(부분합) : https://www.acmicpc.net/problem/1806
</h3>

<br>

## 🚀 문제

10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다. 이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.

---

## 🚦입출력 + 제한사항

- 첫째 줄에 N (10 ≤ N < 100,000)과 S (0 < S ≤ 100,000,000)가 주어진다. 둘째 줄에는 수열이 주어진다. 수열의 각 원소는 공백으로 구분되어져 있으며, 10,000이하의 자연수이다.
- 첫째 줄에 구하고자 하는 최소의 길이를 출력한다. 만일 그러한 합을 만드는 것이 불가능하다면 0을 출력하면 된다. 

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 요즘 부분합 문제 많이풀어서, 제목보고 헐레벌떡 누적합 배열부터 생각하긴 함
- 그리고 부분집합으로 모든 경우의수를 ~ 하려다가 N길이가 100,000이면 안되겠다고 생각함
- 그리고 우연찮게 알고리즘 분류에 투포인터라 돼 있는걸 봐버려서.. 투포인터로 바로 접근했다
- 근데 투포인터 사용에도 꽤나 애를 먹었다. 부분집합을 투포인터로 하는거니까, 그거에 맞춰서 사용해주어야 했는데, 잘못했던건 코드에 적어놓겠다

- [x] 투포인터를 사용하여 부분집합 추출

### 💻코드

```java
/* 투포인터로 돌리기 */
int leftPointer = 0;
int rightPointer = 1;
int minLength = accArray.length - 1;
/* 모든수의 합이 S보다 작은 경우 : 그러한 합을 만드는 것이 불가한 상황*/
if (accArray[accArray.length - 1] < S) {
	System.out.println(0);
	return;
}

/* 1. 잘못된 풀이, 왼쪽에서 오는거, 오른쪽에서 오는거, 서로 한칸씩 오는거 하면 모든 경우의 수가 추출된다고 생각했다. 근데 이러면 중간에서 만나는 친구들을 다 못구함 */
while(leftPointer < rightPointer) {
	if((accArray[rightPointer] - accArray[leftPointer]) >= S) {
		if((rightPointer - leftPointer) < minLength) {
			minLength = rightPointer - leftPointer;	
		}
	}
	leftPointer++;
}
leftPointer = 0;
while(leftPointer < rightPointer) {
	if((accArray[rightPointer] - accArray[leftPointer]) >= S) {
		if((rightPointer - leftPointer) < minLength) {
			minLength = rightPointer - leftPointer;	
		}
	}
	rightPointer--;
}
rightPointer = accArray.length - 1;
while(leftPointer < rightPointer) {
	if((accArray[rightPointer] - accArray[leftPointer]) >= S) {
		if((rightPointer - leftPointer) < minLength) {
			minLength = rightPointer - leftPointer;	
		}
	}
	if((accArray[rightPointer - 1] - accArray[leftPointer]) >= S) {
		if((rightPointer - leftPointer) < minLength) {
			minLength = rightPointer - leftPointer;	
		}
	}
	if((accArray[rightPointer] - accArray[leftPointer + 1]) >= S) {
		if((rightPointer - leftPointer) < minLength) {
			minLength = rightPointer - leftPointer;	
		}
	}
	rightPointer--;
	leftPointer++;
}
/* 2. 올바른 접근 : 서로 첫 인덱스에서 시작하여서, 하나를 옮기다가 S보다 커지면 다른걸 옮기고, 작아지면 다시 다른걸 옮기는 방식으로 전체 탐색 */
while(rightPointer < accArray.length) {
	/* 부분합이 S보다 크다면? minLength 길이 비교하고 갱신*/
	if (accArray[rightPointer] - accArray[leftPointer] >= S) {
		minLength = (minLength > rightPointer - leftPointer) ? rightPointer - leftPointer : minLength;
		leftPointer++; // S보다 큰 지점까지 왔으니, leftPointer를 옮기며 어디까지 길이 단축이 되나 확인
	} else {
		rightPointer++; // S보다 작아지면, 다시 rightPointer를 옮겨서 S보다 커지는 시점 찾기
	}
}
```

### 🙄 후기
소요시간 : 1시간30분  <br>

이전이면 누적합 배열 생각하는거도 걸렸겠지만, 이젠 누적합 배열은 1초만에 생각한다 <br>
발전이긴 발전인데 ~ 아직 많이 부족하다, 이런 접근법은 문제를 해결하는 원론적인 해결책을 먼저 생각하고? 그걸 코드에 옮기려고 하면 투포인터를 바로 생각했을거 같은데.. <br>
확실히 알고리즘 풀땐, 문제를 잘 읽고, 문제에 대해서 충분히 분석한 이후에 코드로 풀어내는 과정을 필히 거쳐야 하는 것 같다 <br>
그리고 이 코드 만지면서 느낀건데, 정답 맞췄다고 끝내지말고, 개선점을 찾자. <br>
물론 시간복잡도 계산은 말할것도 없이 당연히 해야되지만, 꼭 시간, 메모리적인 개선이 아니더라도 코드의 가독성에 대한 고민도 좋은 것 같다. 중복되는 라인이나 굳이 불필요한 라인을 찾아내고 없애는 과정을 꼭 거쳐내자. 그거까지가 문제풀이의 끝이다