public class test {
	public static void main(String[] args) {
		
		System.out.println(new test().solution(new int[]{1, 0, -1, 1, 1, -1, -1}, 2));
		/*System.out.println("TESTING LinkList");
		LnkdList list = new LnkdList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.deleteFromFront();
		list.add(4);
		list.add(5);

		list.print();
		System.out.println("midddle=" + list.findMiddleNode());
		System.out.println("size of list is = " + list.size());*/
		
		
		/*
		System.out.println("TESTING TREE");
		Bst bst = new Bst();
		bst.add(15);
		bst.add(5);
		bst.add(25);
		bst.add(8);
		bst.add(3);
		bst.add(20);
		bst.add(12);
		bst.add(9);
		bst.add(7);
		bst.print();
		System.out.println();
		System.out.println("height of tree:" + bst.getHeight());
		int height = 7;
		System.out.println("Number of nodes at height" + height + " are "
				+ bst.getNumberOfNodedAtHeight(height));
		System.out.println("Maximum some from root to leaf node is = "
				+ bst.getMaxSumFromRootToLeaf());
		*/
		
		/*
		System.out.println("testing queue.");
		Queue queue = new Queue();
		int i;
		queue.insert(1);
		queue.insert(2);
		queue.insert(3);
		queue.insert(4);
		queue.insert(5);
		queue.insert(6);
		i = queue.delete();
		System.out.println("deleted Item = "+ i);
		i = queue.delete();
		System.out.println("deleted Item = "+ i);
		*/
		
		/*
		System.out.println("testing Stack");
		int i;
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		i = stack.pop();
		System.out.println("Poped value = "+ i);
		stack.push(4);
		stack.push(5);
		i = stack.pop();
		System.out.println("Poped value = "+ i);
		i = stack.pop();
		System.out.println("Poped value = "+ i);
		stack.push(6);
		*/
	}
	
	public int solution(int[] A, int S){
		int[] sumArr = new int[A.length];
		sumArr[0]  = A[0];
		for (int i = 1; i < A.length; i++) {
			sumArr[i] = sumArr[i-1] + A[i]; 
		}
		
		if(sumArr[sumArr.length-1] == S){
			return sumArr.length;
		}
		
		int lowIndex = 0; 
		int highIndex = A.length - 1;
		while (lowIndex < highIndex) {
			
			if(sumArr[highIndex] == S){
				return highIndex+1;
			}
			
			int diff = sumArr[highIndex] - sumArr[lowIndex];
			if(diff == S){
				return highIndex - lowIndex;
			}
			else if(diff > S){
				if( A[lowIndex +1] == -1){
					lowIndex++;
				}
				else{
					highIndex--;
				}
			}
			else{//diff < S
				if( A[lowIndex +1] == 1){
					lowIndex++;
				}
				else{
					highIndex--;
				}
			}
		}
				
		return -1;
	}
}
