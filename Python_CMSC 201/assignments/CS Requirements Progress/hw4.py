#File:        hw4.py
#Author:      Thomas Hervey
#Date:        Due 3/7/11
#Section:     3
#E-mail:      h46@umbc.edu
#Description: This program will be used to display the already taken and future
#             required classes for the user's individual school class schedule.
#             This program will ask the CMSC major for the list of classes they
#             have already taken so that it can display how far they are and
#             what more they need to take to graduate.


import string

def main():
    #Create the list for classes the user has already taken.
    already_taken_classes = []

    #Create the list for total CMSC major classes
    list_of_major_classes = ['CMSC 201','CMSC 202','CMSC 203']
    list_of_major_classes.extend(['CMSC 304','CMSC 313','CMSC 331'])
    list_of_major_classes.extend(['CMSC 341','CMSC 345','CMSC 411'])
    list_of_major_classes.extend(['CMSC 421','CMSC 441','MATH 151'])
    list_of_major_classes.extend(['MATH 152','MATH 221','STAT 355'])

    #This list will show what classes the user needs left to take.
    classes_left_to_take = [0]

    #These will be used to check each part A,B,C requirements.
    #These lists will store the part A,B,C and D requirements left to take.
    part_A = []
    part_B = []
    part_C = []
    #This will be called in a function and appended to add the classes
    #that were taken as requirements
    part_D_Requirements = []
    part_D_Taken_Classes = []

    #This creates the list of classes that can be taken in part D to meet the
    #12 credit requirements. This will be midified and printed depending on
    #what has already been taken.
    part_D_Classes = ['BIOL 100', 'BIOL 100L','BIOL 251','BIOL 251L']
    part_D_Classes.extend(['BIOL 252','BIOL 252L','BIOL 275','BIOL 275L'])
    part_D_Classes.extend(['BIOL 301','BIOL 302','BIOL 302L','BIOL 303'])
    part_D_Classes.extend(['BIOL 303L','BIOL 304','BIOL 304L','BIOL 305'])
    part_D_Classes.extend(['BIOL 305L','CHEM 101','CHEM 102','CHEM 102L'])
    part_D_Classes.extend(['GES 110','GES 111','GES 120','PHYS 121'])
    part_D_Classes.extend(['PHYS 122','PHYS 122L','PHYS 340L'])

    #Prints out the program's greeting as well as asks the user's information
    print ('______________________________________')
    print ('Hello, welcome to the schedule mapper.')
    print ('Please recall all all courses you have taken so far.')
    print

    #This get the number of classes to set the size of the list.
    number_of_classes = (input('How many classes have you taken? '))
    print ('______________________________________')
    #This acts as a sentinel loop, getting the first data item.
    a = raw_input('Enter a class you have taken in the form <MAJOR-CODE>' \
                 '<COURSE-NUMBER>: ')
    already_taken_classes.append(a)
  #This loop will fill the first list with all the taken classes.
    x = 1
    while x < number_of_classes:
        #This will take the user's inputs and store them as
        #each class the user has taken in the already_taken_classes list.
        a = raw_input('What else have you taken? ')
        already_taken_classes.append(a)
        x = x + 1
    already_taken_classes.sort()
    print ('_______________________________________')
    print 'To complete the major, you still need to take:'
    print

    #This is where the two functions are called on to get the remaining classses
    #and figure out how to print them
    classes_left_to_take = remaining_classes(already_taken_classes, \
                                             list_of_major_classes, \
                                             classes_left_to_take)

    #This will call on the functions to basically split the classes left into
    #the required sections
    section_printingA(classes_left_to_take, part_A)
    section_printingB(classes_left_to_take, part_B)
    section_printingC(classes_left_to_take, part_C)

    #These will actually print the requirements for each part of the major
    #They will check to see if all of the requirements have been met, if not
    #it will print 'you need to take' and the classes left.
    print 'Part A requirements:'
    if part_A ==[]:
        print 'You have satisfied the part A requirements'
    else:
        for x in part_A:
            print 'You need to take: ' , x

    print

    print 'Part B requirements:'
    if part_B ==[]:
        print 'You have satisfied the part B requirements'
    else:
        for x in part_B:
            print 'You need to take: ' , x

    print

    print 'Part C requirements:'
    if part_C ==[]:
        print 'You have satisfied the part C requirements'
    else:
        print 'You need to take: STAT 355 or STAT 451 in place of 355'

    print('____________________________________')


    section_D(part_D_Classes, already_taken_classes, part_D_Requirements)


