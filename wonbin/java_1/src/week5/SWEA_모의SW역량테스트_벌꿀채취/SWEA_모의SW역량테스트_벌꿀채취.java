package week5.SWEA_모의SW역량테스트_벌꿀채취;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class SWEA_모의SW역량테스트_벌꿀채취 {
	
	static int N; // 벌집 크기
	static int M; // 선택할 꿀통 개수
	static int C; // 꿀통의 최대양
	static int[][] bees;
	static int[][] visited;
	static int[] honey1;
	static int[] honey2;
	static ArrayList<Integer> answer1;
	static ArrayList<Integer> answer2;
	static int[] path = new int[5];
	static int sum = 0;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			bw.append("#" + test_case + "" + " ");
			
			String s = br.readLine();
			String[] s2 = s.split(" ");
			
			N = Integer.parseInt(s2[0]);
			M = Integer.parseInt(s2[1]);
			C = Integer.parseInt(s2[2]);
			bees = new int[N][N];
			visited = new int[N][N];
			honey1 = new int[M];
			honey2 = new int[M];
			answer1 = new ArrayList<>();
			answer2 = new ArrayList<>();
			int d_cnt = 0;
			int d_cnt2 = 0;
			int result = 0;
			
			for(int i=0;i<N;i++) {
				s = br.readLine();
				s2 = s.split(" ");
				for(int j=0;j<N;j++) {
					bees[i][j] = Integer.parseInt(s2[j]);
				}
			}
			
			for(int i=0;i<N;i++) {
				
				for(int j=0;j<N;j++) {
					int tmp = 0;
					int count = 0;
					answer1.clear();
					
					for(int c = j;c<j+M;c++) {
						if(c>=N) {
							d_cnt++;
							break;
						}
						honey1[tmp] = bees[i][c];
						visited[i][c] = 1;
						count += honey1[tmp];
						tmp++;
					}
					if(d_cnt!= 0) {
						d_cnt = 0;
						continue;
					}
					
					if(count > C) {
						Arrays.sort(honey1);
						for(int aa = 1;aa<=tmp;aa++) {
							max = 0;
							comb(0,0,aa,1);
						}
					}
					else {
						for(int c=tmp-1;c>=0;c--) {
							answer1.add(honey1[c]);
						}
					}
					
					for(int k=0;k<N;k++) {
						for(int l=0;l<N;l++) {
							sum = 0;
							d_cnt2 = 0;
							int tmp2 = 0;
							int count2 = 0;
							answer2.clear();
							
							for(int b = l;b<l+M;b++) {
								if(b>=N) break;
								
								if(visited[j][b] != 0) {
									d_cnt2++;
									break;
								}
								else {
									honey2[tmp2] = bees[j][b];
									count2 += honey2[tmp2];
									tmp2++;
								}
							}
							
							if(d_cnt2 != 0) {
								d_cnt2 = 0;
								continue;
							}
							
							if(count2 > C) {
								Arrays.sort(honey2);
								for(int aa = 1;aa<=tmp;aa++) {
									max = 0;
									comb(0,0,aa,2);
								}
								count2 = C;
							}
							else {
								for(int b=tmp2-1;b>=0;b--) {
									answer2.add(honey2[b]);
								}
							}
							
							for(int ab=0;ab<answer1.size();ab++) {
								sum+= (answer1.get(ab) * answer1.get(ab));
							}
							
							for(int ab=0;ab<answer2.size();ab++) {
								sum+= (answer2.get(ab) * answer2.get(ab));
							}
							
							result = Math.max(result, sum);
						}
					}
					
					for(int c = j;c<j+M;c++) {
						visited[i][c] = 0;
					}
					
				}
				
			}
			
			
			bw.append(result+"");
			bw.append("\n");
			bw.flush();
			
		}
		
		bw.close();
	}
	
	static void comb(int start, int cnt, int lev, int answer) {
		
		if(cnt == lev) {
			int sum1 = 0;
			for(int i=0;i<5;i++) {
				if(path[i] != 0) {
					sum1+=path[i];
				}
			}
			
			if(sum1 <= C) {
				if(max<sum1 && answer == 1) {
					answer1.clear();
					max = sum1;
					for(int i=0;i<5;i++) {
						if(path[i] != 0) {
							answer1.add(path[i]);
						}
					}
				}
				
				if(max<sum1 && answer == 2) {
					answer2.clear();
					max = sum1;
					for(int i=0;i<5;i++) {
						if(path[i] != 0) {
							answer2.add(path[i]);
						}
					}
				}
			}
		}
		
		for(int i=start; i<M;i++) {
			path[cnt] = i;
			comb(i+1,cnt+1,lev, answer);
		}
		
	}
}
