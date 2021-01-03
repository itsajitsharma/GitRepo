package com.ajit.interviewbit.arrays;

import java.util.ArrayList;

public class PascalTriangle {
	
	
	/**
	 * Pascal1 - Arrays
	 * 
	 * Given numRows, generate the first numRows of Pascal’s triangle.
	 * 
	 * Pascal’s triangle : To generate A[C] in row R, sum up A’[C] and A’[C-1]
	 * from previous row R - 1.
	 * 
	 * Example:
	 * 
	 * Given numRows = 5,
	 * 
	 * Return
	 * 
	 * [ [1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1] ] Generates the pascal
	 * triangle
	 * 
	 * @param a
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> generate(int a) {
		ArrayList<ArrayList<Integer>> returnList = new ArrayList<ArrayList<Integer>>();
		if (a == 0) {
			return returnList;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		returnList.add(list);
		if (a == 1) {
			return returnList;
		} else {
			for (int i = 1; i < a; i++) {
				list = new ArrayList<Integer>();
				for (int j = 0; j <= i; j++) {
					if (i == j) {
						list.add(returnList.get(i - 1).get(j - 1));
					} else if (j == 0) {
						list.add(returnList.get(i - 1).get(0));
					} else {
						list.add(returnList.get(i - 1).get(j - 1)
								+ returnList.get(i - 1).get(j));
					}
				}
				returnList.add(list);
			}
			return returnList;
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(generate(5));
	}

}
