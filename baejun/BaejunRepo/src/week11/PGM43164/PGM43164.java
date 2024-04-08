package week11.PGM43164;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class PGM43164 {
	public static void main(String[] args) throws IOException {
		Solution solution = new Solution();
		String[][] tickets = {{"ICN", "AAA"}, {"ICN", "CCC"}, {"CCC", "DDD"}, {"AAA", "BBB"}, {"AAA", "BBB"}, {"DDD", "ICN"}, {"BBB", "AAA"}};
		solution.solution(tickets);
	}
}

class Solution {
	static Map<String, PriorityQueue<String>> map;

	public String[] solution(String[][] tickets) {
		String[] answer = new String[tickets.length+1];
		int answerIdx = -1;

		map = new HashMap<>();
		for(int i = 0; i < tickets.length; i++) {
			if(map.get(tickets[i][0]) == null) { // 없음 생성
				map.put(tickets[i][0], new PriorityQueue<>());
				map.get(tickets[i][0]).add(tickets[i][1]);
			} else { // 있음 queue에 value 추가
				map.get(tickets[i][0]).add(tickets[i][1]);
			}
		}

		String value = "ICN";
		String waiting = "";
		boolean flag = false;
		answer[++answerIdx] = value;

		while(!map.get(value).isEmpty()) {
			answer[++answerIdx] = map.get(value).poll();
			if(map.get(answer[answerIdx]) != null && !map.get(answer[answerIdx]).isEmpty()) {
				value = answer[answerIdx];
			} else {
				if(!flag) {
					flag = true;
					waiting = (answer[answerIdx]);
					answerIdx--;
				}
			}
		}
		value = waiting;
		while(map.get(waiting) != null && !map.get(waiting).isEmpty()) {
			answer[++answerIdx] = map.get(waiting).poll();
			if(map.get(answer[answerIdx]) != null && !map.get(answer[answerIdx]).isEmpty()) {
				value = answer[answerIdx];
			}
		}
		System.out.println(Arrays.toString(answer));
		return answer;
	}
}