import java.util.*;
import java.io.*;

public class BOJ_11000 {
    public static int N;
    public static List<Lecture> list;
    public static StringTokenizer st;

    public static class Lecture {
        public int s, e;

        public Lecture(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        list = new ArrayList<>();
        N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            list.add(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        list.sort(((o1, o2) -> o1.s != o2.s ? o1.s - o2.s : o1.e - o2.e));
        int cnt = 1;
        int maxCnt = 0;
        int end = list.get(0).e;
        for (int i = 1; i < N; i++) {
            if (list.get(i).s < end) {
                cnt++;
            } else {
                maxCnt = Math.max(maxCnt, cnt);
                cnt = 1;
                end = list.get(i).e;
            }
        }
        maxCnt = Math.max(maxCnt, cnt);
        System.out.println(maxCnt);
    }
}