# **Dynamic Programming**

> **Index**
>
> 1. **[개요](#out-line)**
> 2. **[다이나믹 프로그래밍](#dynamic-programming)**
> 3. **[구현](#implementation)**
> 4. **[0-1 Knapsack](#0-1_knapsack)**

## **개요** <a id="out-line" />

> **What?**

- 하나의 문제를 여러 개의 작은 문제로 나누어 해결하고, 그 결과를 저장하여 다시 큰 문제를 해결할 때 활용하는 것으로 알고리즘이 아닌 문제 해결 패러다임의 일종.

- DP에서 가장 중요한 개념은 한번 해결한 작은 문제의 결과를 저장하여 재활용하는 것으로, 이를 **메모이제이션(Memoization)** 이라고 한다.

- DP라는 이름은 Richard Bellman이 멋으로 작명한 것이라, 이름 자체에는 큰 의미가 담겨있지 않다.

## **다이나믹 프로그래밍** <a id="dynamic-programming" />

> **Why?**

앞서 언급한 작은 문제로 나누어 해결하는 구조는 일반적인 재귀(Naive Recursion) 구조와 유사하다. 하지만, 일반적인 재귀를 단순하게 사용한다면 같은 문제를 불필요하게 여러 번 해결하는 문제점이 발생할 수
있다. 아래의 예시를 보자.

피보나치 수열은 $$F(1) = 1, F(2) = 1, F(n) = F(n-1) + F(n-2) (n>2)$$ 을 만족하는 수열을 말한다. F(n)을 구하는 프로그램을 작성할 때, 일반적인 재귀 구조로 작성한다면
다음과 같다.

```python
def fibo(n: int):
    if 0 < n < 3:
        return 1
    return fibo(n - 1) + fibo(n - 2)
```

위와 같이 코드를 작성한다면, `fibo(n)`을 호출할 때 `fibo(1)` 또는 `fibo(2)`이 호출될 때까지 재귀적으로 함수가 반복 호출될 것이다. 매 계층마다 2번의 호출이 일어나므로
시간복잡도는 `O(2^N)`이 된다. 이는 `N`이 `100`만 되어도 약 `7해`번 이상 호출되므로 `N`이 조금만 커져도 계산하는 것이 불가능에 가깝다.

이제 피보나치 수열을 다시 한 번 보자. $$F(3) = F(2) + F(1)$$ 이므로 $$F(4) = F(3) + F(2) = F(2) + F(2) + F(1)$$ 과 같다. 일반적인 재귀 구조는 이러한 성질을
활용하여, 값이 상수로 정의되는 $$F(1)$$ 과 $$F(2)$$ 항을 이용하여 $$F(n)$$을 구하게 되는 것이다. 하지만, $$n$$ 이 커질 때, 이를 $$F(1)$$ 과 $$F(2)$$ 항 만을 이용하여
계산한다면 너무 많은 재귀 호출이 발생한다. 아래의 사진과 같이 많은 **중복 호출**이 발생하게 되는 것이다.

<div align="center">
<img src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F99109F3B5BE7553E1B" style="background-color: white; height: 250px">
</div>

> **Solution**

여기에 DP 개념을 적용한다면, 한 번 계산한 항을 저장하여 다시 활용함으로써 시간복잡도를 `O(n)`으로 줄일 수 있다.

```python
fibo_seq = [0, 1, 1]


def fibo(n: int):
    if n < 3:
        return fibo_seq[n]
    while len(fibo_seq) <= n:
        fibo_seq.append(fibo_seq[-1] + fibo_seq[-2])
    return fibo_seq[n]
```

## **구현** <a id="implementation" />

> **How?**

>1. [주어진 문제가 DP로 풀 수 있는 문제인지 판별](#1)
>2. [문제의 변수 파악](#2)
>3. [변수 간 관계식 정립 (점화식)](#3)
>4. [메모이제이션](#4)
>5. [기저 상태 파악](#5)
>6. [구현](#6)

### 주어진 문제가 DP로 풀 수 있는 문제인지 판별 <a id="1"/>
DP를 적용하여 풀 수 있는 문제는 두 가지 유형이 있다.

1) Overlapping Subproblems (중복 부분 문제)
2) Optimal Substructure (최적 부분 구조)

**Overlapping Subproblems** : 
동일한 작은 문제들이 반복하여 나타나는 구조라면, 메모이제이션을 통해 한 번 해결한 문제의 해답을 가지고 문제 해결을 최적화하는 유형. 앞서 소개한 피보나치 수열은 해당 유형에 속한다.

**Optimal Substructure** : 
부분 문제의 최적 결과 값을 사용하여 전체 문제의 최적 결과를 낼 수 있는 경우. 해결하기 어려운 본래의 문제를 가장 작은 단위의 문제로 만들었을 때는 쉽게 해결할 수 있는 경우가 이 유형에 해당한다.

### 문제의 변수 파악 <a id="2"/>
DP는 특정 변수에 대한 해답을 다른 변수에서 찾아 최적화하는 것이기에 문제에서 사용되는 변수를 파악해야한다. 피보나치 수열의 경우 n이 변수가 된다.

### 변수 간 관계식 정립 (점화식) <a id="3"/>
문제의 변수를 파악했다면, 변수 간의 관계를 정립하여 식으로 만들어야 한다. 이를테면, 피보나치 수열의 $$F(n) = F(n-1) + F(n-2)$$ 식과 같다.

### 메모이제이션 <a id="4"/>
점화식까지 도출했다면 각 변수에 따른 결과값을 저장하는 과정인 메모이제이션이 필요하다. 결과값을 저장할 적절한 크기의 배열을 선언하고, 점화식에 따라 값을 채워나가면 된다. 


### 기저 상태 파악 <a id="5"/>
위의 과정까지 진행했다면, 가장 작은 문제의 상태를 파악해야 한다. 문제에서 직접 주어준다거나, 작은 문제에서는 간단한 논리 또는 시뮬레이션을 통해 기저 상태를 알아낼 수 있다. 
피보나치 수열에서는 $$F(1) = 1, F(2) = 1$$ 과 같이 기저 상태를 사전에 정의한다.

### 구현 <a id="6"/>
구현에는 두 가지 방식이 있다.

1) Top-down (하향식 접근법)
2) Bottom-up (상향식 접근법)

**Top-down (Memoization)**

하향식 접근은 일반적인 재귀 구조와 같이 큰 문제에서 부터 호출하여 작은 문제로 접근하되, 한 번 해결한 문제는 메모이제이션을 통해 값을 저장하고 재활용하는 방식이다. 

```python
fibo_seq = [0] * (n+1)

def fibo(n: int):
    if n < 2:
        return n
    if fibo_seq[n] > 0:
        return fibo_seq[n]
    fibo_seq[n] = fibo(n-1) + fibo(n-2)
    return fibo_seq[n]
```

**Bottom-up (Tabulation)**

상향식 접근은 가장 작은 문제부터 메모이제이션 테이블을 채워가며 큰 문제의 해답을 찾는 방식으로 특별히 Tabulation이라는 별칭으로 구분짓기도 한다.

```python
fibo_seq = [0, 1, 1]


def fibo(n: int):
    if n < 3:
        return fibo_seq[n]
    while len(fibo_seq) <= n:
        fibo_seq.append(fibo_seq[-1] + fibo_seq[-2])
    return fibo_seq[n]
```

> Trade-off ?
 
두 접근법 간의 정답은 없다. 문제에 따라 더 유리하게 작동하거나, 구현이 쉬운 구조가 있을 것. 다만, 하향식 접근은 재귀 구조를 활용하기에 호출 횟수가 너무나 많다면 Stack Overflow가 발생할 수도 있을 것이다.
(Python은 기본 recursion limit이 1000으로 설정되어 있기 때문에 제한을 늘리거나, 상향식 접근을 활용하는 것이 좋다.)

## **0-1 Knapsack** <a id="0-1_knapsack" />

[링크](https://howudong.tistory.com/106)