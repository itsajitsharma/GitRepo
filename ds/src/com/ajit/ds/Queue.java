package com.ajit.ds;


import java.util.NoSuchElementException;

public class Queue {
	
	private Node front;
	private Node rear;
	
	public Queue(){
		front = rear = null;
	}
	
	public boolean isEmpty(){
		return front==null;
	}
	
	public int dequeue(){
		if(front==null)
			throw new NoSuchElementException();
		else{
			int i = front.value;
			front = front.next;
			if(front == null){
				rear = null;
			}
			return i;
		}
	}
	
	public void enqueue(int i){
		if(rear == null)
			front = rear = new Node( i, null);
		else{
			rear = rear.next = new Node( i, null);
		}
		if(front == null){
			front = rear;
		}
	}
	
	private class Node{
		int value;
		Node next;
		
		Node(int i, Node next){
			value=i;
			this.next = next;
		}
		
	}
}

