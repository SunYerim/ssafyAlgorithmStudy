import java.util.*;

class Solution {
    private static Set<String> ansSet;

    public static int solution(String[] user_id, String[] banned_id) {
        ansSet = new LinkedHashSet<>();

        // user_id에서 banned_id의 개수 만큼 뽑는 조합을 만듦
        comb(user_id, banned_id, new LinkedHashSet<>());

        return ansSet.size();
    }

    private static void comb(String[] user_id, String[] banned_id, Set<String> set) {
        // 조합 하나가 만들어 졌으면
        if(set.size() == banned_id.length){
            // 해당 조합이 banned_id와 매칭이 되는지 확인
            if(check(set, banned_id)){
                // 중복되지 않게 정렬하여 저장
                Iterator<String> iter = set.iterator();
                int i=0;
                String[] strArr = new String[banned_id.length];
                while (iter.hasNext()){
                    strArr[i++] = iter.next();
                }
                Arrays.sort(strArr);

                StringBuilder sb = new StringBuilder();
                for (String s : strArr) {
                    sb.append(s).append(" ");
                }

                ansSet.add(sb.toString());
            }
            return;
        }

        // 중복되지 않게 조합을 만듦
        for (String user : user_id) {
            if(!set.contains(user)){
                set.add(user);
                comb(user_id, banned_id, set);
                set.remove(user);
            }
        }
    }

    // 조합과 banned_id가 매칭이 되는지 확인
    private static boolean check(Set<String> set, String[] banned_id) {
        Iterator<String> iter = set.iterator();
        for (int i = 0; i < banned_id.length; i++) {
            if(!isSame(iter.next(), banned_id[i])){
                return false;
            }
        }
        return true;
    }

    // 두 문자열이 매칭 되는지 확인
    private static boolean isSame(String next, String s) {
        if(next.length() != s.length()) return false;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) == '*') continue;
            if(next.charAt(i) != s.charAt(i)) return false;
        }
        return true;
    }
}