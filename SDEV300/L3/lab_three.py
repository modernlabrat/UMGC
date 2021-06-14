import us_data as us
import sys
import matplotlib.pyplot as plt

def display_data():
    """ Displays all the U.S states in Alphabetical order.

        Includes Capital, State Population, and Flower.
    """
    us.print_data()


def search_data():
    """ Displays the Capital, Population, and Flower given input.
    """
    data = us.get_data()
    state = get_state_input(data)

    us.print_state(state)
    us.show_state_flowers(state)
    print()

def display_bar():
    """ Displays the top five populations in the US via bar graph!
    """
    data = us.get_data()

    populations = []

    for key, value in data.items():
        populations.append((key, value["population"])) # create tuples
    
    populations.sort(key = lambda x: x[1]) # sort tuples by 2nd value 

    top_five = populations[-5:] # get last 5 values
    
    top_five_states = []
    top_five_populations = []

    for x in top_five:
        state, population = x

        top_five_states.append(state)
        top_five_populations.append(population)
    
    fig = plt.figure()
    ax = fig.add_axes([0,0,1,1])
    ax.bar(top_five_states, top_five_populations)

    ax.legend(labels=['Population'])
    
    plt.show()

def exit_program():
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
    state = input("Please enter a valid US state to search: ")
    state = state.strip().capitalize()

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
        # "4": update_population,
        "5": exit_program
    }

    while 1:
        print("\nWelcome to the Python State Capital and Flower List Application\n")
        print("1: Display all U.S. States in Alphabetical order along with the Capital, State Population, and Flower")
        print("2: Search for a specific state and display the appropriate Capital name, State Population, and an image of the associated State Flower.")
        print("3: Provide a Bar graph of the top 5 populated States showing their overall population.")
        print("4. Update the overall state population for a specific state.")
        print("5. Exit the program")
        print("\n Enter '<' to return to the menu at any time.\n")

        option = input("Enter an option: ").strip()

        if option in options.keys():
            options[option]()
        else:
            print("Please enter a valid menu option.")
