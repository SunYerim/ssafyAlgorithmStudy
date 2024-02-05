package week2.BOJ2002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ2002 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		Queue<String> queue = new LinkedList<>();
		ArrayList<String> overtakingCarList = new ArrayList<>();
		int overtakingCount = 0;
		
		for(int i = 0; i < N; i++) {
			queue.offer(in.readLine());
		}
		for(int i = 0; i < N; i++) {
			String currentCar = in.readLine();
			while(overtakingCarList.contains(queue.peek())) { // 추월차는 이미 카운팅했으니 queue에서 그냥 빼주기
				queue.poll();
			}
			if (!queue.peek().equals(currentCar)) { // 나와야될 차 말고 다른애가 나오면 추월했단 소리
				overtakingCarList.add(currentCar);
				overtakingCount++;
			} else {
				queue.poll();
			}
		}
		System.out.println(overtakingCount);
	}
}
