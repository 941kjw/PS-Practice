input = input("")
dial = ["ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"]
count = 0

for c in range(len(input)):
    for i in dial:
        if input[c] in i:
            count += dial.index(i)+3

print(count)
