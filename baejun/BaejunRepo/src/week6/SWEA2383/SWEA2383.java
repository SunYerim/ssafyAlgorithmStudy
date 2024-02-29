package week6.SWEA2383;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA2383 {
	static int N, personCnt, minTime;
	static int[][] map, persons;
	static int[] floor;
	static boolean[] visited;
	static ArrayList<Comb> combList;

	static class Comb {
		int[] aFloor;
		int[] bFloor;
		int sum;

		Comb(int[] aFloor, int[] bFloor, int sum) {
			this.aFloor = new int[aFloor.length];
			this.bFloor = new int[bFloor.length];
			for(int i = 0; i < aFloor.length; i++) this.aFloor[i] = aFloor[i];
			for(int i = 0; i < bFloor.length; i++) this.bFloor[i] = bFloor[i];
			this.sum = sum;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			sb.append("#" + testCase + " ");
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];
			floor = new int[4];
			int floorCnt = -1;
			personCnt = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) personCnt++;
					if(map[i][j] > 1) {
						floor[++floorCnt] = i;
						floor[++floorCnt] = j;
					}
				}
			}
			persons = new int[personCnt][2];
			int personsIndex = -1;
			// 사람별로 1번계단, 2번계단으로의 소요시간 계산
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 1) {
						int dist1x = Math.abs(floor[0] - i);
						int dist1y = Math.abs(floor[1] - j);
						int dist2x = Math.abs(floor[2] - i);
						int dist2y = Math.abs(floor[3] - j);
						personsIndex++;
						persons[personsIndex][0] = Math.abs(dist1x+dist1y);
						persons[personsIndex][1] = Math.abs(dist2x+dist2y);
					}
				}
			}
			visited = new boolean[personCnt];
			combList = new ArrayList<>();
			for(int i = 0; i <= personCnt; i++) {
				combination(0, 0, i); // 조합으로 사람 별로 계단 선택하는 경우의 수 고르기				
			}
			Collections.sort(combList, new Comparator<Comb>() {
				@Override
				public int compare(Comb o1, Comb o2) {
					return Integer.compare(o1.sum, o2.sum);
				}
			});
			minTime = Integer.MAX_VALUE;
			for(int i = 0; i < combList.size(); i++) {
				checkDistance(i); // 경우의 수 별로 소요시간 체크하기
			}
			sb.append(minTime+1).append("\n");
		}
		System.out.println(sb);
	}

	private static void checkDistance(int idx) {
		ArrayList<Integer> onTheAFloor = new ArrayList<>();
		ArrayList<Integer> onTheBFloor = new ArrayList<>();
		Queue<Integer> waitingLineA = new LinkedList<>();
		Queue<Integer> waitingLineB = new LinkedList<>();
		int cnt = 0;

		int curTime = 0;
		while(curTime < Integer.MAX_VALUE) {
			curTime++;
			// 계단 타고있는애들 1분씩 내려주기
			for(int i = onTheAFloor.size()-1; i >= 0; i--) { 
				int a = onTheAFloor.get(i);
				a--;
				if(a == 0) { 
					onTheAFloor.remove(i);
					cnt++;
					if(!waitingLineA.isEmpty()) {
						onTheAFloor.add(i, waitingLineA.poll());
					}
				}
				else onTheAFloor.set(i, a);
			}
			for(int i = onTheBFloor.size()-1; i >= 0; i--) { 
				int a = onTheBFloor.get(i);
				a--;
				if(a == 0) {
					onTheBFloor.remove(i);
					cnt++;
					if(!waitingLineB.isEmpty()) {
						onTheBFloor.add(i, waitingLineB.poll());
					}
				}
				else onTheBFloor.set(i, a);
			}
			for(int i = 0; i < combList.get(idx).aFloor.length; i++) {
				if(combList.get(idx).aFloor[i] == curTime) {
					if(onTheAFloor.size() < 3) onTheAFloor.add(map[floor[0]][floor[1]]);
					else {
						waitingLineA.offer(map[floor[0]][floor[1]]);
					}
				}
			}
			for(int i = 0; i < combList.get(idx).bFloor.length; i++) {
				if(combList.get(idx).bFloor[i] == curTime) {
					if(onTheBFloor.size() < 3) onTheBFloor.add(map[floor[2]][floor[3]]);
					else {
						waitingLineB.offer(map[floor[2]][floor[3]]);
					}
				}
			}
			if(cnt == personCnt) {
				if(minTime > curTime) minTime = curTime;
				break;
			}
		}
	}

	private static void combination(int start, int cnt, int r) {
		//기저조건
		if(cnt == r) {
			int[] aFloor = new int[r];
			int aFloorIndex = -1;
			int[] bFloor = new int[personCnt-r];
			int bFloorIndex = -1;
			int sum = 0;

			for(int i = 0; i < personCnt; i++) {
				if(visited[i]) {
					aFloor[++aFloorIndex] = persons[i][0];
					sum += persons[i][0];
				}
				else {
					bFloor[++bFloorIndex] = persons[i][1];
					sum += persons[i][1];
				}
			}
			combList.add(new Comb(aFloor, bFloor, sum));
			return;
		}
		//유도조건
		for(int i = start; i < personCnt; i++) {
			if(!visited[i]) {
				visited[i] = true;
				combination(i+1, cnt+1, r);
				visited[i] = false;
			}
		}
	}
}