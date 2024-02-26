<h3 align="center"> 
    📢  [A형대비] SWEA(활주로 건설) : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeW7FakkUDFAVH&categoryId=AWIeW7FakkUDFAVH&categoryType=CODE&problemTitle=%EB%AA%A8%EC%9D%98&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
</h3>

<br>

## 🚀 문제

너무 길어서 설명 보세요

---

## 🚦입출력 + 제한사항

1. 시간제한 : 최대 50 개 테스트 케이스를 모두 통과하는 데 C / C++ / Java 모두 3 초
2. N 의 크기는 6 이상 20 이하의 정수이다. ( 6 ≤ N ≤ 20 )
3. 경사로의 높이는 항상 1 이고, 길이 X 는 2 이상 4 이하의 정수이다. ( 2 ≤ X ≤ 4 )
4. 지형의 높이는 1 이상 6 이하의 정수이다.
5. 동일한 셀에 두 개 이상의 경사로를 겹쳐서 사용할 수 없다.
( 아래 [Fig. 10] 과 같은 경우는 경사로를 설치하여 활주로를 연결 할 수 없다. )

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 그냥 개 빡구현
- 근데 누적합을 이용해서 조금 줄여볼까 했음.(시간이 줄어든건지 모르겠지만..)
- 1차이가 나는 인덱스를 만났을때, 다시 뒤로 X길이 만큼 탐색하는게 아니라, 누적합을 이용해서 확인함
- 그리고 visited 역할 또한 누적합이 해주는데, 방문해서 경사로를 설치한 곳엔 누적합을 -1로 바꿔서 -1인 곳에 설치하려하면 불가능을 뱉는다.

- [x] 누적합 배열을 통해 경사로 설치 가능 여부 확인 및 기록

### 💻코드

```java
for(int i = 0; i < N; i++) {
	/* 배열을 행 또는 열 별로 구분하여 처리하고 초기화(누적합도) */
	for(int j = 0; j < N; j++) {
		arrayRowCol[j] = array[i][j];
		if(j == 0) {
			accArray[j] = 1;
		} else {
			accArray[j] = (arrayRowCol[j]==arrayRowCol[j-1]) ? accArray[j-1]+1 : 1;
		}
	}
	/* 배열이 모든 같은 값이라면 무조건 가능 */
	if(accArray[accArray.length-1] == accArray.length) {
		cnt++;
		continue;
	}
	boolean flag = false;
	for(int k = 1; k < N; k++) {
		if(flag) break;
		if(Math.abs(arrayRowCol[k] - arrayRowCol[k-1]) > 1) { 
			flag = true; 
			break;
		}
		else if(Math.abs(arrayRowCol[k] - arrayRowCol[k-1]) == 1) {
			if(arrayRowCol[k] > arrayRowCol[k-1]) {
				if(accArray[k-1] < X) {
					flag = true; 
					break;
				}
				for(int q = k-1; q > k-1-X; q--) {
					if(accArray[q] == -1) {
						flag = true;
						break;
					}
					accArray[q] = -1;
				}
			} else {
				if(k+X-1 >= N) {
					flag = true;
					break;
				}
				if(accArray[k+X-1] != X) {
					flag = true;
					break;
				}
				for(int q = k; q < k+X; q++) {
					if(accArray[q] == -1) {
						flag = true;
						break;
					}
					accArray[q] = -1;
				}
			}
		}
	}
	if(!flag) cnt++;
}
```

### 🙄 후기
소요시간 : 1박2일? <br>
개같은 구현.. <br>
이런 더럽고 가지 쳐야할 경우의 수가 많으면 많을수록 문제 정리와 설계를 똑바로 하고 손가락을 키보드에 갖다대야한다.<br>
이런 문제 뇌빼고 키보드에 손 갖다대는건 자살행위나 다름없다 ..