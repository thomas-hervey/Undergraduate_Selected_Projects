#File:       proj1.py
#Author:     Thomas Hervey
#Date:       5/1/11
#Email:      h46@umbc.edu
#Section:    3
#
#proj1 is an error checking program that takes in a passed HTML or XHTML file
#and checks to make sure that all of the code tag formattings are correct for
#in order for thecode to work. If there are any problems with the tags,
#proj1 will quit and print an error message explaining that there were either
#tag balancing issues or there were an incorrect number of tags. If the XHTML
#code that gets checked is okay with no tag issues, there will be a message
#that prints that particular result.

import sys
import string
#Importing our other files to que, deque, push and pop
from queue import *
from stack import *


def main():
    #Setting our file variableto the name of the file that the user wants to
    #check. This file will then be opened and read line by line.
    ourFile = open(sys.argv[1], 'r')
    #Declaring an empty que
    ourQue = []
    #Declaring an empty stack
    ourStack = []
    #A call to print greeting gives the user information about the program.
    printGreeting()

    #PHASE I
    #
    #Starts the loop to read the file line by line and store is proerly in the
    #que. This is the beginning of Phase I.
    for line in ourFile:
        #Calls the stripText function and passes it the line to be checked.
        #Here, the line will be checked to see if there are any tags. If so,
        #the excess text will be stripped a call to enque passing it the new
        #tag will be stored in the que ourQue. Once there are no more tags in
        #the line, the next line will be examined.
        stripText(line, ourQue)
    #Once all of our tags have been saved into ourQue successfully without
    #errors, we can now finish phase I by printing out ourQue.
    for value in ourQue:
        print value
    #Print statement letting the user know Phase I is completed.
    print 'Phase 1:  End of file was reached for ' + str(sys.argv[1]) +\
          ' with no errors'
    print '*******************************************************************'
    #Close our file, we will not need it anymore
    ourFile.close()

    #PHASE II
    #
    #This is the beginning of Phase II. Here a for loop for each value in
    #ourQue, we will check to see if it is a header tag. If so, it will be
    #pushed into ourStack. If the tag is an end tag, it will be dequed and
    #the top value of the stack will be popped of the list. Then the two
    #will be compared to see if they are a match.
    for x in range(0, len(ourQue)):
      #Grabs the top value in our que
      dequedValue = deque(ourQue)
      #Checks to see if there is a slash in the tag. If so, it will be
      #determined if it is an end tag, a self-closing tag, or an error.
      if '/' in dequedValue:
        #Checks to see if the top qued value is self-closing
        selfClosing(dequedValue, ourStack)
        #If the value isn't self-closing, the top stack value will be popped
        #and the two will be compared
      #Otherwise, the tag is a header tag and will be immediately pushed
      #onto our stack.
      else:
        push(dequedValue, ourStack)
    #Once Phase II is completed successfully, a message will be printed
    #and the program will exit.
    print 'Phase 2: The tags match in this document.'
    print '*******************************************************************'
    sys.exit()


# printGreeting()
# This will print a greeting/ instructions to the user about how the program
# will work.
# Input: NONE
# Output: NONE
def printGreeting():
  print '_____________________________________________________________________'
  print 'Hello and welcome to the XHTML/HTML tag checker program. '
  print 'This program will take a look at your designated code file to make '
  print 'sure that all of the tags are in the correct spot and that they have '
  print 'a corresponding closing tag. This way, when the code runs in the '
  print 'future, there will be no missing tags or syntax errors.'
  print '_____________________________________________________________________'


