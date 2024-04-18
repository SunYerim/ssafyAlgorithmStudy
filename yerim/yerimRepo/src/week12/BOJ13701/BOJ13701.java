package week12.BOJ13701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ13701 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        HashSet<Integer> hashSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for (String tmp : inputs) {
            int a = Integer.parseInt(tmp);
            if (!hashSet.contains(a)) {
                hashSet.add(a);
                sb.append(a).append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}