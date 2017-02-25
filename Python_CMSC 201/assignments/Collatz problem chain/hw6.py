#File Name:       hw6.py
#Author:          Thomas Hervey
#Section:         3
#E-Mail:          h46@umbc.edu
#Description:     This program prints out the chain of numbers in the unusual
#                 sequence called the Hailstone process. Given a an integer
#                 or a range, the chain will be printed out. The chain's
#                 interesting propertiy is that (although with debate as to how
#                 and if it always works) given any number other than 1, if
#                 the number is even divide it by two and if it is odd,
#                 multiply it by three and add one. Eventually this should
#                 reach the number one.

import sys
import textwrap

MAX = 10000
MAX2 = 1000000


#This is our main method. It acts as the skeleton of the program. It calls on
#several functions that record and print the sequence, print the menu and
#acts as an overall control structure.
def main():
    printGreeting()

    userMenuChoice = printMenu()


    #This declares the min, max, question variables to be passed to the R
    #option.
    question1 = 'Enter a starting range integer'
    question2 = 'Enter an ending range integer'
    question3 = 'Enter an integer to be sequenced'

    while userMenuChoice[0] != 'q' and userMenuChoice[0] != 'Q':

        #Letter I option
        if userMenuChoice[0] == 'i' or userMenuChoice[0] == 'I':
            integerValue = getValidInt(question3, MIN, MAX)
            #With the integer in mind, the hailstone function is called.
            hailstone(integerValue)

        #Letter R option
        if userMenuChoice[0] == 'r' or userMenuChoice[0] == 'R':
            #Calls the getValidInt function to check the user's entered range.
            startRangeInt = getValidInt(question1, MIN, MAX)
            endRangeInt = getValidInt(question2, MIN, MAX)
            #Checks to make sure the end value is larger than the start value
            while startRangeInt >= endRangeInt:
                print 'Sorry, the end range integer must be greater than the'\
                      ' start range integer.'
                endRangeInt = getValidInt(question2, MIN, MAX)
        #With the two integers in mind the hailstoneLooping function is called.
            hailstoneLooping(startRangeInt, endRangeInt)

        #Letter L option
        if userMenuChoice[0] == 'l' or userMenuChoice[0] == 'L':
            startRangeInt = getValidInt(question1, MIN, MAX)
            endRangeInt = getValidInt(question2, MIN, MAX)
            while startRangeInt >= endRangeInt:
                print 'Sorry, the end range integer must be greater than the'\
                      ' start range integer.'
                endRangeInt = getValidInt(question2, MIN, MAX)
            hailstoneLooping2(startRangeInt, endRangeInt)

        #Letter X option (extra credit)
        if userMenuChoice[0] == 'x' or userMenuChoice[0] == 'X':
            extraCredit()

        #Letter Q option
        #if userMenuChoice[0] == 'q' or 'Q':
            #print 'You have now quit the program!.'
            #sys.exit()


        #Anything other than options I,i,R,r,L,l,Q,q ask again for an input
        else:
            print('________________________________________________________________')
            print 'Please enter in another menu command.'
            print('________________________________________________________________')
        #Calls the printMenu function again and gets the results. Then the
        #loop contiunes again.
        userMenuChoice = printMenu()
    print '**************************'
    print 'You have quit the program.'
    sys.exit()


#This function acts as the greater, letting the user know what the program
#will do.
def printGreeting():
    print('Hello and welcome to the Hailstone Process Program')
    print('This program prints out the chain of numbers in the unusual\n'\
          'sequence called the Hialstone Sequence. Given a an integer\n'\
          'or a range, the chain will be printed out. The chains\n'\
          'interesting propertiy is that (although with debate as to how\n'\
          'and if it always works) given any number other than 1, if\n'\
          'the number is even divide it by two and if it is odd,\n'\
          'multiply it by three and add one. Eventually this should\n'\
          'reach the number one. ')
    print('________________________________________________________________')


