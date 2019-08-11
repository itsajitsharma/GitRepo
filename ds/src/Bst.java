public class Bst {

	private class node {
		int info;
		node left;
		node right;
	}

	node root;

	public Bst() {
		root = null;
	}

	public void add(int info) {
		node nd = new node();
		nd.info = info;
		if (root == null) {
			root = nd;
		} else {
			node temp = root;
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

	public int getHeight() {
		return findHeight(root);
	}

	private int findHeight(node root) {
		if (root == null) {
			return 0;
		} else {
			int hLeft = 1 + findHeight(root.left);
			int hRight= 1 + findHeight(root.right);
			return hLeft > hRight ? hLeft : hRight;

		}
	}

	public void print() {
		inorder(root);
	}

	private void inorder(node root) {
		if (root != null) {
			inorder(root.left);
			System.out.print(root.info + " ");
			inorder(root.right);
		}
	}
	
	public int getNumberOfNodedAtHeight(int height){
		return findNumberOfNodesAtHeight(root,height);
	}

	private int findNumberOfNodesAtHeight(node root,int height) {
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

	private int findMaxSumDepth(node root) {
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

	private int findMaxSumWidth(node root) {
		
		return 0;
	}

}
