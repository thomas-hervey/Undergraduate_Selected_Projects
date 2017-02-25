#File:        design2.txt
#Author:      Thomas Hervey
#Date:        5/9/11
#E-Mail:      h46@umbc.edu
#Section:     3
#Description: This file will act as an outline for our proj2.py file which 
#             will use recursive functions to draw the koch snowflake pattern.
#             This file will act as a description of what each of the functions
#             will do and how they interact with each other. There will also
#             be a declaration of constants and any imported files we will use.


import sys
import textwrap
import string
from time import sleep
from graphics import *
from util import *
from turtle import *
import math


#Declaring minimum and maximum degree range constants
DEGREE_MIN = 0
DEGREE_MAX = 4

#Declaring a degree constant for the C-Curve and the Animated Koch Curve
CCURVEDEGREE, DRAGONCURVEDEGREE = 12, 12
KOCHANIMATED = 4
#Declaring redian constant and window dimensions
RADIAN = 0.01745329
WIN_HEIGHT = 500
WIN_WIDTH = 500




#printGreeting()
#Input: NONE
#Output: Printed greeting
#This function will give an introduction to the user as to what the program
#is able to do and a bit of a background into the koch curve, fractals and
#recursion.
def printGreeting():
    print textwrap.fill('Hello and welcome to the Koch Snowflake program. This program will show several different recursive visualizations that are interesting mathmatical phenomenon. The Koch Snowflake is found by recursively splitting an equilateral triangle on all by creating a smaller replica triangle. This can be done to any number of degrees to get a pattern of smaller and smaller triangles coming off of each other. This pattern is seen in several natural occurances as well as mathamtical projections such as the C-Curve and the Dragon curve, which are both options to view in this program. Fractals as a whole topic can be further explored into their complexities and infinate extent.')





#printMenu()
#Input: NONE
#Output: menuChoice
#This function will print the menu options to the user and makes sure that the user enters in a valid response
def printMenu():
    print
    print '%10s %1s' % ('K  -',  'View the Koch Curve')
    print
    print '%10s %1s' % ('C  -',  'View the C-Curve')
    print
    print '%10s %1s' % ('A  -',  'View an Animated Koch Curve')
    print
    print '%10s %1s' % ('D  -',  'View the Dragon Curve')
    print
    print '%10s %1s' % ('Q  -',  'Quit')
    print
    #Getting the user's choice
    menuChoice = raw_input('Enter your choice: ')

    if menuChoice[0] == 'Q' or menuChoice[0] == 'q':
        sys.exit()
    else:
        return menuChoice
    
#drawKochSnowflake()
#Input: degree
#Output: Graphic representation of a Koch snowflake to the (n)th degree as
#        specified by the user.
#This function acts as the control structure for the K menu option. Here,
#the graphic window will be created and using several calls to other functions,
#with a vaild degree number, the recursive snowflake will be drawn.
def drawKochSnowflake():
    question = 'What degree would you like your Koch Snowflake go to? '
    #Once within the K menu option, a call these two functions will get more
    #desired trates including a degree for which the graphic goes and the color
    kochDegree = getValidInt(question, DEGREE_MIN, DEGREE_MAX)

    #Sets a color list and gets the valid color that the user wants the graphics to be in
    colorList = ['RED', 'ORANGE', 'YELLOW' , 'GREEN', 'BLUE', 'INDIGO', 'VIOLET', 'BLACK']
    color = getValidColor(colorList)

    #The window will be created with the appropriate title,size and coordinates
    title = 'Kotch Snowflake'
    win = createWindow(title, WIN_WIDTH, WIN_HEIGHT)
    
    #Create the starting location and direction for our turtle to be set when
    #initialized in our turtle class.
    location = Point(0, 0)
    #Direction in degrees for now
    direction = 0

    #Declaring the turtle object
    thisTurtle = Turtle(location, direction)

    length = 1000
    
    koching(thisTurtle, length, kochDegree, win, color)