#This function will check the differences between the required lists and the
#required lists and store each value for the remaining classes in  new list.
#This checks to see if each item is in both lists, if not, the remaining ones
#will be stored.
def remaining_classes(already_taken_classes, list_of_major_classes,\
                      classes_left_to_take):
     #This deals with the either or option for the STAT requirement
     #If the user enters in 451, it will be deleted or replaced with STAT 355.
     if 'STAT 451' in already_taken_classes:
         already_taken_classes.remove('STAT 451')
         already_taken_classes.append('STAT 355')
     if already_taken_classes.count('STAT 355') ==2:
         already_taken_classes.remove('STAT 355')

     #This sets classes_left_to_take equal to the difference between our lists
     #of required classes and already taken classes. It then returns
     #classes_left_to_take so it can be used again.
     classes_left_to_take = [item for item in list_of_major_classes if not \
                             item in already_taken_classes]
     classes_left_to_take.sort()
     return classes_left_to_take


#This function will put all of the A required classes in the right list.
def section_printingA(classes_left_to_take, part_A):
    for X in classes_left_to_take:
        if 'CMSC' in X:
            part_A.append(X)


#This function will put all of the B required classes in the right list.
def section_printingB(classes_left_to_take, part_B):
    for X in classes_left_to_take:
        if 'MATH' in X:
            part_B.append(X)


#This function will put all of the C required classes in the right list.
def section_printingC(classes_left_to_take, part_C):
    for X in classes_left_to_take:
        if 'STAT' in X:
            part_C.append(X)


#This function will be dealing with the first and second parts of part D
def section_D(part_D_Classes, already_taken_classes, part_D_Requirements):

    #These for loops will check each value of already taken classes and
    #remove any taken from the list of bio classes and adds it to the
    #required part D list. This will be good because then we can check to see
    #what requirements have been met and what needs to be taken left.
    #Then, after that, we can print out the remaining credits part and show
    #what classes they can choose from (having removed the already taken ones)

    #*******************************************************************
    #NONE OF THIS REALLY WORKS: I figured I would leave what work for this part
    #I had done already. The credit number part also doesnt work
    for X in already_taken_classes:
        if 'BIOL 100' in X:
            part_D_Classes.remove(X)
            part_D_Requirements.append(X)
    for X in already_taken_classes:
        if 'BIOL 301' in X:
            part_D_Classes.remove(X)
            part_D_Requirements.append(X)
    for X in already_taken_classes:
        if 'CHEM 101' in X:
            part_D_Classes.remove(X)
            part_D_Requirements.append(X)
    for X in already_taken_classes:
        if 'CHEM 102' in X:
            part_D_Classes.remove(X)
            part_D_Requirements.append(X)
    for X in already_taken_classes:
        if 'PHYS 121' in X:
            part_D_Classes.remove(X)
            part_D_Requirements.append(X)
    for X in already_taken_classes:
        if 'PHYS 122' in X:
            part_D_Classes.remove(X)
            part_D_Requirements.append(X)
    print 'Part D Requirements: '

    #This will figure out the OR part for the first part. This checks to see
    #what has been taken and lets the user know what path they can choose from
    #the remaining requirements.

    #This checks the BIOL, CHEM and PHYS parts
    #for X in part_D_Requirements:
     #   if 'BIOL 100' and 'BIOL 301' in part_D_Requirements:
      #      print 'You have satisfied part D requirements'
       # elif 'CHEM 101' and 'CHEM 102' in part_D_Requirements:
       #     print 'You have satisfied part D requirements'
      #  elif 'PHYS 121' and 'PHYS 122' in part_D_Requirements:
        #    print 'You have satisfied part D requirements'
        #else:
            #if 'BIOL 100' in part_D_Classes:
            #    print 'You need to take BIOL 100 /n'
            #if 'BIOL 301' !in part_D_Classes:
            #    print 'AND BIOL 301'
            #print 'OR'
            #if 'CHEM 101' !in part_D_Classes:
            #    print 'You need take CHEM 101 /n'
            #if

    #This will print out the classes option that is in the second part of part
    #D. It will be formatted to be like the example and it will have only
    #the classes left that haven't been taken as options.
    print 'You currently have 0 science credits'
    print 'You need to have 12 total science creits taken from the following '\
          +'list:'
    print 'BIOL 100 BIOL 100L'
    print 'BIOL 251 BIOL 151L'
    print 'BIOL 252 BIOL 252L'
    print 'BIOL 175 BIOL 275L'
    print 'BIOL 301'
    print 'BIOL 302 BIOL 302L'
    print 'BIOL 303 BIOL 303L'
    print 'BIOL 304 BIOL 304L'
    print 'BIOL 305 BIOL 305L'
    print 'CHEM 101'
    print 'CHEM 102 CHEM 102L'
    print 'GES  110 GES  111'
    print 'GES  120 PHYS 121'
    print 'PHYS 122 PHYS 122L'
    print 'PHYS 340L'

    print '_____________________________________________'


main()
