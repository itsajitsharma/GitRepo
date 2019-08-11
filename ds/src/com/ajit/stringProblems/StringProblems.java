package com.ajit.stringProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StringProblems {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		combine("abcd", new StringBuffer(), 0);
		/*String findRepeatedString = findRepeatedString("ababab");
		System.out.println(findRepeatedString);*/
		//printRevLevelOrder(new int[]{-1, 0, 0, 1, 2,2});
		//printSumConbinations(6);
	}
	//prints all combination of a given string
	public  static void combine(String instr, StringBuffer outstr, int index)
	{
	    for (int i = index; i < instr.length(); i++)
	    {
	        outstr.append(instr.charAt(i));
	        System.out.println(outstr);
	        combine(instr, outstr, i + 1);
	        outstr.deleteCharAt(outstr.length() - 1);
	    }
	} 
	
	
	/**
	 * Qus: A number is given and u need to print all unique combination 
	 * which sums to that given number. for example: given number is 5 so ur answer  should be 
	[1, 1, 1, 1, 1]
	[1, 1, 1, 2]
	[1, 1, 3]
	[1, 2, 2]
	[1, 4]
	[2, 3]
	[5]
	
	1 - 	1
	2 - 	1, 1
			2
	3 -	 1, 1, 1
		1, 2
		3
	4-	1,1,1,1
		1,1,2
		1,3
		4
		2,2

	5-	1,1,1,1,1
		1,1,1,2
		1,1,3
		1,4
		1,2,2
		2,3
		5

	6-	1,1,1,1,1,1
		1,1,1,1,2
		1,1,1,3
		1,1,4
		1,1,2,2
		1,2,3
		1,5
		2,4
		2,2,2
		3,3
		6
	 */
	
	public static void printSumConbinations(int num)
	{
		Map<Integer, ArrayList<int[]>> sumCombinationMap = new HashMap<Integer, ArrayList<int[]>>();
		
		for(int i=1; i<=num; i++ ){
			updateSumCombMap(i, sumCombinationMap);
		}
		
		ArrayList<int[]> arrayList = sumCombinationMap.get(num);
		for (int[] is : arrayList) {
			System.out.print("[");
			for (int i = 1; i < is.length; i++) {
				for (int j = 0; j < is[i]; j++) {
					System.out.print(i + ", ");
				}
			}
			System.out.println("]");
		}
	}
	
	//dynamic programming solution
	private static void updateSumCombMap(int currentNum, Map<Integer, ArrayList<int[]>> sumCombinationMap) {
		if(currentNum > 0 && sumCombinationMap != null){
			ArrayList<int[]> combsList = new ArrayList<int[]>();
			sumCombinationMap.put(currentNum, combsList);
			if(currentNum==1){
				int[] arr = new int[]{0,1};
				combsList.add(arr);
			}
			else{
				for (int i = currentNum-1; i >= currentNum/2; i--) {
					int diff = currentNum - i;
					ArrayList<int[]> combsListExisting = sumCombinationMap.get(i);
					for (int[] js : combsListExisting) {
						if( (diff -1) >=0 && (diff-1) < js.length && js[diff-1] == 0 ){
							int[] newSumComb = Arrays.copyOf(js, js.length);
							newSumComb[diff] = newSumComb[diff] +1;
							combsList.add(newSumComb);
						}
					}
					
				}
				int[] arr = new int[currentNum+1];
				arr[currentNum] = 1;
				combsList.add(arr);
			}
		}
		
	}
	/**
	 * String is given and you need to check if you can present it as NT
	where N is a number
	and T is pattern
	like
	abcabcabcabc = 4abc
	so ur method should return NT or false
	abcabd should return false
	 */
	public  static String findRepeatedString(String instr)
	{	
		char[] charArray = instr.toCharArray();
	    for (int size = 1; size <= instr.length()/2; size++)
	    {
	    	boolean matched = true;
	    	for(int startIndex = 0 ; startIndex < size; startIndex++){
	    		int tempIndex = startIndex;
	    		while(tempIndex + size < instr.length()){
	    			if(charArray[tempIndex] == charArray[tempIndex + size]){
	    				tempIndex  =  tempIndex + size;
	    			}
	    			else{
	    				matched = false;
	    				break;
	    			}
	    		}
	    		
	    		if(!matched){
	    			break;
	    		}
	    	}
	    	if(matched){
    			return ""+size+instr.substring(0,size);
    		}
	    }
	    return "false";
	} 
	
	/*
		binary tree is given in form of array
		like -1, 0, 0, 1, 2,2
		a[i] represents the index of parent of i
		if a[i] is -1, i is root
		now you need to print level order traversal in reverse
		last level se start karke 0th level tak
	*/

	public  static String printRevLevelOrder(int[] treeArr)
	{	
		class LevelInfo{
			int level;
			int rankInLevel;
			boolean leftChildOccured = false;
			
			LevelInfo(int level, int rank){
				this.level = level;
				this.rankInLevel = rank;
			}
			
		}
		
		class NodeInfo{
			int index;
			int rankInLevel;
			
			NodeInfo(int index, int rank){
				this.index = index;
				this.rankInLevel = rank;
			}
			
			public String toString() {
				return "[" + index +", "+rankInLevel + "]";
			}
		}
		//Map(node, (level, rankInlevel) )
		Map<Integer, LevelInfo> nodeMap = new HashMap<Integer, LevelInfo>();
		
		//Map(level, list((index, rankInLevel)))
		Map<Integer, ArrayList<NodeInfo>> levelInfoMap = new TreeMap<Integer, ArrayList<NodeInfo>>(Collections.reverseOrder());
		
		for (int i = 0; i < treeArr.length; i++){
			if(treeArr[i] == -1){
				nodeMap.put(i, new LevelInfo(0, 1));
				ArrayList<NodeInfo> nodeInfoList = new ArrayList<NodeInfo>();
				levelInfoMap.put(0, nodeInfoList);
				nodeInfoList.add(new NodeInfo(i, 1));
			}
			else{
				//get the level and rank of parent
				int parLevel = nodeMap.get(treeArr[i]).level;
				int parRank = nodeMap.get(treeArr[i]).rankInLevel;
				boolean isLeftChildAlreadyOccurred = nodeMap.get(treeArr[i]).leftChildOccured;
				
				//update nodeMap for this node
				int currentLevel = parLevel +1;
				int currentNodeRank = parRank*2;
				
				if(!isLeftChildAlreadyOccurred){
					currentNodeRank--;
					nodeMap.get(treeArr[i]).leftChildOccured = true;;
				}
				
				nodeMap.put(i, new LevelInfo(currentLevel, currentNodeRank));
				
				//update levelMap for this node
				ArrayList<NodeInfo> nodeList;
				if(levelInfoMap.get(currentLevel) != null){
					boolean addedTolist = false;
					nodeList = levelInfoMap.get(currentLevel);
					for (int j = 0; j < nodeList.size(); j++) {
						if(nodeList.get(j).rankInLevel > currentNodeRank){
							nodeList.add(j, new NodeInfo(i, currentNodeRank));
							addedTolist = true;
							break;
						}
					}
					if(!addedTolist){
						nodeList.add(new NodeInfo(i, currentNodeRank));
					}
				}
				else{
					nodeList = new ArrayList<NodeInfo>();
					nodeList.add(new NodeInfo(i, currentNodeRank));
					levelInfoMap.put(currentLevel, nodeList);
				}
			}
		}
		
		//Now iterate over levelInfoMap and print the lists
		StringBuilder strBld = new StringBuilder();
		for (int level : levelInfoMap.keySet()) {
			ArrayList<NodeInfo> nodeList = levelInfoMap.get(level);
			for (NodeInfo nodeInfo : nodeList) {
				strBld.append(""+nodeInfo.index + ", ");
			}
		}
		
		return strBld.substring(0,strBld.length()-1);
	} 

}
