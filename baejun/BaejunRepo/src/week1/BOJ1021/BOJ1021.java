package week1.BOJ1021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ1021 {
	private static LinkedList<Integer> linkedList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		linkedList = new LinkedList<>();

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N =Integer.parseInt(st.nextToken());
		int M =Integer.parseInt(st.nextToken());
		for(int i = 1; i <= N; i++) {
			linkedList.add(i);
		}
		st = new StringTokenizer(in.readLine());
		int cnt = 0;
		for(int i = 0; i < M; i++) {
			int pickUpNumber = Integer.parseInt(st.nextToken());
			int idx = linkedList.indexOf(pickUpNumber);
			int size = linkedList.size() / 2;
			int direction = 0; // 0은 left, 1은 right
			if(idx > size) {
				direction = 1;
			}
			while(true) {
				// 뽑아낼 원소가 첫번째 자리에 오면 뽑아내기
				if(linkedList.get(0) == pickUpNumber) {
					linkedList.remove(0);
					break;
				}
				// 방향에 따라 각 끝자리 요소 삭제와 삽입
				if(direction == 0) {
					cnt++;
					int removeEle = linkedList.remove(0);
					linkedList.add(removeEle);
				} else {
					cnt++;
					int removeEle = linkedList.remove(linkedList.size() - 1);
					linkedList.add(0, removeEle);
				}
			}
		}
		System.out.println(cnt);
	}
}
