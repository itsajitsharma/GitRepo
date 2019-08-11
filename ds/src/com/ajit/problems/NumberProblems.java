package com.ajit.problems;

public class NumberProblems {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		printDigits(1234);

	}

	
	
	public static void printDigits(int digit) {
		if (digit >= 10) {
			printDigits(digit / 10);
			System.out.println(digit % 10);
		} else {
			System.out.println(digit);
		}
	}

}
