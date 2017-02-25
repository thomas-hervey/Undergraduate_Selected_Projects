#Filename:      hw3.py
#Name:          Thomas Hervey
#Date:          2/21/11
#Section #:     3
#Email:         h46@umbc.edu
#Discription:   This program will act as a decoder to reverse the encoder effect
#               of the Caesar cypher that we made in out lab. This will be done
#               through a combination of a fixed rotation length and English
#               letter frequency

NUMBER_OF_LETTERS = 26
LETTER_E = 4
import string
import textwrap


def main():
    #This asks the user for the original input text
    text = raw_input('')
    #This puts the input text to all caps
    all_caps = string.upper(text)
    #These are the two declared arrays, the first one to store frequencies,
    #the next one stores the final letters
    current_array = []
    final_array = []

    #This loop will go through all of the ASCII values 'A'-'Z' and call on the
    #frequency function to assign the frequency of each letter for the current
    #selected letter in the alphabet
    for i in range(26):
        current_letter = chr(ord('A') + i)
        #This sets the frequency of the current letter by calling the frequency
        #method to check how often in the input text the letter appears
        letter_frequency = frequency(all_caps, current_letter)

        #This adds the new frequency number to the correct position in the
        #current_array array in the exact position 1-26, just like the
        #letters of the alphabet. This will be used to check how similar
        #it is to the expected letter frequency in the English language
        current_array.append(letter_frequency)

    #This is the max value variable in the array that will be referenced
    max_value = max(current_array)
    #Checks to see if "E" is the most frequent number, so no need for a shift
    if max_value == current_array[4]:
        print all_caps
    #else, call the shift  function
    else:
        shift(all_caps)

    frequency_index = 21

    #This loop uses our new shift to store the correct letters in the right
    #places in the final_array array. This is just like our lab3 work
    for char in all_caps:
        if char != ' ' and char != ',' and char != '.':
            value = ord(char)
            new_value = value + frequency_index
            if new_value > ord('Z'):
                new_value = new_value - NUMBER_OF_LETTERS
            char = chr(new_value)
        final_array.append(char)
    #This will be the final message as a string not coming from the final_array
    final_message = ''.join(final_array)
    print textwrap.fill(final_message)


#This function will check to see the frequency of the current passed letter in
#in the text.
def frequency(all_caps, current_letter):
    character_counter = 0
    character_counter = string.count(all_caps,current_letter)
    return character_counter


#This function will act as the shift effect to the string. This will be called
#as the else statement in the main function whenever the highest frequency
#letter is not equal to the array's index at 4 (the letter E)
def shift(current_array):
    frequency_index = current_array.index(max(current_array))
    if frequency_index < LETTER_E:
        frequency_index = LETTER_E - frequency_index
    else:
        frequency_index = LETTER_E - frequency_index + NUMBER_OF_LETTERS


main()
