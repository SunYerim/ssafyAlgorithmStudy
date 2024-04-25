
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test {

	static int N;
	static int M;
	static ArrayList<Integer>[] list;
	static int[] group;
	static int[] people;
	static int know;
	static int[] knowlist;
	static int answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		String[] s2 = s.split(" ");
		
		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		group = new int[M+1];
		people = new int[N+1];
		
		s = br.readLine();
		s2 = s.split(" ");
		
		know = Integer.parseInt(s2[0]);
		list = new ArrayList[M+1];
		
		for(int i=0;i<=M;i++) {
			list[i] = new ArrayList<>();
		}
		
		if(know != 0) {
			knowlist = new int[know+1];
			for(int i=1;i<=know;i++) {
				knowlist[i] = Integer.parseInt(s2[i]);
			}
		}
		
		for(int i=1;i<=M;i++) {
			s = br.readLine();
			s2 = s.split(" ");
			int c = Integer.parseInt(s2[0]);
			
			for(int j=1;j<=c;j++) {
				list[i].add(Integer.parseInt(s2[j]));
			}
		}
		
		if(know != 0) {
			for(int i=1;i<=know;i++) {
				for(int j=1;j<=M;j++) {
					for(int k=0;k<list[j].size();k++) {
						if(list[j].get(k) == knowlist[i]) {
							for(int t=0;t<list[j].size();t++) {
								people[list[j].get(t)] = list[j].get(t);
							}
							break;
						}
					}
				}
			}
			
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=M;j++) {
					for(int k=0;k<list[j].size();k++) {
						if(list[j].get(k) == people[i]) {
							group[j]++;
							break;
						}
					}
				}
			}
		}
		
		for(int i=1;i<=M;i++) {
			if(group[i] == 0)
				answer++;
		}
		
		bw.append(answer+"");
		bw.append("\n");
		bw.close();
		
	}

	
}
