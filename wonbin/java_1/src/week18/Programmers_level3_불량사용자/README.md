# [Programmers][level3] 불량사용자 / (AC)

---

> **문제 설명**
> 

[[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/64064)]

[문제 내용 전체]

개발팀 내에서 이벤트 개발을 담당하고 있는 "무지"는 최근 진행된 카카오이모티콘 이벤트에 비정상적인 방법으로 당첨을 시도한 응모자들을 발견하였습니다. 이런 응모자들을 따로 모아 `불량 사용자`라는 이름으로 목록을 만들어서 당첨 처리 시 제외하도록 이벤트 당첨자 담당자인 "프로도" 에게 전달하려고 합니다. 이 때 개인정보 보호을 위해 사용자 아이디 중 일부 문자를 '*' 문자로 가려서 전달했습니다. 가리고자 하는 문자 하나에 '*' 문자 하나를 사용하였고 아이디 당 최소 하나 이상의 '*' 문자를 사용하였습니다.

"무지"와 "프로도"는 불량 사용자 목록에 매핑된 응모자 아이디를 `제재 아이디` 라고 부르기로 하였습니다.

예를 들어, 이벤트에 응모한 전체 사용자 아이디 목록이 다음과 같다면

응모자 아이디

---

frodo

---

fradi

---

crodo

---

abc123

---

frodoc

---

다음과 같이 불량 사용자 아이디 목록이 전달된 경우,

불량 사용자

---

fr*d*

---

abc1**

---

불량 사용자에 매핑되어 당첨에서 제외되어야 야 할 제재 아이디 목록은 다음과 같이 두 가지 경우가 있을 수 있습니다.

제재 아이디

---

frodo

---

abc123

---

제재 아이디

---

fradi

---

abc123

---

이벤트 응모자 아이디 목록이 담긴 배열 user_id와 불량 사용자 아이디 목록이 담긴 배열 banned_id가 매개변수로 주어질 때, 당첨에서 제외되어야 할 제재 아이디 목록은 몇가지 경우의 수가 가능한 지 return 하도록 solution 함수를 완성해주세요.

---

> **제한사항**
> 

입력/출력 제한사항

### **[제한사항]**

- user_id 배열의 크기는 1 이상 8 이하입니다.
- user_id 배열 각 원소들의 값은 길이가 1 이상 8 이하인 문자열입니다.
    - 응모한 사용자 아이디들은 서로 중복되지 않습니다.
    - 응모한 사용자 아이디는 알파벳 소문자와 숫자로만으로 구성되어 있습니다.
- banned_id 배열의 크기는 1 이상 user_id 배열의 크기 이하입니다.
- banned_id 배열 각 원소들의 값은 길이가 1 이상 8 이하인 문자열입니다.
    - 불량 사용자 아이디는 알파벳 소문자와 숫자, 가리기 위한 문자 '*' 로만 이루어져 있습니다.
    - 불량 사용자 아이디는 '*' 문자를 하나 이상 포함하고 있습니다.
    - 불량 사용자 아이디 하나는 응모자 아이디 중 하나에 해당하고 같은 응모자 아이디가 중복해서 제재 아이디 목록에 들어가는 경우는 없습니다.
- 제재 아이디 목록들을 구했을 때 아이디들이 나열된 순서와 관계없이 아이디 목록의 내용이 동일하다면 같은 것으로 처리하여 하나로 세면 됩니다.

---

> **문제 풀이**
> 

본인만의 풀이, 접근 방식 등등

나의 풀이

1. set을 사용하여 조건에 맞는 불량사용자들을 추출함
2. 그중에서 조합을 사용해서 조건에 맞는 불량사용자들의 집합을 추출함
3. 리스트들을 출력

이렇게 하니 하나의 테케가 계속 맞지 않아서 답을 참고 했는데 방법은 거의 똑같았다. 왜 틀린지는 아직도 잘 모르겠다..

---

> **코드**
> 

```java
import java.util.*;

class Solution {
    
    static HashSet<String> result_id;
    static ArrayList<String> list;
    static HashSet<String> result;
    static String[] path;
    static int tmp;
    static int count;
    static int[] visited;
    static int[] length_arr = new int[9];
    static int[] length_arr2 = new int[9];
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        result_id = new HashSet<>();
        result = new HashSet<>();
        
        for(int i =0; i<banned_id.length; i++) {
            length_arr[banned_id[i].length()]++;
        }
        
        for(int i=0;i<user_id.length;i++) {
            for(int j=0;j<banned_id.length;j++) {
                tmp = 0;
                if(user_id[i].length() != banned_id[j].length()) {
                    continue;
                }
                else {
                    for(int k=0;k<user_id[i].length();k++) {
                        if(user_id[i].charAt(k) == banned_id[j].charAt(k)) {
                            tmp++;
                        }
                        else if(banned_id[j].charAt(k) == '*') {
                            tmp++;
                        }
                    }
                    
                    if(tmp == user_id[i].length())
                        result_id.add(user_id[i]);
                }
            }
        }
        
        list = new ArrayList(result_id);
        visited = new int[list.size()];
        path = new String[banned_id.length];
        comb(0, 0, banned_id.length, banned_id);
        answer = result.size();
        
        return answer;
    }
    
    static void comb(int start, int cnt, int r, String[] banned_id) {
        
        if(cnt == r) {
            int check = 0;
            int check2 = 0;
            copyarr();
            for(int i=0;i<path.length;i++) {
                if(path[i] != null) {
                    length_arr2[path[i].length()]--;
                    if(length_arr2[path[i].length()] < 0) {
                        check = 1;
                    }
                }
                else {
                    check2++;
                }
            }
            if(check == 0 && check2 != path.length) {
                String str = "";
                for(int i=0;i<path.length;i++) {
                    str+= path[i];
                }
                System.out.println();
                result.add(str);
            }
            
            return;
        }
        
        for(int i=start;i<list.size();i++) {
            path[cnt] = list.get(i);
            comb(i+1, cnt+1, r, banned_id);
        }
    }
    
    static void copyarr() {
        for(int i=0;i<length_arr.length;i++) {
            length_arr2[i] = length_arr[i];
        }
    }
    
}
```

정답 코드

```java
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
```

---

> **후기**
> 

문제에 대한 짧은 코멘트

- set과 조합을 활용하는 문제라서 기본기가 중요한 문제이다.

문제 풀이 시간/ 실행시간/ 메모리/코드길이

- 2시간? / 135.63ms / 91.9MB / 69줄

알고리즘 분류

- 자료구조
- 조합
- 순열