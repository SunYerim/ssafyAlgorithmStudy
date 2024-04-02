package week10.BOJ2171;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	static int answer;
	static boolean flagForComb;

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
		
		for(int i = 0; i < nodes.size(); i++) { // 지금 노드리스트를 돌면서
			if(nodes.get(i).list.size() < 2) continue; // y축 개수가 2개이하면 직사각형 안되니 패스
			for(int j = i+1; j < nodes.size(); j++) {
				if(nodes.get(j).list.size() < 2) continue; // y축 개수가 2개이하면 직사각형 안되니 패스
				int count = 0;
				for(int k = 0; k < nodes.get(i).list.size(); k++) { // 같은 y축 개수 세기
					flagForComb = false;
					binarySearch2(0, nodes.get(j).list.size()-1, nodes.get(i).list.get(k), j);
					if(flagForComb) count++;
				}
				answer += (count * (count-1)) / 2; // 조합으로 나오는 경우의 수만큼 더해주기
			}
		}

		System.out.println(answer);
	}

	private static void binarySearch2(int left, int right, int target, int idx) {
		int mid = (left+right) / 2;
		if(mid > nodes.get(idx).list.size()-1) return;
		if(nodes.get(idx).list.get(mid) == target) {
			flagForComb = true;
			return;
		}
		if(left <= right) {
			if(nodes.get(idx).list.get(mid) > target) {
				right = mid - 1;
				binarySearch2(left, right, target, idx);
			} else {
				left = mid + 1;
				binarySearch2(left, right, target, idx);
			}
		}
		return;
	}

	private static void binarySearch(int left, int right) {
		int mid = (left+right) / 2;
		if(mid > nodes.size()-1) return;
		if(nodes.get(mid).idx == target) { // mid 대신 ArrayList.get(mid)
			flag = true;
			idx = mid;
			return;
		}

		if(left <= right) {
			if(nodes.get(mid).idx > target) {
				right = mid - 1;
				binarySearch(left, right);
			} else {
				left = mid + 1;
				binarySearch(left, right);
			}
		}
	}
}