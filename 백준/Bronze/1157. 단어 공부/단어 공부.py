word = input("").upper()

candidates = list(set(word))

count = []

for c in candidates:
    cnt = word.count(c)
    count.append(cnt)

if (count.count(max(count))) > 1:
    print('?')
else:
    index = count.index(max(count))
    print(candidates[index])
