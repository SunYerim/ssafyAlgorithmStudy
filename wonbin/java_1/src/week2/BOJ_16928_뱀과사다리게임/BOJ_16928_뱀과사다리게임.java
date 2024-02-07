package week2.BOJ_16928_뱀과사다리게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16928_뱀과사다리게임 {

	// 결과를 한 번에 출력하기 위한 StringBuilder
		private static StringBuilder sb = new StringBuilder();
		static int[] arr;

		public static void main(String[] args) throws IOException {

			HashMap<Integer, Integer> ladder_hash = new HashMap<Integer, Integer>();
			HashMap<Integer, Integer> ladder_hash2 = new HashMap<Integer, Integer>();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			String s = br.readLine();
			String[] s2 = s.split(" ");
			int ladder = Integer.parseInt(s2[0]);
			int snake = Integer.parseInt(s2[1]);
			arr = new int[101];
			int answer;
			
			for(int i=1;i<arr.length;i++) {
				arr[i] = i;
			}
			
			for(int i=0;i<ladder;i++) {
				String input = br.readLine();
				String[] input2 = input.split(" ");
				arr[Integer.parseInt(input2[0])] =  Integer.parseInt(input2[1]);
			}
			
			for(int i=0;i<snake;i++) {
				String input = br.readLine();
				String[] input2 = input.split(" ");
				arr[Integer.parseInt(input2[0])] = Integer.parseInt(input2[1]);
			}
				
			answer = bfs(1);	
			
			sb.append(answer);
			
			System.out.println(sb);
		}
		
		private static int bfs(int startNode) {

			int[] check = new int[101]; // 방문 순서를 기록하기 위한 배열 생성. 
			Queue<Integer> q = new LinkedList<>();
			q.offer(startNode); //처음에 인덱스 1이 들어간다.
			check[startNode] = 0; 

			while (true) {
				int visitedNum = q.poll();
				//주사위 1~6이 나오는 경우를 살피기.
				for (int i = 1; i < 7; i++) {            	
					int newNode = visitedNum + i;
	                
	                // board의 범위를 넘기면 무시하기
	                // - check 배열에 IndexOutOfBoundsException을 발생시킬 수 있기 때문
					if (newNode > 100) { 
						continue;
					}
	                
	                // check되어있는 경우(방문을 한적이 있는 경우)는 무시한다는 것을 전제로 함.
					if (check[arr[newNode]] == 0) { 
						q.offer(arr[newNode]);
						check[arr[newNode]] = check[visitedNum] + 1;
					}
					if (arr[newNode] == 100) {
						return check[100];
					}
				}

			}

		}
}
