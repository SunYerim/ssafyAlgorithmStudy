package PGS42579;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	public int[] solution(String[] genres, int[] plays) {
		int[] answer;
		int index = 0;
		Map<String, Integer> genrePlays = new HashMap<>();
		Map<String, ArrayList<Music>> genreMusics = new HashMap<>();
		List<String> genreSetList;
		List<Integer> playLists = new ArrayList<>();

		for (int i = 0; i < genres.length; i++) {
			if (genrePlays.get(genres[i]) == null) {
				genrePlays.put(genres[i], plays[i]);
			} else {
				genrePlays.put(genres[i], genrePlays.get(genres[i]) + plays[i]);
			}
			if (genreMusics.get(genres[i]) == null) {
				genreMusics.put(genres[i], new ArrayList<Music>());
			}
			genreMusics.get(genres[i]).add(new Music(i, plays[i]));
		}

		genreSetList = new ArrayList<String>(genrePlays.keySet());

		Collections.sort(genreSetList, (o1, o2) -> genrePlays.get(o2) - genrePlays.get(o1));

		for (String genre : genreSetList) {
			Collections.sort(genreMusics.get(genre), (o1, o2) -> o2.plays - o1.plays);
			for (int i = 0; i < Math.min(genreMusics.get(genre).size(), 2); i++) {
				playLists.add(genreMusics.get(genre).get(i).idx);
			}
		}
		answer = new int[playLists.size()];
		for (int num : playLists) {
			answer[index++] = num;
		}

		return answer;
	}

// 테스트 코드
	public static void main(String[] args) {
		String[] genres = { "classic", "pop", "classic", "classic", "pop" };
		int[] plays = { 500, 600, 150, 800, 2500 };
		Solution sol = new Solution();
		System.out.println(Arrays.toString(sol.solution(genres, plays)));
	}

}

class Music {
	int idx;
	int plays;

	public Music(int idx, int plays) {
		this.idx = idx;
		this.plays = plays;
	}
}