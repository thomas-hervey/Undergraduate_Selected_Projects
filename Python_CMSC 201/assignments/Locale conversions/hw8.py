#File:         hw8.py
#Author:       Thomas Hervey
#E-Mail:       h46@umbc.edu
#Section:      3
#Description:  This program acts as a converter changing and displaying the
#              user's menu option of either current time, currency or
#              temperature for one of several cities around the world. Then
#              it prints out the results and compares it to the doemstic stats
#              here on the Eastern Coast in America.

import textwrap
import sys


def main():
    printGreeting()
    displayMainMenu()
    #This will get the main menu choice 1 - 4 from the user.
    conversionChoice =  getValidInt('Enter your choice (1 - 4): ', 1, 4)
    while conversionChoice != 4:
        #Option to convert the time of the following city choices, calls the
        #convertTime function.
        if conversionChoice == 1:
            convertTime()
        #Option to convert the currency of the following city choices, calls
        #the convertCurrency function.
        elif conversionChoice == 2:
            convertCurrency()
        #Option to convert the temperature of the following city choices, calls
        #the convertTemp function.
        elif conversionChoice == 3:
            convertTemp()
        #After specific conversion is done and the user prompts to go back to
        #the main menu, this loop will display the main menu and get the
        #user's choice again.
        displayMainMenu()
        conversionChoice = getValidInt('Enter your choice (1 - 4): ', 1, 4)
    sys.exit()

    
#printGreeting() displays text that the user reads to get an understanding of
#what the program does and what they do within the program. It explains that
#it is a conversion program for time, currency and temperature.
#Input: none
#Output: none
def printGreeting():
    print textwrap.fill('Hello and welcome to the foreign converter program. '\
                        'Here in this program, the user will be able to '\
                        'select one of several foreign cities and compare '\
                        'either the time difference, the currency ratio '\
                        'or the temperature between the chosen city and '\
                        'our own area here back on the East Coast of America.')
    print ('________________________________________________________________' \
          '__________')


#displayMainMenu() is called by the main method and acts as the program's main
#menu. It gives the user several conversion options as well as a quit one. The
#main menu is called whenever the user hits M to return to the main menu to
#get out of doing any further conversions of the already chosen type.
#Input: none
#Output: prints menu options
def displayMainMenu():
    print ('*******************************')
    print ('-----------Main Menu-----------')
    print ('*******************************')
    print 'What would you like to convert?'
    print ' \t 1 - Time \n \t 2 - Currency \n \t 3 - Temperature \n \t 4 - QUIT'


#displayLocationsMenu() is a function that acts as the following menu after the
#main menu has made a selection. Within the function, the user has a choice of
#different cities to make a conversion from. It checks to make sure that the
#user put in a valid letter for one of the cities (or M to return to the main
#menu) and returns the location choice to the specific conversion function that
#called it.
#Input: none
#Output: prints locations menu; returns locationChoice
def displayLocationsMenu():
    print ('=================================================')
    print ('-----------------Locations Menu------------------')
    print ('=================================================')
    print ('Choose a location or M to return to the Main Menu')
    print ('\t L - London \n \t S - Stockholm \n \t T - Tampere \n '\
           '\t H - Helsinki \n \t P - St. Petersburg \n \t M - Return to '\
           'Main Menu')
    locationChoice = raw_input('Enter location or M to return to the Main '\
                               'Menu: ')
    #Checks to make sure that the
    while locationChoice[0] != 'L' and  locationChoice[0] != 'l' and  \
          locationChoice[0] != 'S' and  locationChoice[0] != 's' and  \
          locationChoice[0] != 'T' and  locationChoice[0] != 't' and  \
          locationChoice[0] != 'H' and  locationChoice[0] != 'h' and  \
          locationChoice[0] != 'P' and  locationChoice[0] != 'p' and  \
          locationChoice[0] != 'M' and  locationChoice[0] != 'm':
        print('Sorry, that is an invalid letter for an option.')
        locationChoice = raw_input('Enter location or M to return to the '\
                                   'Main Menu: ')
    return locationChoice


