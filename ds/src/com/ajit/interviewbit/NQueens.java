package com.ajit.interviewbit;


public class NQueens {

	public static  void main(String[] args) {
		System.out.println(nQueensProblem(8));

	}
	
	public static boolean nQueensProblem(int n) {
		int[] xCoordinates = new int[n];
		int[] yCoordinates = new int[n];
		
		int currentQueenNo = 1;
		
		boolean queensPlaced = placeOnBoard(n, xCoordinates, yCoordinates, currentQueenNo );
		for (int i = 0; i < xCoordinates.length; i++) {
			System.out.println(xCoordinates[i] + ", " + yCoordinates[i]);
		}
		
		return queensPlaced;
	}

	private static boolean placeOnBoard(int n, int[] xCoordinates,
			int[] yCoordinates, int currentQueenNo) {
		if(currentQueenNo == n+1){
			//all queens are placed now.
			return true;
		}
		else{
			int i = currentQueenNo -1;
			for(int j=0; j<n; j++){
				if(isValidMove(xCoordinates, yCoordinates, i, j, currentQueenNo-1)){
					xCoordinates[i] = i;
					yCoordinates[i] = j;
					boolean othersPlaced = placeOnBoard(n, xCoordinates, yCoordinates, currentQueenNo+1);
					if(othersPlaced){
						return true;
					}
					else{
						//backtrack;
						xCoordinates[i] = -1;
						yCoordinates[i] = -1;
					}
				}
			}
			return false;
		}
	}

	
	private static boolean isValidMove(int[] xCoordinates, int[] yCoordinates, int i, int j, int n){
		for(int x=0; x <n; x++){
			if(i==xCoordinates[x] || j ==yCoordinates[x]){
				return false;
			}
			else{
				if( Math.abs(xCoordinates[x] - i) == Math.abs(yCoordinates[x] - j) ){
					return false;
				}
			}
		}
		return true;
	}
	

}
