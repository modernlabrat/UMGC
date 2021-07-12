""" Main Driver
"""
import sys
import lab_three as lab

if __name__ == '__main__':
    try:
        lab.run()
    except KeyboardInterrupt:
        sys.exit()
