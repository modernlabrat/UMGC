""" Main Driver
"""
import sys
import lab_five as lab

if __name__ == '__main__':
    try:
        lab.run()
    except KeyboardInterrupt:
        sys.exit()