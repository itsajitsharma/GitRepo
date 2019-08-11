package com.ajit.dp;

/**
 * Given a gold mine of n*m dimension. Each field in this mine contains an integer which is amount of gold in tons.
 * Initially miner is in first column but could be at any row i. He can move only (right ->, right up /, right down \). 
 * Find out maximum amount of gold he can collect and path followed by him 
 */
 

public class GoldMine {

	public static void main(String[] args) {
		int[][] goldMine = new int[][]{
										{1,2,3},
										{1,3,1},
										{1,2,1},
										{1,9,1}
									};
		int startingRow = 2;
		int startingCol = 0;
		findMaxGoldCollAndPath(goldMine,startingRow,startingCol);
	}

	public static void findMaxGoldCollAndPath(int[][] goldMine,
			int startingRow, int startingCol) {
		
		//base case. invalid input.
		if(startingCol < 0 || startingCol > goldMine[0].length -1 
				|| startingRow < 0 || startingRow > goldMine.length -1){
			System.out.println("Out of gold mine");
			return;
		}
		
		//create a grid and fill its cells with the max gold value collected by going to that cell.
		//initially the grid cells will have zero values. fill the grid while solving the problem.
		//also we will use top down approach. so will start from the last possible column.
		// The formula for filling the grid will be
		// gold(p,q) = goldMine[p,q] + max( gold(p-1,q-1), gold(p, q-1), gold(p+1, q) )
		GoldenPath[][] goldCollectionGrid = new GoldenPath[goldMine.length][goldMine[0].length];
		for(int i =0; i<goldCollectionGrid.length; i++){
			for(int j=0; j<goldCollectionGrid[0].length; j++){
				goldCollectionGrid[i][j] = new GoldenPath();
			}
		}
		
		//now fill the grid base values
		goldCollectionGrid[startingRow][startingCol].setValue(goldMine[startingRow][startingCol]);
		goldCollectionGrid[startingRow][startingCol].addCellToPath(startingRow, startingCol);
		
		//fill the right most column 
		for(int row = 0 ; row < goldMine.length; row++){
			fillGoldCollectionGrid(goldMine, goldCollectionGrid, startingRow, startingCol, row, goldMine[0].length-1);
		}
		
		//find the max value in the filledgrid last column
		int maxVal = 0;
		GoldenPath treasurePath = new GoldenPath();
		for(int row = 0 ; row < goldCollectionGrid.length; row++){
			if(maxVal < goldCollectionGrid[row][goldCollectionGrid[0].length-1].getValue()){
				maxVal = goldCollectionGrid[row][goldCollectionGrid[0].length-1].getValue();
				treasurePath = goldCollectionGrid[row][goldCollectionGrid[0].length-1];
			}
		}
		//System.out.println("Max gold collected is = " + maxVal);
		System.out.println("Treasure Path is " + treasurePath);
	}
	
	

	private static GoldenPath fillGoldCollectionGrid(int[][] goldMine,
			GoldenPath[][] goldCollectionGrid, int startingRow, int startingCol, int row, int col) {
		
		if(!isCellAccessible(startingRow, startingCol, goldMine.length-1, goldMine[0].length-1, row, col)){
			return new GoldenPath();
		}
		if(goldCollectionGrid[row][col].getValue() != 0){
			return goldCollectionGrid[row][col];
		}
		GoldenPath pathValue = getMax(fillGoldCollectionGrid(goldMine, goldCollectionGrid, startingRow, startingCol, row-1, col-1),
						fillGoldCollectionGrid(goldMine, goldCollectionGrid, startingRow, startingCol, row, col-1),
						fillGoldCollectionGrid(goldMine, goldCollectionGrid, startingRow, startingCol, row+1, col-1));
		
		goldCollectionGrid[row][col].setValue(pathValue.getValue() + goldMine[row][col]);
		goldCollectionGrid[row][col].appendPath(pathValue.getPath());
		goldCollectionGrid[row][col].addCellToPath(row,col);
		return goldCollectionGrid[row][col];
		
	}
	
	private static GoldenPath getMax(GoldenPath iPath,
			GoldenPath jPath,
			GoldenPath kPath) {
		if(iPath.getValue() > jPath.getValue()){
			if(iPath.getValue() > kPath.getValue()){
				return iPath;
			}
			else{
				return kPath;
			}
		}
		else{
			if(jPath.getValue()>kPath.getValue()){
				return jPath;
			}
			else{
				return kPath;
			}
		}
	}

	public static int getMax(int i, int j, int k){
		if(i > j){
			if(i > k){
				return i;
			}
			else{
				return k;
			}
		}
		else{
			if(j>k){
				return j;
			}
			else{
				return k;
			}
		}
	}

	private static boolean isCellAccessible(int startRow, int startCol, int maxRow, int maxCol, int row, int col){
		if(row < 0 || row > maxRow  
				|| col < 0 || col > maxCol || col <startCol){
			return false;
		}
		if( startRow - (startCol - col) < row
				|| startRow + (startCol - col) > row 
				|| col < startCol || col < 0){
			return false;
		}
		return true;
	}
	
}

class GoldenPath {
	private int value=0;
	private StringBuilder path = new StringBuilder();
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public void addCellToPath(int i, int j){
		path.append("["+i+","+j+"] ");
	}
	
	public void removeLastCellFromPath(){
		if(path.length()>0)
			path.setLength(path.length()-6);
	}
	
	public void appendPath(String path){
		this.path.append(path);
	}
	
	public String getPath(){
		return path.toString();
	}
	
	public String toString(){
		return "Gold Collected = "+ value + ".\n and path is " + path;
	}
}
