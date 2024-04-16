package week12.BOJ1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ1987 {
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static boolean[] alphabetVisited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			String str = in.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		visited = new boolean[R][C];
		alphabetVisited = new boolean[27]; // 26개인데 1번부터 사용
		
		max = 0;
		visited[0][0] = true;
		alphabetVisited[(int)map[0][0]-64] = true;
		DFS(0, 0, 0);
		System.out.println(max+1);
	}

	private static void DFS(int x, int y, int cnt) {
		if(max < cnt) max = cnt; // 최대값 비교 갱신
		
		for(int i = 0; i < 4; i++) {
			int nr = x + dx[i];
			int nc = y + dy[i];
			if(nr>=0&&nr<R&&nc>=0&&nc<C&&!visited[nr][nc]) {
				int alphabetIndex = (int)map[nr][nc]-64; // 해당 알파벳을 썼는지 확인하기 위해 알파벳 인덱스로 변환
				if(!alphabetVisited[alphabetIndex]) { // 해당 인덱스를 방문했는지 여부와 해당 알파벳을 사용했는지를 백트래킹
					visited[nr][nc] = true;
					alphabetVisited[alphabetIndex] = true;
					DFS(nr, nc, cnt+1);
					visited[nr][nc] = false;
					alphabetVisited[alphabetIndex] = false;					
				}
			}
		}
	}
}