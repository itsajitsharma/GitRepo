import java.util.NoSuchElementException;

import org.omg.CORBA.INTERNAL;

public class LnkdList {

	private class node {
		int info;
		node next;
	}

	private node first;
	private int size;

	public LnkdList() {
		first = null;
		size = 0;
	}
	
	public boolean isEmpty(){
		if(first == null)
			return true;
		else
			return false;
	}
	
	public int size(){
		return size;
	}
	
	public void addAtFront(int info){
		node nd = new node();
		nd.info = info;
		if(first != null){
			nd.next = first;
		}else{
			nd.next = null;
		}
		first = nd;
		size++;
	}
	
	public void add(int info) {
		node nd = new node();
		nd.info = info;
		nd.next = null;
		if (first == null) {
			first = nd;
		} else {
			node temp = first;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = nd;
		}
		size++;
	}
	
	public int deleteFromFront(){
		if(first == null){
			throw new NoSuchElementException();
		}
		int info =  first.info;
		first = first.next;
		size--;
		return info;
	}

	public int findMiddleNode() {
		if(first == null){
			return Integer.MIN_VALUE;
		}
		node slow = first;
		node fast = first;
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
		node temp = first;
		while (temp != null) {
			System.out.print(temp.info + " ");
			temp = temp.next;
		}

	}
}
