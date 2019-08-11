package com.ajit.problems;

/**The knight is placed on the first block of an empty board and, moving according 
 * to the rules of chess, must visit each square exactly once.
 *
 */

public class KnightTourPath {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int boardSize = 8; 
		printTour(boardSize);
	}

	public static void printTour(int boardSize) {
		int[][] solution = new int[boardSize][boardSize];
		//initialize the solution array with -1 values
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				solution[i][j] = -1;
			}
		}

		int[] xMoves = new int[]{2, 1, -1, -2, -2, -1,  1,  2};
		int[] yMoves = new int[]{1, 2,  2,  1, -1, -2, -2, -1};
		
		//starting at 0,0 and this is first move
		boolean validTour = prepareTour(0, 0, 1, solution, xMoves, yMoves );
		if(validTour){
			for (int i = 0; i < boardSize; i++) {
				for (int j = 0; j < boardSize; j++) {
					System.out.print(solution[i][j]+"   ");
				}
				System.out.println();
			}
		}
		else{
			System.out.println("Tour can not be completed");
		}
	}

	private static boolean prepareTour(int currentRow, int currentCol, int movNumber, int[][] solution,
			int[] xMoves, int[] yMoves) {
		//TODO: correct condition below
		if(movNumber == solution.length*8){
			return true;
		}
		
		for(int i=0; i<8; i ++){
			if( isValidMove(solution, currentRow + xMoves[i], currentCol +yMoves[i]) ){
				solution[currentRow+xMoves[i]][currentCol+yMoves[i]] = movNumber+1;
				boolean isValidTour = prepareTour(currentRow+xMoves[i], currentCol+yMoves[i], movNumber+1, solution, xMoves, yMoves);
				if(isValidTour){
					return true;
				}
				else{
					solution[currentRow+xMoves[i]][currentCol+yMoves[i]] = -1;
					
				}
			}
		}
		return false;
		
	}
	
	private static boolean isValidMove(int[][] solution, int currentRow, int currentCol){
		if( currentRow >=0  && currentRow< 8 &&
				currentCol >=0 && currentCol < 8 &&
					solution[currentRow][currentCol] == -1){
			return true;
		}
		else{
			return false;
		}
	}
	
	

}
