package com.ajit.linklist;

/**
 * @author ajit
 * This program finds out whether a linkList is palindrom or not. 
 * 
 */
public class CheckLnListPalindrome {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkList lnList = new LinkList();
		CheckLnListPalindrome chk = new CheckLnListPalindrome();
		boolean isPalindrome = false;
		lnList.add(1);
		lnList.add(2);
		lnList.add(3);
		lnList.add(3);
		lnList.add(2);
		lnList.add(1);
		isPalindrome = chk.checkPalindrome(lnList.first);
		System.out.println(isPalindrome);
	}
	
	boolean checkPalindrome(Node start){
		Node fwd = start;
		Node returnedNode = null;
		if(start == null){
			return true;
		}else if(fwd.next == null){
			return true;
		}else if(fwd.next.next == null ){
			if(start.info == start.next.info){
				return true;
			}else{
				return false;
			}
		}else{
			returnedNode = checkPalindrome(start.next, start.next.next);
			if(returnedNode == null){
				return false;
			}else if(returnedNode.info == start.info){
				return true;
			}else{
				return false;
			}
		}
	}
	
	Node checkPalindrome(Node slow, Node fwd){
		Node returnedNode = null;
		if(fwd.next == null){ //odd no of nodes in the list, with slow as middle node
			return slow.next;
		}else if(fwd.next.next == null ){  //even no of nodes in the list, with slow and slow.next as middle node
			if(slow.info == slow.next.info){
				return slow.next.next;
			}else{
				return null;
			}
		}else{
			returnedNode = checkPalindrome(slow.next, fwd.next.next);
			if(returnedNode == null){
				return null;
			}else if(returnedNode.info == slow.info){
				return returnedNode.next;
			}else{
				return null;
			}
		}
	}

}
