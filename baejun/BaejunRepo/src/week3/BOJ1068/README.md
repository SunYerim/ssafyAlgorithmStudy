<h3 align="center"> 
    📢  [골드5] 백준(트리) : https://www.acmicpc.net/problem/1068
</h3>

<br>

## 🚀 문제

트리에서 리프 노드란, 자식의 개수가 0인 노드를 말한다.
트리가 주어졌을 때, 노드 하나를 지울 것이다. 그 때, 남은 트리에서 리프 노드의 개수를 구하는 프로그램을 작성하시오. 노드를 지우면 그 노드와 노드의 모든 자손이 트리에서 제거된다.
예를 들어, 다음과 같은 트리가 있다고 하자.
![image](https://github.com/SunYerim/ssafyAlgorithmStudy/assets/78029066/3bec3223-fe7b-412d-95af-deca44f6fdd6)

현재 리프 노드의 개수는 3개이다. (초록색 색칠된 노드) 이때, 1번을 지우면, 다음과 같이 변한다. 검정색으로 색칠된 노드가 트리에서 제거된 노드이다.

![image](https://github.com/SunYerim/ssafyAlgorithmStudy/assets/78029066/6a64a4f8-b0cd-420e-8699-cc75e7421343)

이제 리프 노드의 개수는 1개이다.


---

## 🚦입출력 + 제한사항

- 첫째 줄에 트리의 노드의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄에는 0번 노드부터 N-1번 노드까지, 각 노드의 부모가 주어진다. 만약 부모가 없다면 (루트) -1이 주어진다. 셋째 줄에는 지울 노드의 번호가 주어진다
- 첫째 줄에 입력으로 주어진 트리에서 입력으로 주어진 노드를 지웠을 때, 리프 노드의 개수를 출력한다.

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 접근법자체는 직관적이였음. class만들어서 트리의 자식번호 관리
- Tree형 배열을 만들어 문제에서 요구하는 인덱스 삭제 등 수행
- DFS 돌며 leaf node 개수 세기
- 그러고 쉽게 테케통과까지 했는데, 뭔 예외가 너무 많아서 틀리다가, 예외처리 다해줬는데도 틀리길래 읭~? 했는데
- 알고보니 이진트리가 아니여도 된다더라, ㅋㅋ 솔직히 좀 억까 아닌가싶음, 그림도 이진트리로 주면서

- [x] dfs 탐색을 통해 리프노드 개수 계산
- [x] tree클래스를 통해 트리 자식관계 관리(이진트리가 아니므로 left right가 아닌 list형으로 자식 관리)

### 💻코드

```java
/* Tree 클래스로 관리(이진트리가 아니므로, left right가 아니라 list로 자식관리) */
class Tree {
	List<Integer> list; // 자식노드 리스트

	Tree() {
		list = new ArrayList<>();
	}
}

/* 입력값 받으며 부모노드에 본인(자식) 추가하기 */
for(int i = 0; i < N; i++) {
	int parentNode = Integer.parseInt(st.nextToken());
	if(parentNode == -1) {
		rootIndex = i;
	} else { // 부모노드에 본인을 자식으로 추가
		trees[parentNode].list.add(i);
	}
}

/* 삭제 인덱스의 참조를 끊어버림 ==> 그 하위의 연결 돼 있는 애들 탐색 불가해짐 */
removeIndexChecking : for(int i = 0; i < trees.length; i++) { // break를 위한 라벨링
	for(int j = 0; j < trees[i].list.size(); j++) {
		if (trees[i].list.get(j) == removeIndex) {
			trees[i].list.remove(j);
			break removeIndexChecking;
		}
	}
}

/* leafnode 개수구하기*/
private static void dfs(int treeNumber) {
	//leafNode 인지 체크
	if(trees[treeNumber].list.size() == 0) {
		leafNodeCount++;
		return;
	}
	for(int i = 0; i < trees[treeNumber].list.size(); i++) {
		dfs(trees[treeNumber].list.get(i));
	}
}
```

### 🙄 후기
소요시간 : 1시간30분  <br>
뭐 이진트리 조건까지 체킹안한건 물론 잘못이지만, 그림 테케 싹다 이진트리였는데 문제가 그거였던게 좀 허무하긴하다 <br>
그래도 참조 자체를 끊어버려서 그 밑에 애들을 다 버려버리는 아이디어를 생각보다 빠르게 떠올림, 잘 푼거같아서 만족한다 <br>
그리고 tree같은거를 어떻게 관리해야되나 이런거도 좀 어색하고 어려웠는데, 클래스 만들어서 다루는게 확실히 편한거 같다. 경험이 쌓인다 ~
