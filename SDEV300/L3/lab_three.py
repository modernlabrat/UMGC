""" A command line menu-driven python application providing users with the
    ability to search and display U.S. State Capital, population and Flowers.
"""

import sys
import matplotlib.pyplot as plt
from usa_data_generator import UsaDataGenerator as generator

def display_data(us):
    """ Displays all the U.S states in Alphabetical order.

        Includes Capital, State Population, and Flower.
    """
    us.print_data()


def search_data(us):
    """ Displays the Capital, Population, and Flower given input.
    """
    data = us.get_data()
    state = get_state_input(data)

    us.print_state(state)
    us.show_state_flowers(state)
    print()

def display_bar(us):
    """ Displays the top five populations in the US via bar graph!
    """
    data = us.get_data()

    populations = []

    for key, value in data.items():
        populations.append((key, value["population"])) # create tuples

    populations.sort(key = lambda pop_value: pop_value[1]) # sort tuples by 2nd value

    top_five = populations[-5:] # get last 5 values

    top_five_states = []
    top_five_populations = []

    for item in top_five:
        state, population = item

        top_five_states.append(state)
        top_five_populations.append(population)

    fig = plt.figure()
    ax_obj = fig.add_axes([0,0,1,1])
    ax_obj.bar(top_five_states, top_five_populations)

    ax_obj.legend(labels=['Population'])

    plt.show()

def check_int(response):
    """ checks if response is a valid int greater than 0

    Args:
        response (string): input from the user

    Raises:
        ValueError: in case int value is less than 0, raise
        value error - checked.

    Returns:
        int: response to int type
    """
    try:
        if int(response) >= 0:
            return int(response)

        raise ValueError()
    except ValueError:
        response = input('Please enter a valid int: ').strip()

    return check_int(response=response)

def update_population(us):
    """ Updates the population of a state via user input
    """
    data = us.get_data()
    state = get_state_input(data)
    new_population = check_int(input("Enter a new population (whole numbers only >= 0): "))

    us.update_state(state, "population", new_population)
    us.print_state(state)

def exit_program(us):
    """ Exits the Program
    """
    print("Thank you for participating! Goodbye.")
    sys.exit()


def get_state_input(data):
    """ Prompts the user for a valid US state.

    Args:
        data (dict): [A dictionary of the U.S states and appropriate info]

    Returns:
        string: A valid U.S state given by the user.
    """
    state = input("Please enter a valid US state to search/update: ").strip()

    state_names = state.split(" ")

    if len(state_names) > 1:
        for state_concat in state_names:
            state_concat.strip().capitalize()

        state = state_names[0] + " " + state_names[1]
    else:
        state = state.capitalize()

    if state in data.keys():
        return state

    return get_state_input(data)


def check_input(response):
    """Checks if the user wants to return to the menu

    Args:
        response (string): the user's input

    Returns:
        string: the user's input
    """

    if response == "<":
        run()

    return response


def run():
    """ Displays menu options and prompts the user for a menu option.
    """
    options = {
        "1": display_data,
        "2": search_data,
        "3": display_bar,
        "4": update_population,
        "5": exit_program
    }

    while 1:
        print("\nWelcome to the Python State Capital and Flower List Application\n" +
            "\n1: Display all U.S. States in Alphabetical order",
            "along with the Capital, State Population, and Flower" +
            "\n2: Search for a specific state and display the appropriate" +
            "Capital name, State Population, and an image of the associated State Flower." +
            "\n3: Provide a Bar graph of the top 5 populated States",
            "showing their overall population." +
            "\n4. Update the overall state population for a specific state." +
            "\n5. Exit the program")
        print("\nEnter '<' to return to the menu at any time.\n")

        option = input("Enter an option: ").strip()

        if option in options.keys():
            us = generator()
            options[option](us)
        else:
            print("Please enter a valid menu option.")
