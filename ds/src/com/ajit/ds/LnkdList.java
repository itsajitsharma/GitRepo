package com.ajit.ds;

import java.util.NoSuchElementException;



public class LnkdList {

	private class Node {
		int info;
		Node next;
	}

	private Node start;
	private int size;

	public LnkdList() {
		start = null;
		size = 0;
	}
	
	public boolean isEmpty(){
		if(start == null)
			return true;
		else
			return false;
	}
	
	public int size(){
		return size;
	}
	
	public void addAtFront(int info){
		Node nd = new Node();
		nd.info = info;
		if(start != null){
			nd.next = start;
		}else{
			nd.next = null;
		}
		start = nd;
		size++;
	}
	
	public void add(int info) {
		Node nd = new Node();
		nd.info = info;
		nd.next = null;
		if (start == null) {
			start = nd;
		} else {
			Node temp = start;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = nd;
		}
		size++;
	}
	
	public int deleteFromFront(){
		if(start == null){
			throw new NoSuchElementException();
		}
		int info =  start.info;
		start = start.next;
		size--;
		return info;
	}

	public int findMiddleNode() {
		Node slow = start;
		Node fast = start;
		while (fast != null) {
			fast = fast.next;
			if (fast != null) {
				fast = fast.next;

			} else {
				break;
			}
			slow = slow.next;

		}
		return slow.info;
	}

	public void print() {
		Node temp = start;
		while (temp != null) {
			System.out.print(temp.info + " ");
			temp = temp.next;
		}

	}
	
	//Given a singly linked list, remove all the nodes which have a greater value on right side.
	public void deleteIfGreaterOnRight()
	{
		//return 
		ReturnObject returnObj;
		returnObj= deleteIfGreaterOnRight(start);
		if(returnObj.nextNodeToBeDeleted){
			start= start.next;
			size--;
		}
	}
	
	private ReturnObject deleteIfGreaterOnRight(Node start){
		ReturnObject returnObj;
		if(start==null){
			returnObj=new ReturnObject(Integer.MIN_VALUE,false);
			return returnObj;
		}
		else if(start.next==null){
			returnObj=new ReturnObject(start.info,false);
			return returnObj;
		}
		else{
			returnObj=deleteIfGreaterOnRight(start.next);
			if(returnObj.nextNodeToBeDeleted){
				start.next=start.next.next;
				this.size--;
			}
			if(start.info < returnObj.maxValue){
				returnObj.nextNodeToBeDeleted=true;
			}
			else{
				returnObj.maxValue=start.info;
			}
		}
		return returnObj;
	}
	
	class ReturnObject{
		int maxValue;
		boolean nextNodeToBeDeleted;
		
		public ReturnObject() {
			super();
		}



		public ReturnObject(int maxValue, boolean nextNodeToBeDeleted) {
			super();
			this.maxValue = maxValue;
			this.nextNodeToBeDeleted = nextNodeToBeDeleted;
		}
	}
	
	
	
	/**
	 * This function merges 2 sorted lists into one sorted list
	 * @param l1
	 * @param l2
	 * @return
	 */
	private static Node sortedMerge(Node l1,Node l2){
		Node result = null;
		 
		  
		  if (l1 == null)
		     return(l2);
		  else if (l2==null)
		     return(l1);
		 
		  /* Pick either a or b, and recur */
		  if (l1.info <= l2.info)
		  {
		     result = l1;
		     result.next = sortedMerge(l1.next, l2);
		  }
		  else
		  {
		     result = l2;
		     result.next = sortedMerge(l1, l2.next);
		  }
		  return(result);
	       
	}
}
