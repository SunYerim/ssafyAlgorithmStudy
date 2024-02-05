package week2.BOJ_16928_뱀과사다리게임;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BOJ_16928_뱀과사다리게임 {

	// 결과를 한 번에 출력하기 위한 StringBuilder
		private static StringBuilder sb = new StringBuilder();

		public static void main(String[] args) throws Exception {

			HashMap<Integer, Integer> ladder_hash = new HashMap<Integer, Integer>();
			HashMap<Integer, Integer> ladder_hash2 = new HashMap<Integer, Integer>();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			String s = br.readLine();
			String[] s2 = s.split(" ");
			int ladder = Integer.parseInt(s2[0]);
			int snake = Integer.parseInt(s2[1]);
			int[] arr = new int[101];
			int point = 1;
			int max = 0;
			int count = 0;
			int ld_max = 0;
			int ld_sum =0;
			int point_sum = 0;
			
			for(int i=0;i<ladder;i++) {
				String input = br.readLine();
				String[] input2 = input.split(" ");
				ladder_hash.put(Integer.parseInt(input2[0]), Integer.parseInt(input2[1]));
				ladder_hash2.put(Integer.parseInt(input2[1]), Integer.parseInt(input2[0]));
				arr[Integer.parseInt(input2[0])] = 1;
				ld_sum += Integer.parseInt(input2[1]);
				point_sum += Integer.parseInt(input2[0]);
				if(ld_max<Integer.parseInt(input2[1])) {
					ld_max = Integer.parseInt(input2[1]);
				}
			}
			
			ld_sum = ld_sum/6;
			point_sum = point_sum/6;
			
			for(int i=0;i<snake;i++) {
				String input = br.readLine();
				String[] input2 = input.split(" ");
				arr[Integer.parseInt(input2[0])] = 2;
			}
			
			while(point!=100) {
				for(int j = 1;j<=6;j++) {
					if(ladder_hash.containsKey(point+j)) {
						if(max<ladder_hash.get(point+j)) {
							max=ladder_hash.get(point+j);
						}
					}
				}
				
				if(max!=0) {
					if(ld_sum > point_sum) {
						if(point<=ladder_hash2.get(ld_max) && point+6>=ladder_hash2.get(ld_max)) {
							point = ladder_hash.get(ladder_hash2.get(max));
							count++;
						}
						else {
							continue;
						}
					}
					else {
						point = ladder_hash.get(ladder_hash2.get(max));
						count++;
					}
				}
				else {
					for(int j=6;j>0;j--) {
						if(point + j >100) {
							continue;
						}
						else if(arr[point + j] != 2) {
							point += j;
							count++;
							break;
						}
					}
				}
				
				max=0;
				
			}
			
			sb.append(count).append('\n');
			
			System.out.println(sb);
		}
}