#This function will print the menu options for the user to select which type
#of sequence they want as well as if they want to quit. It will also be the
#method that prompts the user for what number(s) they want to use.
def printMenu():
    str1 = '\tI - view sequence for an Individual value'
    str2 = '\tR - view sequence for a Range of values'
    str3 = '\tL - find the Longest chain'
    str4 = '\tX - Extra Credit, longest chain under 1,000,000'
    str5 = '\tQ - Quit'
    print 'Please enter the letter corresponding to the option you want.'
    print
    print (str1)
    print
    print (str2)
    print
    print (str3)
    print
    print (str4)
    print
    print (str5)
    print
    print

    #This checks to see what the menu choice the user selected and
    #in return gets the different inputs.
    userMenuChoice = raw_input('Enter your choice: ')
    return userMenuChoice


#This is a small function that will deal with the user entering in the
#correct values for the range option in the menu. It will be called if the user
#enters the letter R
def getValidInt(question, MIN, MAX):

    # use a bad value to enter the loop
    value = MAX + 1

    # compose the prompt
    prompt = question + " (" + str(MIN) + "-" + str(MAX) + "): "

    # continue to get values until the user enters a valid one
    while value == "" or value < MIN or value > MAX:
        value = raw_input(prompt)
        if len(value) != 0:
            value = int(value)

    # return a valid value
    return value


#This function will check to see if the passed value is odd or not. It will
#then be returned to the hailstone function.
def isOdd(x):
    if x % 2 ==1:
        return True
    else:
        return False


#This function uses a while loop and prints out a single chain for each number
#in the loop.The loop will keep going until the number reaches 1. There is a
#counter that keeps track of how long the chain is and in the end returns how
#long the sequence chain is.
def hailstone(x):
    counterNumber = 0
    print '-' + str(x) + '-'
    while x !=1:
        #The current number is odd, multiply it by three and add one
        if isOdd(x)==True:
            x = (x * 3) + 1
        #The current number is even, divide it by two
        else:
            x = (x / 2)
        counterNumber += 1
        print ' -> ' + str(x),
    print '; ' + str(counterNumber)


#This function is used for the user menu options L or X
#This function uses a while loop and prints out a single chain for each number
#in the loop.The loop will keep going until the number reaches 1. There is a
#counter that keeps track of how long the chain is and in the end returns how
#long the sequence chain is.
def hailstone2(x):
    counterNumber = 0
    while x !=1:
        #The current number is odd, multiply it by three and add one
        if isOdd(x)==True:
            x = (x * 3) + 1
        #The current number is even, divide it by two
        else:
            x = (x / 2)
        counterNumber += 1
    return counterNumber


#This function is called when the R option is picked. Given the starting and
#ending range numbers, this function will use a loop to call the hailstone
#function over and over to print out all of the dersired numbers in the range.
def hailstoneLooping(startRangeInt, endRangeInt):
    for x in range(startRangeInt, endRangeInt + 1):
        hailstone(x)


#This function is called when the L or X option is picked. Given the starting
#and ending range numbers, this function will use a loop to call the hailstone
#function over and over to print out all of the dersired numbers in the range.
#This function is different from the other looping function because it doesn't
#call hailstone, but hailstone2, which doesn't print the chain.
def hailstoneLooping2(startRangeInt, endRangeInt):
    largestNumber = 0
    counterNumber = 0
    LongestChainCounterValue = 0
    for x in range(startRangeInt, endRangeInt + 1):
        hailstone2(x)
        counterNumber = hailstone2(x)
        if largestNumber < counterNumber:
            largestNumber = counterNumber
            largestChainCounterValue = x
    print '-' + str(largestChainCounterValue) + '-'
    print largestNumber


#This function is for the extra credit. Because it doesn't require any input
#values, it can simply be called through the menu option 'X' and return the
#desired value. It acts the same way as option L, but with a larger range.
def extraCredit():
    print 'This may take a while, please wait...'
    hailstoneLooping2(1, MAX2)


main()
