package week3.BOJ1068;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Tree {
	List<Integer> list; // 자식노드 리스트

	Tree() {
		list = new ArrayList<>();
	}
}

public class BOJ1068 {
	private static Tree[] trees;
	private static int leafNodeCount;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());
		trees = new Tree[N];
		for(int i = 0; i < N; i++) {
			trees[i] = new Tree();
		}
		StringTokenizer st = new StringTokenizer(in.readLine());
		int rootIndex = 0; // root가 0번이 아닐수도 있으므로 따로 관리

		for(int i = 0; i < N; i++) {
			int parentNode = Integer.parseInt(st.nextToken());
			if(parentNode == -1) {
				rootIndex = i;
			} else { // 부모노드에 본인을 자식으로 추가
				trees[parentNode].list.add(i);
			}
		}
		int removeIndex = Integer.parseInt(in.readLine());
		if (removeIndex == rootIndex) { // root노드 삭제 시 -> 타노스이므로 0출력
			System.out.println(0);
			return;
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
		dfs(rootIndex);
		System.out.println(leafNodeCount);
	}

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
}