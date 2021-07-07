import sys
import hashlib

customers = []
logged_in = False
current_customer = ''
hackable = True

def sign_up():
    global customers
    global hackable

    username = input("Enter Username: ")
    password = input("Enter Password: ")
    
    #.encode('UTF-8') # add this to the end of line 14

    #password = hashlib.sha256(password).hexdigest()
    #hackable = False

    customers.append({"username": username, "password": password})

def login():
    global customers
    global logged_in
    global current_customer

    username = input("Enter Username: ")

    for customer in customers:
        if username == customer["username"]:
            password = input("Enter Password: ")
            if password == customer["password"]:
                logged_in = True
                current_customer = customer["username"]
            else:
                print("Invalid Password")
        else:
            print("Username not found.")

def shop():
    global logged_in
    global current_customer

    if logged_in:
        print("You are logged in as:", current_customer)
    else:
        print("You are not logged in.")

def hack():
    global customers
    global hackable

    if hackable:
        if len(customers) > 0:
            print("We are a secret hacker's organization and we have hacked your database. Now, we have your emails and passwords.")
            print("Thanks for making it extremely easy for us to hack into your customer's accounts! Hopefully they use these passwords on other websites")
            print("Should've at least encrypted your data!")
        else:
            print("We hacked the database and luckily there are no customers to exploit.")
    else:
        print("We were able to hack into the database, but can't seem to crack any of these random passwords! Better luck next time.")
    
    for customer in customers:
        print(customer)

def menu():
    menu_options = ["1", "2", "3", "4"]

    print("1: register")
    print("2: login")
    print("3: shop")
    print("4: hack")
    
    option = input("Choose an option: ")

    if option in menu_options:
        if option == "1":
            sign_up()
        elif option == "2":
            login()
        elif option == "3":
            shop()
        else:
            hack()
    else:
        print("Please select a menu option.")
    
    menu()

try:
    menu()
except KeyboardInterrupt:
    sys.exit(0)


