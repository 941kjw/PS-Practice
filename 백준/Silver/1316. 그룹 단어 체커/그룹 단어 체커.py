class ContinueI(Exception):
    pass


count = int(input(""))

unique_count = 0
for i in range(count):
    word = input("")
    word_set = set()
    last_c = ""
    try:
        for c in word:
            if c not in word_set:
                word_set.add(c)
            elif last_c != c and c in word_set:
                raise ContinueI
            last_c = c
    except ContinueI:
        continue
    unique_count += 1

print(unique_count)
