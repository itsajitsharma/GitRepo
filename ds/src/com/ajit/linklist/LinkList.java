package com.ajit.linklist;


public class LinkList {

	
	Node first;

	public LinkList() {
		first = null;
	}
	
	public boolean isEmpty(){
		if(first == null)
			return true;
		else
			return false;
	}
	
	public void addAtFront(int info){
		Node nd = new Node();
		nd.info = info;
		if(first != null){
			nd.next = first;
		}else{
			nd.next = null;
		}
		first = nd;
	}
	
	public void add(int info) {
		Node nd = new Node();
		nd.info = info;
		nd.next = null;
		if (first == null) {
			first = nd;
		} else {
			Node temp = first;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = nd;
		}
	}
	
	public int deleteFromFront(){
		if(first == null){
			System.out.println("Empty LinkList.");
			return 0;
		}
		int info =  first.info;
		first = first.next;
		return info;
	}

	public int findMiddleNode() {
		Node slow = first;
		Node fast = first;
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
		Node temp = first;
		while (temp != null) {
			System.out.print(temp.info + " ");
			temp = temp.next;
		}

	}
	
	
	
}

