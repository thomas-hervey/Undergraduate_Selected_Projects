#File:        turtle.py
#Author:      Thomas Hervey
#E-Mail:      h46@umbc.edu
#Section:     3
#Description: This program will be imported into our proj2.py program to be
#             used as the graphics tool of the programs. For all four graphic
#             choices, passed data to the several functions below will
#             graph each of the according images.


from graphics import *
import math

class Turtle:

    #initialize()
    #Input: thisTurtle, location, direction
    #Output: NONE
    #This is our constructor that will create our instance variables
    def __init__(self, location, direction):
        self.location = location
        self.direction = float(direction)

  
    
    
    #turn()
    #Input: self, degree
    #Output: turnValue (turtle's orientation)
    #This method will act as a way to give the turtle a new direction based off
    #of the old direction in terms of degrees (out of 360). Therefore, a turn
    #90 will cause the turtle to face 90 degrees counterclockwise from its last
    #direction.
    def turn(self, degree):
        #assigns and returns a variable equal to the radian equivalent of
        #degrees
        self.direction = degree * (math.pi / 180)




    #draw()
    #Input: self, direction, length, win
    #Output: graphic line on the window
    #This method
    def draw(self, direction, length, win, color):
        #The new X and Y coordinates are found by using trigonometry from
        #our length (hypotenuse length) and angle (interior angle)
        newXHeight = math.cos(self.direction) * length
        newYHeight = math.sin(self.direction) * length

        oldPoint = self.location
        
        #The point is now created form the X and Y values
        newPoint = Point(self.location.getX()+ newXHeight, \
                         self.location.getY() + newYHeight)
        #A line between the current location and the next location point is
        #created. This will be the actual line that is drawn, it is essentially
        #the hypotenuse of the triange.
        line = Line(oldPoint, newPoint)
        line.setOutline(color)
        line.draw(win)
        #After the line is created, the new turtle position location will be
        #updated to where the new point is located
        self.location = newPoint
        self.direction = newPoint
        
        
    
  
