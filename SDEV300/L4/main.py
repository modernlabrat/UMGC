"""Main Driver
"""

import sys
import lab_four as lab

if __name__ == "__main__":
    try:
        lab.run()
    except KeyboardInterrupt:
        sys.exit(0)
