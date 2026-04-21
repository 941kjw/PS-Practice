input = input("")
special = ["c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="]

for s in special:
    input = input.replace(s, str(special.index(s)))

print(len(input))
