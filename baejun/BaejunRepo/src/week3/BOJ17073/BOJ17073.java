package week3.BOJ17073;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Tree {
	List<Integer> list;
	
	Tree() {
		list = new ArrayList<>();
	}
}

public class BOJ17073 {
	// 노드가 자식이 있다? 그럼 무조건 0이될때까지 자식한테 물을 내려줌. 즉, 최종상태엔 leaf node만 물을 갖고있음, 즉 (물의 양 / leaf node 수)가 정답
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		double W = Integer.parseInt(st.nextToken());
		/* tree 생성하고 간선 연결 */
		Tree[] trees = new Tree[N + 1]; // 1번 인덱스부터 시작
		for(int i = 0; i < trees.length; i++) {
			trees[i] = new Tree();
		}
		
		for(int i = 1; i <= N-1; i++) {
			st = new StringTokenizer(in.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			trees[num1].list.add(num2);
			trees[num2].list.add(num1);
		}
		int count = 0;
		/* leaf노드 개수 세기(1번노드는 무조건 root노드라 문제에 명시 돼 있어서 생략) */
		for(int i = 2; i <= N; i++) {
			if(trees[i].list.size() == 1) count++;
		}
		System.out.printf("%.3f", W / count); // 물의 총 양에서 리프노드 수 나눠주기 -> 기댓값이 0 이상인 애들만 집계한다했음, 자식노드가 있으면 무조건 기댓값은 0임.
	}
}
