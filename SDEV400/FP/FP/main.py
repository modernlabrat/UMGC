import boto3
import sys
import hashlib
import os
import webbrowser

from botocore.exceptions import ClientError, ParamValidationError
from dynamo import run
from datetime import datetime as dt
from getpass import getpass
from collections import Counter



s3_client = boto3.client('s3') # S3 Client
s3_resource = boto3.resource('s3') # S3 Resource
dynamodb = boto3.client('dynamodb')
dynamodb_resource = boto3.resource('dynamodb')

products_bucket = False
users_dynamo = False
logged_in = False

go = False
cart = []
cart_total = 0.00

catalog = {
    "1": {
        "Shirts": 
            {
                "1": ["LA Graphic Tee", "losangelesgraphic.jpg"],
                "2": ["Pink Short Sleeve", "pinkshortsleeve.jpg"]
            }
        },
    "2": {
        "Pants": 
            {
                "1": ["Distressed Black Jeans", "distressedblackjeans.jpg"],
                "2": ["Khakis", "khakis.jpg"]
            }
        },
    "3": {
        "Shoes": 
            {
                "1": ["Nike Revolution 5", "nike5.jpg"],
                "2": ["Black Classic Crocs", "crocs.jpg"]
            }
        }
}

def register():
    # Prompts user for a username and password to register for app
    global dynamodb_resource
    global logged_in

    if logged_in:
        print('You are logged in.')
    else:
        username = check_username(check_for_return(input("Enter your desired username (min length=6): ")))
        password = confirm_password(check_for_return(check_password_length(getpass("Enter a Password (min length=8, passwords are hidden when typed): "))))
        
        password_hash = hashlib.sha256(password.encode())

        try:
            users_table = dynamodb_resource.Table('Users')
            response = users_table.put_item(
                Item={
                    'Username': username,
                    'Password': password_hash.hexdigest()
                }
            )

            print("Registered and Logged In Successfully")
            logged_in = True
        except ClientError as ce:
            print("Error", ce)


def login(username=""):
    # method that logs in users
    global logged_in
    
    if username == "":    
        username = check_for_return(input("Username: "))

    try:
        response = dynamodb.get_item(TableName="Users", Key={'Username': {'S': username}})
        if response.get("Item") is None:
            print(username, "not found.")
            login()
        else:
            password = check_for_return(getpass("Password (input is hidden): "))
            password_hex = hashlib.sha256(password.encode()).hexdigest()
            
            if response.get("Item").get("Password").get("S") == password_hex:
                logged_in = True
                print("Successfully logged in")
            else:
                print("Invalid Password.")
                login(username)
    except dynamodb.exceptions.ResourceNotFoundException:
        print("Error: no Users database")

def view_catalog(catalog_option="", product_option=""):
    # method that displays catalog and products
    global catalog
    global logged_in
    global cart
    
    if catalog_option == "":
        for key, value in catalog.items():
            for catalog_item in value.keys():
                print(key + ":", catalog_item)
    
        catalog_option = check_for_return(input("Select a Category: "))
    
    if catalog_option in catalog.keys():
        selected_catalog = catalog.get(catalog_option)
        products_list = []
        products_keys = []
        products_values = []
            
        for current_products in selected_catalog.values():
            for key, value in current_products.items():
                products_list.append({key: value})
                
                if product_option == "":
                    print(key + ":", value[0])
            
        if product_option == "":        
            product_option = check_for_return(input("Select a Product: "))
    
        for product_dict in products_list:
            for key, value in product_dict.items():
                products_keys.append(key)

        if product_option in products_keys:
            selected_index = products_keys.index(product_option)
            selected_product = products_list[selected_index].get(product_option)
    
            print("1: Show\n2: Add to Cart")
            selected_option = check_for_return(input("Select an Option: "))
            
            if selected_option in ["1", "2"]:
                if selected_option == "1":
                    for selected_catalog_key in selected_catalog.keys():
                        price = get_price(selected_catalog_key, selected_product[0])
                        print("\nProduct:", selected_product[0])
                        print("Price:", price)
                    image_url = "https://sdev400-products-ks.s3.amazonaws.com/" + selected_product[1]
                    print("\nClick the link below to view the", selected_product[0], "product:", image_url)
                    
                    for selected_catalog_key in selected_catalog.keys():
                        add_to_cart_check(check_for_return(input("Do you want to add this item to your cart? (Y/N): ")), selected_catalog_key, selected_product[0])

                else:
                    for selected_catalog_key in selected_catalog.keys():
                        cart_item = {
                            "Category": selected_catalog_key,
                            "Product": selected_product[0]
                        }

                        cart.append(cart_item)
                    print("Added to Cart")
            else:
                print("Please enter a valid option (1/2).")
                view_catalog(catalog_option, product_option)
        else:
            print("Please enter a valid product option.")
            view_catalog(catalog_option)
    else:
        print("Please enter a valid category option.")
        view_catalog()

