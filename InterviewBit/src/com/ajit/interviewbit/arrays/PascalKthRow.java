package com.ajit.interviewbit.arrays;

import java.util.ArrayList;

public class PascalKthRow {

	public static void main(String[] args) {
		System.out.println(getRow(4));

	}
	
	
	/**
	 * PASCAL2 - Arrays
	 * 
	 * 
	 * Given an index k, return the kth row of the Pascal’s triangle.
	 * 
	 * Pascal’s triangle : To generate A[C] in row R, sum up A’[C] and A’[C-1]
	 * from previous row R - 1.
	 * 
	 * Example:
	 * 
	 * Input : k = 3
	 * 
	 * Return : [1,3,3,1] NOTE : k is 0 based. k = 0, corresponds to the row
	 * [1]. Note:Could you optimize your algorithm to use only O(k) extra space?
	 * 
	 * @param a
	 * @return
	 */
	public static ArrayList<Integer> getRow(int a) {
		if (a < 0) {
			return null;
		}
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		tempList.add(1);
		returnList.add(1);
		for (int i = 0; i <= a; i++) {
			for (int j = 1; j <= i; j++) {
				if (j < i) {
					returnList.set(j, tempList.get(j - 1) + tempList.get(j));
				} else {
					returnList.add(1);
				}
			}
			tempList = new ArrayList<Integer>(returnList);
		}
		return returnList;
	}

}
