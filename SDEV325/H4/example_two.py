admin_database = {
    "access_codes": ["12345", "54321", "00000"],
    "access_groups": {
        "admin": ["001", "002"],
        "manager": ["003"]
    }
}

employees = [
    {"full_name": "Kyra Samuel", "id": "001", "password": "password0"},
    {"full_name": "John Doe", "id": "765", "password": "password1"},
    {"full_name": "Cris Owens", "id": "321", "password": "password2"},
    {"full_name": "Lucy May", "id": "545", "password": "password3"},
    {"full_name": "Jacob Lee", "id": "643", "password": "password4"}
]

def validated():
    global admin_database
    global employees

    eid = input("Please enter your eid: ")
    for employee in employees:
        if eid == employee["id"]:
            password = input("Enter your password: ")
            if password == employee["password"]:
                if eid in admin_database["access_groups"]["admin"]:
                        print("You are authorized")
                        return True
    return False


def update():
    global admin_database
    global employees

    if validated():
        print("1: add access codes")
        print("2: add admin")
        print("3: add employee")

        response = input("Enter an option: ")
        if response == "1":
            access_code = input("Enter new access code: ")
            admin_database["access_codes"].append(access_code)
        elif response == "2":
            admin_id = input("Enter employee id: ")
            
            for employee in employees:
                if admin_id == employee["id"]:
                    admin_database["access_groups"]["admin"].append(admin_id)
                else:
                    print("No employee found by that id.")
        elif response == "3":
            full_name = input("Enter full name: ")
            eid = input("Enter employee id: ")
            employees.append({"full_name": full_name, "id": eid})
        else:
            print("Please enter a valid menu option")
    else:
       print("You are not authorized to commit changes, please seek a supervisor.")

def server_room():
    global admin_database
    code = input("Enter access code: ")
    
    codes = admin_database["access_codes"]

    if code in codes:
        print("You are in the server room.")
    else:
        print("Invalid Code. You are not allowed in.")

def public_room():
    print("You are in the public area.")

def run():
    print("1: Update Database")
    print("2: Enter Server Room")
    print("3: Enter Public Area")

    menu_options = {
        "1": update,
        "2": server_room,
        "3": public_room
    }

    response = input("Enter a menu option: ")

    if response in menu_options.keys():
        menu_options[response]()
    else:
        print("Please enter a valid menu option.")
    
    run()

run()
