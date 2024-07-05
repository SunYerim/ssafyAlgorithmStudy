<h3 align="center"> 
    📢  [골드5] 백준(강의실 배정) : https://www.acmicpc.net/problem/11000
</h3>

<br>

## 🚀 문제
수강신청의 마스터 김종혜 선생님에게 새로운 과제가 주어졌다.

김종혜 선생님한테는 Si에 시작해서 Ti에 끝나는 N개의 수업이 주어지는데, 최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다.

참고로, 수업이 끝난 직후에 다음 수업을 시작할 수 있다. (즉, Ti ≤ Sj 일 경우 i 수업과 j 수업은 같이 들을 수 있다.)

수강신청 대충한 게 찔리면, 선생님을 도와드리자!

---

## 🚦입출력 + 제한사항

- 

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 우선순위 큐 두개 사용

### 💻코드

```java
public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    N = Integer.parseInt(in.readLine());

    PriorityQueue<Node> queue = new PriorityQueue<>();
    PriorityQueue<Integer> room = new PriorityQueue<>();


    for(int i = 0; i < N; i++) {
        st = new StringTokenizer(in.readLine());
        int num1 = Integer.parseInt(st.nextToken());
        int num2 = Integer.parseInt(st.nextToken());
        queue.offer(new Node(num1, num2));
    }

    int answer = 0;
    while(!queue.isEmpty()) {
        Node curNode = queue.poll();
        if(room.isEmpty()) {
            room.offer(curNode.end);
            answer++;
            continue;
        }
        if(room.peek() <= curNode.start) { // 기존에 있던 방 사용
            room.poll();
        } else { // 새로운 방 생성
            answer++;
        }
        room.offer(curNode.end);
    }
    System.out.println(answer);
}
```

### 🙄 후기
소요시간 : 1시간  <br>