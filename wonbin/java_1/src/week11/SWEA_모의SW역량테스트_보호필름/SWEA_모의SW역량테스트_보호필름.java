package week11.SWEA_모의SW역량테스트_보호필름;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_모의SW역량테스트_보호필름 {
	
	static int D;
	static int W;
	static int K;
	static int[][] map;
	static int[] inject;
	static int answer;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			String s = br.readLine();
			String[] s2 = s.split(" ");
			
			D = Integer.parseInt(s2[0]); // 세로
			W = Integer.parseInt(s2[1]); // 가로
			K = Integer.parseInt(s2[2]);
			map = new int[D][W];
			inject = new int[D];
			answer = K;
			
			for(int i=0;i<D;i++) {
				s = br.readLine();
				s2 = s.split(" ");
				for(int j=0;j<W;j++) {
					map[i][j] = Integer.parseInt(s2[j]);
				}
			}
			
			subs(0,0);
			
			bw.append("#"+tc+""+" "+answer+"");
			bw.append("\n");
		}
		
		bw.flush();
		bw.close();
	}

	static boolean check() {
		for (int w = 0; w < W; w++) {
			int cnt = 1;
			for (int d = 0; d < D - 1; d++) {
				int curr = inject[d] == -1 ? map[d][w] : inject[d]; // -1 이면 그대로 아니면 주입된 약물과 비교
				int next = inject[d + 1] == -1 ? map[d + 1][w] : inject[d + 1]; // -1 이면 그대로 아니면 주입된 약물과 비교
				if (curr == next) { // 만약 위와 아래가 같다면
					cnt++;
					if (cnt >= K)
						break;
				} else {
					cnt = 1;
				}
			}
			if (cnt < K)
				return false;
		}
		return true;

	}
	
	static void subs(int row, int cnt) {
		if (cnt >= answer)
			return;
		if (row == D) {
			if (check())
				answer = Math.min(answer, cnt);
			return;
		}
		inject[row] = -1;// 그대로
		subs(row + 1, cnt);
		inject[row] = 0;// A투입
		subs(row + 1, cnt + 1);
		inject[row] = 1;// B투입
		subs(row + 1, cnt + 1);
	}
	
	
}
