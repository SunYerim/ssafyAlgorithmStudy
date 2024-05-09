package week15.BOJ_G4_1043_거짓말;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_G4_1043_거짓말 {

	static int N;
	static int M;
	static int[] parents;
	static ArrayList<Integer> np;
	static ArrayList<Integer>[] party;
	static int[] lie;
	static int tmp;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		String[] s2 = s.split(" ");

		N = Integer.parseInt(s2[0]);
		M = Integer.parseInt(s2[1]);
		parents = new int[N + 1];
		party = new ArrayList[M];
		np = new ArrayList<>();
		lie = new int[M];

		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}

		for (int i = 0; i < M; i++) {
			party[i] = new ArrayList<>();
		}

		s = br.readLine();
		s2 = s.split(" ");

		if (Integer.parseInt(s2[0]) != 0) {
			for (int i = 1; i <= Integer.parseInt(s2[0]); i++) {
				np.add(Integer.parseInt(s2[i]));
			}
		}

		for (int i = 0; i < M; i++) {
			s = br.readLine();
			s2 = s.split(" ");
			for (int j = 1; j <= Integer.parseInt(s2[0]); j++) {
				if (np.size() != 0 && j != Integer.parseInt(s2[0])) {
					union(Integer.parseInt(s2[j]), Integer.parseInt(s2[j + 1]));
				}
				party[i].add(Integer.parseInt(s2[j]));
			}
		}

		if (np.size() == 0) {
			System.out.println(M);
		} else {
			for (int p = 0; p < np.size(); p++) {
				for (int i = 0; i < M; i++) {
					for (int j = 0; j < party[i].size(); j++) {
						int a = find(np.get(p));
						int b = find(party[i].get(j));

						if (a == b) { // 소문이 퍼지는 그룹이면 tmp++
							tmp++;
							break;
						}
					}
					if (tmp == 1) {
						lie[i] = 1;
					}
					tmp = 0;
				}
			}

			for (int i = 0; i < M; i++) { // 소문이 안퍼졌으면 answer++
				if (lie[i] == 0)
					answer++;
			}

			System.out.println(answer);
		}

	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x >= y)
			parents[x] = y;
		else
			parents[y] = x;
	}

	static int find(int x) {
		if (parents[x] == x)
			return x;
		else
			return find(parents[x]);
	}

}
