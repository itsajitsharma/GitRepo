package com.ajit.interviewbit;

import java.util.ArrayList;


/*You are given a binary string(i.e. with characters 0 and 1) S consisting of characters S1, S2, …, SN. In a single operation, you can choose two indices L and R such that 1 <= L <= R <= N and flip the characters SL, SL+1, …, SR. By flipping, we mean change character 0 to 1 and vice-versa.

Your aim is to perform ATMOST one operation such that in final string number of 1s is maximised. If you don’t want to perform the operation, return an empty array. Else, return an array consisting of two elements denoting L and R. If there are multiple solutions, return the lexicographically smallest pair of L and R.

Notes:

Pair (a, b) is lexicographically smaller than pair (c, d) if a < c or, if a == c and b < d.
For example,

S = 010

Pair of [L, R] | Final string

[1 1]          | 110
[1 2]          | 100
[1 3]          | 101
[2 2]          | 000
[2 3]          | 001

We see that two pairs [1, 1] and [1, 3] give same number of 1s in final string. So, we return [1, 1].
Another example,

If S = 111
No operation can give us more than three 1s in final string. So, we return empty array [].

*
*/

public class FlipArray {
	
	public static void main(String[] args) {
		System.out.println(flip("010"));
		System.out.println(flip("1101010001"));
	}
	
	public static ArrayList<Integer> flip(String A){
		if(A==null || A.length() ==0){
			return new ArrayList<Integer>();
		}
		char[] charArray = A.toCharArray();
		int maxSum = 0;
		int currSum = 0;
		int maxStartInd = -1;
		int maxEndInd = -1;
		int currStartInd = 0;
		int currEndInd = -1;
		int onesCount = 0;
		for (int i = 0; i < charArray.length; i++) {
			if(charArray[i] == '0'){
				currSum++;
				currEndInd = i;
				if(currSum > maxSum){
					maxSum = currSum;
					maxStartInd = currStartInd;
					maxEndInd = currEndInd;
				}
			}
			else{
				onesCount++;
				currSum--;
				if(currSum < 0){
					currStartInd = i+1;
					currSum = 0;
				}
			}
		}
		
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		if(maxSum != 0){
			returnList.add(maxStartInd+1);
			returnList.add(maxEndInd+1);
		}
		return returnList;
	}

}
