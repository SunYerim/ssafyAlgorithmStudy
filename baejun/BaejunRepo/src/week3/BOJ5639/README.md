<h3 align="center"> 
    📢  [골드5] 백준(이진검색트리) : https://www.acmicpc.net/problem/5639
</h3>

<br>

## 🚀 문제

이진 검색 트리는 다음과 같은 세 가지 조건을 만족하는 이진 트리이다.

- 노드의 왼쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 작다.
- 노드의 오른쪽 서브트리에 있는 모든 노드의 키는 노드의 키보다 크다.
- 왼쪽, 오른쪽 서브트리도 이진 검색 트리이다.

전위 순회 (루트-왼쪽-오른쪽)은 루트를 방문하고, 왼쪽 서브트리, 오른쪽 서브 트리를 순서대로 방문하면서 노드의 키를 출력한다. 후위 순회 (왼쪽-오른쪽-루트)는 왼쪽 서브트리, 오른쪽 서브트리, 루트 노드 순서대로 키를 출력한다. 예를 들어, 위의 이진 검색 트리의 전위 순회 결과는 50 30 24 5 28 45 98 52 60 이고, 후위 순회 결과는 5 28 24 45 30 60 52 98 50 이다.
이진 검색 트리를 전위 순회한 결과가 주어졌을 때, 이 트리를 후위 순회한 결과를 구하는 프로그램을 작성하시오.

---

## 🚦입출력 + 제한사항

- 트리를 전위 순회한 결과가 주어진다. 노드에 들어있는 키의 값은 106보다 작은 양의 정수이다. 모든 값은 한 줄에 하나씩 주어지며, 노드의 수는 10,000개 이하이다. 같은 키를 가지는 노드는 없다.
- 입력으로 주어진 이진 검색 트리를 후위 순회한 결과를 한 줄에 하나씩 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 일단 입력값을 이진검색트리 조건에 맞게 값을 비교해가면서 입력값의 자리를 찾아 삽입(부모연결)
- 그렇게 트리를 완성시키면 그냥 dfs 돌면서 왼-오-root 순으로 순회

- [x] Tree클래스 생성을 통해 관리

### 💻코드

```java
/* 후위 순회 메서드 */
private static void postorderTraversal(int nodeIndex) {
	// 좌, 우, 루트 순서
	if(list.get(nodeIndex).leftNode != -1) postorderTraversal(list.get(nodeIndex).leftNode);
	if(list.get(nodeIndex).rightNode != -1) postorderTraversal(list.get(nodeIndex).rightNode);
	sb.append(list.get(nodeIndex).value).append("\n");
	return;
}
/* 부모찾아서 연결시키기 */
private static void findParent(int treeIdx) {
	if(newNode < list.get(treeIdx).value) {
		if(list.get(treeIdx).leftNode == -1) {
			list.get(treeIdx).leftNode = list.size();
		} else {
			findParent(list.get(treeIdx).leftNode);
		}
		return;
	}
	if(newNode > list.get(treeIdx).value) {
		if(list.get(treeIdx).rightNode == -1) {
			list.get(treeIdx).rightNode = list.size();
		} else {
			findParent(list.get(treeIdx).rightNode);
		}
		return;
	}
}
```

### 🙄 후기
소요시간 : 30분  <br>
문제가 꼬아놓은게 없어서 그런가 생각보다 금방 풀었다 <br>
확실히 dfs bfs 문제 많이 풀면서 적응해나가고 있다는 생각이 든다. <br>
양치기로 계속 경험 쌓으면서 아이디어 떠올리는 연습 해보자