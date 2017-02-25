d .#File:         hw5.py
#Author:       Thomass Hervey
#Date:         Due 3/13/11
#E-mail:       h46@umbc.edu
#Description:  This program will be used to list all of the different atributes
#              of the positive integers in range that the user gives. Things
#              that will be covered are whether the number is: ODD/EVEN, PRIME/
#              COMPOSITE, PERFECT/ABUNDANT/DEFICIENT,SQUARE,TRIANGULAR.
#              Each number will be printed in a formatted table to list which
#              of the previous criteria it meets.


import math
import string


#This is the skeleton function of the program that will call and run all of
#the other functions
def main():
    print sumDivisors(20)

    #Main starts off by printing the greeting
    printGreeting()

    #This will get the range of numbers from the user and assign them to
    #variables start_integer and end_integer
    start_integer = input('Please enter your starting positive range number: ')
    while start_integer > 100000 or start_integer <= 0:
        start_integer = input('Sorry that is out of the 1-100000 range.' \
                              ' Please enter a new starting positive \nrange '\
                              'integer: ')
    end_integer = input('Please enter your ending positive range integer '\
                        'greater than '+ str(start_integer) + ': ')
    while end_integer > 100000 or end_integer <= 0:
        end_integer = input('Sorry that is out of the 1-100000 range. ' \
                            'Please enter a new ending positive \nrange '\
                            'integer: ')
    while end_integer <= start_integer:
        end_integer = input('Sorry, your end positive range integer must be '\
                            'more than your start positive \nrange integer.'\
                            ' \nPlease enter a new end positive range integer: ')
    print ('______________________________________________________________'\
           '________________')

    printTableHeading()
    #This loop will go through all of the numbers that we want in the range
    #that the user has put in. n will be used as the current integer that is
    #called in each function.
    for n in range(start_integer,end_integer+1):

        #This will set the odd variable as a string equal to the return value
        #of isOdd
        if (isOdd(n) == True):
            odd = 'Odd'
        else:
            odd = 'Even'

        #This will set the prime variable as a string equal to the return value
        #of isPime
        if (n == 1):
            prime = 'Neither'
        elif (isPrime(n) == True):
            prime = 'Prime'
        else:
            prime = 'Composite'

        #This will set the perfect variable as a string equal to the return
        #value of checkForPerfect
        if (checkForPerfect(n) == 'Perfect'):
            perfect = 'Perfect'
        elif (checkForPerfect(n) == 'Abundant'):
            perfect = 'Abundant'
        else:
            perfect = 'Deficient'

        #This will set the square variable as the string equal to the return
        #value of isSquare
        if (isSquare(n) == True):
            square = 'Square'
        else:
            square = ''

        #This will set the triangular variable equal to the return value of
        #isTriangular
        if (isTriangular(n) == True):
            triangular = 'Triangular'
        else:
            triangular = ''

        #This calls on the printing function that will actually print each
        #line in our chart with the updated information for the letter n each
        #time the loop goes through.
        printTableLine(n, odd, prime, perfect, square, triangular)


#This function will explain to/greet the user about the program. It also will
#return the two numbers that define the user's range of integers.
def printGreeting():
    print ('______________________________________________________________'\
           '________________')
    print 'Hello, welcome to the Classifier. This program will classify ' \
          'positive integers \nin several ways including:'
    print 'Odd/Even  Prime/Composite   Perfect/Abundant/Deficient  Square  '\
          'and  Triangular.'
    print ('______________________________________________________________'\
           '________________')
    print 'Please enter the two positive integers for which you want your '\
          'range to be. The range should be within 1 - 10000.'


#This function will print the header/table format.
def printTableHeading():
    str1,str2 = 'Int','Classifications....................................'\
                '.............'
    print "%-10s %-15s" % (str1,str2)
    print ('----------------------------------------------------------------'\
          '----------------')


#This predicate function will check to see if the integer is odd. If so, it
#returns True if not, it will return False.
def isOdd(n):
    if n % 2 ==1:
        return True
    else:
        return False


#This predicate function will check to see if the integer is prime. If so, it
#returns True, if not it returns False.
def isPrime(n):
    for x in range (2,(n/2)+1):
        if n % x ==0:
            return False
    else:
        return True


#This function will check to see if the integer is 'perfect' 'abundant' or
#'deficient.' Depending on the type, a different value will be returned.
def checkForPerfect(n):
    sumDivisors_number = sumDivisors(n)
    #These check to see if the divisor sum is less than, equal to, or greater
    #than our integer n. Depending on what it is, the function returns
    #which condition it is.
    if sumDivisors_number == n:
        return 'Perfect'
    elif sumDivisors_number > n:
        return 'Abundant'
    else:
        return 'Deficient'


#This function returns the sum of all of the divisors of the passed integer.
def sumDivisors(n):
    divisor_sum = 0
    #This goes through each number before n and gets the divisor sum
    for x in range(1,n):
        #Calls the isDivisor function to  check to see if the value
        #is a divisor or not
        if isDivisor(n,x) == True:
            divisor_sum +=x
    return divisor_sum


#This prediacte function will check to see if the passed value b is a divisor
#of the passed value a. If it is, the function will return True.
def isDivisor(a,b):
        if a % b ==0:
            return True
        else:
            return False


#This function checks to see if the integer is a perfect square. If it is, it
#will return True, if not, it will return False
def isSquare(n):
    if isDivisor(n,math.sqrt(n)):
        return True
    else:
        return False


#This function checks to see if the integer is a triangular number. If it is,
#the function will return True, if not, it will return False.
def isTriangular(n):
    #This sets our value as a temporary variable that will go through the
    #formula to find if it is triangular. If so, it then checks to see if
    #it is greater than 0 or not to finalize if the number is triangular.
    temp_variable = (math.sqrt(8 * n + 1) - 1) / 2
    #This checks to see if temp_varialbe is an integer. If so, n is
    #triangular because the number is whole. If the number is a double/float
    #n is not triangular.
    if temp_variable - int(temp_variable) > 0:
        return False
    else:
        return True


#This is the actual printing function that will go through the range of the
#numbers that the user gave and print out each integer with its corresponding
#atributes line-by-line.
def printTableLine(n, odd, prime, perfect, square, triangular):
    print '%-10i %-10s %-10s %-10s %-10s %-10s' % (n, odd, prime, perfect, square, triangular)


main()
