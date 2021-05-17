""" Registers users for the U.S voting system.

    Requires the zipcodes package - see readMe.md
    Prompts the User for valid information for registering
    to vote in the United States.
"""
import sys
import zipcodes


def check_continue():
    """ Checks if the user wants to continue with voting.

        Prompts the user to continue with the Voter Registration System.
        if it is not a valid (Y/N) response, reprompts the user for a
        valid response.

        Args:
            response: The input response, set to "" by default. If invalid,
              it is set to new response.
    """

    response = input(
        "Do you want to continue with Voter Registration? Y/N: ").strip()

    response = response.upper()

    if response in ['Y', 'N']:
        if response == "N":
            print("Thank you for participating! Goodbye.")
            sys.exit(0)
    else:
        print("Please enter Y/N.")
        check_continue()


def get_name(first=True):
    """ Validates if the User's first or last name is letters only.

        Args:
            first: Set to True by default to prompt
            for the first name of the user. If false,
            it is prompting for the last name.
    """

    if first:
        name = input("What is your first name: ").strip()
    else:
        name = input("What is your last name: ").strip()

    if not name.isalpha():
        print("Only letters are accepted. ")
        return get_name(first)

    return name


def get_age():
    """ Validates if age is an int value only.
    """
    age = input("What is your age: ").strip()
    if not age.isdigit() or int(age) > 119:
        print("Only int values below 119 are accepted. ")
        return get_age()

    return age


def check_citizenship():
    """ Validates if user is an U.S Citizen.

        If user is not a U.S Citizen, the system is closed.
    """
    citizenship = input("Are you a U.S Citizen (Y/N): ").strip().upper()
    if citizenship in ['Y', 'N']:
        if citizenship == "N":
            print("Sorry! Only U.S Citizens are allowed to vote in the U.S.")
            print("Thank you for participating! Goodbye.")
            sys.exit(0)
    else:
        print("Please enter Y/N.")
        check_citizenship()


def get_state():
    """ Validates if State is an U.S State Abbreviation only
    """
    states = ["AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DC", "DE", "FL", "GA",
              "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
              "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
              "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
              "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"]

    state = input("What state do you live in?: ").upper().strip()
    if state not in states:
        print("Please enter a valid US State Abbreviation.")
        return get_state()

    return state


def get_zip_code(state):
    """ Validates if zip code is a real US zip code.

        Uses the package zipcodes to verify if input
        is a valid U.S Zip Code. See readMe.md for
        instructions to install the zipcodes package.
        Searches for the zip code in the State provided,
        if no zip code is found - prompts for new zip
        code.

        Args:
            state: The state provided by the User
    """
    found = False
    zip_code = input("What is your zipcode?: ").upper().strip()

    state_zip_codes = zipcodes.filter_by(state=state)

    for state_dict in state_zip_codes:
        if zip_code == state_dict.get("zip_code"):
            found = True

    try:
        if not zipcodes.is_real(zip_code) or not found:
            print("Please enter a valid US Zip Code for", state + ".")
            return get_zip_code(state)
    except ValueError:
        print("Please enter a valid US Zip Code for", state + ".")
        return get_zip_code(state)

    return zip_code


def run():
    """ Runs the Python Voter Registration Application.
    """
    print("Welcome to the Python Voter Registration Application.")
    check_continue()

    voter = {}

    first_name = get_name()
    voter["first_name"] = first_name

    check_continue()

    last_name = get_name(first=False)
    voter["last_name"] = last_name

    check_continue()

    age = get_age()
    voter["age"] = age

    check_continue()

    check_citizenship()
    check_continue()

    state = get_state()
    voter["state"] = state

    check_continue()

    zip_code = get_zip_code(state)
    voter["zip_code"] = zip_code

    print("Thank you for registering to vote. Here is the information we received:\n")
    print("Name (first last): ", voter.get(
        "first_name"), voter.get("last_name"))
    print("Age: ", voter.get("age"))
    print("U.S. Citizen: Yes")
    print("State: ", voter.get("state"))
    print("Zipcode: ", voter.get("zip_code"))
    print("Thanks for trying the Voter Registration Application.")
    print("Your voter registration card should be shipped within 3 weeks.")
