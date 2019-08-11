package com.ajit.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class ArrayIncremetor {
	
	/**
	 * 
	 * Input sequence
	 * N -> size of array with default values as zeroes 
	 * a, b, K -> increments the values in array between index a and b (a, b inclusive) by K.
	 * a,b, k can be supplied M times.
	 * return the max value in array, after above operations. 
	 */
	
	public static void main(String[] args) {
		test();
	}
	
	public static boolean test(){
		int[] array = new int[10];
		
		//System.out.println(Arrays.asList(array));
		List<InputSet> inputSetList = new ArrayList<InputSet>();
		inputSetList.add(new InputSet(2, 7, 100));
		inputSetList.add(new InputSet(3, 9, 100));
		inputSetList.add(new InputSet(1, 3, 100));
		
		int maxValue = arrayIncrementor(array, inputSetList);
		//System.out.println(maxValue);
		
		return true;
	}

	
	/**
	 * You can get O(N + M). Keep an extra increment array B the same size of A initially empty (filled with 0). If you need to increment the range (i, j) with value k then do B[i] += k and B[j + 1] -= k

		Now do a partial sum transformation in B, considering you're indexing from 0:
		
		for (int i = 1; i < N; ++i) B[i] += B[i - 1];
		And now the final values of A are A[i] + B[i]
	 * @param array
	 * @param inputSetList
	 * @return
	 */
	private static int arrayIncrementor(int[] array, List<InputSet> inputSetList) {
		int[] temp = new int[array.length];
		for (InputSet inputSet : inputSetList) {
			temp[inputSet.a] = temp[inputSet.a] + inputSet.K;
			if(inputSet.b < array.length-1){
				temp[inputSet.b+1] = temp[inputSet.b+1] - inputSet.K;
			}
		}
		
		int max = 0;
		int index = -1;
		for (int i = 1; i < temp.length; i++) {
			temp[i] = temp[i]+temp[i-1];
			if(temp[i] > max){
				max = temp[i];
				index = i;
			}
		}
		
		System.out.println("max value: "+ max +" at index: "+ index);
		
		return max;
	}
		
	

}

class InputSet {
	int a;
	int b;
	int K;
	
	InputSet(int a,int b, int K){
		this.a= a;
		this.b = b;
		this.K=K;
	}
	
}
