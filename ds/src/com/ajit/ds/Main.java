package com.ajit.ds;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		//main.testStack();
		//main.testQueue();
		//main.testLinkList();
		main.testBST();
	}
	
	public void testBST(){
		System.out.println("TESTING Bst");
		Bst bst = new Bst();
		bst.add(3);
		bst.add(2);
		bst.add(1);
		//list.deleteFromFront();
		bst.add(5);
		bst.add(4);
		bst.printInOrder();
		System.out.println();
		//System.out.println(bst.kthSmallest(4));
		bst.convertToLinkList();
		bst.printInOrder();
		
	}
	
	public void testQueue() {
		Queue queue = new Queue();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		try{
			System.out.println("dequeue from Queue = "+queue.dequeue());
			System.out.println("dequeue from Queue = "+queue.dequeue());
			System.out.println("dequeue from Queue = "+queue.dequeue());
			System.out.println("dequeue from Queue = "+queue.dequeue());
			System.out.println("dequeue from Queue = "+queue.dequeue());
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}

	public void testStack(){
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		try{
			System.out.println("poped from stack = "+stack.pop());
			System.out.println("poped from stack = "+stack.pop());
			System.out.println("poped from stack = "+stack.pop());
			System.out.println("poped from stack = "+stack.pop());
			System.out.println("poped from stack = "+stack.pop());
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void testLinkList(){
		System.out.println("TESTING LinkList");
		LnkdList list = new LnkdList();
		list.add(3);
		list.add(2);
		list.add(1);
		//list.deleteFromFront();
		list.add(5);
		list.add(4);
		list.print();
		list.deleteIfGreaterOnRight();
		list.print();
		//System.out.println("midddle=" + list.findMiddleNode());
		//System.out.println("size of list is = " + list.size());
		try{
			//list.deleteFromFront();
			//list.deleteFromFront();
			//list.deleteFromFront();
			//list.deleteFromFront();
			//list.deleteFromFront();
		}catch(Exception e){
			System.out.println(e.toString());
		}
		//System.out.println("size of list is = " + list.size());
	}

}
