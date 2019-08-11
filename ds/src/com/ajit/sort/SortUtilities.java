package com.ajit.sort;

import java.util.Arrays;

public class SortUtilities {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {1,13,15,4,7,6};
		
		quicksort(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));

	}

	private static void quicksort(int b[], int startIndex, int endIndex) {
		int lower = startIndex+1;
		int upper = endIndex;
		int pivotIndex = startIndex;
		int pivotValue = b[pivotIndex];
		
		while(upper > lower){
			while(pivotValue>=b[lower] && upper>lower){
				lower++;
			}
			while(pivotValue<=b[upper] && upper>lower){
				upper--;
			}
			//when both of the above condition is false i.e 
			//we have found an element at lower index which is smaller than
			// pivot value and an element at upper index whose value is greater than pivot value and both 
			//upper index and lower index are not equal then swap 
			if(upper > lower){
				int temp = b[lower];
				b[lower]= b[upper];
				b[upper]= temp;
			}	
		}
		//we will reach this condition when upper index is equal to lower index
		
		// then either place pivot value at right position that is 
		//either at upper index or 
		if(pivotValue>b[upper]){
			int temp = b[startIndex];
			b[startIndex]= b[upper];
			b[upper]= temp;
			pivotIndex = upper;  
			
		}else{
			//at one below that
			int temp = b[startIndex];
			b[startIndex]= b[upper-1];
			b[upper-1]= temp;
			pivotIndex = upper-1;
		}
		// now once we have placed the pivot element at its right position that is all
		// elements on its left are smaller and all its elements on it right are greater 
		// then start sorting left and right of pivot value 
		
		//following condition checks that updated pivot index is greater than start Index 
		//i.e call quick sort recursively on left of the pivot index
		if( pivotIndex-1> startIndex){
			SortUtilities.quicksort(b, startIndex, pivotIndex-1);
		}
		//following condition checks that updated pivot index is less than end Index 
		//i.e call quick sort recursively on right of the pivot index
		if( pivotIndex+1< endIndex){
			SortUtilities.quicksort(b, pivotIndex+1, endIndex);
		}
			
		
	}

}
