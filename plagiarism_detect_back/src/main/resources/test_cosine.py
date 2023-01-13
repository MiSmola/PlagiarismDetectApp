import sys
from pysimilar import compare

firstString = sys.argv[1]
secondString = sys.argv[2]

print(compare(firstString.lower(), secondString.lower()))