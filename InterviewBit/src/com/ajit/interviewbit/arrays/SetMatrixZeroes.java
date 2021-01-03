package com.ajit.interviewbit.arrays;

import java.util.ArrayList;

public class SetMatrixZeroes {

	public static void main(String[] args) {

		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(3);
		a.add(5);
		a.add(7);

		ArrayList<Integer> b = new ArrayList<Integer>();
		b.add(10);
		b.add(11);
		b.add(16);
		b.add(20);

		ArrayList<Integer> c = new ArrayList<Integer>();
		c.add(23);
		c.add(30);
		c.add(34);
		c.add(50);

		ArrayList<ArrayList<Integer>> arrList = new ArrayList<ArrayList<Integer>>();
		arrList.add(a);
		arrList.add(b);
		arrList.add(c);

		// setZeroes(arrList); System.out.println(searchMatrix(arrList, 50));

	}

	/**
	 * SETZERO - Arrays
	 * 
	 * Given an m x n matrix of 0s and 1s, if an element is 0, set its entire row
	 * and column to 0.
	 * 
	 * Do it in place.
	 * 
	 * Example
	 * 
	 * Given array A as
	 * 
	 * 1 0 1 1 1 1 1 1 1 On returning, the array A should be :
	 * 
	 * 0 0 0 1 0 1 1 0 1 Note that this will be evaluated on the extra memory used.
	 * Try to minimize the space and time complexity.
	 * 
	 * @param A
	 */
	public static void setZeroes(ArrayList<ArrayList<Integer>> A) {
		int m, n;

		if (A == null)
			return;

		m = A.size();
		n = A.get(0).size();

		if (n == 0)
			return;

		for (int i = 0; i < m; i++) {
			boolean zero = false;
			for (int j = 0; j < n; j++) {
				if (A.get(i).get(j) == 0)
					zero = true;
			}

			if (zero)
				clearRow(A, i, m, n, 2);

		}

		for (int i = 0; i < n; i++) {
			boolean zero = false;
			for (int j = 0; j < m; j++) {
				if (A.get(j).get(i) == 0)
					zero = true;
			}

			if (zero)
				clearCol(A, i, m, n, 2);

		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (A.get(i).get(j) == 2)
					A.get(i).set(j, 0);
			}
		}
	}

	private static void clearRow(ArrayList<ArrayList<Integer>> A, int row, int m, int n, int value) {

		for (int i = 0; i < n; i++) {
			if (A.get(row).get(i) == 1)
				A.get(row).set(i, value);
		}
	}

	private static void clearCol(ArrayList<ArrayList<Integer>> A, int col, int m, int n, int value) {
		for (int i = 0; i < m; i++) {
			if (A.get(i).get(col) == 1)
				A.get(i).set(col, value);
		}
	}

}
