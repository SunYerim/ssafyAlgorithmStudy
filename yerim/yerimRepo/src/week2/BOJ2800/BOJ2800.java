package week2.BOJ2800;

// 사전식으로 출력
// string 배열에 담았다가 sort 시켜서 출력?
// 우선 괄호쌍만큼 조합 -> 만들 수 있는 모든 경우의 수
// 결과값을 TreeSet에 답아 중복값을 제거함과 동시에 정렬까지되도록 해준다.



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pair {
    int left, right;

    public Pair(int left, int right) {
        this.left = left;
        this.right = right;
    }
}
public class BOJ2800 {
    static List<Pair> pairs;
    static int pairCount;
    static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        Stack<Integer> s = new Stack<>();
        set = new TreeSet<>();

        pairs = new ArrayList<>();

        int inputSize = line.length();

        for (int i = 0; i < inputSize; i++) {
            char cur = line.charAt(i);
            // 괄호 만났을 때
            if(cur == '(') {
                s.add(i);
            } else if (cur == ')') {
                pairs.add(new Pair(s.pop(), i));
            }
        }

        pairCount = pairs.size();
        makeString(0, line);

        // 입력값 제거
        set.remove(line);

        StringBuilder ans = new StringBuilder();
        for (String s1 : set) {
            ans.append(s1).append('\n');
        }
        System.out.println(ans);
    }

    static void makeString(int index, String str) {
        if (index == pairCount) {
            // 공백 제거 후 set에 add
            set.add(str.replaceAll(" ", ""));
            return;

        }

        // 현재 괄호 제거
        Pair pair = pairs.get(index);
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(pair.left, ' ');
        sb.setCharAt(pair.right, ' ');
        makeString(index + 1, sb.toString());

        // 제거 안 함
        makeString(index + 1, str);
    }
}
