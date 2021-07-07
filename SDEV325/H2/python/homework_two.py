def run():
    """ Without Input Validation
    """
    expression = input('Enter your expression: ').strip()
    if expression:
        print("Result:", eval(expression))

#run() #comment this out when running go()

def go():
    """With input validation
    """

    expression = input('Enter your expression: ').strip()
    expression_list = expression.split(" ")
    
    try:
        int(expression_list[0])
        operator = expression_list[1]
        int(expression_list[2])

        if operator in ["+", "-", "/", "*"]:
            print("Result:", eval(expression))
        else:
            print("Invalid Expression")
    except:
        print("Invalid Expression")

go() #comment this out when calling run()
