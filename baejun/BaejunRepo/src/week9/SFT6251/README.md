<h3 align="center"> 
    📢  [레벨3] 소프티어(업무 처리) : https://softeer.ai/practice/6251
</h3>

<br>

## 🚀 문제

너무 길어헝!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
---

## 🚦입출력 + 제한사항

1 ≤ H ≤ 10
1 ≤ K ≤ 10
1 ≤ R ≤ 1,000

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 일단 트리를 만들어줘야한다. 완전이진트리라서 그냥 뽞뽞뽞 집어넣으면됨
- 말단 노드들한텐 queue를 부여해서 업무 들어온 순으로 처리
- 이후 R일만큼 홀 짝 구분하며 업무 승계

- [x] tree + queue

### 💻코드

```java
/* R일만큼 업무 진행 */
for(int task = 1; task <= R; task++) {
	/* 부서장의 업무완료처리*/
	if(task % 2 == 1) {
		if(trees.get(1).leftQueue.size() > 0) taskSum += trees.get(1).leftQueue.poll();
	} else {
		if(trees.get(1).rightQueue.size() > 0) taskSum += trees.get(1).rightQueue.poll();
	}
	/* 갖고있는 업무 올려주기 */
	for(int i = 1; i < H; i++) {
		for(int j = sqrtOftwo[i]; j < sqrtOftwo[i]*2; j++) {
			if(task % 2 == 1) { // 홀수일이면 좌측에서 올라온 업무처리
				/* 부모노드 기준 left인지 right인지 구분해서 삽입 */
				if(j % 2 == 0) {
					if(trees.get(j).leftQueue.size() > 0) trees.get(j/2).leftQueue.offer(trees.get(j).leftQueue.poll());
				} else {
					if(trees.get(j).leftQueue.size() > 0) trees.get(j/2).rightQueue.offer(trees.get(j).leftQueue.poll());
				}
			} else { // 짝수일이면 우측에서 올라온 업무처리
				/* 부모노드 기준 left인지 right인지 구분해서 삽입 */
				if(j % 2 == 0) {
					if(trees.get(j).rightQueue.size() > 0) trees.get(j/2).leftQueue.offer(trees.get(j).rightQueue.poll());
				} else {
					if(trees.get(j).rightQueue.size() > 0) trees.get(j/2).rightQueue.offer(trees.get(j).rightQueue.poll());
				}
			}
		}
	}

	for(int i = sqrtOftwo[H]; i < sqrtOftwo[H]*2; i++) { // 말단 노드는 그냥 양쪽 다 올리면 됨
		if(i % 2 == 0) { // 짝수면 leftNode인거임
			if(trees.get(i).queue.size() > 0) trees.get(i/2).leftQueue.offer(trees.get(i).queue.poll());
		} else { // 홀수면 rightNode인거임
			if(trees.get(i).queue.size() > 0) trees.get(i/2).rightQueue.offer(trees.get(i).queue.poll());
		}
	}
}
```

### 🙄 후기
소요시간 : 2시간  <br>
일단 ide 없이 그 화면에서 바로 푸려니까 굉장히 빡빡하다, 실수할 가능성 20000% 니까 무조건 실수한다 생각하고 하나하나 sout찍어봐야될듯 <br>
그리고 문제를 참 똑바로 읽는다고 읽었는데.. 빡시네