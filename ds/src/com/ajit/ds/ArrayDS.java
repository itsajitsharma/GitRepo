package com.ajit.ds;

import java.util.HashMap;
import java.util.Map;

public class ArrayDS {
	
	public static void main(String[] args) {
		//System.out.println(removeOccurences("abcdefgh", "ah"));
		//System.out.println(removeOccurences(new char[]{'a','b','a','c','d','e','f','g','h'}, new char[]{'a','h'}));
		//int[] arr={0,2,3,0};
		//System.out.println(rotatedsSearch(arr, 7, 0, 7));
		//geMaxNumber(arr);
		printCombinations("abc");
		//printArray(reArrangeArray(new int[]{1,4,7,2,5,8,3,6,9}));
	}

	
	/**
	 * Prints all combinations of characters from input string
	 * @param inStr
	 */
	public static void printCombinations(String inStr){
		int maxValue = (int)Math.pow(2.0,inStr.length())-1;
	
		StringBuilder str= new StringBuilder();
	
		for(int i=maxValue;i>0;i--)
		{
			String binStr=Integer.toBinaryString(i);
			int k=0;
			StringBuilder zeroString= new StringBuilder();
			while(binStr.length()+k < inStr.length()){
				zeroString.append("0");
				k++;
			}
			binStr=zeroString.toString()+binStr;
			zeroString.setLength(0);
			for (int j=0;j<binStr.length();j++)
			{
				if(binStr.charAt(j)=='1')
				{
					str.append(inStr.charAt(j));
				}
			}
	
			System.out.println(str.toString());
			str.setLength(0);
		}
	}
	
	
	/**
	 * This function returns the maximum number created by combination of digits given in array which is divisible
	 * by integar given as second argument.
	 * @param a
	 * @param b
	 * @return
	 */
	/*public static Integer geMaxDivisibleNumber(int[] a, int b) {
		if (a == null) {
			System.out.println("number not found");
			return null;
		}
		int numberOfZeroes = 0;
		Arrays.sort(a);
		//keep the numbers in in descending order
		for(int i=0; i<a.length/2; i++){
			int temp=a[i];
			a[i] = a[a.length - i];
			a[a.length - i] = temp;
		}
		
		
		int j = 0;
		while (a[j] == 0)
			j++;
		int firstIndexOfNonZeroNumber;
		double num;
		for (double i = Math.pow(2, a.length - j) - 1; i >= 1; i--) {
			firstIndexOfNonZeroNumber = a.length - 1;
			String binaryVal = Integer.toBinaryString((int) i);
			if(binaryVal.length() < a.length-j){
				for(int m=0;m <a.length-binaryVal.length();m++){
					binaryVal = "0" +binaryVal;
				}
			}
			num = 0;
			for (int k = 0; k <= binaryVal.length() - 1; k++) {
				if (binaryVal.charAt(k) == '1') {
					num = num * 10 + a[firstIndexOfNonZeroNumber];
				}
				firstIndexOfNonZeroNumber--;
			}
			if (num % 3 == 0) {
				num = num * Math.pow(10, j);
				System.out.println((int) num);
				return;
			}
		}
		System.out.println("number not found");
	}*/
	
	/*public static void geMaxNumber(int[] a) {
		if (a == null) {
			System.out.println("number not found");
			return;
		}
		Arrays.sort(a);
		if (a[0] != 0) {
			System.out.println("number not found");
			return;
		}
		int j = 0;
		while (a[j] == 0)
			j++;
		int firstIndexOfNonZeroNumber;
		double num;
		for (double i = Math.pow(2, a.length - j) - 1; i >= 1; i--) {
			firstIndexOfNonZeroNumber = a.length - 1;
			String binaryVal = Integer.toBinaryString((int) i);
			if(binaryVal.length() < a.length-j){
				for(int m=0;m <a.length-binaryVal.length();m++){
					binaryVal = "0" +binaryVal;
				}
			}
			num = 0;
			for (int k = 0; k <= binaryVal.length() - 1; k++) {
				if (binaryVal.charAt(k) == '1') {
					num = num * 10 + a[firstIndexOfNonZeroNumber];
				}
				firstIndexOfNonZeroNumber--;
			}
			if (num % 3 == 0) {
				num = num * Math.pow(10, j);
				System.out.println((int) num);
				return;
			}
		}
		System.out.println("number not found");
	}*/



	
	/**
	 * this function removes all character of string b, from string a
	 * @param a
	 * @param b
	 * @return
	 */
	static String removeOccurences(String a, String b) {
		//char[] outputArr = b.toCharArray();
		for(int i=0; i<b.length(); i++){
			a= a.replaceAll(Character.toString(b.charAt(i)), "");
		}
		return a;
	}
	
	/**
	 * this function removes all character of array b, from array a
	 * @param a
	 * @param b
	 * @return
	 */
	static char[] removeOccurences(char[] a, char[] b) {
		//char[] outputArr = b.toCharArray();
		Map<Character, Integer> charMap = new HashMap<Character, Integer>();
		for(int i=0; i<b.length; i++){
			charMap.put(b[i], 1);
		}
		
		int currentInd = 0;
		int i=0;
		while(i<a.length){
			if(charMap.get(a[i]) != null){
				i++;
			}
			else{
				a[currentInd] = a[i];
				i++;
				currentInd++;
			}
		}
		for(i = currentInd; i < a.length; i++){
			a[i] = '\0';
		}
		return a;
		
	}
	
	
	
