package com.ajit.linklist;

import com.ajit.ds.LnkdList;

public class Utility {

	public static void main(String[] args) {
		/*Node a = new Node(1);
		a.next = new Node(1);
		a.next = new Node(1);
		a.next = new Node(1);
		a.next = new Node(1);
		list.add(3);
		list.add(2);
		list.add(1);
		//list.deleteFromFront();
		list.add(5);
		list.add(4);
		list.print();
		list.deleteIfGreaterOnRight();
		list.print();*/
	}
	
	
	
	
	private static Node merge(Node l1,Node l2){
		Node result = null;
		 
		  
		  if (l1 == null)
		     return(l2);
		  else if (l2==null)
		     return(l1);
		 
		  /* Pick either a or b, and recur */
		  if (l1.val <= l2.val)
		  {
		     result = l1;
		     result.next = merge(l1.next, l2);
		  }
		  else
		  {
		     result = l2;
		     result.next = merge(l1, l2.next);
		  }
		  return(result);
	       
	}
	
	private static class Node{
        Node next;
        int val;
        Node(int data){
                val = data;                
        }
        
	};
}
