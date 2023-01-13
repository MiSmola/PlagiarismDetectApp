import Levenshtein as lev
import sys

firstString = sys.argv[1]
secondString = sys.argv[2]

distanceLeven = lev.distance(firstString.lower(),secondString.lower()),
print(distanceLeven)
