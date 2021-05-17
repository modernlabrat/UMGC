def run():
    height = checkInt(input("Enter height: "))
    width = checkInt(input(("Enter width: ")))
    
    print("\nDisplaying Rectangle\n")
    
    for i in range(height):
        for j in range(width):
            print('*', end='')
        print()
    
    area = width * height
    
    print('Area:', area)
def checkInt(num):
    if num.isnumeric():
       return int(num)
    else:
       return checkInt(input('int values only. Enter a number: '))
       