package com.ajit.interviewbit.arrays;

import java.util.ArrayList;

/**
 * Give a N*N square matrix, return an array of its anti-diagonals. Look at the example for more details.

Example:

		
Input: 	

1 2 3
4 5 6
7 8 9

Return the following :

[ 
  [1],
  [2, 4],
  [3, 5, 7],
  [6, 8],
  [9]
]


Input : 
1 2
3 4

Return the following  : 

[
  [1],
  [2, 3],
  [4]
]
 * 
 * 
 * @author Ajit
 *
 */

public class AntiDiagonals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public ArrayList<ArrayList<Integer>> diagonal(ArrayList<ArrayList<Integer>> A) {
        ArrayList<ArrayList<Integer>> returnList = new ArrayList<ArrayList<Integer>>();
        if(A == null || A.size()==0){
            return returnList;
        }
        if(A.size()==1){
            return A;
        }
        int numRows = A.size();
        for(int col=0; col<numRows; col++){
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int row=0; row<=col; row++){
                list.add(A.get(row).get(col-row));
            }
            returnList.add(list);
        }
        for(int row=1; row<numRows; row++){
            ArrayList<Integer> list = new ArrayList<Integer>();
            int rowInc=0;
            for(int col=numRows-1; col-row>=0; col--,rowInc++){
                list.add(A.get(row+rowInc).get(col));
            }
            returnList.add(list);
        }
        return returnList;
    }

}
