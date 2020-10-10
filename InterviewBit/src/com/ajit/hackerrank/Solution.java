package com.ajit.hackerrank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
    	
    	marksWalk();
        
    }

    
    
	
    /**
     * 
     * Marc loves cupcakes, but he also likes to stay fit. He eats  cupcakes in one sitting, and each cupcake  has a calorie count, . After eating a cupcake with  calories, he must walk at least  (where  is the number cupcakes he has already eaten) miles to maintain his weight.

		Given the individual calorie counts for each of the  cupcakes, find and print a long integer denoting the minimum number of miles Marc must walk to maintain his weight. Note that he can eat the cupcakes in any order.
		
		Input Format
		
		The first line contains an integer, , denoting the number of cupcakes. 
		The second line contains  space-separated integers describing the respective calorie counts of each cupcake, .
		
		Constraints
		
		Output Format
		
		Print a long integer denoting the minimum number of miles Marc must walk to maintain his weight.
		
		Sample Input 0
		
		3
		1 3 2
		Sample Output 0
		
		11
		Explanation 0
		
		Let's say the number of miles Marc must walk to maintain his weight is . He can minimize  by eating the  cupcakes in the following order:
		
		Eat the cupcake with  calories, so .
		Eat the cupcake with  calories, so .
		Eat the cupcake with  calories, so .
		We then print the final value of , which is , as our answer.
		

     */
    
    
    private static void marksWalk() {
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] calories = new int[n];
        for(int calories_i=0; calories_i < n; calories_i++){
            calories[calories_i] = in.nextInt();
        }
        // your code goes here
        Arrays.sort(calories);
        long minCal = 0;
        for (int i = 0; i<n; i++) {
        	minCal = minCal + (long)(Math.pow(2.0, (i)))*calories[n-i-1];
		}
        System.out.println(minCal);
		
	}
}
