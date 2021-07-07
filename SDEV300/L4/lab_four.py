""" Lab 4
    Kyra Samuel
    SDEV300
"""
import re
import numpy as np

def run():
    """ Runs the Matrix Game
    """
    print("*****************Welcome to the Python Matrix Application***********")
    print("Do you want to play the Matrix game?")

    response = confirm(input("Enter Y for Yes or N for No: "))

    if response == "Y":
        valid_phone_number(input("Enter your phone number (XXX-XXX-XXXX): "))
        valid_zip_code(
            input("Enter your zip code+4 (XXXXX-XXXX): "))

        first_matrix = create_matrix("Enter your first 3x3 matrix:")
        print("Your first matrix is:")
        np.set_printoptions(formatter={'float': '{: 0.2f}'.format})

        print(first_matrix)

        second_matrix = create_matrix("Enter your second 3x3 matrix:")
        print("Your second matrix is:")
        print(second_matrix)

        matrix_menu(first_matrix=first_matrix, second_matrix=second_matrix)

    print("***************** Thanks for playing Python Numpy ***********")

def valid_phone_number(phone_number):
    """ Validates phone number input

    Args:
        phone_number (string): phone number input given by user

    Returns:
        string: valid phone number (XXX-XXX-XXXX)
    """
    pattern = re.compile("(\\d\\d\\d\\-\\d\\d\\d-\\d\\d\\d\\d)")

    match = pattern.fullmatch(phone_number)

    if match is None:
        new_phone_number = input("Please enter a valid phone number. (XXX-XXX-XXXX): ")
        return valid_phone_number(new_phone_number)

    return phone_number


def valid_zip_code(zip_code):
    """ Validates zip code input

    Args:
        zip_code (string): zip code+4 input given by user

    Returns:
        string: valid zip code+4 (XXXXX-XXXX)
    """
    pattern = re.compile("(\\d\\d\\d\\d\\d-\\d\\d\\d\\d)")

    match = pattern.fullmatch(zip_code)

    if match is None:
        new_zip_code = input(
            "Please enter a valid zip code. (XXXXX-XXXX): ")
        return valid_zip_code(new_zip_code)

    return zip_code

def confirm(response):
    """ Y or N confirmation

    Args:
        response (string): input given by user

    Returns:
        string: a valid 'Y' or 'N'
    """
    if response.strip().upper() in ['Y', 'N']:
        return response

    response = input("Please enter 'Y' or 'N': ")
    return confirm(response)

def create_matrix(input_message):
    """ creates a 3x3 matrix given the user input.

    Args:
        input_message (string): custom question for creating matrix

    Returns:
        np.matrix: returns a matrix given user input
    """
    pattern = re.compile(
        "^\\d*\\.?\\d*\\s\\d*\\.?\\d*\\s\\d*\\.?\\d*$")  # add floats
    print(input_message)

    first_row_strings = input().strip()
    second_row_strings = input().strip()
    third_row_strings = input().strip()

    rows = [first_row_strings, second_row_strings, third_row_strings]
    matches = []

    for row in rows:
        match = pattern.fullmatch(row)
        matches.append(match)

    if None in matches:
        print("One or more of your rows are invalid.")

        indices = [i for i, x in enumerate(matches) if x is None]

        for i in indices:
            print("invalid row:", rows[i])

        return create_matrix(input_message)

    matrix = []

    for row in rows:
        string_values = row.split(" ")
        values = []

        for value in string_values:
            try:
                values.append(int(value))
            except ValueError:
                values.append(float(value))

        matrix.append(values)

    return np.matrix(matrix)

def matrix_operations(option, first_matrix, second_matrix):
    """ Performs math operations on matrices

    Args:
        option (string): option selected by user
        first_matrix (np.matrix): first matrix created by user
        second_matrix (np.matrix): second matrix created by user
    """
    result = None

    if option == "Addition":
        result = np.add(first_matrix, second_matrix)
    elif option == "Subtraction":
        result = np.subtract(first_matrix, second_matrix)
    elif option == "Matrix Multiplication":
        result = np.matmul(first_matrix, second_matrix)
    elif option == "Element by Element Multiplication":
        result = np.multiply(first_matrix, second_matrix)

    print(result)
    columns = np.transpose(result)

    print("The transpose is:")
    print(columns)
    print("The row and column mean values of the results are:")

    print("Rows: ", end="")
    for row in result:
        print("{:.2f}".format(np.mean(row)), end=" ")

    print("\nColumns: ", end="")
    for column in columns:
        print("{:.2f}".format(np.mean(column)), end=" ")

    response = confirm(input("\nDo you want to perform another operation (Y/N): "))

    if response == "Y":
        matrix_menu(first_matrix=first_matrix, second_matrix=second_matrix)
    else:
        response = confirm(input("Do you want to play the Matrix Game? (Y/N): "))
        if response == "Y":
            run()

def matrix_menu(first_matrix, second_matrix):
    """Displays math operation options for the user to select from

    Args:
        first_matrix (np.matrix): first matrix created by user
        second_matrix (np.matrix): second matrix created by user
    """
    print("Select a Matrix Operation from the list below:")
    options = {
        "a": "Addition",
        "b": "Subtraction",
        "c": "Matrix Multiplication",
        "d": "Element by Element Multiplication"
    }

    menu_keys = options.keys()

    for key in menu_keys:
        print(key + ".", options[key])

    response = input().strip().lower()

    if response in options.keys():
        print("You selected", options[response] + ".", "The results are:")
        matrix_operations(option=options[response],
          first_matrix=first_matrix,
          second_matrix=second_matrix)
    else:
        print("Please select a valid menu option")
        matrix_menu(first_matrix=first_matrix, second_matrix=second_matrix)
