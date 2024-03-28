<h3 align="center"> 
    📢  [레벨3] 소프티어(성적 평가) : https://softeer.ai/practice/6250
</h3>

<br>

## 🚀 문제

너무 길어헝
---

## 🚦입출력 + 제한사항

3 ≤ N ≤ 100,000

---

### 📜 문제 풀이(기능 목록, 접근법)
**🕸접근법**
- java 제한이 2초였고, 정렬이 n log n 이니까 그냥 막가파 정렬 후 등수매기기 수법 무조건 먹힌다 생각함
- 먹히긴 먹혔는데, 실행시간보니까 생각보다 오래걸려서 놀람. 어디서 좀 잡아먹는건지몰겠다

- [x] Arrays.sort + Comparator

### 💻코드

```java
// 첫번째 대회를 기준으로 점수 정렬 후 등수 매기기
Arrays.sort(persons, new Comparator<Person>() {
	@Override
	public int compare(Person o1, Person o2) {
		return o2.firstCompetitionScore - o1.firstCompetitionScore;
	}
});
persons[0].firstCompetitionRank = 1;
for(int i = 1; i < N; i++) {
	if(persons[i-1].firstCompetitionScore == persons[i].firstCompetitionScore) {
		persons[i].firstCompetitionRank = persons[i-1].firstCompetitionRank;
	} else {
		persons[i].firstCompetitionRank = i+1;
	}
}
```

### 🙄 후기
소요시간 : 1시간 20분  <br>
이것도 ide 없으니까 compartor를 ..ㅋㅋ