package week4.BOJ2583;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2583 {
	static boolean[][] rectangle;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static List<Integer> answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(in.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        rectangle = new boolean[M][N];
        visited = new boolean[M][N];
        
        for(int i = 0; i < K; i++) { // 색칠된 부분 채우기
        	st = new StringTokenizer(in.readLine());
        	int y1 = Integer.parseInt(st.nextToken());
        	int x1 = M - Integer.parseInt(st.nextToken());
        	int y2 = Integer.parseInt(st.nextToken());
        	int x2 = M - Integer.parseInt(st.nextToken());
        	for(int row = x2; row < x1; row++) {
        		for(int col = y1; col < y2; col++) {
        			rectangle[row][col] = true;
        		}
        	}
        }
        
        answer = new ArrayList<>();
        for(int i = 0; i < rectangle.length; i++) {
        	for(int j = 0; j < rectangle[0].length; j++) {
        		if(!rectangle[i][j] && !visited[i][j]) { // 빈공간이고 방문안했으면 진입
        			bfs(i, j);
        		}
        	}
        }
        System.out.println(answer.size());
        answer.sort(null);
        for(int e : answer) {
        	System.out.print(e + " ");
        }
	}

	private static void bfs(int row, int col) {
		int area = 0;
		Queue<int[]> queue = new LinkedList<>();
		int[] arr = {row, col};
		queue.add(arr);
		visited[row][col] = true;
		while(!queue.isEmpty()) {
			int[] arr2 = queue.poll();
			area++;
			for(int i = 0; i < 4; i++) {
				int nr = arr2[0] + dx[i];
				int nc = arr2[1] + dy[i];
				if(nr>=0&&nr<rectangle.length&&nc>=0&&nc<rectangle[0].length&&!rectangle[nr][nc]&&!visited[nr][nc]) {
					int[] arr3 = {nr, nc};
					queue.add(arr3);
					visited[nr][nc] = true;
				}
			}
		}
		answer.add(area);
	}
}
