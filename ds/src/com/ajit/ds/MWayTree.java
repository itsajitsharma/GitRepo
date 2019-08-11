package com.ajit.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class Node {
	String label;
	List<Node> children;
	
	Node(){
		
	}
	
	Node(String label, List<Node> children){
		this.label = label;
		this.children = children;
	}
	
	Node(String label){
		this.label = label;
		this.children = null;
	}
}


public class MWayTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node d = new Node("D", null);
        Node e = new Node("E", null);
        Node f = new Node("F", null);
        Node g = new Node("G", null);
        List<Node> lst1 = new ArrayList<Node>();
        lst1.add(d);
        lst1.add(e);
        lst1.add(f);
        lst1.add(g);
        Node b = new Node("B", lst1);
        Node c = new Node("C", null);
        List<Node> lst = new ArrayList<Node>();
        lst.add(b);
        lst.add(c);
        Node root = new Node("A", lst);
        
        MWayTree testTree = new MWayTree();
        
        List<Node> returnList = testTree.traverseTreeAndReturnListContainingAllNodes(root);
        for (Node node : returnList) {
                System.out.print(node.label+" ");
        }

	}
	
	
	public List<Node> traverseTreeAndReturnListContainingAllNodes(Node rootNode){
		List<Node> returnList = new ArrayList<Node>();
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(rootNode);
		while(!queue.isEmpty()){
			Node node = processAndAppendChild(queue);
			Node newNode = new Node(node.label);
			returnList.add(newNode);
		}
		return returnList;
		
	}


	private Node processAndAppendChild(Queue<Node> queue) {
		Node node = queue.poll();
		List<Node> childList = node.children;
		if(childList != null){
			appendNodesForProcessing(queue, childList);
		}
		return node;
	}


	private void appendNodesForProcessing(Queue<Node> queue, List<Node> childList) {
		queue.addAll(childList);
	}
	
	
	public Integer findCommonLargest(List<Integer> list1, List<Integer> list2) {
		if (list1 == null || list2 == null) {
			return null;
		}
		Integer max = null;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Integer i : list1) {
			map.put(i, 1);
		}
		for (Integer i : list2) {
			if (map.get(i) != null && (max == null || i > max)) {
				max = i;
			}
		}
		return max;

	}

}
