package week10.BOJ2171;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;


public class BOJ2171 {
	static class Node {
		int idx; // x축
		ArrayList<Integer> list; // 같은 x축을 가지는 y축 리스트
		
		Node(int idx) {
			this.idx = idx;
			this.list = new ArrayList<>();
		}
	}
	
	static int N;
	static int target, idx;
	static boolean flag;
	static ArrayList<Node> nodes;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		StringTokenizer st;

		nodes = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			target = x;
			idx = 0;
			flag = false;
			if(nodes.size() != 0) binarySearch(0, nodes.size()-1);
			if(!flag) {
				nodes.add(new Node(x));
				nodes.get(nodes.size()-1).list.add(y);
			} else {
				nodes.get(idx).list.add(y);
			}
			Collections.sort(nodes, new Comparator<Node>() { // idx순 정렬
				@Override
				public int compare(Node o1, Node o2) {
					return o1.idx - o2.idx;
				}
			});
		}
		
		for(int i = 0; i < nodes.size(); i++) { // idx별로 list 정렬(추후 이분탐색 위해서)
			Collections.sort(nodes.get(i).list);
		}
		for(int i = 0; i < nodes.size(); i++) {
			if(nodes.get(i).list.size() > 1) { // 1개뿐이라면 어차피 직사각형 못만드므로 패스
				combination(); // 조합
			}
		}
	}

	private static void binarySearch(int left, int right) {
		int mid = (left+right) / 2;
		if(mid > nodes.size()) return;
		if(nodes.get(mid).idx == target) { // mid 대신 ArrayList.get(mid)
			flag = true;
			idx = mid;
			return;
		}
		
		if(left <= right) {
			if(mid > target) {
				right = mid - 1;
				binarySearch(left, right);
			} else {
				left = mid + 1;
				binarySearch(left, right);
			}
		}
	}
}
