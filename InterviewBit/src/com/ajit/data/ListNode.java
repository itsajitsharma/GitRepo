package com.ajit.data;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
		next = null;
	}

	@Override
	public String toString() {
		StringBuilder strBld = new StringBuilder();
		ListNode a = this;
		while (a != null) {
			strBld.append(a.val + ", ");
			a = a.next;
		}
		return strBld.toString();
		// return val + ", " + next==null?"":next.toString();
	}

}
