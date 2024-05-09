package week15.BOJ1043;

import java.util.*;
import java.io.*;


public class BOJ1043 {
	static int N, M;
	static int[] alreadyKnowTruth;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(in.readLine());
		int idx = Integer.parseInt(st.nextToken());
		int answer = 0;
		
		/* 진실을 아는 사람이 없다면 그냥 싹 다 허풍 */
		if(idx != 0) {
			alreadyKnowTruth = new int[idx];

			for(int i = 0; i < idx; i++) {
				alreadyKnowTruth[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(alreadyKnowTruth);
			parents = new int[N+1];
			makeset();

			ArrayList<ArrayList<Integer>> partyInfo = new ArrayList<>();
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
				int people = Integer.parseInt(st.nextToken());
				partyInfo.add(new ArrayList<>());
				int num1 = Integer.parseInt(st.nextToken());
				partyInfo.get(i).add(num1);
				for(int j = 0; j < people-1; j++) {
					int num2 = Integer.parseInt(st.nextToken());
					partyInfo.get(i).add(num2);
					union(num1, num2);
					num1 = num2;
				}
			}

			answer = 0;

			for(int i = 0; i < partyInfo.size(); i++) {
				boolean flag = true;
				for(int j = 0; j < partyInfo.get(i).size(); j++) {
					int target = find(parents[partyInfo.get(i).get(j)]);
					if(Arrays.binarySearch(alreadyKnowTruth, target) >= 0) {
						flag = false;
						break;
					}
				}
				if(flag) {
					answer++;
				}
			}
		} else {
			answer = M;
		}
		System.out.println(answer);
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		int rootAContainsTruth = Arrays.binarySearch(alreadyKnowTruth, rootA);
		int rootBContainsTruth = Arrays.binarySearch(alreadyKnowTruth, rootB);

		if(rootAContainsTruth >= 0 && rootBContainsTruth < 0) {
			parents[rootB] = rootA;
		} else if(rootAContainsTruth < 0 && rootBContainsTruth >= 0) {
			parents[rootA] = rootB;
		} else {
			parents[rootA] = rootB;
		}
	}

	private static int find(int a) {
		if(parents[a] == a) return a;

		return parents[a] = find(parents[a]);
	}

	private static void makeset() {
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
}