# valueCompare()
# If the top value of ourQue is an end tag, this will be called to access the
# top value of ourQue() and ourStack() and see if they match. This will be done
# by first seeing if either one is self closing by calling selfClosing(). If
# so then the iteration will terminate and the selfClosing value will be
# removed and the next value in that stack or que will be compared. Otherwise,
# if the two match, a correponding message will be printed that they match and
# each will be removed from their corresponding order and the next copy to
# ourStack() will iterate. If they are different, then an appropriate error
# message will appear explaining which tag was an error and the program will
# exit.
# Input: dequedValue, poppedValue
# Output: printed statement
def valueCompare(dequedValue, poppedValue):
  #Declaring temporary variables that will only look at the letter part of
  #the tag, not the braces or slashes. This is how we can compare to see if
  #the tags match. By checking to see if these are the same, we can say that
  #(because the popped value already is a head tag and the dequed value already
  #is an end tag, so nothing else needs to be looked at in the strings) the
  #tags are matching or not. These are made by slicing.
  dequedText = dequedValue[2 : -1]
  poppedText = poppedValue[1 : -1]

  #This is the actual check to see if the two temporary string variables are
  #the same. If so, the tags match.
  if dequedText == poppedText:
    print str(poppedValue) + ' matches ' + str(dequedValue)
  else:
    #If the tags do not match, print an error and exit the program there
    print 'Phase 2: Error: Didnt finish because the tags ' + str(poppedValue) \
          + ' and ' + str(dequedValue) + ' do not match. \nMake sure the tags'\
          ' here are in order and have the same names for starting and '\
          '\nending tags.'
    sys.exit()


# stripText()
# Using the current line, removes everything not enclosed by '<' and '>'
# Inputs: line, our Que
# Output: lineTag
def stripText(line, ourQue):
    #In order to do this efficiently, a temporary string will hold each
    #character in a tag. Then, when this tag ends it will be immediately
    #Enqued. This way, each character in the line can be looped through so
    #if there are more than one tag in a line, they will all be stored.

    #Declare a temoporary tag variable that has characters added to them.
    workingTag = ''

    #Decklare a variable that changes depending on its 'location' within or
    #outside of a tag. This is determined and changed whenever the for loop
    #comes accross a > (to go 'inside' the tag) or a < (to get 'outside).
    characterLocation = ''

    #Loop through each character in the loop
    for character in line:
        #If we find a first brace, we know we are getting into a tag
        if character == '<':
            #Set the characterLocation to inside in order to add the character
            #to the workingTag string. At the end it will be all one string
            #that will be enqued.
            characterLocation = 'Inside Tag'
        #If we find an end brace, we know we are getting out of a tag
        if character == '>':
            characterLocation = 'Outside Tag'
            workingTag += character
            #Once we have our tag string, enque is immediately called and the
            #tag is stored in the next slot.
            enque(workingTag, ourQue)
            #Reset both our location and temporary tag back to empty.
            characterLocation = ''
            workingTag = ''

        #As long as we are still inside the tag, add that character to our
        #string.
        if characterLocation == 'Inside Tag':
            workingTag += character
    #After going through every character in the loop, if there was a starting
    #brace < without an ending brace >, the characterLocation will still say
    #'Inside Tag'. Therefore, if we are still inside the tag, we can say there
    #is an error and a corresponding message will be printed folloed by a
    #system exit.
    if characterLocation =='Inside Tag':
        print 'Phase I: File didnt end- This line cannot end in the middle of'\
              ' a tag. This line \nfound a starting brace <, but no ending '\
              'brace > for the line ' + str(line)
        sys.exit()


# selfClosing()
# This checks to see if, before comparing the tags, either of the tags is self
# closing. This is important because if it is, it doesn't need a pair or to be
# compared.
# Input: line, backslash, endBrace
# Output: NONE, printed self-closing message
def selfClosing(dequedValue, ourStack):
  #In order to examine each letter in our value, we must make a temporary
  #list and add each value to it. The it will be checked to see where the
  #slash / is in the list.
  valueList = []
  for x in dequedValue:
    valueList.append(x)

  #If the slash / is the second to last character in the value, it is a self
  #closing tag. This tag needs to be out of our comparison.
  if valueList[-2] == '/':
    print dequedValue + ' is self-closing'

  #Checks to see if the slash / is the second character. If this is so, then
  #this tag is an end tag. It will pop off the top stack value and compare
  #it to the top value on the que by calling valueCompare.
  elif valueList[1] == '/':
    poppedValue = pop(ourStack)
    valueCompare(dequedValue, poppedValue)

  #Otherwise, if there is a slash / and it isn't the second or second to last
  #character in the value string, it is an error and not a real functioning
  #tag.
  else:
    print 'Error: Phase II incomplete: This line has a shash / that isnt '\
              'in the correct spot to be designated as an end tag or a self-'\
              'closing tag.'
    sys.exit()


main()
