package com.ajit.interviewbit.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LargestNumber {

	public static void main(String[] args) {

		ArrayList<Integer> a = new ArrayList<Integer>(); // 3, 30, 34, 5, 9
		int[] arr = new int[] { 751, 718, 708, 722, 77 };// , 9, 99, 999, 9999,9998};
		for (int i : arr) {
			a.add(i);
		}
		a.add(3);
		a.add(30);
		a.add(34);
		a.add(5);
		// a.add(5);
		// a.add(9);
		// a.add(7);
		System.out.println(largestNumber(a));

	}

	// DO NOT MODIFY THE LIST
	public static String largestNumber(final List<Integer> a) {
		StringBuilder strBld = new StringBuilder();
		Collections.sort(a, new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				// System.out.println("Comparing "+ arg0 +" and " + arg1);
				if (arg0 == arg1) {
					return 0;
				}
				long first = Long.parseLong(String.valueOf(arg0) + String.valueOf(arg1));
				long second = Long.parseLong(String.valueOf(arg1) + String.valueOf(arg0));
				return first > second ? 1 : -1;

			}
		});

		// check for all numbers are 0
		boolean nonZeroOccurred = false;
		for (int i = a.size() - 1; i >= 0; i--) {
			if (a.get(i) > 0) {
				nonZeroOccurred = true;
			}
			if (nonZeroOccurred) {
				strBld.append(a.get(i));
			}
		}

		if (strBld.length() == 0) {
			return "0";
		}
		return strBld.toString();
	}

}
