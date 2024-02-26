package week5.SWEA2117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA2117 {
	static int[][] city;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int maxHomeCount = 1;
			sb.append("#" + test_case + " ");
			st = new StringTokenizer(in.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			city = new int[N][N];
			int cntExsitHome = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0; j < N; j++) {
					city[i][j] = Integer.parseInt(st.nextToken());
					if(city[i][j] == 1) cntExsitHome++;
				}
			}
			int maxCost = M * cntExsitHome;
			
			// 예외처리 1 : N*N 이 싹다 1이고 인당 비용 2이상이면 답이 N*N임
			if(cntExsitHome == N*N && M > 1) {
				sb.append(N*N).append("\n");
				continue;
			}
			
			for(int K = 2; K <= N+1; K++) {
				// 가지치기 1 : 회사의 비용이 집이 내는 비용 최대값보다 크면 애초에 설치 못함
				int costK = K * K + (K - 1) * (K - 1);
				if(costK > maxCost) break;
				
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						visited = new boolean[N][N];
						ArrayList<Integer> list = new ArrayList<>();
						visited[i][j] = true;
						list.add(i);
						list.add(j);
						int homeCount = (city[i][j] == 1) ? 1 : 0;

						for(int q = K-1; q > 0; q--) {
							int listSize = list.size();
							for(int li = 0; li < listSize; li+=2) {
								for(int direc = 0; direc < 4; direc++) {
									int nr = list.get(li) + dx[direc];
									int nc = list.get(li+1) + dy[direc];
									if(nr>=0&&nr<N&&nc>=0&&nc<N&&!visited[nr][nc]) {
										visited[nr][nc] = true;
										if(city[nr][nc] == 1) homeCount++;
										list.add(nr);
										list.add(nc);
									}
								}
							}
						}
						if(costK <= homeCount*M) { // 등호주의.. 이걸로 30분 헤맸다
							if(maxHomeCount < homeCount) maxHomeCount = homeCount;
						}
					}
				}
			}
			sb.append(maxHomeCount).append("\n");
		}
		System.out.println(sb);
	}
}
