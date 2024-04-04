package week8.PGS42895;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    static final int minNum = 8;
    public static int getBasicNumber(int N, int cnt) {
        int res = 0;

        while (cnt > 0) {
            cnt -= 1;
            res += N * Math.pow(10, cnt);
        }

        return res;
    }

    public int solution(int N, int number) {
        if (N == number) {
            return 1;
        }

        int answer = -1;


        // 초기화
        List<Set<Integer>> s = new ArrayList<>(minNum);
        // 각 set마다 기본 수 "N" * i 수 초기화
        int idx = 1;

        for (int i = 0; i < minNum; i++) {
            s.add(new HashSet<>());
        }

        for (Set<Integer> set : s) {
            set.add(getBasicNumber(N, idx));
            idx += 1;
        }

        // n 일반화
        // {
        //     "n" * i,
        //     1번 set 사칙연산 n-1번 set,
        //     2번 set 사칙연산 n-2번 set,
        //     ...
        //     n-1번 set 사칙연산 1번 set,
        // }
        // number를 가장 최소로 만드는 수 구함.
        for (int i = 1; i < minNum; i++) {
            for (int j = 0; j < i; j++) {
                for (int op1 : s.get(j)) {
                    for (int op2 : s.get(i - j - 1)) {
                        s.get(i).add(op1 + op2);
                        s.get(i).add(op1 - op2);
                        s.get(i).add(op1 * op2);

                        if (op2 != 0) {
                            s.get(i).add(op1 / op2);
                        }
                    }
                }
            }

            if (s.get(i).contains(number)) {
                answer = i + 1;
                break;
            }
        }

        return answer;
    }


}
