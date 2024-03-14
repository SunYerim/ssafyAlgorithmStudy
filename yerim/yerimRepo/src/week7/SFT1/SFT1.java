package week7.SFT1;

/* logic
    1. 모든 염기서열의 문자 위치마다 들어갈 수 있는 글자와 count를 토대로
    2. 각 자리 문자에서 꼭 들어가야되는 글자의 수가 max인 것이 답이 되는 거 같음*/
import java.io.*;
import java.util.*;

public class SFT1 {
    static int n, m;
    static String[] list1;
    static int[] count;
    static HashSet<Character>[] list2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list1 = new String[n];
        list2 = new HashSet[m];
        count = new int[m];

        for (int i = 0; i < n; i++) {
            list1[i] = br.readLine();
        }
        for (int i = 0; i < m; i++) {
            list2[i] = new HashSet<>();
        }

        // 각 자리 수 돌면서 반드시 들어가야하는 글자 수 세아리는 메서드
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (list1[j].charAt(i) != '.') {
                    list2[i].add(list1[j].charAt(i));
                }
            }
        }
        for (int i = 0; i < m; i++) {
            count[i] = list2[i].size();
        }
        Arrays.sort(count);
        System.out.println(count[count.length-1]);
    }
}