#convertTime() is a function that gets called if the user puts in 1 in the main
#menu. It is the head function that deals with all of the time conversion.
#It first gets the location choice and then, depending on the city, calls
#getValidTime() and prints out the original and new time conversion in one
#line comparing the two.
#Input: none
#Output: print final conversion comparison statement
def convertTime():
    #Time constants
    LONDON_HRS = 5
    STOCKHOLM_HRS = 6
    TAMPERE_HRS = 7
    HELSINKI_HRS = 7
    ST_PETE_HRS = 8
    #Getting the city location for the first time, calling on the
    #locationChoice function
    locationChoice = displayLocationsMenu()
    #Delcare city initial variable to change depending on locationChoice.
    city = ''
    #As long as the user doesn't select M, do the selection and prompt again.
    while locationChoice[0] != 'M' and locationChoice[0] != 'm':
        #Before going onto the different city options, make sure the time that
        #the user put in is a real possible time
        questionHour, questionMinute = getValidTime()

        #Choice for London
        if locationChoice[0] == 'L' or  locationChoice[0] == 'l':
            #Getting the foreign to eastern conversion
            amPm, questionHour2 = foreignTimetoEastern(questionHour, \
                                                       LONDON_HRS)
            city = 'London'
        #Choice for Stockholm
        elif locationChoice[0] == 'S' or  locationChoice[0] == 's':
            #Getting the foreign to eastern conversion
            amPm, questionHour2 = foreignTimetoEastern(questionHour, \
                                                       STOCKHOLM_HRS)
            city = 'Stockholm'
        #Choice for Tampere
        elif locationChoice[0] == 'T' or  locationChoice[0] == 't':
            amPm, questionHour2 = foreignTimetoEastern(questionHour, \
                                                       TAMPERE_HRS)
            city = 'Tampere'
        #Choice for Helsinki
        elif locationChoice[0] == 'H' or  locationChoice[0] == 'h':
            #Getting the foreign to eastern conversion
            amPm, questionHour2 = foreignTimetoEastern(questionHour, \
                                                       HELSINKI_HRS)
            city = 'Helsinki'
        #Choice for St. Petersburg
        else:
            #Getting the foreign to eastern conversion
            amPm, questionHour2 = foreignTimetoEastern(questionHour, \
                                                       ST_PETE_HRS)
            city = 'St. Petersburg'
        #Checks to see if the minute is less than 10, if so it adds a 0.
        if questionMinute < 10:
            questionMinute = '0%s' % (questionMinute)
        #prints out the final time conversion and asks for the location again.
        print str(questionHour) + ':' + str(questionMinute) + ' in ' \
              + str(city) + ' is ' + str(int(questionHour2)) + ':' \
              + str(questionMinute) + ' ' + str(amPm) + ' EST'
        #Calling the location menu again to get a new value for the while loop.
        locationChoice = displayLocationsMenu()


#getValidTime() is called by convertTime to get the original two variables
#needed to be converted and printed, questionHour and questionMinute. The
#function asks for the users input on both and calls getValidInt to check
#to make sure that the number is useable.
#Input: none
#Output: questionHour, questionMinute (both valid usable numbers)
def getValidTime():
    MINMINUTE = 0
    MAXMINUTE = 59
    MAXHOUR = 23
    MINHOUR = 0
    MAXHOUR = 23
    print 'What time is it?'
    print
    #Checks for valid and sets our hour equal to the hour question
    questionHour = getValidInt('Enter the hour (0 - 23): ', MINHOUR, MAXHOUR)
    #Checks for balid and sets our hour equal to the minute question
    questionMinute = getValidInt('Enter the minute (0 - 59): ', \
                                 MINMINUTE, MAXMINUTE)
    return questionHour, questionMinute


#getValidInt() is called by getValidTime to check to make sure that both
#questionHour and questionMinute are within the designated reasonable range.
#If either of the two passed varibles isn't in the designated range, the
#question to get an appropriate number will be continuted to be asked until a
#valid number is given. This function is called twice.
#Input: question (string for the appropriate question, like an input);
#       range max; range min
#Output: returns a valid userNumber
def getValidInt(question, min, max):
    userNumber = input(question)
    while userNumber < min or userNumber > max:
        userNumber = input(question)
    return userNumber


#foreignTimetoEastern() is called by convertTime and decides if the time here
#on the east coast is AM or PM and what number it is in, not military style.
#Input: hour number; time adjustment
#Output: amPm (either 'AM' or 'PM'); standard time rounded integer hour
def foreignTimetoEastern(hour, adjustment):
    #Declare the AM/PM variable
    amPm = 'AM'
    #Makes sure that the hour is standard not military if it is over 12
    #(this is when it is in the pm: 13-23)
    if hour > 12:
        hour -= 12
        amPm = 'PM'
        hour -= adjustment
        if hour < 0:
            hour += 12
            amPm = 'AM'
    #Makes sure time is valid when the starting hour isn't over 12.
    else:
        hour -= adjustment
        if hour < 0:
            hour += 12
            amPm = 'PM'
    #Sets all 0 O'clocks to 12
    if hour == 0:
        hour = 12
    return amPm, round(int(hour))


