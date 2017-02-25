#File Name:       hw7.py
#Author:          Thomas Hervey
#Section:         3
#E-Mail:          h46@umbc.edu
#Description:     This is an addition to the hailstone I project. Here, we
#                 have added the user option 'H' to visually see a histogram
#                 (using graphics) of chain lengths of a range of 10 values.
#
#
#                 This program prints out the chain of numbers in the unusual
#                 sequence called the Hailstone process. Given a an integer
#                 or a range, the chain will be printed out. The chain's
#                 interesting propertiy is that (although with debate as to how
#                 and if it always works) given any number other than 1, if
#                 the number is even divide it by two and if it is odd,
#                 multiply it by three and add one. Eventually this should
#                 reach the number one.

import sys
import textwrap
from graphics import *
from time import sleep
#The first three constants are used to set the range limits for R,L, X options.
#The next two constants are used to create the proper window size for H.
MIN = 1
MAX = 10000
MAX2 = 1000000
WINDOW_HEIGHT = 500
WINDOW_WIDTH = 500


#main() is our main method. It acts as the skeleton of the program. It calls on
#several functions that record and print the sequence, print the menu and
#acts as an overall control structure.
#Input: Nothing
#Output: Nothing
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
        elif userMenuChoice[0] == 'r' or userMenuChoice[0] == 'R':
            #Calls the getValidInt function to check the user's entered range.
            startRangeInt = getValidInt(question1, MIN, MAX)
            endRangeInt = getValidInt(question2, startRangeInt + 1, MAX)
            #Checks to make sure the end value is larger than the start value
            while startRangeInt >= endRangeInt:
                print 'Sorry, the end range integer must be greater than the'\
                      ' start range integer.'
                endRangeInt = getValidInt(question2, startRangeInt + 1, MAX)
        #With the two integers in mind the hailstoneLooping function is called.
            hailstoneLooping(startRangeInt, endRangeInt)

        #Letter L option
        elif userMenuChoice[0] == 'l' or userMenuChoice[0] == 'L':
            startRangeInt = getValidInt(question1, MIN, MAX)
            endRangeInt = getValidInt(question2, startRangeInt + 1, MAX)
            while startRangeInt >= endRangeInt:
                print 'Sorry, the end range integer must be greater than the'\
                      ' start range integer.'
                endRangeInt = getValidInt(question2, startRangeInt + 1, MAX)
            hailstoneLooping2(startRangeInt, endRangeInt)

        #Letter X option (extra credit)
        elif userMenuChoice[0] == 'x' or userMenuChoice[0] == 'X':
            extraCredit()

        #Letter H option to visually show a chain length histogram for a range.
        elif userMenuChoice[0] == 'h' or userMenuChoice[0] == 'H':
            #Calls the getValidInt function to check the user's entered range.
            startRangeInt = getValidInt(question1, MIN, MAX)
            hailstoneLooping3(startRangeInt, startRangeInt + 10)

        #Anything other than options I,i,R,r,L,l,Q,q ask again for an input
        else:
            print('________________________________________________________________')
            print 'Please enter in another menu command.'
            print('________________________________________________________________')
        #Calls the printMenu function again and gets the results. Then the
        #loop contiunes again.
        userMenuChoice = printMenu()

    #When the user chooses 'Q" or 'q' the while loop will not start and the
    #program will quit here.
    print '**************************'
    print 'You have quit the program.'
    sys.exit()

    
#printGreeting() will display the initial greeting text to the user to give
#them a background into how and why the program should be used.
def printGreeting():
    print('Hello and welcome to the Hailstone Process Program.')
    print('This program prints out the chain of numbers in the unusual\n'\
          'sequence called the Hialstone Sequence. Given a an integer\n'\
          'or a range, the chain will be printed out. The chains\n'\
          'interesting property is that (although with debate as to how\n'\
          'and if it always works) given any number other than 1, if\n'\
          'the number is even divide it by two and if it is odd,\n'\
          'multiply it by three and add one. Eventually this should\n'\
          'reach the number one. ')
    print('________________________________________________________________')


#printMenu() will display the menu options with proper formatting to give the
#user a good visual sense of all of the options that they can choose from.
def printMenu():
    str1 = '\tI - view sequence for an Individual value'
    str2 = '\tR - view sequence for a Range of values'
    str3 = '\tL - find the Longest chain'
    str4 = '\tX - Extra Credit, longest chain under 1,000,000'
    str5 = '\tH - View a histogram of chain lengths for a range.'
    str6 = '\tQ - Quit'
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
    print (str6)
    print


    #This checks to see what the menu choice the user selected and
    #in return gets the different inputs.
    userMenuChoice = raw_input('Enter your choice: ')
    return userMenuChoice


#getValidInt() checks to see if the passed integer is within the range that
#the program can use and handle.
#Input: the specific question text depending on which stage they're in, the
#       known constant maximum value, the known constant minimum value.
#Output: acceptable value for that question
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


#isOdd() checks to see if the passed argument is an odd integer or not.
#Input: the integer currently in the hailstone while loop.
#Output: Boolean, true if the value is odd, false if it is even.
def isOdd(x):
    if x % 2 ==1:
        return True
    else:
        return False


