package week4.SWEA무선충전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class charger {
	int number;
	List<int[]> position;
	int performance;

	public charger(int number, List<int[]> position, int performance) {
		this.number = number;
		this.position = position;
		this.performance = performance;
	}
}

public class SWEA무선충전 {
	static List<charger> list;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int[] APosition;
	static int[] BPosition;
	static int sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 寃곌낵瑜� �븳 踰덉뿉 異쒕젰�븯湲� �쐞�븳 StringBuilder
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T;
		T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			list = new ArrayList<>();
			APosition = new int[2];
			BPosition = new int[2];
			APosition[0] = 0;
			APosition[1] = 0;
			BPosition[0] = 9;
			BPosition[1] = 9;
			sum = 0;
			sb.append("#" + test_case + " ");
			st = new StringTokenizer(in.readLine());
			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(in.readLine());
			int[] aMove = new int[M];
			int[] bMove = new int[M];

			for(int i = 0; i < M; i++) {
				aMove[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < M; i++) {
				bMove[i] = Integer.parseInt(st.nextToken());
			}
			for(int i = 0; i < A; i++) {
				st = new StringTokenizer(in.readLine());
				int y = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken()) - 1;
				int coverage = Integer.parseInt(st.nextToken());
				int performance = Integer.parseInt(st.nextToken());
				int[] arr = {x, y};
				List<int[]> position = new ArrayList<>();
				boolean[][] visited = new boolean[10][10];
				position.add(arr);
				visited[arr[0]][arr[1]] = true;
				for(int j = 0; j < coverage; j++) {
					for(int z = position.size()-1; z >= 0; z--) {
						for(int q = 0; q < 4; q++) {
							int nr = position.get(z)[0] + dx[q];
							int nc = position.get(z)[1] + dy[q];
							if(nr>=0&&nr<10&&nc>=0&&nc<10&&!visited[nr][nc]) {
								visited[nr][nc]=true;
								int[] arr2 = {nr, nc};
								position.add(arr2);
							}
						}
					}
				}
				list.add(new charger(i, position, performance));
			}
			//�뿬湲곌퉴吏�媛� �엯�젰媛�
			checkingCharge();
			for(int i = 0; i < M; i++) {
				switch(aMove[i]) {
				case 1:
					APosition[0]--;
					break;
				case 2:
					APosition[1]++;
					break;
				case 3:
					APosition[0]++;
					break;
				case 4:
					APosition[1]--;
					break;
				}
				switch(bMove[i]) {
				case 1:
					BPosition[0]--;
					break;
				case 2:
					BPosition[1]++;
					break;
				case 3:
					BPosition[0]++;
					break;
				case 4:
					BPosition[1]--;
					break;
				}
				checkingCharge();
			}
			System.out.println("#" + test_case + " " + sum);
		}
	}

	private static void checkingCharge() {
		int aMax = 0;
		int bMax = 0;
		List<Integer> aList = new ArrayList<>();
		List<Integer> bList = new ArrayList<>();

		// 踰붿쐞�뿉 �엳�쑝硫� flag �꽭�썙�꽌 泥섎━�븯湲�
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < list.get(i).position.size(); j++) {
				int nx = list.get(i).position.get(j)[0];
				int ny = list.get(i).position.get(j)[1];
				if(APosition[0] == nx && APosition[1] == ny) {
					aList.add(i);
				}
				if(BPosition[0] == nx && BPosition[1] == ny) {
					bList.add(i);
				}
			}
		}
		if(aList.size() == 0 && bList.size() == 0) {
			return;
		} else {
			sum += getMax(aList, bList);
		}
	}

	private static int getMax(List<Integer> aList, List<Integer> bList) {
		int max = 0;
		if(aList.size() == 0) {
			for(int e : bList) {
				if(max < list.get(e).performance) max = list.get(e).performance;
			}
		} else if (bList.size() == 0) {
			for(int e : aList) {
				if(max < list.get(e).performance) max = list.get(e).performance;
			}
		} else {
			for(int i = 0; i < aList.size(); i++) {
				for(int j = 0; j < bList.size(); j++) {
					if(aList.get(i) == bList.get(j)) {
						int sum = 0;
						sum += list.get(aList.get(i)).performance;
						if(max < sum) max = sum;
					} else {
						int sum = 0;
						sum += list.get(aList.get(i)).performance;
						sum += list.get(bList.get(j)).performance;
						if(max < sum) max = sum;
					}
				}
			}
		}

		return max;
	}
}
