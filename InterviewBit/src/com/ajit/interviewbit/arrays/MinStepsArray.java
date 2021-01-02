package com.ajit.interviewbit;

import java.util.ArrayList;

/*
 * REACH - Arrays
 * 
 * You are in an infinite 2D grid where you can move in any of the 8
 * directions :
 * 
 * (x,y) to (x+1, y), (x - 1, y), (x, y+1), (x, y-1), (x-1, y-1), (x+1,y+1),
 * (x-1,y+1), (x+1,y-1) You are given a sequence of points and the order in
 * which you need to cover the points. Give the minimum number of steps in
 * which you can achieve it. You start from the first point.
 * 
 * Example :
 * 
 * Input : [(0, 0), (1, 1), (1, 2)] Output : 2 It takes 1 step to move from
 * (0, 0) to (1, 1). It takes one more step to move from (1, 1) to (1, 2).
 */

public class MinStepsArray {

	public static void main(String[] args) {

		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		a.add(-7);
		a.add(-13);
		b.add(1);
		b.add(-5);
		MinStepsArray test = new MinStepsArray();
		System.out.println(test.coverPoints(a, b));

	}

	// X and Y co-ordinates of the points in order.
	// Each point is represented by (X.get(i), Y.get(i))
	public int coverPoints(ArrayList<Integer> X, ArrayList<Integer> Y) {
		int noOfMoves = 0;
		boolean isFirst = true;
		int currentX = 0;
		int currentY = 0;
		for (int i = 0; i < X.size(); i++) {
			if (isFirst) {
				currentX = X.get(i);
				currentY = Y.get(i);
				isFirst = false;
			} else {
				int xDiff = Math.abs(X.get(i) - currentX);
				int yDiff = Math.abs(Y.get(i) - currentY);
				noOfMoves = noOfMoves + (Math.max(xDiff, yDiff));
				currentX = X.get(i);
				currentY = Y.get(i);
			}
		}
		return noOfMoves;
	}

}
