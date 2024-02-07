def solution(str1, str2):
    set1 = []
    set2 = []
    for idx in range(len(str1)-1):
        if str1[idx].isalpha() and str1[idx+1].isalpha():
            set1.append(''.join(str1[idx].lower()+str1[idx+1].lower()))
    for idx in range(len(str2)-1):
        if str2[idx].isalpha() and str2[idx+1].isalpha():
            set2.append(''.join(str2[idx].lower()+str2[idx+1].lower()))

    temp_1 = set1.copy()
    result_hap = set1.copy()
    result_gyo = []

    for i in set2:
        if i in temp_1:
            result_gyo.append(i)
            temp_1.remove(i)
        else:
            result_hap.append(i)

    return int(len(result_gyo)/len(result_hap)*65536) if result_hap else 65536