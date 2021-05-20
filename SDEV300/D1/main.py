""" Simple Program for Discussion 1. SDEV300
"""
import sys

def run():
    """ Draws a square based on number given by User.
    """
    sides = input("Enter sides: ")

    x_sides = 0

    if sides.isdigit():
        while x_sides < int(sides):
            y_sides = 0
            while y_sides < int(sides):
                y_sides = y_sides + 1
                print("*", end="  ")
            x_sides = x_sides + 1
            print()

while 1:
    try:
        run()
    except KeyboardInterrupt:
        sys.exit(0)
