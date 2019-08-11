package com.ajit.problems;


/**Imagine you have a row of numbers like below(a triangle). By starting at the top of the triangle, find the maximum number in eavch line and sum them up. example below.

5
9 6
4 6 8
0 7 1 5

answer ie --> 5 + 9 + 8 +7 = 29

input specification:
A string of n numbers( 0<=n<=10power10)
eg. 5#9#6#4#6#8#0#7#1#5

output specification:
A sum o the max number in each line(as string) or ouput invalid in case of invalid iput/triangle.

example
eg1:
input:5#9#6#4#6#8#0#7#1#5
output:29

eg2:
input:5#9#6#4#6#8#0#7#1
output:Invalid

eg2:
input:5#9##4#6#8#0#7#1
output:Invalid

**/




public class TriangleSum {

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		Scanner scanIn = new Scanner(System.in);
	    String input1 = scanIn.nextLine();
	    System.out.println(validtrianglesum(input1));
	    scanIn.close();
	}*/
	
	
	public static String validtrianglesum(String input1)
    {
		String[] split = input1.split("#");
		
		long rowNumber = 1;
		long currentCol = 0;
		long sum = 0;
		long maxInRow = 0;
		boolean atEndOfRow = true;
		for (int i = 0; i < split.length; i++) {
			atEndOfRow = false;
			currentCol++;
			long currentNum = 0;
			try{
				currentNum = Long.parseLong(split[i]);
			}
			catch(NumberFormatException nfe){
				return "Invalid";
			}
			
			if(currentNum > maxInRow){
				maxInRow = currentNum;
			}
			
			if(currentCol == rowNumber){
				sum = sum + maxInRow;
				maxInRow = 0;
				currentCol =0;
				rowNumber++;
				atEndOfRow = true;
				continue;
			}
		}
		
		if(atEndOfRow){
			return String.valueOf(sum);
		}
		else{
			return "Invalid";
		}
    }

}
