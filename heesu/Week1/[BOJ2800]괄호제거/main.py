from itertools import combinations

input_str = input()
stack = []
answer = []
answers = set()
for i in range(len(input_str)):
    if input_str[i] == '(':
        stack.append(i)
    elif input_str[i] == ')':
        answer.append((stack.pop(), i))

for i in range(len(answer)):
    for remove in combinations(answer,i+1):
        temp = list(input_str)
        for idx in remove:
            temp[idx[0]] = temp[idx[1]] = ""
        answers.add("".join(temp))

for i in sorted(answers):
    print(i)