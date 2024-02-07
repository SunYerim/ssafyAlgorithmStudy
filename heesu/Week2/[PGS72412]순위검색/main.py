def solution(info, query):
    answer = []
    dict = {}
    for idx, member_info in enumerate(info):
        lang, pos, career, food, score = member_info.split(" ")
        score = int(score)
        dict[idx] = {"score": score, "lang":lang, "pos":pos, "career":career, "food":food}
    for q in query:
        lang, pos, career, foodAndScore = q.split(" and ")
        food, score = foodAndScore.split(" ")
        score = int(score)
        
        count = 0
        for key, value in dict.items():
            if score <= value['score'] and (lang == '-' or value['lang'] == lang) and (pos == '-' or value['pos'] == pos) and (career == '-' or value['career'] == career) and (food == '-' or value['food'] == food):
                count+=1
        answer.append(count)
    
    return answer


