package com.ajit.interviewbit.arrays;

import java.util.ArrayList;

public class FirstMissingInteger {

	public static void main(String[] args) {
		
		 ArrayList<Integer> a = new ArrayList<Integer>(); 
		 a.add(1);
		 a.add(4); 
		 a.add(-1); 
		 a.add(1);
		 System.out.println(firstMissingPositive(a));

	}
	
	
	/**
	 * POSITIVE - Arrays
	 * 
	 * Given an unsorted integer array, find the first missing positive integer.
	 * 
	 * Example:
	 * 
	 * Given [1,2,0] return 3,
	 * 
	 * [3,4,-1,1] return 2,
	 * 
	 * [-8, -7, -6] returns 1
	 * 
	 * Your algorithm should run in O(n) time and use constant space.
	 * 
	 * @param a
	 * @return
	 */
	public static int firstMissingPositive(ArrayList<Integer> a) {
		
//		This code takes advantage of two insights:
//		1. Numbers greater then n can be ignored because the missing integer must be in the range 1..n+1
//		2. If each cell in the array were to contain positive integers only, 
//		we can use the negative of the stored number as a flag to mark something 
//		(in this case the flag indicates this index was found in some cell of the array)
		
		int j = a.size() - 1;
		// boolean allNegative = true;
		for (int i = 0; i < a.size() && j >= i; i++) {
			if (a.get(i) < 1 || a.get(i) > a.size()) {
				int temp = a.get(i);
				a.set(i, a.get(j));
				a.set(j, temp);
				j--;
				i--;
			}
			/*
			 * else{ allNegative = false; }
			 */
		}

		/*
		 * if(allNegative){ return 1; }
		 */

		for (int i = 0; i <= j; i++) {
			a.set(Math.abs(a.get(i)) - 1,
					-Math.abs((a.get(Math.abs(a.get(i)) - 1))));
		}

		for (int i = 0; i <= j; i++) {
			if (a.get(i) > 0) {
				return i + 1;
			}
		}
		return j + 2;
	}

}
