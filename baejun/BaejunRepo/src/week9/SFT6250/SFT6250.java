package week9.SFT6250;

import java.io.*;
import java.util.*;

class Person {
	int number;
	int firstCompetitionScore;
	int secondCompetitionScore;
	int thirdCompetitionScore;
	int totalScore;
	int firstCompetitionRank;
	int secondCompetitionRank;
	int thirdCompetitionRank;
	int totalRank;
}

public class SFT6250 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(in.readLine());
		Person[] persons = new Person[N];
		for(int i = 0; i < N; i++) {
			persons[i] = new Person();
			persons[i].number = i;
		}

		// 첫 대회 점수입력
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			persons[i].firstCompetitionScore = Integer.parseInt(st.nextToken());
		}
		// 둘째 대회 점수입력
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			persons[i].secondCompetitionScore = Integer.parseInt(st.nextToken());
		}
		// 셋째 대회 점수입력
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			persons[i].thirdCompetitionScore = Integer.parseInt(st.nextToken());
		}
		// 종합 점수
		for(int i = 0; i < N; i++) {
			persons[i].totalScore = persons[i].firstCompetitionScore + persons[i].secondCompetitionScore + persons[i].thirdCompetitionScore;
		}

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

		// 두번째 대회를 기준으로 점수 정렬 후 등수 매기기
		Arrays.sort(persons, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o2.secondCompetitionScore - o1.secondCompetitionScore;
			}
		});
		persons[0].secondCompetitionRank = 1;
		for(int i = 1; i < N; i++) {
			if(persons[i-1].secondCompetitionScore == persons[i].secondCompetitionScore) {
				persons[i].secondCompetitionRank = persons[i-1].secondCompetitionRank;
			} else {
				persons[i].secondCompetitionRank = i+1;
			}
		}

		// 세번째 대회를 기준으로 점수 정렬 후 등수 매기기
		Arrays.sort(persons, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o2.thirdCompetitionScore - o1.thirdCompetitionScore;
			}
		});
		persons[0].thirdCompetitionRank = 1;
		for(int i = 1; i < N; i++) {
			if(persons[i-1].thirdCompetitionScore == persons[i].thirdCompetitionScore) {
				persons[i].thirdCompetitionRank = persons[i-1].thirdCompetitionRank;
			} else {
				persons[i].thirdCompetitionRank = i+1;
			}
		}

		// 종합 점수를 기준으로 점수 정렬 후 등수 매기기
		Arrays.sort(persons, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o2.totalScore - o1.totalScore;
			}
		});
		persons[0].totalRank = 1;
		for(int i = 1; i < N; i++) {
			if(persons[i-1].totalScore == persons[i].totalScore) {
				persons[i].totalRank = persons[i-1].totalRank;
			} else {
				persons[i].totalRank = i+1;
			}
		}

		// 번호 순 정렬
		Arrays.sort(persons, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o1.number - o2.number;
			}
		});

		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();
		StringBuilder sb4 = new StringBuilder();

		for(Person p : persons) {
			sb1.append(p.firstCompetitionRank + " ");
			sb2.append(p.secondCompetitionRank + " ");
			sb3.append(p.thirdCompetitionRank + " ");
			sb4.append(p.totalRank + " ");
		}

		System.out.println(sb1);
		System.out.println(sb2);
		System.out.println(sb3);
		System.out.println(sb4);
	}
}
