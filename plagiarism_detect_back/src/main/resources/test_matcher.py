from difflib import SequenceMatcher
import sys

string1 = sys.argv[1]
string2 = sys.argv[2]

match = SequenceMatcher(None, string1, string2).find_longest_match(0, len(string1), 0, len(string2))

print(match)
print(string1[match.a: match.a + match.size])
print(string2[match.b: match.b + match.size])