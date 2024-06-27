package week20.PGS92342;

/* logic
    1. 백트레킹 + 조건
    2. 라이언 -> 어피치보다 같은 점수에 화살 하나만 더 꽂히면 라이언이 점수 얻음.*/
class Solution {
    static private int[] res = new int[11];
    static private int[] scores = {-1};
    static private int max = Integer.MIN_VALUE;
    public int[] solution(int n, int[] info) {
        backtracking(0, n, info);

        if (max == -1) {
            scores = new int[1];
            scores[0] = -1;
        }
        return scores;
    }

    // backtracking
    static void backtracking(int depth, int n, int[] info) {
        // 종료
        if (depth == n) {
            int diff = scoreDiff(info);
            if (max <= diff) {
                max = diff;
                scores = res.clone();
            }
            return;
        }

        for (int i = 0; i < info.length && res[i] <= info[i]; i++) {
            res[i] += 1;
            backtracking(depth + 1, n, info);
            res[i] -= 1;
        }
    }

    // 점수차이 구하기
    static int scoreDiff(int[] info) {
        int apeach = 0, lion = 0;
        for (int i = 0; i < res.length; i++) {
            if (info[i] == 0 && res[i] == 0) continue;
            if (info[i] >= res[i]) apeach += (10-i);
            else lion += (10-i);
        }
        int diff = lion - apeach;
        if (diff <= 0) return -1;
        return diff;
    }
}