package week5.SWEA_모의SW역량테스트_핀볼게임;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class SWEA_모의SW역량테스트_핀볼게임 {
	
	static int N;
	static int[][] map;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static int count = 0;
	static int max;
	static holl[] wh = new holl[11];
	
	static class holl{
		int x;
		int y;
		int x_end;
		int y_end;
		public holl(int x, int y, int x_end, int y_end) {
			super();
			this.x = x;
			this.y = y;
			this.x_end = x_end;
			this.y_end = y_end;
		}
		@Override
		public String toString() {
			return "holl [x=" + x + ", y=" + y + ", x_end=" + x_end + ", y_end=" + y_end + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1;test_case<=T;test_case++) {
			bw.append("#"+test_case+""+" ");
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			max = Integer.MIN_VALUE;
			int tmp[] = new int[11];
			int tx[] = new int[11];
			int ty[] = new int[11];
			
			for(int i=0;i<N;i++) {
				String s = br.readLine();
				String[] s2 = s.split(" ");
				
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(s2[j]);
					if(map[i][j] >= 6) {
						tmp[map[i][j]]++;
						if(tmp[map[i][j]] == 2) {
							wh[map[i][j]] = new holl(tx[map[i][j]], ty[map[i][j]], j, i);
							continue;
						}
						tx[map[i][j]] = j;
						ty[map[i][j]] = i;
					}
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j = 0;j<N;j++) {
					if(map[i][j] == 0) {
						for(int k=0;k<4;k++) {
							count = 0;
							int ny = dy[k]+i;
							int nx = dx[k]+j;
							int dir = k;
							int y = i; // 현재 y
							int x = j; // 현재 x
							int h_count = 0;
							
							while(true) {
								
								if(ny>=0 && ny<N && nx>=0 &&nx<N && ((ny==i && nx ==j) || (map[ny][nx] == -1)))
										break; // 종료 조건
									
								if(ny<0 || ny>=N || nx<0 || nx>=N) { // 벽을 만났을 때
									if(dir + 2 >=4)
										dir = (dir+2) - 4;
									else
										dir+=2;
									ny = dy[dir] + ny;
									nx = dx[dir] + nx;
									count++;
									y = ny;
									x = nx;
								}
								else if (map[ny][nx] == 0){
									ny = dy[dir] + ny;;
									nx = dx[dir] + nx;;
									y = ny;
									x = nx;
								}
								else if(map[ny][nx] == 1) { // 1번 삼각형일 경우
									if(dir == 0 ) { // 윗방향일 경우
										dir = 2; // 반대(아래)로 가기
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
									else if(dir == 1) { // 우측일 경우
										dir = 3; // 반대(좌측)으로 가기
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
									else if(dir == 2) { //아래 일 경우
										dir = 1; // 우측으로 꺾임
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
									else if(dir == 3) { //좌측 일 경우
										dir = 0; // 위로 꺾임
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
								}
								else if(map[ny][nx] == 2) { // 2번 삼각형일 경우
									if(dir == 0 ) { // 윗방향일 경우
										dir = 1; // 우측으로 가기
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
									else if(dir == 1) { // 우측일 경우
										dir = 3; // 반대(좌측)으로 가기
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
									else if(dir == 2) { //아래 일 경우
										dir = 0; // 반대(위쪽)로 꺾임
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
									else if(dir == 3) { //좌측 일 경우
										dir = 2; // 밑으로 꺾임
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
								}
								else if(map[ny][nx] == 3) { // 3번 삼각형일 경우
									if(dir == 0 ) { // 윗방향일 경우
										dir = 3; // 좌측으로 가기
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
									else if(dir == 1) { // 우측일 경우
										dir = 2; // 아래로 가기
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
									else if(dir == 2) { //아래 일 경우
										dir = 0; // 반대(위쪽)로 꺾임
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
									else if(dir == 3) { //좌측 일 경우
										dir = 1; // 반대(우측)로 꺾임
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
								}
								else if(map[ny][nx] == 4) { // 4번 삼각형일 경우
									if(dir == 0 ) { // 윗방향일 경우
										dir = 2; // 반대(아래)로 가기
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
									else if(dir == 1) { // 우측일 경우
										dir = 0; // 위쪽으로 가기
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
									else if(dir == 2) { //아래 일 경우
										dir = 3; // 죄측으로 꺾임
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
									else if(dir == 3) { //좌측 일 경우
										dir = 1; // 반대(우측)로 꺾임
										ny = dy[dir] + ny;;
										nx = dx[dir] + nx;;
										count++;
										y = ny;
										x = nx;
									}
								}
								else if(map[ny][nx] == 5) { // 정사각형일 경우
									if(dir + 2 >=4)
										dir = (dir+2) - 4;
									else
										dir+=2; // 무조건 반대로
									ny = dy[dir] + ny;;
									nx = dx[dir] + nx;;
									count++;
									y = ny;
									x = nx;
								}
								else if(map[ny][nx] >= 6){ // 웜홀을 만나면
									if(ny == wh[map[ny][nx]].y && nx == wh[map[ny][nx]].x) {
										int y1 = ny;
										int x1 = nx;
										ny = wh[map[y1][x1]].y_end + dy[dir];
										nx = wh[map[y1][x1]].x_end + dx[dir];
									}
									else if(ny == wh[map[ny][nx]].y_end && nx == wh[map[ny][nx]].x_end) {
										int y1 = ny;
										int x1 = nx;
										ny = wh[map[y1][x1]].y + dy[dir];
										nx = wh[map[y1][x1]].x + dx[dir];
									}
								}
							}
							
							max = Math.max(max, count);
						}
					}
				}
			}
			bw.append(max+""+"\n");
			bw.flush();
		}
		bw.close();
	}
	

}