#convertCurrency() is a function that gets called when the user enters option
#2 in the main menu. From here, it gets the locationChoice by calling
#displayLocationsMenu and, depending on the city, makes sure the currency
#the user enters in is a real acceptable number by calling getPositiveReal,
#and then calls foreignToDollars to to all of the currency conversion.
#Input: none
#Output: none
def convertCurrency():
    #Currency constants
    POUNDS = 1.53730
    KRONORS = 0.139083
    EUROS = 1.34960
    RUBLE = 0.0343348
    #Getting the city location for the first time, calling on the
    #locationChoice function
    locationChoice = displayLocationsMenu()
    #As long as the user doesn't select M, do the selection and prompt again.
    while locationChoice[0] != 'M' and locationChoice[0] != 'm':
        #Choice for London
        if locationChoice[0] == 'L' or  locationChoice[0] == 'l':
            amount = getPositiveReal('How many pounds? ')
            foreignToDollars(amount, POUNDS)
        #Choice for Stockholm
        elif locationChoice[0] == 'S' or  locationChoice[0] == 's':
            amount = getPositiveReal('How many kronors? ')
            foreignToDollars(amount, KRONORS)
        #Choice for Tampere
        elif locationChoice[0] == 'T' or  locationChoice[0] == 't':
            amount = getPositiveReal('How many euros? ')
            foreignToDollars(amount, EUROS)
        #Choice for Helsinki
        elif locationChoice[0] == 'H' or  locationChoice[0] == 'h':
            amount = getPositiveReal('How many euros? ')
            foreignToDollars(amount, EUROS)
        #Choice for St. Petersburg
        else:
            amount = getPositiveReal('How many ruple? ')
            foreignToDollars(amount, RUBLE)
        #Get a new location to be checked by the while loop again.
        locationChoice = displayLocationsMenu()


#getPositiveReal() is called by convertCurrency to make sure that the user only
#enters in a positive currency amount. Every time they enter a number less than
#or equal to 0, they will be prompted again.
#Input: question(asking how much money do we want to convert)
#Output: returns the valid amount of money the user put in.
def getPositiveReal(question):
    amount = input(question)
    while amount <= 0:
        amount = input(question)
    return amount


#foreignToDollars() is called by convertCurreny to do the actual conversion
#of the user entered known currency to domestic US dollars. It checks to see
#which currency type it is by looking at its conversion type. Depending on
#which type it is, currencyName changes and then the converted US dollar
#amount is converted using formula with the function inputs. Then, the
#converted currency is rounded and the final conversion statement is printed.
#Input: units(foreign currency amount); conv(the conversion rate)
#Output: final converted currency print statement
def foreignToDollars(units, conv):
    POUNDS = 1.53730
    KRONORS = 0.139083
    EUROS = 1.34960
    RUBLE = 0.0343348
    currencyName = ''
    if conv == POUNDS:
        currencyName = 'Pounds'
    elif conv == KRONORS:
        currencyName = 'Kronor'
    elif conv == EUROS:
        currencyName = 'Euro'
    else:
        currencyName = 'Ruble'
    #Formula to get the number of foreign currency times the conversion rate
    #and set it equal to our variable for the dollar amount.
    currencyConverted = units * conv
    #This rounds our final dollar variable to two decimal places.
    currencyConverted = round(currencyConverted, 2)
    print str(units) + ' ' + currencyName + ' is $' + str(currencyConverted)


#convertTemp() is a function that is called when the user enters in option 3
#from the main menu. It is the head function when dealing with temperature
#change. It gets the user's celsius input, calls calsiusToFahrenheit which
#converts the number, and then prints out the final conversion statement.
#Input: none
#Output: final temperature conversion statement
def convertTemp():
    #Getting the celsius temperature from the user.
    celsius = input('What is the celsius temperature? ')
    #Calls calsiusToFahrenheit to convert the temperature.
    fahrenheit = celsiusToFahrenheit(celsius)
    print str(celsius) + ' degrees C is ' + str(fahrenheit) + ' degrees F.'


#celsiusToFahrenheit() is called by convertTemp to get the user's input and
#change it to a Fahrenheit temperature reading. This is done by the simple
#mathmatical formula.
#Input: celsius(user's celsius number)
#Output: returns fahrenheit variable
def celsiusToFahrenheit(celsius):
    fahrenheit = celsius * (9.0 / 5.0) + 32
    return fahrenheit


main()
