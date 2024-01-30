package week1.BOJ2800;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2800 {
	private static String[] expressionArr;
	private static ArrayList<int[]> pairsOfBracket;
	private static ArrayList<String> differentExpression;
	private static boolean[] visited;

	/* 괄호쌍 pair를 인덱스번호로 저장 */
	private static void findPairsOfBracket() {
		int[] leftBracket = new int[10];
		int leftBracketIndex = -1;
		for(int i = 0; i < expressionArr.length; i++) {
			if (expressionArr[i].equals("(")) {
				leftBracket[++leftBracketIndex] = i;
			} else if (expressionArr[i].equals(")")) {
				int[] arr = {leftBracket[leftBracketIndex], i};
				pairsOfBracket.add(arr);
				leftBracket[leftBracketIndex--] = 0;
			}
		}
	}

	/* nCr 형태로 combination 메서드 호출 */
	private static void makeDifferentExpression() {
		for(int i = 1; i <= pairsOfBracket.size(); i++) {
			combination(pairsOfBracket, visited, 0, i);
		}
	}

	/* 재귀호출로 숫자 선정하여 조건에 맞는 수식 중복체크 후 생성 */
	private static void combination(ArrayList<int[]> arrList, boolean[] visited, int start, int r) {
		if(r == 0) {
			ArrayList<Integer> checkedBracket = new ArrayList<>();
			for(int i = 0; i < visited.length; i++) {
				if(visited[i]) {
					checkedBracket.add(pairsOfBracket.get(i)[0]);
					checkedBracket.add(pairsOfBracket.get(i)[1]);
				}
			}
			String newExpression = new String();
			for(int i = 0; i < expressionArr.length; i++) {
				if(!checkedBracket.contains(i)) {
					newExpression += expressionArr[i];
				}
			}
			if(!differentExpression.contains(newExpression)) {
				differentExpression.add(newExpression);
			}
			
		} else {
			for(int i = start; i < pairsOfBracket.size(); i++) {
				visited[i] = true;
				combination(pairsOfBracket, visited, i + 1, r - 1);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String expression = in.readLine(); // 수식 입력값

		expressionArr = new String[expression.length()];
		expressionArr = expression.split("");
		pairsOfBracket = new ArrayList<int[]>();
		differentExpression = new ArrayList<>();
		visited = new boolean[pairsOfBracket.size()];

		findPairsOfBracket();
		makeDifferentExpression();
		/* 사전 순 정렬 후 출력 */ 
		Collections.sort(differentExpression);
		for(String e : differentExpression) {
			System.out.println(e);
		}
	}
}