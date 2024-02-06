package week2.BOJ18808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18808 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[][] noteBook = new int[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
		int stickerQuantity = Integer.parseInt(st.nextToken());
		/* 스티커 개수만큼 케이스 돌리기 */
		for (int test_case = 1; test_case <= stickerQuantity; test_case++) {
			st = new StringTokenizer(in.readLine());
			int[][] sticker = new int[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
			for(int i = 0; i < sticker.length; i++) { // 배열 입력받기, 직사각형임에 주의
				st = new StringTokenizer(in.readLine());
				for(int j = 0; j < sticker[i].length; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 노트북 배열 순회하며 자리찾기
			noteBookLabel: for(int noteBookRow = 0; noteBookRow <= noteBook.length; noteBookRow++) {
				for(int noteBookCol = 0; noteBookCol < noteBook[noteBookRow].length; noteBookCol++) {
					for(int rotate = 0; rotate < 4; rotate++) {
						boolean flag = false; // 스티커 부착 가능 여부
						stickerLabel: for(int i = 0; i < sticker.length; i++) {
							for(int j = 0; j < sticker[i].length; j++) {
								if(sticker[i][j] == 1) {
									if(noteBook[noteBookRow+i][noteBookCol+j] == 1) {
										break stickerLabel;
									}
								}
								if(i == sticker.length - 1 && j == sticker[i].length - 1) {
									flag = true;
								}
							}
						}
						if(flag) {
							for(int i = 0; i < sticker.length; i++) {
								for (int j = 0; j < sticker[i].length; j++) {
									noteBook[noteBookRow+i][noteBookCol+j] = sticker[i][j];
								}
							}
							break noteBookLabel;
						} else { //회전하기, 회전은 0, 1, 2, 3(90, 180, 270, 360)
							if (rotate == 3) {
								break noteBookLabel;
							}
							rotateSticker(sticker);
						}
					}
				}
			}
		}
		int cnt = 0;
		for(int[] arr : noteBook) {
			for(int e : arr) {
				if(e == 1) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

	private static void rotateSticker(int[][] sticker) {
		int[][] tmp = new int[sticker[0].length][sticker.length];
		for(int r=0; r<sticker.length; r++) {
			for(int c=0; c<sticker[0].length; c++) {
				int newR = sticker.length-(r+1);
				tmp[c][r] = sticker[newR][c];
			}
		}
		sticker=tmp;
	}
}
