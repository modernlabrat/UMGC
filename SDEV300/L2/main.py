"""Main Driver
"""

import sys
import lab_two as lab

if __name__ == '__main__':
    try:
        lab.run()
    except KeyboardInterrupt:
        sys.exit(0)
