""" Main Driver.

    Calls the run() from lab_one.py
"""
import sys
import lab_one as lab

if __name__ == "__main__":
    try:
        lab.run()
    except KeyboardInterrupt:
        sys.exit(0)
