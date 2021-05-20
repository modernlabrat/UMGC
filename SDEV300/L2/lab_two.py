""" SDEV300: Lab Two.

    Written by Kyra Samuel on 05/19/2021
"""

import sys
import string
import secrets
import math
import datetime as dt

def generate_password():
    """Generates a secured password based on User's input
    """

    alphabet = string.ascii_letters
    length = check_int(input("Enter length (min=8): ").strip(), use="password")

    mixed_case = return_boolean(input("Include Mix-Cased Characters (Y/N): ").strip().upper())

    if not mixed_case:
        alphabet = ""

        lowercase_input = input(
            "Include Lowercase Characters (Y/N): ").strip()
        include_lowercase = return_boolean(lowercase_input.upper())

        if include_lowercase:
            alphabet = alphabet + string.ascii_lowercase

        uppercase_input = input("Include Uppercase Characters (Y/N): ").strip()
        include_uppercase = return_boolean(uppercase_input.upper())

        if include_uppercase:
            alphabet = alphabet + string.ascii_uppercase

    digits_input = input("Include Digits (Y/N): ").strip()
    include_digits = return_boolean(digits_input.upper())

    if include_digits:
        alphabet = alphabet + string.digits

    include_characters = return_boolean(
        input("Include Characters (Y/N): ").strip().upper())

    if include_characters:
        alphabet = alphabet + string.punctuation

    if alphabet == "":
        print("You selected 'N' for all custom options.", end=" ")
        print("A random", str(length) + "-digit password will be generated.")

        alphabet = string.ascii_letters + string.digits

    password = ''.join(secrets.choice(alphabet) for i in range(length))
    print("Generated a new", str(length) + "-digit", "password:", password)

def calculate_percentage():
    """ Calculates and formats a percentage given a numerator and denominator.
    """
    numerator = check_double(input("Enter numerator: ").strip())

    denominator_input = input("Enter denominator (cannot equal 0): ").strip()
    denominator = check_double(denominator_input, use="denominator")

    decimal_input = input(
        "Enter # of decimal points (min=0) or press 'Enter' for full result: ")
    decimal_points = check_int(decimal_input.strip(), use="decimal")

    result = (numerator / denominator) * 100

    if decimal_points != -1:
        format_string = "{:." + str(decimal_points) + "f}"
        result = format_string.format(result)

    result = str(result)

    print("\n" + str(numerator) + "/" + str(denominator), "equals", result + "%")

def days_until():
    """ Returns the number of days until July 4th, 2025
    """
    today = dt.date.today()
    july_25th_2025 = dt.date(2025, 7, 4)

    print("There are", (july_25th_2025 - today).days,
          "days until July 4th, 2025.")

def law_of_cosines():
    """ Prints the answer to the question presented in Lab Two.
    """
    angle_c = math.radians(37)

    c_squared = pow(8, 2) + pow(11, 2) - 2 * 8 * 11 * math.cos(angle_c)
    c_result = math.sqrt(c_squared)

    print("c=" + "{:.2f}".format(c_result))


def calculate_volume():
    """ Calculates the volume of a right circular cylinder.
    """

    radius = check_double(input("Enter radius: ").strip())
    height = check_double(input("Enter height: ").strip())

    volume = math.pi * pow(radius, 2) * height

    print("V=" +
          "{:.3f}".format(volume), "cubic units.")

def exit_program():
    """ Thanks the user and exits the program.
    """
    print("Thank you for participating! Cya.")
    sys.exit(0)

def check_double(number, use=""):
    """ Checks to see if the parameter passed is a valid double type input

    Args:
        number (String): Represents the input given by the User,
        use (String): Represents what the number will be used for, if applicable.

    Returns:
        double: Returns the double type of the number parameter passed.
    """

    if number == "<":
        run()

    try:
        if use == "":
            return float(number)

        if use == "denominator":
            denominator = float(number)

            if denominator != 0:
                return denominator
    except ValueError:
        pass

    return check_double(input("Please enter a valid double value: ").strip(), use=use)


def check_int(number, use=""):
    """ Checks to see if the parameter passed is a valid int type input

    Args:
        number (String): Represents the input given by the User
        use (String): Represents what the number will be used for, if applicable.

    Returns:
        int: Returns the int type of the number parameter passed.
    """

    if number == "<":
        run()

    if number.isdigit():
        if use == "":
            return int(number)

        if use == "password":
            password_length = int(number)

            if password_length >= 8:
                return password_length

        if use == "denominator":
            denominator = int(number)

            if denominator != 0:
                return denominator

    if use == "decimal":
        try:
            if number == "":
                return -1

            decimal = int(number)

            if decimal >= 0:
                return decimal
        except ValueError:
            pass

    return check_int(input("Please enter a valid int value: ").strip(), use=use)

def return_boolean(response):
    """ Returns a boolean based on the User's input

    Args:
        response (String): The response given by the User

    Returns:
        boolean: Returns True if "Y". Returns False if "N"
    """

    if response == "<":
        run()

    if response in ["Y", "N"]:
        if response == "Y":
            return True
        return False

    return return_boolean(input("Please enter Y/N: ").strip().upper())

def run():
    """ Prompts the user to select a menu option
    """

    menu_options = {
        "a": [generate_password, "a. Generate Secure Password"],
        "b": [calculate_percentage, "b. Calculate and Format a Percentage"],
        "c": [days_until, "c. How many days from today until July 4, 2025?"],
        "d": [law_of_cosines, "d. Use the Law of Cosines to calculate the leg of a triangle."],
        "e": [calculate_volume, "e. Calculate the volume of a Right Circular Cylinder"],
        "f": [exit_program, "f. Exit program"]
    }

    while 1:
        print("\nWelcome to the Python Math and Security Application\n")

        for value in menu_options.values():
            print(value[1])

        print("\nEnter '<' to return to the menu at any time.")
        response = input("\nEnter an option: ").strip().lower()

        if response in menu_options.keys():
            menu_options[response][0]()
        else:
            print("Please enter a valid menu option.")
