"""
This module is an example of the INPUT-COMPUTE-OUTPUT pattern.
It demonstrates:
  -- DEFINING functions:
       -- main
       -- convert_to_celsius
       -- convert_to_fahrenheit
  -- How    main   CALLS the    convert_to_...    functions.
  -- How to INPUT a number using:
       -- input: to prompt for and input a string
       -- float: to convert a string to a number
       -- a variable in which to store the number,
             via the ASSIGNMENT operator = (read it as "gets")
  -- How to COMPUTE using variables and arithmetic operators
  -- How to OUTPUT using:
       -- print
       -- variables that hold values
  -- The code at the bottom that makes execution START in   main
  -- Comments:
       -- Docstrings (multi-line, in green) and
       -- Internal comments (single-line, in pink)

Authors: Susan Computewell (aka John Zelle), modified by David Mutchler,
         Amanda Stouder, Chandan Rupakheti, Claude Anderson,
         and their colleagues.  December 2013.
"""
#-----------------------------------------------------------------------
# Students: Read and run this program.  There is nothing else
#           for you to do here.  Just use it as an example to see:
#   1. Defining functions
#   2. Calling functions
#   3. Getting input, computing with it, and printing output.
#-----------------------------------------------------------------------


def main():
    """ Calls the   convert_to_...   functions to demonstrate them. """
    convert_to_celsius()
    convert_to_fahrenheit()


def convert_to_celsius():
    """
    Prompts for and inputs a Fahrenheit temperature
    and prints (on the console) the equivalent Celsius temperature.
    """
    input_string = input('What is the Fahrenheit temperature? ')
    fahrenheit = float(input_string)
    celsius = (fahrenheit - 32) * (5 / 9)

    print('That temperature is', celsius, 'degrees Celsius.')


def convert_to_fahrenheit():
    """
    Prompts  for and inputs a Celsius temperature
    and prints (on the console) the equivalent Fahrenheit temperature.
    """
    # Note: The next statement does input and "floatifying"
    #   in a SINGLE statement: it calls the  float  function,
    #   using the value obtained from calling the  input  function.
    celsius = float(input('What is the Celsius temperature? '))
    fahrenheit = ((9 / 5) * celsius) + 32

    print('That temperature is', fahrenheit, 'degrees Fahrenheit.')

#-----------------------------------------------------------------------
# If this module is running at the top level (as opposed to being
# imported by another module), then call the 'main' function.
#-----------------------------------------------------------------------
if __name__ == '__main__':
    main()
