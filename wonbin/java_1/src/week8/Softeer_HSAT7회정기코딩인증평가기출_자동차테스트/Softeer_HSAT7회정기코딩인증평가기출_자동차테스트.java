package week8.Softeer_HSAT7회정기코딩인증평가기출_자동차테스트;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Softeer_HSAT7회정기코딩인증평가기출_자동차테스트 {
	static int N;
	static int Q;
	static ArrayList<Integer> car;
	static int middle;
	static int[] answer;
	static int[] path;
	
	 public static void main(String[] args) throws IOException{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			String s = br.readLine();
			String[] s2 = s.split(" ");
			
			N = Integer.parseInt(s2[0]);
			Q = Integer.parseInt(s2[1]);
			car = new ArrayList<>();
			answer = new int[Q];
			
			s = br.readLine();
			s2 = s.split(" ");
			
			for(int i=0;i<N;i++) {
				car.add(Integer.parseInt(s2[i]));
			}

			Collections.sort(car);
			
			for(int i=0;i<Q;i++) {
				int tmp = Integer.parseInt(br.readLine());
				
				int cnt = car.indexOf(tmp);
				
				if(binarySearch(0, car.size(), cnt) == 1) {
					int a1 = cnt;
					int a2 = car.size() - cnt - 1;
					if(cnt == 0)
						a1 = 0;
					if(cnt == car.size() - 1)
						a2 = 0;
					
					answer[i] = a1 * a2;
				}
				else
					answer[i] = 0;
				
			}
			
			for(int i=0;i<Q;i++) {
				bw.append(answer[i]+"");
				bw.append("\n");
			}
			bw.flush();
			bw.close();
	    }

	 static int binarySearch(int start, int end, int target) {
		 
		 while(start <= end) {
			 
			 int mid = (start + end) / 2;
			 
			 if(target == mid)
				 return 1;
			 else if(target > mid)
				 start = mid +1;
			 else if(target < mid)
				 end = mid -1;
		 }
		 
		 return 0;
		 
	 }

}