#koching()
#Input: thisTurtle, length, kochDegree, win
#Output: NONE
#This function has the recursive part of the curve where a call to draw from my turtle
#object will graph a line for each 'koch'. This is done by finding a new point and in
#relation to our old point, a line can be created and graphed to the window. Then the new
#direction and location will be stored for the next koching
def koching(thisTurtle, length, kochDegree, win, color):
    turnRight = 120 * (math.pi / 180)
    turnLeft = -60 * (math.pi / 180)
    #When the degree is zero (at the base of our recusrion at the base koch degree), draw
    #the line that needs to be created
    if kochDegree == 0:
        thisTurtle.draw(thisTurtle.direction, length, win, color)
    #Otherwise, keep dividing the length and keep lowering the degree. Then recursively
    #call this function. After the lowest recursion finishes, each level will go up until
    #that recursive call is done. Then there is an according turn three times with another
    #recursive call in between.
    else:
        length = length / 3
        kochDegree1 = kochDegree - 1
        koching(thisTurtle, length, kochDegree1, win, color)
        thisTurtle.turn(turnLeft)
        koching(thisTurtle, length, kochDegree1, win, color)
        thisTurtle.turn(turnRight)
        koching(thisTurtle, length, kochDegree1, win, color)
        thisTurtle.turn(turnLeft)
        koching(thisTurtle, length, kochDegree1, win, color)

    


#drawCCurve()
#Input: degree
#Output: Graphic representation of a C-Curve to the n(th) degree as
#        specified by the user.
#This function acts as the control structure for the C menu option. Here,
#the graphic window will be created and using several calls to other functions,
#with a valid degree number, the recursive C-Curve will be drawn.
def drawCCurve():
    title = 'C-Curve'
    win = createWindow(title, WIN_WIDTH, WIN_HEIGHT)
    


    
#drawKochAnimation()
#Input: degree
#Output: Graphic representation of a Koch snowflake to the (n)th degree as
#        specified by the user. Using a time function, the recursive function
#        will draw each degree seperately so that the user can visually see
#        the Koch snowflake develop in an animated way.
#This function acts as the control structure for the A menu option. Here,
#the graphic window will be created and using several calls to other functions,
#with a valid degree number, the recursive snowflake will be drawn with timed
#animation for each degree.
def drawKochAnimation():
    title = 'Animated Koch Curve'
    win = createWindow(title, WIN_WIDTH, WIN_HEIGHT)



   
#drawDragonCurve()
#Input: degree
#Output: Graphic representation of a Koch snowflake to the (n)th degree as
#        specified by the user.
#This function acts as the control structure for the D menu option. Here,
#the graphic window will be created and using several calls to other functions,
#with a valid degree number, the recursive dragon will be drawn.
def drawDragonCurve(degree):
    title = 'Dragon Curve'
    win = createWindow(title, WIN_WIDTH, WIN_HEIGHT)





#createWindow()
#Input: title, WIN_WIDTH, WIN_HEIGHT
#Output: Graphic window
#This function will create a window that will have the turtle draw on every
#time that a menu choice is selected and the program is ready to begin drawing.
def createWindow(title, WIN_WIDTH, WIN_HEIGHT):
    win = GraphWin(title, WIN_WIDTH, WIN_HEIGHT)
    win.setCoords(-250, -250, 250, 250)
    return win
    

    
def main():
    #A call to printGreeting to give the user a greeting and background info
    printGreeting()

    #A call to getValidMenuChoice to show the user the menu for the first time
    #and make sure that they enter a valid choice
    menuChoice = printMenu()

    #As long as the user doesn't select Q to quit, keep going back to the main
    #menu and continuing on with the next desired menu option.
    while menuChoice != 'Q':
        #If the user wants to see the Koch snowflake
        if menuChoice[0] == 'K' or menuChoice[0] == 'k':
            #Once within the menu K option, a control function that deals with
            #all of menu K's options will be called.
            drawKochSnowflake()
        #If the user wants to see the C-Curve
        elif menuChoice[0] == 'C' or menuChoice[0] == 'c':
            drawCCurve()
        #If the user wants to see the animated Koch snowflake
        elif menuChoice[0] == 'A' or menuChoice[0] == 'a':
            drawKochAnimation()
        #If the user wants to see the dragon curve
        elif menuChoice == 'D' or menuChoice[0] =='d':
            #A call to getValidDegree will make sure that the degree that the
            #user wants the graphic to go to is an acceptable number
            degree = getValidDegree
            drawDragonCurve(degree)
        #Otherwise, they must have entered an incorrect value so the user will be prompted
        #again for their menu choice.
        else:
            print 'Sorry, that isnt an acceptable menu choice. here are the options '\
                  'again. If you wish to quit please press Q.'
            
        #A call to getValidMenuChoice to bring back up the main menu options
        menuChoice = printMenu()


    sys.exit()





main()
