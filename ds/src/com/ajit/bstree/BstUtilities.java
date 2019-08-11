package com.ajit.bstree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


class Node{
	int value;
	Node left;
	Node right;
	Node sibling;
	Node next; //field for inorder successor
	
	public Node(int value){
		this.value = value;
	}
	
	public Node(int value,  Node left, Node right){
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public Node(Node left, int value, Node right){
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
}

public class BstUtilities {

	public static void main(String[] args) {
		
		
		//Testing the function createTreeFromPreOrder
		/*String str = "NNLNLLNNLLL";
		Node node = BstUtilities.createTreeFromPreOrder(str);
		System.out.println("printing in order");
		BstUtilities.printInOrder(node);
		System.out.println("printing pre order");
		BstUtilities.printPreOrder(node);*/
		
		Node root = addRecursive(null, 10);
		addRecursive(root, 20);
		addRecursive(root, 5);
		addRecursive(root, 25);
		addRecursive(root, 15);
		addRecursive(root, 3);
		addRecursive(root, 7);
		addRecursive(root, 2);
		addRecursive(root, 4);
		addRecursive(root, 1);
		List<String> pathList = getPathsHavingSum(root, 10);
		
	}
	
	
	public static List<String> getPathsHavingSum(Node node, int requiredSum) {
		ReturnInfo returnInfo = new ReturnInfo();
		returnInfo = getResultPathsHavingSum(node, requiredSum, returnInfo );
		return returnInfo.resultList;
	}
	
	private static ReturnInfo getResultPathsHavingSum(Node node, int requiredSum, ReturnInfo returnInfo) {
		if( node == null ){
			return returnInfo;
		}
		else {
			
			if(node.left != null){
				returnInfo = getResultPathsHavingSum(node.left, requiredSum, returnInfo );
			}
			//value at current node is greater than required sum. This node and all nodes above it can't be part of resultant path.
			//return the resultObj as it is. + all the intermediate path below it will be removed.
			if(node.value > requiredSum){
				returnInfo.pathSumInfo = new ArrayList<PathSumInfo>();
				return returnInfo;
			}
			if(node.right != null){
				ReturnInfo returnInfoRightTree = new ReturnInfo();
				returnInfoRightTree = getResultPathsHavingSum(node.right, requiredSum, returnInfoRightTree );
			}
			//Now analyse the retunInfo object and add the value of current node to sum
			/*if(returnInfo.pathSumInfo.size() == 0){
				if(node.value == requiredSum){
					returnInfo.resultList.add(node.value+"");
				}
				else{
					PathSumInfo pathSumInfo = new PathSumInfo();
					pathSumInfo.tempPath.append(node.value + " ");
					pathSumInfo.tempSum = node.value;
					returnInfo.pathSumInfo.add(pathSumInfo);
				}
			}
			else{
				//itrate over the pathSumInfo list and add the currentNode value to all the objects in list and 
			}*/
		}
		
		return null;
	}

	private static class ReturnInfo{
		List<String> resultList = new ArrayList<String>();
		List<PathSumInfo> pathSumInfo = new ArrayList<PathSumInfo>();
		
		public ReturnInfo(){
		}
		
		public ReturnInfo(ArrayList<String> resultList){
			this.resultList = resultList;
		}
	}
	
	private static class PathSumInfo{
		
		StringBuilder tempPath = new StringBuilder();
		private int tempSum = 0;
		
		void addPath(char ch){
			tempPath.append(ch);
		}
		
		void removeLastNodeFromPath(){
			if(tempPath.length()>0){
				tempPath.deleteCharAt(tempPath.length()-1) ;
				tempPath.deleteCharAt(tempPath.length()-1) ;
			}
		}

		public int getTempSum() {
			return tempSum;
		}

		public void setTempSum(int tempSum) {
			this.tempSum = tempSum;
		}
		
		public StringBuilder getTempPath() {
			return tempPath;
		}

		public void setTempPath(StringBuilder tempPath) {
			this.tempPath = tempPath;
		}
		
		
		
	}


	public static void printBoundryNodes(Node root){
		if(root == null){
			return ;
		}
		Node current = root;
		while(current.left != null){
			System.out.println(current.value);
			current = current.left;
		}
		printLeafNodes(root);
		current = root.right;
		if(!isLeafNode(current)){
			while(current.right != null){
				System.out.println(current.value);
				current = current.right;
			}
		}
	}
	
	public static boolean isLeafNode(Node root){
		if(root == null || root.left != null || root.right != null){
			return false;
		}
		return true;
	}
	
	public static void printLeafNodes(Node root){
		if(root == null){
			return;
		}
		else if(isLeafNode(root)){
			System.out.println(root.value);
		}
		else{
			printLeafNodes(root.left);
			printLeafNodes(root.right);
		}
	}
		
	
	
	public static void populateSibling(Node root){
		if(root == null){
			return;
		}
		Stack<Node> stk = new Stack<Node>();
		root.sibling = null;
		if(root.right != null){
			root.right.sibling = null;
			stk.push(root.right);
		}
		if(root.left != null){
			root.left.sibling = root.right;
			stk.push(root.left);
		}
		while(!stk.isEmpty()){
			Node current = stk.pop();
			Node parentSibling = current.sibling;
			Node sibling = getSibling(parentSibling);
			if(current.right != null){
				current.right.sibling = sibling;
				stk.push(current.right);
				if(current.left != null){
					current.left.sibling = current.right;
					stk.push(current.left);
				}
			}
			else if(current.left != null){
				current.left.sibling = sibling;
			}
		}
	}
	
	private static Node getSibling(Node parentSibling){
		while( parentSibling != null){
			if(parentSibling.left != null){
				return parentSibling.left;
			}
			else if(parentSibling.right != null){
				return parentSibling.right;
			}
			else
				parentSibling = parentSibling.sibling;
		}
		return parentSibling;
	}
	/*
	 * This method prints the In order traversal of a binary tree.
	 */
	public static void printInOrder(Node node) {
		if(node != null){
			printInOrder(node.left);
			System.out.println(node.value);
			printInOrder(node.right);
		}	
	}
	
	/*
	 * This method prints the pre order traversal of a binary tree.
	 */
	public static void printPreOrder(Node node) {
		if(node != null){
			System.out.println(node.value);
			printPreOrder(node.left);
			printPreOrder(node.right);
		}	
	}

	/*
	 * This method takes the preorder traversal of complete binary tree as input
	 * (with N representing the internal node and L representing the external(leaf) nodes)
	 * and creates the binary tree from it and returns the Root node of the newly constructed
	 * tree.
	 * 
	 */
	public static Node createTreeFromPreOrder(String string){
		Node root = null;
		Node current;
		char[] str = string.toCharArray();
		if(str.length == 0){
			return null;
		}else if(str[0] == 'L'){
			root = new Node(null, 'L', null);
			return root;
		}else if(str[0] == 'N'){
			root = new Node(null, 'N', null);
			LinkedList<Node> stack = new LinkedList<Node>();
			stack.add(root);
			current = root;
			boolean addAtRight = false;
			for (int i = 1; i < str.length; i++) {
				if(!addAtRight){
					if(str[i] == 'N'){
						current.left = new Node(null, 'N', null);
						current = current.left;
						stack.add(current);
						addAtRight = false;
					}else{
						current.left = new Node(null, 'L', null);
						addAtRight = true;
					}
				}else{
					if(str[i] == 'N'){
						current= stack.removeLast();
						current.right = new Node(null, 'N', null);
						current = current.right;
						stack.add(current);
						addAtRight = false;
					}else{
						current=stack.removeLast();
						current.right = new Node(null, 'L', null);
						addAtRight = true;
					}
				}
			}
		}
		return root;
	}
	
	public static Node getInorderSuccessor(Node root) {
		fillInorderSuccessor(root, null);
		return root;
	}

	static private Node fillInorderSuccessor(Node node, Node prev) {
		if (node != null) {
			prev = fillInorderSuccessor(node.right, prev);
			node.next = prev;
			prev = node;
			prev = fillInorderSuccessor(node.left, prev);
		}
		return prev;
	}
	
	public static Node addRecursive(Node temp, int info) {
		if (temp == null) {
			Node nd = new Node(info);
			temp = nd;
		} else if(temp.value > info) {
			temp.left = addRecursive(temp.left, info);
		} else {
			temp.right = addRecursive(temp.right, info);
		}
		return temp;
	}

	public static void addNonRecursive(Node root, int info) {
		Node nd = new Node(info, null, null);
		if (root == null) {
			root = nd;
		} else {
			Node temp = root;
			while (true) {
				if (info > temp.value) {
					if (temp.right == null) {
						temp.right = nd;
						break;
					}
					temp = temp.right;
				} else {
					if (temp.left == null) {
						temp.left = nd;
						break;
					}
					temp = temp.left;

				}
			}

		}

	}
}
