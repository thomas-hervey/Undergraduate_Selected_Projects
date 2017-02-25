#File:        queue.py
#Author:      Thomas Hervey
#Date:        5/1/11
#E-Mail:      h46@umbc.edu
#Section:     3
#Description: This program will be imported into our proj1 program and used
#             to get the information about how to and the actual storage of
#             our examined tags into a que. This program is able to place tags
#             into a que and retrieve tags off of the top when needed.


# enque()
# Using the current line of the file, checks to see if there are any tags in
# the line. If so, it takes and stores only the needed tags.
# Inputs: lineText (current file line), ourQue()
# Output: Calls stripTest(lineText), stores lineTag in ourQue()
def enque(ourTag, ourQue):
    if ourTag != None:
        #Adds the passed tag value ontop of our que.
        ourQue.append(ourTag)


# deque()
# Accesses the first value in ourQue as text which will be used for comparison.
# Then, the value will be removed from the top of ourQue().
# Inputs: ourQue()
# Outputs: ourQue(0)
def deque(ourQue):
   #Sets a que size varaible and checks to see that if it is larger than 0.
   queSize = len(ourQue)
   if queSize > 0:
       #Declares the value to be dequed as the first value in the q
       dequedValue = ourQue[0]
       #Once the value is 'dequed' is is deleted so further comparisons can
       #be made.
       del(ourQue[0])
       #Returning acts as 'dequeing' by getting the value to compare.
       return dequedValue
   else:
       print 'Empty Que'
