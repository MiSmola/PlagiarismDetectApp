import Levenshtein as lev
import sys

Str1 = sys.argv[1]
Str2 = sys.argv[2]

with open(Str1, "r") as f:
    content1 = f.read()

with open(Str2, "r") as f:
    content2 = f.read()

Distance = lev.distance(content1.lower(), content2.lower()),
print(Distance[0])

# Ratio = lev.ratio(Str1.lower(),Str2.lower())
# print(Ratio)