#hailstone() uses a while loop to alter the passed argument in different ways
#depending on if it is even or odd and prints out the resulting alteration and
#arrow symbol each time it iterates through the loop. While doing this, a
#'chain length claculator' adds up the number of 'chain links' and prints that
#out at the very end.
#Input: the integer currently in the hailstoneLooping() range for loop
#Output: prints the current range integer, (arrow and chain link * iterations)
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
    counterNumber += 1
    print '; ' + str(counterNumber)


#hailstone2() is similar to hailstone() but it is for menu options L and X.
#This function also alters the passed range value arguement, but it doesn't
#print it, it simply returns chain length variable.
#Input: the current integer in the hailstoneLooping2() for loop
#Output: counterNumber, the chain length variable.
def hailstone2(x):
    #Declares the chain length.
    counterNumber = 0
    while x !=1:
        #The current number is odd, multiply it by three and add one
        if isOdd(x)==True:
            x = (x * 3) + 1
        #The current number is even, divide it by two
        else:
            x = (x / 2)
        counterNumber += 1
    #Returns the final chain length
    counterNumber += 1
    return counterNumber


#hailstoneLooping() uses the desired range of numbers when option R is picked
#and iterates through and passes the current range value into hailstone().
#Input: starting desired range integer, ending desired range integer.
#Output: nothing
def hailstoneLooping(startRangeInt, endRangeInt):
    for x in range(startRangeInt, endRangeInt + 1):
        hailstone(x)


#hailstoneLooping2 uses the desired range of numberd when option L or X is
#picked and iterates and passes the current range value into hailstone2().
#It is here after the loop (which not only iterates but because hailstone2()
#doesn't print anything, checks to see if the new returned chain length is
#greater than the last and saves it as the new longest length) it prints both
#the value and the chain length of the longest chain length number.
#Input: starting desired range integer, ending desired range integer
#Output: prints the largest chain length and corresponding value.
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


#hailstoneLooping3() uses the desired range of numbers when option H is picked
#and iterates and passes the current range value into hailstone2(). Then,
#using the declares lists, the values and chain lengths are stored to be passed
#into drawHistogram.
#Input: starting desired range integer, ending desired range integer
#Output: Nothing
def hailstoneLooping3(start, end):
    longestLength = 1
    hailstoneValues = []
    hailstoneChainLengths = []
    longestLength = 0
    for x in range(start, end):
        hailstoneValues.append(x)
        counterNumber = hailstone2(x)
        hailstoneChainLengths.append(counterNumber)
        if counterNumber > longestLength:
            longestLength = counterNumber
    drawHistogram(hailstoneValues, hailstoneChainLengths, longestLength)


#drawHistogram() is called by hailstone3 to graph the known information.
#The range and chain lengths are passed, then the function will use graphics
#to visualize a histogram. This will be done by creating the range as the x-
#axis and the lengths as the y-axis. Then, using formatting and creating boxes,
#a histogram will be made in a seperate window.
#Input: list of desired values, list of corresponding chain lengths, longest
#       length.
#Output: Graphic histogram
def drawHistogram(hailstoneValues, hailstoneChainLengths, longestLength):
   #Creates the window with a title and size
   win = GraphWin('Histogram of Chain Lengths', WINDOW_HEIGHT, WINDOW_WIDTH)
   win.setCoords(0.0, 0.0, 20.0, longestLength + longestLength / 20.0)
   win.setBackground('grey')
   xFormat = 1.0
   for x in range(len(hailstoneValues)):
       #Declare our two points to be used to map the histogram top left and
       #bottom right corners.
       p1 = Point(xFormat, hailstoneChainLengths[x])
       p2 = Point(xFormat + 1.0, longestLength / 20.0)
       valueTextPoint = Point(xFormat + 0.5, longestLength / 40.0)
       #Create a rectangle that will be each individual column for each value
       #in our range. It takes the top left and bottom right points and graphs
       #it, slightly over from one to the next each time through the loop.
       rectangle1 = Rectangle(p1, p2)
       rectangle1.setFill('blue')
       rectangle1.draw(win)
       #This creates a variable that changes every time through the for loop.
       #The variable is the printed value number for our range, and it is
       #formatted to be printed below each by having the same X- coordiante as
       #the column X-coordinate.
       values = Text(valueTextPoint, hailstoneValues[x])
       values.draw(win)
       #Shifts the next column and text over 2 coordinates out of our 20.
       xFormat += 2
   #Declaring the variables used in the following loop. These will be used
   #to format where each of the four labels will be printed (1/4 of the height)
   labelPosition = longestLength / 4.0
   yScale = 0
   #This for loop prints out the y-axis labels. The text is formatted to
   #being .5 coordinates for x, and then uses yScale as an accumulator to
   #shift the next printef y-axis lable 1/4 of the way up the window.
   for x in range(4):
       yScale += labelPosition
       labelTextPoint = Point(.5, yScale)
       yLabel = Text(labelTextPoint, int(yScale))
       yLabel.draw(win)
   #This keeps the window open for 10 seconds, then closes it.
   sleep(10.0)
   win.close()


#extraCredit() is for the extra credit. Because it doesn't require any input
#values, it can simply be called through the menu option 'X' and return the
#desired value. It acts the same way as option L, but with a larger range.
#This function calls hailstoneLooping2 and passes predetermined range values.
#Input: Nothing, simply called by option X.
#Output: Nothing
def extraCredit():
    print 'This may take a while, please wait...'
    hailstoneLooping2(1, MAX2)


main()
