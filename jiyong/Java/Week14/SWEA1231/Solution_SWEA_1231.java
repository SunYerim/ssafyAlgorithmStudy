package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_1231 {
	static int N;
	static Node[] graph;
	static StringBuilder sb = new StringBuilder();

	static class Node {
		char letter;
		int left, right;

		public Node(char letter, int left, int right) {
			this.letter = letter;
			this.left = left;
			this.right = right;
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			N = Integer.parseInt(br.readLine());
			graph = new Node[N + 1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				char c = st.nextToken().charAt(0);
				int left = -1;
				int right = -1;
				if (st.hasMoreTokens()) {
					left = Integer.parseInt(st.nextToken());
				}
				if (st.hasMoreTokens()) {
					right = Integer.parseInt(st.nextToken());
				}
				graph[num] = new Node(c, left, right);

			}

			inorder(1);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void inorder(int i) {
		if (graph[i].left != -1) {
			inorder(graph[i].left);
		}
		sb.append(graph[i].letter);
		if (graph[i].right != -1) {
			inorder(graph[i].right);
		}

	}
}
