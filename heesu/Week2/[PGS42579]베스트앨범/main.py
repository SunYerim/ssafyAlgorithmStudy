import heapq

def solution(genres, plays):
    answer = []
    dict = {}
    # dict - key: 장르명 , value: heapq에서 배열 [플레이 횟수, i(고유번호)]
    
    genre_count = {}
    # genre_count - 장르별 총 재생횟수 카운트
    
    for i in range(len(genres)):
        if genres[i] in dict:
            heapq.heappush(dict[genres[i]], [-plays[i], i])
            genre_count[genres[i]] += plays[i]
        else:
            dict[genres[i]] = []
            heapq.heappush(dict[genres[i]], [-plays[i], i])
            genre_count[genres[i]] = plays[i]
    
    genre_count = sorted(genre_count.items(), key=lambda x: x[1], reverse=True)
    # 많이 재생된 순서대로 장르 정렬
    
    for genre, _ in genre_count:
        top_songs = dict[genre]
        answer.append(heapq.heappop(top_songs)[1])
        if top_songs: # 여기서 애 먹음. 한 곡만 있는 경우도 고려
            answer.append(heapq.heappop(top_songs)[1])
        
    return answer