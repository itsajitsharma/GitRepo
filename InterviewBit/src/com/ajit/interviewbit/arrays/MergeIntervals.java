package com.ajit.interviewbit.arrays;

import java.util.ArrayList;

import com.ajit.data.Interval;

public class MergeIntervals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * INTERVAL - Arrays
	 * 
	 * Given a set of non-overlapping intervals, insert a new interval into the
	 * intervals (merge if necessary).
	 * 
	 * You may assume that the intervals were initially sorted according to
	 * their start times.
	 * 
	 * Example 1:
	 * 
	 * Given intervals [1,3],[6,9] insert and merge [2,5] would result in
	 * [1,5],[6,9].
	 * 
	 * Example 2:
	 * 
	 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] would
	 * result in [1,2],[3,10],[12,16].
	 * 
	 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
	 * 
	 * Make sure the returned intervals are also sorted.
	 * 
	 * @param a
	 * @return
	 */
	public ArrayList<Interval> insert(ArrayList<Interval> intervals,
			Interval newInterval) {
		ArrayList<Interval> returnIntervals = new ArrayList<Interval>();

		for (Interval interval : intervals) {
			// New interval is completely before current interval
			if (interval.getStart() > newInterval.getEnd()) {
				returnIntervals.add(newInterval);
				newInterval = interval;
			} else if (interval.getEnd() < newInterval.getStart()) {// new interval is
															// completely after
															// current interval
				returnIntervals.add(interval);
			} else {// newInterval is overlapping with currentInterval
				int newStart = interval.getStart() >= newInterval.getStart() ? newInterval.getStart()
						: interval.getStart();
				int newEnd = interval.getEnd() <= newInterval.getEnd() ? newInterval.getEnd()
						: interval.getEnd();
				newInterval = new Interval(newStart, newEnd);
			}
		}
		returnIntervals.add(newInterval);

		return returnIntervals;
	}

}
