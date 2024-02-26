package week5.SWEA4013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4013 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;	

		int T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			int K = Integer.parseInt(in.readLine());
			int[][] gearWheels = new int[4][8];
			int[][] gearIndex = new int[4][2];
			for(int i = 0; i < gearIndex.length; i++) {
				gearIndex[i][0] = 2;
				gearIndex[i][1] = 6;
			}
			for(int i = 0; i < gearWheels.length; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0; j < gearWheels[i].length; j++) {
					gearWheels[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 횟수만큼 회전진행
			for(int trial = 0; trial < K; trial++) {
				st = new StringTokenizer(in.readLine());
				int rotateGear = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				if(rotateGear == 1) {

				} else if(rotateGear == 2) {

				} else if(rotateGear == 3) {

				} else if(rotateGear == 4) {

				}
			}
		}

		System.out.println(sb);
	}
}
