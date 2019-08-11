package com.ajit.ds;

import java.util.NoSuchElementException;

public class Stack {
	
	private Node top;
	
	public Stack(){
		top = null;
	}
	
	public boolean isEmpty(){
		return top==null;
	}
	
	public int pop(){
		if(top==null)
			throw new NoSuchElementException();
		else{
			int i = top.value;
			top = top.next;
			return i;
		}
	}
	
	public void push(int i){
		top = new Node( i, top);
	}
	
	private class Node{
		int value;
		Node next;
		
		public Node(int i, Node next){
			value=i;
			this.next = next;
		}
		
	}
}