def view_cart(for_print=True):
    # method that allows user to views cart if for_print is True, also gets cart total
    global cart
    cleaned_cart = []
    official_cart = []
    count = 0
    global cart_total 

    if len(cart) > 0:
        for items in cart:
            quantity = cart.count(items)
            cart_item = {
                "Item": items,
                "Quantity": quantity
            }
            
            if cart_item not in cleaned_cart:
                cleaned_cart.append(cart_item)
        
        for items in cleaned_cart:
            count += 1
            base_price = get_price(items.get("Item").get("Category"), items.get("Item").get("Product"))
            base_price_int = float(base_price.replace("$", ""))
            price = base_price_int * items.get("Quantity")
            cart_total = cart_total + price
            
            if for_print:
                print(str(count) + ":", items.get("Item").get("Product"), "x" + str(items.get("Quantity")), "${:,.2f}".format(price))
            else:
                cart_item = {
                    "Catalog": items.get("Item").get("Category"),
                    "Product": items.get("Item").get("Product"),
                    "Quantity": items.get("Quantity"),
                    "Price": price
                }
                
                official_cart.append({count: cart_item})
        
        if for_print:
            print("\nTotal:", "${:,.2f}".format(cart_total))
        else:
            return official_cart
    else:
        print("Cart is empty.")
        

def checkout():
    # method that checkouts the logged in user
    global logged_in
    global dynamodb
    global cart_total
    global cart

    if logged_in:
        if len(cart) > 0:
            view_cart(False)
            print("Your total is", "${:,.2f}".format(cart_total))
            response = check_for_return(input("Do you wish to purchase the items in your cart? (Y/N): "))
            if response in ['Y', 'y', 'N', 'n']:
                if response in ["Y", "y"]:
                    cart.clear()
                    cart_total = 0.00
                    print("Thank you for your purchase!")
            else:
                print("Please enter 'Y' or 'N'")
                checkout()
        else:
            print("Cart is empty.")
    else:
        print("Please login or register before making purchases.")

def add_to_cart_check(response, catalog, product):
    # method that adds items to cart, if user enters Y or N
    global cart
    if response in ['Y', 'y', 'N', 'n']:
        if response in ['Y', 'y']:
            cart_item = {
                "Category": catalog,
                "Product": product
            }

            cart.append(cart_item)
            print("Added to Cart")
    else:
        add_to_cart_check(check_for_return(input("Please enter 'Y' or 'N': " )), catalog, product)
            
def get_price(catalog, name):
    # method that gets price from dynamodb 
    global dynamodb
    try:
        response = dynamodb.get_item(TableName="Products", Key={'Catalog': {'S': catalog}, 'Name': {'S': name}})
        if response.get("Item") is None:
            print("No product found")
        else:
            return response.get("Item").get("Price").get("S")
    except dynamodb.exceptions.ResourceNotFoundException:
        print("Error: no Users database")
    
def check_username(att_username):
    # method that checks if username is already entered
    if len(att_username) >= 6:
        try:
            response = dynamodb.get_item(TableName="Users", Key={'Username': {'S': att_username}})
            if response.get("Item") is None:
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
    # method that checks for password length
    if len(att_password) >= 8:
        return att_password
    else:
        print("Password must be 8 or more characters in length.")
        return check_for_return(check_password_length(getpass("Enter a Password (min length=8, passwords are hidden when typed): ")))
        
def confirm_password(password):
    # method that prompts user to show password
    accepted_password = same_passwords(check_for_return(getpass("Confirm Password: ")), password)

    show = check_for_return(input("Show Password? (Y/N): "))
    
    if show in ['Y', 'y', 'N', 'n']:
        if show in ['Y', 'y']:
            print('Password:', accepted_password)
            return accepted_password
        return accepted_password
    else:
        print("Please enter 'Y' or 'N'.")
        return confirm_password(password)

def same_passwords(password1, password2):
    # method that checks for same passwords
    if password1 == password2:
        return password2
    else:
        return same_passwords(check_for_return(getpass("Passwords do not match. Confirm Password: ")), password2)

def logout():
    # method that logs out user
    global logged_in
    
    logged_in = False
    print("Successfully logged out")

def exit_program():
    # method that displays date and time then exits program
    current_time = dt.now()
    print("Timestamp: %s Goodbye!" %current_time)
    global go 
    go = False
    sys.exit(0)

def check_for_return(user_input):
    # returns user to menu based on user input
    user_input = user_input.strip()

    if user_input is "<":
        start()
    return user_input  

menu = {
    "1": register,
    "2": login,
    "3": view_catalog,
    "4": view_cart,
    "5": checkout,
    "6": logout,
    "0": exit_program
}

def start():
    # method that displays menu and calls the appropriate function based on user input

    global go
    global dynamodb
    
    print("loading...")
    run(dynamodb)

    go = True

    while(go):
        print("\n      MENU      \n1: Register\n2: Login\n3: Shop\n4: View Cart\n5: Checkout\n6: Logout\n0: Exit Program")
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
