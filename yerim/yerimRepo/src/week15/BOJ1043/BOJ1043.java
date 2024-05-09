package week15.BOJ1043;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1043 {
    static int n, m;
    static Set<Integer> truth = new HashSet<>(); // 진실을 아는 사람들의 집합
    static Set<Integer>[] parties; // 각 파티에 참가하는 사람들 집합
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int truthSize = Integer.parseInt(st.nextToken());
        for (int i = 0; i < truthSize; i++) {
            truth.add(Integer.parseInt(st.nextToken()));
        }

        parties = new HashSet[m+1];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int partySize = Integer.parseInt(st.nextToken());
            parties[i] = new HashSet<>();
            for (int j = 0; j < partySize; j++) {
                parties[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        System.out.println(getMaxParties());

    }

    // 과장된 이야기를 할 수 있는 파티의 최댓값을 구하는 메서드
    static int getMaxParties() {
        int maxParties = m; // 과장된 이야기를 할 수 있는 파티의 최댓값
        boolean[] knowsTruth = new boolean[m+1]; // 각 파티가 진실을 아는 사람이 있으면 true로 표시

        // 각 파티를 순회하면서 진실을 아는 사람이 있는지 확인
        for (int i = 1; i <= m; i++) {
            for (int person : parties[i]) {
                if (truth.contains(person)) {
                    knowsTruth[i] = true;
                    break;
                }
            }
        }

        // 과장된 이야기를 할 수 있는 파티의 개수 계산
        for (boolean knows : knowsTruth) {
            if (knows) {
                maxParties--;
            }
        }

        return maxParties;
    }


}
