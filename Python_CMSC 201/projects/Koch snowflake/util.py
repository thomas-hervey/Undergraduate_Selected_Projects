#File:        util.py
#Author:      Thomas Hervey
#E-Mail:      h46@umbc.edu
#Section:     3
#Description: This file will be imported into proj2.py and used to get value
#             references from modules. These will basically give the user
#             options and make sure that their selections are valid.




#getValidColor()
#Input: NONE
#Output: color choice
#This function will ask the user for which color they want to use and makes
#sure that it is a valid color and is within the range of our standard
#'8-color crayon box'.
def getValidColor(colorList):
    #Getting the color choice from the user's input
    colorChoice = raw_input('Please enter a color you would like to use: ')
    colorChoice = colorChoice.upper()
    #As long as the color choice isn't one of the normal valid colors, keep
    #prompting the user to enter in a new value or enter Q to quit.
    while colorList.count(colorChoice) != 1:
        if colorChoice[0] == 'Q':
            sys.exit()
        colorChoice = raw_input('Sorry that isnt a valid color. Please select'\
                                'select a new color from the \nfollowing '
                                'list: \nRED, ORANGE, YELLOW, GREEN, BLUE, '\
                                'INDIGO VIOLET, BLACK: ')
        colorChoice = colorChoice.upper()                   
    return colorChoice




def getValidInt(question, min, max):
    # use a bad value to enter the loop
    value = max + 1
    
    # compose the prompt
    prompt = question + " (" + str(min) + "-" + str(max) + "): "
    
    # continue to get values until the user enters a valid one
    while value == "" or value < min or value > max:
        value = raw_input(prompt)
        if len(value) != 0:
            value = int(value)
            
        # return a valid value
        return value
