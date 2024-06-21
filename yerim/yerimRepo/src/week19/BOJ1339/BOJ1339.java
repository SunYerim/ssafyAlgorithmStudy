package week19.BOJ1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1339 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        Map<Character, Integer> alphabetWeight = new HashMap<>();

        for (String word : words) {
            int length = word.length();
            for (int i = 0; i < length; i++) {
                char c = word.charAt(i);
                int weight = (int) Math.pow(10, length - i - 1);
                alphabetWeight.put(c, alphabetWeight.getOrDefault(c, 0) + weight);
            }
        }

        List<Integer> weights = new ArrayList<>(alphabetWeight.values());
        Collections.sort(weights, Collections.reverseOrder());

        int maxSum = 0;
        int number = 9;
        for (int weight : weights) {
            maxSum += weight * number;
            number--;
        }

        System.out.println(maxSum);

    }
}