def solution(cacheSize, cities):
    time = 0
    curTime = 0
    cache_dict = {}
    for city in cities:
        city = city.lower()
        curTime += 1
        if city in cache_dict: # cache hit
            cache_dict[city] = curTime
            time += 1
        else:
            time += 5
            cache_dict[city] = curTime
            if len(cache_dict) > cacheSize:
                cache_dict = dict(sorted(cache_dict.items(), key = lambda x:x[1]))
                min_key = min(cache_dict, key=cache_dict.get)
                cache_dict.pop(min_key)

    return time