package com.ajit.bstree;


public class TreeNode {
	
	char info;
	TreeNode left;
	TreeNode right;
	TreeNode next;
	
	public TreeNode(){
		
	}
	
	
	
	public TreeNode(TreeNode tnode1, char info, TreeNode tnode2){
		this.info = info;
		this.left = tnode1;
		this.right = tnode2;
	}
	
}
