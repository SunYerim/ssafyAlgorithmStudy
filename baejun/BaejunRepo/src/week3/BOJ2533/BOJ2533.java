package week3.BOJ2533;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Tree {
	List<Integer> list;
	boolean earlyAdaptor;

	Tree() {
		list = new ArrayList<>();
	}
}

public class BOJ2533 {
	private static Tree[] trees;
	private static int N;
	private static boolean flag;
	private static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(in.readLine());
		trees = new Tree[N+1]; // 1번인덱스부터 사용
		visited = new boolean[N+1];
		for(int i = 1; i <= N; i++) {
			trees[i] = new Tree();
		}
		for(int i = 1; i <= N; i++) {
			try {
				st = new StringTokenizer(in.readLine());
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				trees[num1].list.add(num2);
				trees[num2].list.add(num1);
			} catch (Exception e) {
				break;
			}
		}
		Arrays.sort(trees, new Comparator<Tree>() {
			@Override
			public int compare(Tree o1, Tree o2) {
				if(o1 != null && o2 != null) return o1.list.size() - o2.list.size();
				return 0;
			}
		});
		for(Tree tree : trees) {
			if(tree == null) continue;
			System.out.println(tree.list);
		}
		visited = new boolean[N+1];
		for(int i = 1; i <= N; i++) {
			if(trees[i].list.size() > 1) break;

			if(trees[i] != null && !visited[trees[i].list.get(0)]) {
				trees[trees[i].list.get(0)].earlyAdaptor = true;
				visited[trees[i].list.get(0)] = true;
			}
		}
		for(Tree tree : trees) {
			if(tree == null) continue;
			System.out.println(tree.earlyAdaptor);
		}
	}

	private static void dfs(int node) {
		if(flag) {
			visited[node] = true;
			for(int e : trees[node].list) {
				if(!visited[e]) dfs(e);
			}
			if(trees[node].earlyAdaptor) return;
			for(int e : trees[node].list) {
				if(!trees[e].earlyAdaptor) {
					flag = false;
					return;
				}
			}
		}
	}
}
