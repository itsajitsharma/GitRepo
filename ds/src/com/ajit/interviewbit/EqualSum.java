package com.ajit.interviewbit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Given an array A of integers, find the index of values that satisfy A + B = C + D, where A,B,C & D are integers values in the array

	Note:
	
	1) Return the indices `A1 B1 C1 D1`, so that 
	  A[A1] + A[B1] = A[C1] + A[D1]
	  A1 < B1, C1 < D1
	  A1 < C1, B1 != D1, B1 != C1 
	
	2) If there are more than one solutions, 
	   then return the tuple of values which are lexicographical smallest. 
	
	Assume we have two solutions
	S1 : A1 B1 C1 D1 ( these are values of indices int the array )  
	S2 : A2 B2 C2 D2
	
	S1 is lexicographically smaller than S2 iff
	  A1 < A2 OR
	  A1 = A2 AND B1 < B2 OR
	  A1 = A2 AND B1 = B2 AND C1 < C2 OR 
	  A1 = A2 AND B1 = B2 AND C1 = C2 AND D1 < D2
	Example:
	
	Input: [3, 4, 7, 1, 2, 9, 8]
	Output: [0, 2, 3, 5] (O index)
	If no solution is possible, return an empty list.
 * @author Ajit
 *
 */

public class EqualSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(3);
		a.add(4);
		a.add(7);
		a.add(1);
		a.add(2);
		a.add(9);
		a.add(8);
		System.out.println((new EqualSum()).equal(a));

	}
	
	
	public ArrayList<Integer> equal(ArrayList<Integer> a) {
        ArrayList<Integer> returnList =  new ArrayList<Integer>();
        Map<Long, List<Pair>> sumToListOfPairMap = new HashMap<Long, List<Pair>>();
        
        //Generate all pairs and store their sum in the map
        for(int i=0; i<a.size();i++){
        	for(int j = i+1; j< a.size(); j++){
        		long sum = a.get(i)+a.get(j);
        		List<Pair> list = sumToListOfPairMap.get(sum);
        		if(list == null){
        			list = new ArrayList<EqualSum.Pair>();
        			sumToListOfPairMap.put(sum, list);
        		}
        		list.add(new Pair(i,j));
        	}
        }
        
        //As the Pair objects in the lists are already sorted. We can have first two pairs from each list as our solution candidates.
        List<List<Integer>> solutionCandidatesList = new ArrayList<List<Integer>>();
        for (Long sum : sumToListOfPairMap.keySet()) {
        	List<Pair> list = sumToListOfPairMap.get(sum);
        	if(list.size() > 1){
        		Pair candidate1 = list.get(0);
        		Pair candidate2 = null;
        		for (int i=1; i<list.size(); i++) {
					if(candidate1.firstIndex < list.get(i).firstIndex
							&& candidate1.secondIndex != list.get(i).firstIndex
							&& candidate1.secondIndex != list.get(i).secondIndex ){
						candidate2 = list.get(i);
						break;
					}
				}
        		if(candidate2 != null){ 
	        		ArrayList<Integer> solutionList = new ArrayList<Integer>();
	        		solutionList.add(candidate1.firstIndex);
	        		solutionList.add(candidate1.secondIndex);
	        		solutionList.add(candidate2.firstIndex);
	        		solutionList.add(candidate2.secondIndex);
	        		solutionCandidatesList.add(solutionList);
        		}
        	}
		}
        
        if(solutionCandidatesList.size() == 0){
        	return new ArrayList<Integer>();
        }
        
        Collections.sort(solutionCandidatesList, new SolutionComparator());
        return (ArrayList<Integer>)solutionCandidatesList.get(0);
    }
	
	


	class SolutionComparator implements Comparator<List<Integer>> {

		@Override
		public int compare(List<Integer> o1, List<Integer> o2) {

			if(o1 == null){
				return -1;
			}
			else if(o2 == null){
				return 1;
			}
			else{
				for(int i=0; i<4; i++){
					if(o1.get(i) < o2.get(i)){
						return -1;
					}
					else if(o1.get(i) > o2.get(i)){
						return 1;
					}
				}
				return 0;
			}
		
		}
		
	}
	
	class Pair implements Comparable<Pair>{
		int firstIndex;
		int secondIndex;
		
		
		public Pair() {
			super();
		}



		public Pair(int firstIndex, int secondIndex) {
			super();
			this.firstIndex = firstIndex;
			this.secondIndex = secondIndex;
		}



		@Override
		public int compareTo(Pair obj) {
			if(obj == null){
				return 1;
			}
			if(this.firstIndex < obj.firstIndex){
				return -1;
			}
			else if(this.firstIndex > obj.firstIndex){
				return 1;
			}
			else{
				if(this.secondIndex < obj.secondIndex){
					return -1;
				}
				else if(this.secondIndex > obj.secondIndex){
					return 1;
				}
				else{
					return 0;
				}
			}
		}



		public int getFirstIndex() {
			return firstIndex;
		}



		public void setFirstIndex(int firstIndex) {
			this.firstIndex = firstIndex;
		}



		public int getSecondIndex() {
			return secondIndex;
		}



		public void setSecondIndex(int secondIndex) {
			this.secondIndex = secondIndex;
		}



		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + firstIndex;
			result = prime * result + secondIndex;
			return result;
		}



		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (firstIndex != other.firstIndex)
				return false;
			if (secondIndex != other.secondIndex)
				return false;
			return true;
		}



		private EqualSum getOuterType() {
			return EqualSum.this;
		}
		
		
	}

}
