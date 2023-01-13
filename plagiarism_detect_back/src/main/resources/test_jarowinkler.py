import Levenshtein as lev
import sys

firstString = sys.argv[1]
secondString = sys.argv[2]

distanceJaroWinkler = lev.jaro_winkler(firstString.lower(), secondString.lower())
print(distanceJaroWinkler)