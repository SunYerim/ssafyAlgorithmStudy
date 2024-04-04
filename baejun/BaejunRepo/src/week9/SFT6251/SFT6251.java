package week9.SFT6251;

import java.io.*;
import java.util.*;

class Tree {
	int leftNode;
	int rightNode;
	Queue<Integer> leftQueue;
	Queue<Integer> rightQueue;
	Queue<Integer> queue;

	public Tree() {
		this.leftNode = -1;
		this.rightNode = -1;
		this.leftQueue = new LinkedList<>();
		this.rightQueue = new LinkedList<>();
		this.queue = new LinkedList<>();
	}
}

public class SFT6251 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] sqrtOftwo = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};

		st = new StringTokenizer(in.readLine());
		int H = Integer.parseInt(st.nextToken());
		int K= Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		ArrayList<Tree> trees = new ArrayList<>();
		trees.add(new Tree()); // 1번부터 쓰기위해서 더미 하나 추가
		trees.add(new Tree());

		/* 트리 생성 */
		for(int i = 0; i < H; i++) {
			for(int j = sqrtOftwo[i]; j < sqrtOftwo[i]*2; j++) {
				trees.get(j).leftNode = j*2;
				trees.get(j).rightNode = j*2+1;
				trees.add(new Tree());
				trees.add(new Tree());
			}
		}

		/* 말단직원들한테 K개만큼 업무 할당 */
		for(int i = sqrtOftwo[H]; i < sqrtOftwo[H]*2; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < K; j++) {
				trees.get(i).queue.add(Integer.parseInt(st.nextToken()));
			}
		}

		int taskSum = 0;
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
		System.out.println(taskSum);
	}
}
