import sys
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np


def select_columns(df):
    """ Prompts options to analyze and modify PopChange.csv data 

    Args:
        df (pd.DataFrame): a pandas DataFrame with all the imported data from PopChange.csv
    """
    columns = df.columns
    menu = {}
    count = 0

    print("Select the Column you want to analyze:")
    for column in columns:
            count = count +1
            print(str(count) + ":", column)

            menu[count] = column

    print(len(columns) +1, end="")
    print(": Exit DataFrame")
    
    response = check_int(input().strip())
    menu_keys = menu.keys()

    if response == len(columns) +1:
        run()
    elif response in menu_keys:
        analyze_column(df=df, column=menu[response])
    else:
        print("Please select a valid menu option.")
        select_columns(df=df)

def exit_program():
    """ A method to exit the program
    """
    print("Thank you for participating!")
    sys.exit(0)

def run():
    """ Runs the Python Data Analysis Application
    """
    housing_df = convert_csv('csv/Housing.csv')
    pop_change_df = convert_csv('csv/PopChange.csv')

    print("***************** Welcome to the Python Data Analysis App**********")
    print("Select the file you want to analyze:")
    print("1: Population Data\n" +
          "2: Housing Data\n" +
          "3: Exit Program")


    response = input().strip()

    if response in ["1", "2", "3"]:
        if response == "1":
            select_columns(df=pop_change_df)
        elif response == "2":
            select_columns(df=housing_df)
        else:
            exit_program()
    else:
        print("Please enter a valid menu option")
        run()

def check_int(value):
    """ checks if input can be converted to an int

    Args:
        value (string): String value to convert to Int value

    Returns:
        int: the value converted to int type
    """
    try:
        return int(value)
    except ValueError:
        new_value = input("Please enter an int value: ").strip()
        return check_int(new_value)

def analyze_column(df, column):
    has_numeric = False
    selected_column = df[column]

    try:
        count = [len(selected_column), "Count"]
        mean = [selected_column.mean(), "Mean"]
        standard_dev = [selected_column.std(), "Standard Deviation"]
        minimum = [selected_column.min(), "Min"]
        maximum = [selected_column.max(), "Max"]

        statistics = [count, mean, standard_dev, minimum, maximum]
        has_numeric = True
    except ValueError:
       pass
    except TypeError:
        pass

    print("You selected", column)
    
    if not has_numeric:
        print("This column does not have numeric values.")
    else:
        print("The statistics for this column are:")

        for stat in statistics:
            print(stat[1] + " = " + "{: .2f}".format(stat[0]))
    
        print("The Histogram of this column is now displayed.")

        df.hist(column=column, bins='auto')
        plt.show()
    
    select_columns(df=df)

def convert_csv(path):
    """ Converts a csv to pd.DataFrame

    Args:
        path (string): path to csv file

    Returns:
        pd.DataFrame: pd.DataFrame created from csv file
    """
    return pd.read_csv(path)
