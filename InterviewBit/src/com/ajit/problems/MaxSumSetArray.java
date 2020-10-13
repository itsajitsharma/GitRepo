package com.ajit.problems;

import java.util.ArrayList;

public class MaxSumSetArray {
	
	
	/*
	 * maxset - Arrays
	 * 
	 * Find out the maximum sub-array of non negative numbers from an array. The
	 * sub-array should be continuous. That is, a sub-array created by choosing
	 * the second and fourth element and skipping the third element is invalid.
	 * 
	 * Maximum sub-array is defined in terms of the sum of the elements in the
	 * sub-array. Sub-array A is greater than sub-array B if sum(A) > sum(B).
	 * 
	 * Example:
	 * 
	 * A : [1, 2, 5, -7, 2, 3] The two sub-arrays are [1, 2, 5] [2, 3]. The
	 * answer is [1, 2, 5] as its sum is larger than [2, 3] NOTE: If there is a
	 * tie, then compare with segment's length and return segment which has
	 * maximum length NOTE 2: If there is still a tie, then return the segment
	 * with minimum starting index
	 */

	public ArrayList<Integer> maxset(ArrayList<Integer> a) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		ArrayList<Integer> currentList = new ArrayList<Integer>();
		long sum = 0;
		long currentSum = 0;
		for (Integer val : a) {

			if (val >= 0) {
				currentList.add(val);
				currentSum = currentSum + val;
			} else {
				if (sum < currentSum) {
					sum = currentSum;
					returnList = currentList;
				} else if (sum == currentSum) {
					if (returnList.size() < currentList.size()) {
						returnList = currentList;
					}
				}
				currentSum = 0;
				currentList = new ArrayList<Integer>();
			}
		}

		if (sum < currentSum) {
			sum = currentSum;
			returnList = currentList;
		} else if (sum == currentSum) {
			if (returnList.size() < currentList.size()) {
				returnList = currentList;
			}
		}

		return returnList;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>(); 
		a.add(1967513926);
		a.add(1540383426); 
		a.add(-1303455736); 
		a.add(-521595368);
		MaxSumSetArray test = new MaxSumSetArray(); 
		System.out.println(test.maxset(a));
	}

}
