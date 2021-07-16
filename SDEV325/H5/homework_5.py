import hashlib
import string
import random
import sys

def run():
    print("Select the vulnerability demo to run:")
    print("1: Improper Restriction of Excessive Authentication Attempts")
    print("2: Use of a One-Way Hash without a Salt")
    print("3: Exit")
    
    response = input().strip()
    
    if response == "1":
        brute_force_demo()
    elif response == "2":
        one_way_hash_demo()
    elif response == "3":
        sys.exit(0)
    else:
        run()

def one_way_hash_demo():
    print("Select an option:")
    print("1: hack")
    print("2: salt passwords")
    print("3: go back")
    
    is_hacked = False
    
    response = input().strip()
    
    if response == "1":
        run_hash_demo(is_salted=False)
    elif response == "2":
        run_hash_demo(is_salted=True)
    elif response == "3":
        run()
    
    one_way_hash_demo()
        

def run_hash_demo(is_salted):
    database = []
    customers = []
    
    print("Some users have signed up to your database.")
    if not is_salted:
        print("Users and hackers are unaware that the passwords are not salted, but they are hashed using sha256.")

        random_pw = "RandomPassword".encode()
        random_pw54 = "RandomPassword54".encode()
        hello_world = "hello30world".encode()
        forty = "FortyTwenty40".encode()
        complex_pw = "onfew239ds$#TH".encode()
        
        customers = [
            {"ksamuel@gmail.com": hashlib.sha256(random_pw).hexdigest()},
            {"rarmstrong@outlook.com": hashlib.sha256(hello_world).hexdigest()},
            {"mjohnson@gmail.com": hashlib.sha256(random_pw54).hexdigest()},
            {"hhoward@yahoo.com": hashlib.sha256(hello_world).hexdigest()},
            {"jbailey@outlook.com": hashlib.sha256(forty).hexdigest()},
            {"fbutler@gmail.com": hashlib.sha256(forty).hexdigest()},
            {"ggibbons@icloud.com":hashlib.sha256(complex_pw).hexdigest()}
        ]
        
    else:
        print("Your passwords are hashed using sha256 and salted")
       
        customers = [
            {"ksamuel@gmail.com": hashlib.sha256(str("RandomPassword" + get_salt()).encode()).hexdigest()},
            {"rarmstrong@outlook.com": hashlib.sha256(str("hello30world" + get_salt()).encode()).hexdigest()},
            {"mjohnson@gmail.com": hashlib.sha256(str("RandomPassword54" + get_salt()).encode()).hexdigest()},
            {"hhoward@yahoo.com": hashlib.sha256(str("hello30world" + get_salt()).encode()).hexdigest()},
            {"jbailey@outlook.com": hashlib.sha256(str("FortyTwenty40" + get_salt()).encode()).hexdigest()},
            {"fbutler@gmail.com": hashlib.sha256(str("FortyTwenty40" + get_salt()).encode()).hexdigest()},
            {"ggibbons@icloud.com":hashlib.sha256(str("onfew239ds$#TH" + get_salt()).encode()).hexdigest()}
        ]

    database = [customer for customer in customers]
        
    print("You just received notice that your database has been hacked.")
    print("The hacker can see all emails and passwords.")
    print("This is what the hackers can see:")
    
    for customer in database:
        for key, value in customer.items():
            print("Email:", key, "=> Password:", value)

def get_salt():
    salt = ''
    
    rand_range = random.randint(5, 15)
    
    salt = salt.join(random.choice(string.ascii_letters + string.digits)  for i in range(rand_range))
    
    return salt

def brute_force_demo():
    print("Select an option:")
    print("1: unrestricted")
    print("2: restricted")
    print("3: go back")
    
    response = input().strip()
    
    if response == "1":
        run_brute_force(restricted=False)
    elif response == "2":
        run_brute_force(restricted=True)
    elif response == "3":
        run()
        
    brute_force_demo()
    
def run_brute_force(restricted):
    passwords = ["DogBestfriend312", "Samuels3123", "WifiPassword42", "RandomPassword54",
                "3224354355", "fafvrvagargg", "43jnfsnf;oanf", "423nfsdfnafsfa",
                "pilbfbafweef", "dbfdasbf;lfd", "fdnfanf;egr", "nlnfsndlmfas;df"
                "fl;kanafevpf", "fa;nfafn;aenfa"]
    
    correct_pw = "pilbfbafweef"
    
    print("Running a brute force attack on unsecureweb.com to steal people's data!\n")

    print("Enter your username:")
    print("You entered: kyrasamuel")
    print("Enter your password:")

    if not restricted:
        num_of_attempts = 0
        password_found = False
        
        for x in passwords:
            print("You entered:", x)
            if x != correct_pw:
                num_of_attempts = num_of_attempts +1
                print("Attempt", str(num_of_attempts) + ": Invalid Password - Try Again!")
            else:
                num_of_attempts = num_of_attempts +1
                print("Attempt", str(num_of_attempts) + ": Logged In Successfully with PW:", x)
                break
    else:
        max_attempts = 5
        num_of_attempts = 0

        for x in passwords:
            print("You entered:", x)
            if x != correct_pw:
                num_of_attempts = num_of_attempts +1
                print("Attempt", str(num_of_attempts) + ": Invalid Password - Try Again!")
            else:
                num_of_attempts = num_of_attempts +1
                print("Attempt", str(num_of_attempts) + ": Logged In Successfully with PW:", x)

            if num_of_attempts == max_attempts:
                print("Your account has been locked. Please contact customer service at 757-000-0000")
                break
