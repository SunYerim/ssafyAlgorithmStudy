<h3 align="center"> 
    📢  [골드4] 백준(단어 수학) : https://www.acmicpc.net/problem/1339
</h3>

<br>

## 🚀 문제


---

## 🚦입출력 + 제한사항

- 
---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- 그리디
- 모든 단어를 순회하면서 각 알파벳에 대한 기댓값을 매겼음(자리수만큼 숫자 더해가면서)
- 기댓값으로 정렬하여서, 기댓값이 제일 높은 애 부터 9, 8, 7 .. 0 부여

- [x] 그리디

### 💻코드

```java
// 기댓값 계산
for(int i = 0; i < N; i++) {
	int num1 = 1;
	for(int j = words[i].length()-1; j >= 0; j--) {
		char c = words[i].charAt(j);
		if(map.get(c) == null) {
			map.put(c, num1);
		} else {
			int value = map.get(c);
			value += num1;
			map.put(c, value);
		}
		num1 *= 10;
	}
}

// Map 정렬을 위한 keySet List
List<Character> keys = new ArrayList<>(map.keySet());
Collections.sort(keys, (o1, o2) -> (map.get(o2).compareTo(map.get(o1))));

// 기댓값으로 산정된 알파벳의 value를 담을 map2
Map<Character, Integer> map2 = new HashMap<>();
int expector = 9;
for (Character key : keys) {
	map2.put(key, expector);
	expector--;
}
```

### 🙄 후기
소요시간 : 30분  <br>
그리디한 아이디어는 생각보다 괜찮았고 <br>
3주만 쉬어도 거의 처음 하는 어린아이마냥 다 까먹는단걸 여실히 느꼈다 ㅋㅋ