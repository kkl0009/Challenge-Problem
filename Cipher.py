#!/usr/bin/env python3

"""
Name: Kollin Labowski
Cipher Tech Challenge File
"""

# Given feedback value for Challenge 1
FEEDBACK = 0x87654321

"""
This function was created according to the specifications of Challenge 1. It takes a piece of data and an intitial value, then
obfuscates the data using a Linear Feedback Shift Register.
"""
def Crypt(data: bytes, initialValue: int) -> bytes:
	state = initialValue
	result = []

	for byte in data:
		for i in range(0,8):
			if state & 0x01 == 0x01:
				state = (state >> 0x01) ^ FEEDBACK
			else:
				state = state >> 0x01

		lowestByte = state & 0xFF
		result.append(lowestByte ^ byte)

	listAsBytes = bytes(result)
	return listAsBytes

"""
The following lines of code can be used for testing the above function with input values of "data" and "initialValue"
"""
quit = False
while(not quit):
	inputData = input("Data: ")
	inputValue = input("Initial value: 0x")

	finalResult = Crypt(bytes(inputData, 'utf-8'), int(inputValue, 16))

	for i in finalResult:
		print(hex(i))

	reverseResult = Crypt(finalResult, int(inputValue, 16))

	print(reverseResult)

	while(True):
		choice = input("Continue? (y/n): ")

		if choice == "n":
			quit = True
			break
		elif choice == "y":
			break
		else:
			print("Please select \"y\" or \"n\"")