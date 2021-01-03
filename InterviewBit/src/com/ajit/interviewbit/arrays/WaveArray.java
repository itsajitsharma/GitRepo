package com.ajit.interviewbit.arrays;

import java.util.ArrayList;
import java.util.Collections;

public class WaveArray {

	public static void main(String[] args) {
		
		 ArrayList<Integer> a = new ArrayList<Integer>();
		 //-2,1,-3,4,-1,2,1,-5,4 
		 a.add(1); 
		 a.add(2); 
		 a.add(3); 
		 a.add(4);
		 a.add(5); 
		 a.add(6); 
		 a.add(7); 
		 //a.add(8);
		  
		 System.out.println(wave(a));
		 
	}
	
	
	/**
	 * Wave - Arrays
	 * 
	 * Given an array of integers, sort the array into a wave like array and
	 * return it, In other words, arrange the elements into a sequence such that
	 * a1 >= a2 <= a3 >= a4 <= a5.....
	 * 
	 * Example
	 * 
	 * Given [1, 2, 3, 4]
	 * 
	 * One possible answer : [2, 1, 4, 3] Another possible answer : [4, 1, 3, 2]
	 * NOTE : If there are multiple answers possible, return
	 * 
	 * @param a
	 * @return
	 */
	public static ArrayList<Integer> wave(ArrayList<Integer> a) {
		Collections.sort(a);
		/*
		 * int[] arr = new int[a.size()]; int[] arrB = new int[a.size()];
		 */
		for (int i = 0; i < a.size(); i += 2) {
			if (i + 1 < a.size()) {
				int temp = a.get(i);
				a.set(i, a.get(i + 1));
				a.set(i + 1, temp);
			}
		}

		return a;
	}

}
