import boto3
from botocore.exceptions import ClientError, ParamValidationError
from s3 import run
import hashlib

s3_client = boto3.client('s3') # S3 Client
s3_resource = boto3.resource('s3') # S3 Resource
dynamodb = boto3.client('dynamodb')
dynamodb_resource = boto3.resource('dynamodb')

products_bucket = False
users_dynamo = False
logged_in = False

go = False

def register():
    global dynamodb_resource

    if logged_in:
        print('You are logged in.')
    else:
        username = check_for_return(check_username(input("Enter your desired username (min length=6): ")))
        password = hashlib.sha256(check_for_return(check_password_length(input("Enter a Password (min length=8): "))).encode())

        try:
            users_table = dynamodb_resource.Table('Users')
            response = users_table.put_item(
                Item={
                    'Username': username,
                    'Password': password
                }
            )

            print(response)
        except ClientError as ce:
            print("Error", ec)


def check_username(att_username):
    if len(att_username >= 6):
        try:
            response = dynamodb.get_item(TableName="Users", Key={'Username': {'S': att_username}})
            if not response.get("Item") is None:
                return att_username
            else:
                print("Username already in use.")
                register()
        except dynamodb.exceptions.ResourceNotFoundException:
            print("Error: no Users database")
    else:
        print("Username must be 6 or more characters in length.")
        register()

def check_password_length(att_password):
    if len(att_password) >= 8:
        return att_password
    else:
        print("Password must be 8 or more characters in length.")
        return check_for_return(check_password_length(input("Enter a Password (min length=8): ")))
        
def show_password(password):
    show = check_for_return(input("Show Password? (Y/N): "))

    if show in ['Y', 'y', 'N', 'n']:
        if show in ['Y', 'y']:
            print('Password:', password)
    else:
        print("Please enter 'Y' or 'N'.")
        show_password(password)

menu = {
    "1": register,
    "2": login,
    "3": view_catalog,
    "4": add_to_cart,
    "5": checkout,
    "6": view_purchases,
    "7": logout,
    "0": exit_program
}

def exit_program():
    # method that displays date and time then exits program
    current_time = dt.now()
    print("Timestamp: %s Goodbye!" %current_time)
    global run 
    run = False
    sys.exit(0)

def check_for_return(user_input):
    # returns user to menu based on user input
    user_input = user_input.strip()

    if user_input is "<":
        start()
    return user_input  

catalog = {
    "1": "Shirts",
    "2": "Pants",
    "3": "Shoes",
    "4": "Accessories"
}

shirts = {
    "1": "LA Graphic Tee"
}

def start():
    global go
    global dynamodb

    while not (run(dynamodb)):
        loading = "loading"
        dots = "..."

        print(loading, end=" ")
        print(dots)

        dots = ""
    
    go = True
    # method that displays menu and calls the appropriate function based on user input
    while(go):
        print("      MENU      \n1: Register\n2: Login\n3: View Catalog\n4: Add to Cart\n5: Checkout\n6: View Purchases\n7: Logout\n0: Exit Program")
        print("\nEnter '<' to return to the menu at any time." )
        option = input("\nSelect an option: ")
    
        option = option.strip()
    
        if option in menu.keys() and option != "7":
            menu[option]()
        elif option == "7":
            exit_program()
        else:
            print("Please enter a valid menu option\n")
     
start()
