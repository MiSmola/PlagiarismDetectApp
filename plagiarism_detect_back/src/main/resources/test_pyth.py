import sys
# print sys.argv

print "sys.argv is:",sys.argv
# ['D://my_utils.py', '100', '200', 'google']

a= sys.argv[1]
b= sys.argv[2]
print "a is:", a
print "b is:", b
a= int (a)
b= int(b)

def adder(a, b):
    c=a+b
    return c

print adder(a,b)

searchTerm=sys.argv[3]
print searchTerm  ##google

def word(searchTerm):
    if searchTerm=="google":
        print " you get it"
    else:
        print " the word is different."

word(searchTerm)