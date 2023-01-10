from difflib import SequenceMatcher
import sys

string1 = sys.argv[1]
string2 = sys.argv[2]

# string1 = "C:/Users/djnic/OneDrive/Desktop/engineer/tests/smallFiles/test.txt"
# string2 = "C:/Users/djnic/OneDrive/Desktop/engineer/tests/smallFiles/kiwi.txt"

with open(string1, "r") as f:
    content1 = f.read()

with open(string2, "r") as f:
    content2 = f.read()

match = SequenceMatcher(None, content1, content2).find_longest_match(0, len(content1), 0, len(content2))

print(match.size)


# print(string1[match.a: match.a + match.size])
# print(string2[match.b: match.b + match.size])
