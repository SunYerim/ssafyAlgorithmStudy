package week5.SWEA4013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4013 {
	static class Magnet {
		int leftContact; // 왼쪽 자석과 접촉하는 인덱스
		int rightContact; // 오른쪽 자석과 접촉하는 인덱스
		int arrowContact; // 화살표가 가리키는 인덱스
		int direction; // 회전 방향
		int[] magnetism; // 극(N or S) 정보

		Magnet(int leftContact, int rightContact, int arrowContact) {
			this.leftContact = leftContact;
			this.rightContact = rightContact;
			this.arrowContact = arrowContact;
			magnetism = new int[8];
		}
	}

	static Magnet[] magnets;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;	

		int T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			int K = Integer.parseInt(in.readLine());
			magnets = new Magnet[4];
			magnets[0] = new Magnet(-1, 2, 0);
			magnets[1] = new Magnet(6, 2, 0);
			magnets[2] = new Magnet(6, 2, 0);
			magnets[3] = new Magnet(6, -1, 0);

			for(int i = 0; i < 4; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0; j < 8; j++) {
					magnets[i].magnetism[j] = Integer.parseInt(st.nextToken());
				}
			}

			// 횟수만큼 회전진행
			for(int trial = 0; trial < K; trial++) {
				st = new StringTokenizer(in.readLine());
				int rotateGear = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken());
				boolean[] flags = new boolean[4];
				if(rotateGear == 1) {
					flags[0] = true;
					magnets[0].direction = direction;
					if(magnets[0].magnetism[magnets[0].rightContact] != magnets[1].magnetism[magnets[1].leftContact]) {
						flags[1] = true;
						if(magnets[0].direction == 1) magnets[1].direction = -1;
						else magnets[1].direction = 1;
						if(magnets[1].magnetism[magnets[1].rightContact] != magnets[2].magnetism[magnets[2].leftContact]) {
							flags[2] = true;
							if(magnets[1].direction == 1) magnets[2].direction = -1;
							else magnets[2].direction = 1;
							if(magnets[2].magnetism[magnets[2].rightContact] != magnets[3].magnetism[magnets[3].leftContact]) {
								flags[3] = true;
								if(magnets[2].direction == 1) magnets[3].direction = -1;
								else magnets[3].direction = 1;
							}
						}
					}
				} else if(rotateGear == 2) {
					flags[1] = true;
					magnets[1].direction = direction;
					if(magnets[0].magnetism[magnets[0].rightContact] != magnets[1].magnetism[magnets[1].leftContact]) {
						flags[0] = true;
						if(magnets[1].direction == 1) magnets[0].direction = -1;
						else magnets[0].direction = 1;
					}
					if(magnets[1].magnetism[magnets[1].rightContact] != magnets[2].magnetism[magnets[2].leftContact]) {
						flags[2] = true;
						if(magnets[1].direction == 1) magnets[2].direction = -1;
						else magnets[2].direction = 1;
						if(magnets[2].magnetism[magnets[2].rightContact] != magnets[3].magnetism[magnets[3].leftContact]) {
							flags[3] = true;
							if(magnets[2].direction == 1) magnets[3].direction = -1;
							else magnets[3].direction = 1;
						}
					}
				} else if(rotateGear == 3) {
					flags[2] = true;
					magnets[2].direction = direction;
					if(magnets[2].magnetism[magnets[2].rightContact] != magnets[3].magnetism[magnets[3].leftContact]) {
						flags[3] = true;
						if(magnets[2].direction == 1) magnets[3].direction = -1;
						else magnets[3].direction = 1;
					}
					if(magnets[1].magnetism[magnets[1].rightContact] != magnets[2].magnetism[magnets[2].leftContact]) {
						flags[1] = true;
						if(magnets[2].direction == 1) magnets[1].direction = -1;
						else magnets[1].direction = 1;
						if(magnets[0].magnetism[magnets[0].rightContact] != magnets[1].magnetism[magnets[1].leftContact]) {
							flags[0] = true;
							if(magnets[1].direction == 1) magnets[0].direction = -1;
							else magnets[0].direction = 1;
						}
					}
				} else if(rotateGear == 4) {
					flags[3] = true;
					magnets[3].direction = direction;
					if(magnets[2].magnetism[magnets[2].rightContact] != magnets[3].magnetism[magnets[3].leftContact]) {
						flags[2] = true;
						if(magnets[3].direction == 1) magnets[2].direction = -1;
						else magnets[2].direction = 1;
						if(magnets[1].magnetism[magnets[1].rightContact] != magnets[2].magnetism[magnets[2].leftContact]) {
							flags[1] = true;
							if(magnets[2].direction == 1) magnets[1].direction = -1;
							else magnets[1].direction = 1;
							if(magnets[0].magnetism[magnets[0].rightContact] != magnets[1].magnetism[magnets[1].leftContact]) {
								flags[0] = true;
								if(magnets[1].direction == 1) magnets[0].direction = -1;
								else magnets[0].direction = 1;
							}
						}
					}
				}
				for(int i = 0; i < 4; i++) {
					if(flags[i]) rotatingGear(i, magnets[i].direction);
				}
			}
			int sum = 0;
			for(int i = 0; i < 4; i++) {
				if(magnets[i].magnetism[magnets[i].arrowContact] == 1) { 
					if(i == 0) sum += 1;
					else if(i == 1) sum += 2;
					else if(i == 2) sum += 4;
					else if(i == 3) sum += 8;
				}
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}

	private static void rotatingGear(int magnetNumber, int direc) {
		if (direc == 1) {
			if(magnets[magnetNumber].leftContact != -1) {
				if(--magnets[magnetNumber].leftContact == -1) {
					magnets[magnetNumber].leftContact = 7;
				}
			}
			if(magnets[magnetNumber].rightContact != -1) {
				if(--magnets[magnetNumber].rightContact == -1) {
					magnets[magnetNumber].rightContact = 7;
				}
			}
			if(--magnets[magnetNumber].arrowContact == -1) {
				magnets[magnetNumber].arrowContact = 7;
			}
		}
		else if (direc == -1) {
			if(magnets[magnetNumber].leftContact != -1) {
				if(++magnets[magnetNumber].leftContact == 8) {
					magnets[magnetNumber].leftContact = 0;
				}
			}
			if(magnets[magnetNumber].rightContact != -1) {
				if(++magnets[magnetNumber].rightContact == 8) {
					magnets[magnetNumber].rightContact = 0;
				}
			}
			if(++magnets[magnetNumber].arrowContact == 8) {
				magnets[magnetNumber].arrowContact = 0;
			}
		}
	}
}
