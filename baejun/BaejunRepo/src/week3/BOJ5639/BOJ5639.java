package week3.BOJ5639;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Tree {
	int value;
	int leftNode;
	int rightNode;

	Tree(int value) {
		this.value = value;
		this.leftNode = -1;
		this.rightNode = -1;
	}
}

public class BOJ5639 {
	private static List<Tree> list;
	private static int newNode;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input;
		list = new ArrayList<>();
		list.add(new Tree(Integer.parseInt(in.readLine()))); // root 삽입(전위 순회이므로 무조건 첫 입력값이 root)

		while((input = in.readLine()) != null) {
			newNode = Integer.parseInt(input);
			findParent(0);
			list.add(new Tree(newNode));
		}
		postorderTraversal(0);
		System.out.println(sb);
	}

	private static void postorderTraversal(int nodeIndex) {
		// 좌, 우, 루트 순서
		if(list.get(nodeIndex).leftNode != -1) postorderTraversal(list.get(nodeIndex).leftNode);
		if(list.get(nodeIndex).rightNode != -1) postorderTraversal(list.get(nodeIndex).rightNode);
		sb.append(list.get(nodeIndex).value).append("\n");
		return;
	}

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
}
