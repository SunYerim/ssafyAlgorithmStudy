package week10.BOJ2251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;


public class BOJ2251 {
	static int aMax, bMax, cMax; // 각 물통의 최대리터
	static HashSet<Integer> answer;
	static int cnt;
	static boolean[][] check;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		aMax = Integer.parseInt(st.nextToken());
		bMax = Integer.parseInt(st.nextToken());
		cMax = Integer.parseInt(st.nextToken());
		answer = new HashSet<>();
		check = new boolean[201][201];
		
		dfs(0, 0, cMax, 0);

		ArrayList<Integer> list = new ArrayList<>();
		for(int e : answer) {
			list.add(e);
		}
		Collections.sort(list);
		for(int e : answer) {
			sb.append(e + " ");
		}
		System.out.println(sb);
	}

	private static void dfs(int aCur, int bCur, int cCur, int separate) {
		if(check[aCur][bCur]) return;
		if(separate > -1) {
			int aRemain = aMax - aCur;
			int bRemain = bMax - bCur;
			int cRemain = cMax - cCur;
			/* A가 비어있고, C가 비어있지 않다면 정답에 추가 */
			if(aCur == 0) {
				answer.add(cCur);
			} else {
				if(aCur <= bRemain) { // b에 다 넣기
					answer.add(cCur);
				} else { // b에 다 넣고 남은거 c에 털수있는지 보기
					if(aCur-bRemain <= cRemain) {
						answer.add(cCur + (aCur-bRemain));
					}
				}
				if(aCur <= cRemain) { // c에 다 넣기
					answer.add(cCur+aCur);
				} else { // c에 다 넣고 남은거 b에 털수있는지 보기
					if(aCur-cRemain <= bRemain) {
						answer.add(cCur + cRemain);
					}
				}
			}
		}
		check[aCur][bCur] = true;

		for(int i = separate; i < 3; i++) {
			if(cnt < 500) callDfs(aCur, bCur, cCur, i);
		}
	}

	private static void callDfs(int aCur, int bCur, int cCur, int separate) {
		cnt++;

		int aRemain = aMax - aCur;
		int bRemain = bMax - bCur;
		int cRemain = cMax - cCur;
//		System.out.println("a: " + aCur + " b: " + bCur + " c: " + cCur);

		if(aCur != 0) {
			if(aCur >= bRemain) {
				dfs(aCur-bRemain, bMax, cCur, separate+1);
			} else {
				dfs(0, bCur+aCur, cCur, separate+1);
			}
		}
		if(aCur != 0) {
			if(aCur >= cRemain) {
				dfs(aCur-cRemain, bCur, cMax, separate+1);
			} else {

				dfs(0, bCur, cCur+aCur, separate+1);
			}
		}
		if(bCur != 0) {
			if(bCur >= aRemain) {
				dfs(aMax, bCur-aRemain, cCur, separate+1);
			} else {
				dfs(aCur+bCur, 0, cCur, separate+1);
			}
		}
		if(bCur != 0) {
			if(bCur >= cRemain) {
				dfs(aCur, bCur-cRemain, cMax, separate+1);
			} else {
				dfs(aCur, 0, cCur+bCur, separate+1);
			}
		}
		if(cCur != 0) {
			if(cCur >= bRemain) {
				dfs(aCur, bMax, cCur-bRemain, separate+1);
			} else {
				dfs(aCur, bCur+cCur, 0, separate+1);
			}
		}
		if(cCur != 0) {
			if(cCur >= aRemain) {
				dfs(aMax, bCur, cCur-aRemain, separate+1);
			} else {
				dfs(aCur+cCur, bCur, 0, separate+1);
			}
		}
	}

}