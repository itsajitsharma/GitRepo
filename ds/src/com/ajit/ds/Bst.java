package com.ajit.ds;

public class Bst {

	private TreeNode root;
	
	private class TreeNode {
		int info;
		TreeNode left;
		TreeNode right;
		
		TreeNode(){
			
		}
		
		TreeNode(int info, TreeNode left, TreeNode right){
			this.info = info;
			this.left = left;
			this.right = right;
		}
	}
	
	public Bst() {
		root = null;
	}
	

	public void add(int info) {
		root = addRecursive(root, info);
	}
	
	private TreeNode addRecursive(TreeNode temp, int info) {
		if (temp == null) {
			TreeNode nd = new TreeNode(info, null, null);
			temp = nd;
		} else if(temp.info > info) {
			temp.left = addRecursive(temp.left, info);
		} else {
			temp.right = addRecursive(temp.right, info);
		}
		return temp;
	}

	public void addNonRecursive(int info) {
		TreeNode nd = new TreeNode(info, null, null);
		if (root == null) {
			root = nd;
		} else {
			TreeNode temp = root;
			while (true) {
				if (info > temp.info) {
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
	
	
	public static void main(String[] args) {
		
	}

	public int getHeight() {
		return findHeight(root);
	}

	private int findHeight(TreeNode root) {
		if (root == null) {
			return 0;
		} else {
			int hLeft = 1 + findHeight(root.left);
			int hRight= 1 + findHeight(root.right);
			return hLeft > hRight ? hLeft : hRight;

		}
	}

	public void printInOrder() {
		inorder(root);
	}

	private void inorder(TreeNode root) {
		if (root != null) {
			inorder(root.left);
			System.out.print(root.info + " ");
			inorder(root.right);
		}
	}
	
	public int getNumberOfNodedAtHeight(int height){
		return findNumberOfNodesAtHeight(root,height);
	}

	private int findNumberOfNodesAtHeight(TreeNode root,int height) {
		if (root == null) {
			return 0;
		}
		else if(height == 1){
			return 1;
		} 
		else{
			return findNumberOfNodesAtHeight(root.left, height -1) + findNumberOfNodesAtHeight(root.right, height-1);
		}
	}
	
	public int getMaxSumFromRootToLeaf(){
		return findMaxSumDepth(root);
	}

	private int findMaxSumDepth(TreeNode root) {
		if (root == null) {
			return 0;
		} else {
			int sumLeft = root.info + findMaxSumDepth(root.left);
			int sumRight = root.info + findMaxSumDepth(root.right);
			return sumLeft > sumRight ? sumLeft : sumRight;

		}
	}
	
	
	
	public int getMaxSumWidth(){
		return findMaxSumWidth(root);
	}

	private int findMaxSumWidth(TreeNode root) {
		
		return 0;
	}
	
	
	//checks whether a binary tree is bst or not 
	public boolean isBinarySearchTreeNode(){
		return isBinarySearchTreeNode(root);
	}
	
	boolean isBinarySearchTreeNode(TreeNode node){

        if (node == null){
        	return true;
        }
        if (node.left != null && node.left.info > node.info){
        	return false;
        }
        if (node.right != null && node.right.info < node.info){
        	return false;
        }
        if (node.left!=null && node.left.right!=null && node.left.right.info > node.info){
        	return false;
        }
        if (node.right!=null && node.right.left!=null && node.right.left.info > node.info){
        	return false;
        }
        
        return isBinarySearchTreeNode(node.left) && isBinarySearchTreeNode(node.right);

    }
	
	public int kthSmallest(int k) {
		try{
			int[] arr=new int[3];
			arr[2]=0;
			arr[1]=k;
			arr = kthSmallest(root, arr);
			if(arr[2]==1){
				return arr[0]; 
			}
			else{
				return Integer.MIN_VALUE;
			}
		}catch(Exception ex){
			System.out.println("Element doesn't exists");
		}
		return Integer.MIN_VALUE;
	}

	private int[] kthSmallest(TreeNode root, int[] arr) {
		// int returnVal =Integer.MIN_VALUE;
		if (root != null) {
			arr = kthSmallest(root.left, arr);
			if (arr[2] == 1) {
				return arr;
			} else if (arr[1] == 1) {
				arr[0] = root.info;
				arr[2] = 1;
				return arr;
			} else {
				arr[1] = arr[1] - 1;
				return kthSmallest(root.right, arr);
			}

		}
		return arr;
	}
	
	public Bst trimTree(int minValue, int maxValue){
		root = trimTree(root, minValue, maxValue);
		return this;
	}

	private TreeNode trimTree(TreeNode root, int minValue, int maxValue) {
		if(root == null){
			return null;
		}
		else if (root.info > maxValue){ //go to left subtree
			return trimTree(root.left, minValue, maxValue);
		}
		else if(root.info == maxValue){
			trimLeftNodes(root, minValue);
			root.right = null;
			return root;
		}
		else if (root.info < minValue){ //go to right subtree
			return trimTree(root.right, minValue, maxValue);
		}
		else if(root.info == minValue){
			trimRightNodes(root, maxValue);
			root.left = null;
			return root;
		}
		else{//root is the common ancestor
			trimRightNodes(root, maxValue);
			trimLeftNodes(root, minValue);
			return root;
		}
	}


	private void trimRightNodes(TreeNode root, int maxValue) {
		if(root.right == null){
			return;
		} 
		else if(root.right.info > maxValue){
			root.right = root.right.left;
			trimRightNodes(root, maxValue);
			return;
		}
		else {
			trimRightNodes(root.right, maxValue);
		}
	}


	private void trimLeftNodes(TreeNode root, int minValue) {
		if(root.left == null){
			return;
		} 
		else if(root.left.info < minValue){
			root.left = root.left.right;
			trimLeftNodes(root, minValue);
			return;
		}
		else {
			trimLeftNodes(root.left, minValue);
		}
	}
	
	public TreeNode convertToLinkList(){
		TreeNode[] localLeftArray;
		TreeNode[] localRightArray;
		if(root!=null){
			if (root.left == null) {
				localLeftArray = new TreeNode[2];
				localLeftArray[0] = root;
				localLeftArray[1] = root;
			} else {
				localLeftArray = convertToLinkList(root.left);
				root.left = null;
				localLeftArray[1].right = root;
				localLeftArray[1] = root;
			}
			if (root.right == null) {
				localRightArray = new TreeNode[2];
				localRightArray[0] = root;
				localRightArray[1] = root;
			} else {
				localRightArray = convertToLinkList(root.right);
				root.right = localRightArray[0];
			}
			localLeftArray[1].right = localRightArray[0];
			root=localLeftArray[0];
			return localLeftArray[0];
		}
		return null;
	}


	private TreeNode[] convertToLinkList(TreeNode root	) {
		//the first element in return array will have the start node of list 
		// and the second element will have the last node of the list.
		
		TreeNode[] returnArray = new TreeNode[2];
		returnArray[0] = null;
		returnArray[1] = null;
		TreeNode[] localLeftArray;
		TreeNode[] localRightArray;
		if(root!=null){
			if (root.left == null) {
				localLeftArray = new TreeNode[2];
				localLeftArray[0] = root;
				localLeftArray[1] = root;
			} else {
				localLeftArray = convertToLinkList(root.left);
				root.left = null;
				localLeftArray[1].right = root;
				localLeftArray[1] = root;
			}
			if (root.right == null) {
				localRightArray = new TreeNode[2];
				localRightArray[0] = root;
				localRightArray[1] = root;
			} else {
				localRightArray = convertToLinkList(root.right);
			}
			if(localLeftArray[1] != localRightArray[0]){
				localLeftArray[1].right = localRightArray[0];
				root.right = localRightArray[0];
			}
			returnArray[0] = localLeftArray[0];
			returnArray[1] = localRightArray[1];
		}
		return returnArray;
	}

}
