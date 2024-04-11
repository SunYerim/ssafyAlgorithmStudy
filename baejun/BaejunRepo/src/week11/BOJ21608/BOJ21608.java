package week11.BOJ21608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ21608 {
	static ArrayList<ArrayList<Integer>> list;
	
	static class Node implements Comparable<Node> {
		int x;
		int y;
		int adjacencyCnt;
		int likeStu;
		
		Node(int x, int y, int adjacencyCnt, int likeStu) {
			this.x = x;
			this.y = y;
			this.adjacencyCnt = adjacencyCnt;
			this.likeStu = likeStu;
		}

		@Override
		public int compareTo(Node o) {
			if(o.likeStu == this.likeStu) {
				if(this.adjacencyCnt == o.adjacencyCnt) {
					if(this.x == o.x) {
						return Integer.compare(this.y, o.y);
					} else {
						return Integer.compare(this.x, o.x);
					}
				} else {
					return Integer.compare(o.adjacencyCnt, this.adjacencyCnt);
				}
			} else {
				return Integer.compare(o.likeStu, this.likeStu);
			}
		}
	}
	
	static int[] order;
	static int[][] result;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(in.readLine());
		list = new ArrayList<>();
		for(int i = 0; i < N*N + 1; i++) { // 1번부터 사용
			list.add(new ArrayList<>());
		}
		order = new int[N*N];
		for(int i = 0; i < N*N; i++) {
			st = new StringTokenizer(in.readLine());
			int idx = Integer.parseInt(st.nextToken());
			order[i] = idx;
			for(int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				list.get(idx).add(num);				
			}
		}
		
		result = new int[N+1][N+1];
		for(int action = 0; action < N*N; action++) {
			PriorityQueue<Node> queue = new PriorityQueue<>();
			for(int i = 1; i < result.length; i++) {
				for(int j = 1; j < result.length; j++) {
					if(result[i][j] != 0) continue; // 이미 학생이 앉은 경우
					/* 아니라면 해당 칸에 좋아하는 학생 수와 인접 빈칸을 구해서 queue에 삽입 */
					int like = 0;
					int adjacency = 0;
					for(int k = 0; k < 4; k++) {
						int nr = i + dx[k];
						int nc = j + dy[k];
						if(nr>0&&nr<N+1&&nc>0&&nc<N+1) {
							if(result[nr][nc] == 0) adjacency++; //비어있을때
							else {
								for(int q = 0; q < 4; q++) {
									if(list.get(order[action]).get(q) == result[nr][nc]) like++;
								}
							}
						}
					}
					queue.offer(new Node(i, j, adjacency, like));
				}
			}
			Node curNode = queue.poll();
			result[curNode.x][curNode.y] = order[action];
		}
		int likeSum = 0;
		for(int i = 1; i < N+1; i++) {
			for(int j = 1; j < N+1; j++) {
				int like = 0;
				for(int k = 0; k < 4; k++) {
					int nr = i + dx[k];
					int nc = j + dy[k];
					if(nr>0&&nr<N+1&&nc>0&&nc<N+1) {
						for(int q = 0; q < 4; q++) {
							if(list.get(result[i][j]).get(q) == result[nr][nc]) like++;
						}
					}
				}
				if(like == 1) {
					likeSum += 1;
				} else if(like == 2) {
					likeSum += 10;
				} else if(like == 3) {
					likeSum += 100;
				} else if(like == 4) {
					likeSum += 1000;
				}
			}
		}
		System.out.println(likeSum);
	}
}