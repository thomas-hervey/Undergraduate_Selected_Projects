#File:        stacks.py
#Author:      Thomas Hervey
#Date:        5/1/11
#E-Mail:      h46@gl.umbc.edu
#Section:     3
#Description: This program will be imported into our proj1 program and used to
#             get information about how to and the actual storage of tags from
#             our que inot a stack. This program is able to place tags into a
#             stack and retrieve tags off of the top when needed.


# push()
# This will add the top value (already made sure it is a head tag)
# onto ourStack().
# Inputs:lineText , ourStack()
# Outputs: stores ourQue(0) in ourStack().
def push(value, ourStack):
    ourStack.append(value)


# pop()
# This will access the top value in our stack. This is used to be compared to
# the top value of ourQue(). Then, the value will be removed from ourStack().
# Input: ourStack()
# Output: ourStack(0)
def pop(ourStack):
    stackSize = len(ourStack)
    #As long as there are still values in the stack, pop one off and delete it
    #from the stack and return it.
    if stackSize > 0:
        poppedValue = ourStack[-1]
        del(ourStack[-1])
        #The act of 'popping' by returning our last entered value into our
        #stack list.
        return poppedValue
    else:
        return 'Empty Stack'
