package week1.BOJ13335;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13335 {
	static int n;
	static int w;
	static int L;
	static int currentBridgeWeight;
	static int currentBridgeTruckNumber;
	static int unitTime; // 소요시간
	static Queue<Integer> truckQueue;
	static Queue<Integer> bridgeQueue;

	private static void movingForwardTruck() {
		if(!bridgeQueue.isEmpty()) {
			int nextTruck = 0;
			if(bridgeQueue.peek() != null) nextTruck = bridgeQueue.peek();
			currentBridgeWeight -= bridgeQueue.poll();
			if(nextTruck != 0) currentBridgeTruckNumber--;
		}
	}

	private static void ridingOnBridge() {
		int nextTruck = 0;
		if(truckQueue.peek() != null) nextTruck = truckQueue.peek();			
		if(nextTruck + currentBridgeWeight <= L && currentBridgeTruckNumber <= w) {
			bridgeQueue.offer(truckQueue.poll());
			currentBridgeWeight += nextTruck;
			currentBridgeTruckNumber++;
		} else {
			bridgeQueue.offer(0);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 입력값 받기
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		// 입력값 queue에 저장
		truckQueue = new LinkedList<>();
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			truckQueue.add(Integer.parseInt(st.nextToken()));
		}
		// 트럭 다리 건너기 로직
		currentBridgeWeight = 0;
		unitTime = 0;
		currentBridgeTruckNumber = 0;

		bridgeQueue = new LinkedList<>();
		for(int i = 0; i < w; i++) {
			bridgeQueue.offer(0);
		}

		// truck 다 다리위에 올라올때까지
		while(!bridgeQueue.isEmpty()) {
			unitTime++;
			movingForwardTruck();
			if(!truckQueue.isEmpty()) ridingOnBridge();
		}

		System.out.println(unitTime);
	}
}