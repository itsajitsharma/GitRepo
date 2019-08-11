package com.ajit.bstree;

public class SortProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] ar= new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18}; 
		
		ar = getMergedArray(ar);
		
		for (int i = 0; i < ar.length; i++) {
			System.out.print(ar[i]+" ");
		}
	}
	
	static int[] getMergedArray(int[] a) {
		if(a.length >= 3) {
            int indexB = a.length/3;
            int indexC = 2*(a.length/3);
            int start = 0;
            for(int i = 0; i < a.length/3; i++) {
                    int key1 = a[indexB];
                    int key2 = a[indexC];
                    for(int j = indexC-1; j >= indexB; j--) {
                            a[j+1] = a[j];
                    }
                    for(int k = indexB-1; k >= start; k--) {
                            a[k+2] = a[k];
                    }
                    a[start+1] = key1;
                    a[start+2] = key2;
                    start = start+3;
                    indexB = indexB + 2;
                    indexC = indexC + 1;
            }
		}
		return a;
	}

}
