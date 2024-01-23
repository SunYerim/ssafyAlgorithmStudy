<h3 align="center"> 
    📢  [Lv2]프로그래머스(소수찾기) : https://school.programmers.co.kr/learn/courses/30/lessons/42839
</h3>

<br>

## 🚀 문제

한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.
각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.

---

## 🚦입출력 + 제한사항

- numbers는 길이 1 이상 7 이하인 문자열입니다.
- numbers는 0~9까지 숫자만으로 이루어져 있습니다.
- "013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.

---

### 📜 문제 풀이(기능 목록, 접근법)

- [x] permutation(순열)을 이용하여 모든 숫자 경우의 수 추출(순서는 상관없으니까 방문기록없이 순열추출)
- [x] 제곱근을 이용하여 소수판별 효율성 증대

### 💻코드

```java
/* 소수 판별하는 메소드 */
static boolean checkPrimeNumber(int number) {
	boolean flag = true;
	if(number == 1 || number == 0) flag = false;
	for(int i = 2; i <= Math.sqrt(number); i++) {
		if(number % i  == 0) {
			flag = false;
			break;
		}
	}
	return flag;
}
```
<br>
```java
/* 순열을 구하고(모든 경우의 수) 소수 판별하여 집계 */
static void permutation(int[] arr, int depth, int n, int r) {
	if(depth == r) {
		String str = toString(arr, r);
		int num = Integer.parseInt(str);
		if(!checkingDuplication.contains(num)) {
			checkingDuplication.add(num);
			if(checkPrimeNumber(num)) count++;
		}
		return;
	}
	for(int i = depth; i < n; i++) {
		swap(arr, depth, i);
		permutation(arr, depth + 1, n, r);
		swap(arr, depth, i);
	}
}
```

### 🙄  후기
순열구현조차 나한텐 너무 어렵다.. <br>
일단 자료구조 기본 개념이랑 기본 구현부터 연습하고 문제를 풀어야할까 싶다