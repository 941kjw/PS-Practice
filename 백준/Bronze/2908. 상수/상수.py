input = input("")
input = input.split()
num1 = input[0]
num2 = input[1]
num1 = int(num1[::-1])
num2 = int(num2[::-1])

if(num1 > num2):
    print(num1)
else:
    print(num2)
