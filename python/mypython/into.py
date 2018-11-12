output = []
for i in range(1, 5):
    for j in range(1, 5):
        for k in range(1,5):
            if i!= j and i!= k and j!=k:
                str1 = ''
                str1 = str1 + str(i) + str(j) + str(k)
                print(str1)
                output.append(str1)

print(output)