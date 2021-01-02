package com.ajit.interviewbit.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * MAXSUM - Arrays
 * 
 * Find the contiguous subarray within an array (containing at least one
 * number) which has the largest sum.
 * 
 * For example:
 * 
 * Given the array [-2,1,-3,4,-1,2,1,-5,4],
 * 
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * 
 * For this problem, return the maximum sum.
 * 
 * @param a
 * @return
 */

public class MaxSumContinuosSubArray {
	
	public static void main(String[] args) {
		
		
		 ArrayList<Integer> a = new ArrayList<Integer>();
		 //-2,1,-3,4,-1,2,1,-5,4 
		 a.add(-2); 
		 a.add(1); 
		 a.add(-3); 
		 a.add(4);
		 a.add(-1); 
		 a.add(2); 
		 a.add(1); 
		 a.add(-5); 
		 a.add(4); 
		 MaxSumContinuosSubArray test = new MaxSumContinuosSubArray(); 
		 System.out.println(test.maxSubArray(a));
		 
		 ArrayList<Integer> b = new ArrayList<Integer>();
		 //-2,1,-3,4,-1,2,1,-5,4 
		 b.add(-6); 
		 b.add(-4);
		 System.out.println(test.maxSubArray(b));
		 
		
	}
	
	
	public int maxSubArray(final List<Integer> a) {
		int sum = 0;
		int currentSum = 0;
		boolean allNegative = true;
		int minVal = Integer.MIN_VALUE;
		for (Integer value : a) {
			if (value < 0) {
				if (currentSum > sum) {
					sum = currentSum;
				}
				if (allNegative && (value > minVal)) {
					minVal = value;
				}
			} else {
				allNegative = false;
			}
			currentSum = currentSum + value;
			if (currentSum < 0) {
				currentSum = 0;
			}
		}
		if (allNegative) {
			return minVal;
		}
		if (currentSum > sum) {
			sum = currentSum;
		}
		return sum;
	}

}
