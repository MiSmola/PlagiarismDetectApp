import Levenshtein as lev
import sys

Str1 = sys.argv[1]
Str2 = sys.argv[2]

Distance = lev.distance(Str1.lower(),Str2.lower()),
print(Distance[0])

# Ratio = lev.ratio(Str1.lower(),Str2.lower())
# print(Ratio)