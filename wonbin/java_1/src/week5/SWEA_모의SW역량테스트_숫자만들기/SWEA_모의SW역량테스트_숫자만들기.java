package week5.SWEA_모의SW역량테스트_숫자만들기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_모의SW역량테스트_숫자만들기 {
	
	static int N;
	static int[] math;
	static int[] m_count;
	static int[] path;
	static int[] number;
	static int min;
	static int max;
	static int[] visited;
	static int answer;
	static int tmp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1;test_case<=T;test_case++) {
			bw.append("#"+test_case+""+" ");
			
			N = Integer.parseInt(br.readLine());
			math = new int[N+1];
			m_count = new int[N];
			visited = new int[N];
			path = new int[N];
			number = new int[N + 1];
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			tmp = 1;
			
			String s = br.readLine();
			String[] s2 = s.split(" ");
			
			for(int i=1;i<=s2.length;i++) {
				math[i] = Integer.parseInt(s2[i-1]); // 연산자 개수 저장
			}
			
			for(int i=1;i<N;i++) {
				if(math[i] != 0) {
					for(int j = 0;j<math[i];j++) {
						m_count[tmp] = i; // 순열을 위한 인덱스 저장
						tmp++;
					}
				}
			}
			
			s = br.readLine();
			s2 = s.split(" ");
			
			for(int i=1;i<N+1;i++) {
				number[i] = Integer.parseInt(s2[i-1]); // 수식에 사용되는 숫자 저장
			}
			
			Permutation(1);
			
			answer = max - min;
			
			bw.append(answer+""+"\n");
			bw.flush();
		}
		
		bw.close();
		
	}
	
	static void Permutation(int cnt) {
		if(cnt == N) {
			int sum = number[1];
			for(int i=1;i<cnt;i++) {
				if(path[i] == 1)
					sum += number[i+1];
				else if(path[i] == 2)
					sum -=number[i+1];
				else if(path[i] == 3)
					sum *=number[i+1];
				else if(path[i] == 4)
					sum /=number[i+1];
			}
			min = Math.min(min, sum);
			max = Math.max(max, sum);
		}
		
		for(int i = 1;i<tmp;i++) {
			if(visited[i] != 0)
				continue;
			path[cnt] = m_count[i];
			visited[i]++;
			Permutation(cnt+1);
			path[cnt] = 0;
			visited[i]--;
		}
	}
}