	static String copyStr(String a, int b, int c, int d) {
		char[] outputArr = a.toCharArray();
		int boundryIndex = a.length()-1;
		int currentIndex = b;
		int inputIndex = c;
		for(int destinationLen=1; destinationLen<=d; destinationLen++ ){
			if( (currentIndex > boundryIndex) || (inputIndex > boundryIndex) ){
				break;
			}
			else{
				outputArr[currentIndex++] = outputArr[inputIndex++];
			}
		}
		return new String(outputArr);
	}
	
	
	
	
	/**
	 * prints all the permutation/Anagrams of the input string. 
	 * @param str
	 */
	public static void printPermutations(String str) {
		char[] strArr = str.toCharArray();
		printPermutations(strArr, 0);
	}

	private static void printPermutations(char[] strArr, int i) {
		if(i == strArr.length -1){
			System.out.println(java.util.Arrays.toString(strArr));
		}
		else{
			for(int j=i; j<=strArr.length-1; j++){
				swap(strArr, i, j);
				printPermutations(strArr, i+1);
				swap(strArr, j, i);
			}
		}
		
	}

	private static void swap(char[] strArr, int i, int j) {
		char temp = strArr[i];
		strArr[i] = strArr[j];
		strArr[j] = temp;
	}
	
	
	/**
	 * prints all the strings which can be created by using the characters in the input array.
	 * @param length
	 * @param inputArray
	 */
	public static void generateStrings(int length, String inStr){
		char[] tempArr = new char[length];	
		char[] inputArray = inStr.toCharArray();
		generateStrings(tempArr, 0, inputArray);
	}


	private static void generateStrings(char[] tempArr, int currentIndex, char[] inputArray) {
		
		if(currentIndex == tempArr.length-1){
			for(int i = 0; i<inputArray.length; i++){
				tempArr[currentIndex] = inputArray[i];
				System.out.println(java.util.Arrays.toString(tempArr));
			}
		}
		else{
			for(int i = 0; i<inputArray.length; i++){
				tempArr[currentIndex] = inputArray[i];
				generateStrings(tempArr, currentIndex+1, inputArray);
			}
		}
	}
	
	
	public static int binarySearch(int[] a, int x, int Low, int High) {
		int Mid;

		while (Low <= High) {
			Mid = (Low + High) / 2;
			if (a[Mid] < x)
				Low = Mid + 1;
			else if (a[Mid] > x)
				High = Mid - 1;
			else
				return Mid; /* Found */
		}
		return -1; /* NotFound is defined as -1 */
	}

	public static int rotatedsSearch(int[] a, int x,int beg,int end)
	{
		int mid;
		if(beg>end)
			return -1;
		mid = (beg+end)/2;
		int res;
		if(a[mid]==x)
		{
			return mid;
		}
		if(beg==(mid-1))
		{
			if(a[beg]==x)
				return beg;
			else
			{
				res=rotatedsSearch(a, x, mid+1, end);
				return res;
			}
		}
		if(a[beg]<=a[mid]) // first half array is sorted.
		{
			if( a[beg]<=x && a[mid]>=x)
			{
				return binarySearch(a, x,beg,mid);
			}
			res = rotatedsSearch(a,x,mid+1,end);
			return res;
		}
		else //second half is sorted
		{
			if(a[mid]<x && a[end]>=x)
				return binarySearch(a, x,mid+1,end);
			else
			{
				return rotatedsSearch(a, x,beg,mid-1);
			}
		}
		
	}
	
	public static void printArray(Object[] obj){
		System.out.print("[");
		for(int i=0;i<obj.length;i++){
			System.out.print(obj[i]);
		}
		System.out.print("]");
	} 
	
	public static void printArray(int[] obj){
		System.out.print("[");
		for(int i=0;i<obj.length;i++){
			System.out.print(obj[i]);
		}
		System.out.print("]");
	} 
	
	
	/**
	 * this method rearranges the array elements a1,a2,a3,b1,b2,b3,c1,c2,c3 to a1,b1,c1,a2,b2,c2,a3,b3,c3
	 * @param arr
	 */
	public static int[] reArrangeArray(int[] arr){
		
		int currentIndex = 0;
		
		reArranger(arr, currentIndex);
		
		return arr;
		
		
		// The below commented solution fails as it currentIndex and targetIndex creates loops
		/*int currentIndex = 1;
		int targetIndex = 1;
		int tempNumber = arr[1];
		for(int i=1; i<arr.length; i++){
			if(currentIndex < arr.length/3){
				targetIndex = currentIndex*3;
			}
			else if(currentIndex >= arr.length/3 && currentIndex < (2* arr.length)/3){
				targetIndex = (currentIndex - arr.length/3)*3 + 1;
			}
			else{
				targetIndex = (currentIndex - (2*arr.length)/3)*3 + 2;
			}
			System.out.println("target index = " + targetIndex);
			tempNumber = arr[targetIndex];
			arr[targetIndex] = arr[currentIndex];
			currentIndex = targetIndex;
		}
		return arr;*/
	}


	private static void reArranger(int[] arr, int currentIndex) {
		if(currentIndex < arr.length){
			int valAtCurrentIndex = arr[currentIndex];
			reArranger(arr, currentIndex+1);
			//now calculate the targetIndex for currentVal and transfer it there
			int targetIndex = 0;
			if(currentIndex < arr.length/3){
				targetIndex = currentIndex*3;
			}
			else if(currentIndex >= arr.length/3 && currentIndex < (2* arr.length)/3){
				targetIndex = (currentIndex - arr.length/3)*3 + 1;
			}
			else{
				targetIndex = (currentIndex - (2*arr.length)/3)*3 + 2;
			}
			arr[targetIndex] = valAtCurrentIndex;
		}
	}
	
	
}

