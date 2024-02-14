package week3.BOJ_11725_트리의부모찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;

import javax.management.Query;

public class BOJ_11725_트리의부모찾기 {
	
	static int N;
	static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
	static StringBuffer sb = new StringBuffer();
	static int[] n_count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int tmp = 1;
		n_count = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {

			String s = br.readLine();
			String[] s2 = s.split(" ");

			tree.get(Integer.parseInt(s2[0])).add(Integer.parseInt(s2[1]));
			tree.get(Integer.parseInt(s2[1])).add(Integer.parseInt(s2[0]));
		}
		
		Queue<Integer> qu = new ArrayDeque<>();
		qu.add(1);
		while(!qu.isEmpty()) {
			tmp = qu.peek();
			
			for(int i=0;i<tree.get(tmp).size();i++) {
				if(n_count[tree.get(tmp).get(i)] == 0) {
					n_count[tree.get(tmp).get(i)] = tmp;
					qu.add(tree.get(tmp).get(i));
				}
				else if(n_count[tree.get(tmp).get(i)] == 1) continue;
				
			}
			qu.poll();
		}

		
		for(int i=2;i<=N;i++) {
			System.out.println(n_count[i]);
		}
	}
}


