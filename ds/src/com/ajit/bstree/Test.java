package com.ajit.bstree;

import java.util.*;

class TreesNode {
	public TreesNode left, right;
	public int data;

	TreesNode(int newData) {
		left = right = null;
		data = newData;
	}
};

public class Test {

	static void printKDistanceNodes(TreesNode root, TreesNode start, int K) {
		/*
		 * This program prints the nodes which are K distance away from
		 * input node
		 **/
		if (K == 0) {
			System.out.println(start.data);
			return;
		}
		Stack<Object> s = new Stack<Object>();
		TreeSet t = new TreeSet();

		while (true) {
			int level = s.empty() ? 0 : ((Integer) s.pop()).intValue();
			TreesNode n = s.empty() ? root : (TreesNode) s.pop();
			String Path = s.empty() ? "" : (String) s.pop();
			level++;
			if (n == start) {
				getAnc(root, Path, K, t);
				getChild(start, K, t);
				break;
			}
			if (n.left != null) {
				s.push(Path + "L");
				s.push(n.left);
				s.push(new Integer(level));
			}
			if (n.right != null) {
				s.push(Path + "R");
				s.push(n.right);
				s.push(new Integer(level));
			}
			if (s.empty())
				break;
		}
		Iterator i = t.iterator();
		while (i.hasNext())
			System.out.println(i.next());
	}

	public static void getAnc(TreesNode root, String Path, int K, TreeSet t) {
		if (Path.length() < K)
			return;
		Path = Path.substring(0, Path.length() - K);
		while (Path.length() > 0) {
			if (Path.charAt(0) == 'L')
				root = root.left;
			else
				root = root.right;
			Path = Path.substring(1, Path.length());
		}
		t.add(root.data);
	}

	public static void getChild(TreesNode start, int K, TreeSet t) {
		if (K == 1) {
			if (start.left != null)
				t.add(start.left.data);
			if (start.right != null)
				t.add(start.right.data);
		} else {
			if (start.left != null)
				getChild(start.left, K - 1, t);
			if (start.right != null)
				getChild(start.right, K - 1, t);
		}
	}

	public static void main(String arr[]) {
		TreesNode start = new TreesNode(5);
		start.left = new TreesNode(2);
		start.right = new TreesNode(8);
		start.left.left = new TreesNode(-4);
		start.left.right = new TreesNode(4);
		start.right.left = new TreesNode(6);
		start.right.right = new TreesNode(8);
		printKDistanceNodes(start, start.right, 1);
		
		Integer i =5;
		change(i);
		System.out.println("i ="+i);
	}

	private static void change(Integer i) {
		
		i++;
	}
	
	
	
	/*binarySearch(int x, int Low, int High) {
		int Mid;

		while (Low <= High) {
			Mid = (Low + High) / 2;
			if (a[Mid] < x)
				Low = Mid + 1;
			else if (a[Mid] > x)
				High = Mid - 1;
			else
				return Mid;  Found 
		}
		return -1;  NotFound is defined as -1 
	}

	int search(int x,int beg,int end)
	{
		int mid;
		if(beg>end)
			return -1;
		mid = (beg+end)/2;
		//int res;
		if(a[mid]==x)
		{
			res=mid;
			return res;
		}
		if(beg==(mid-1))
		{
			if(a[beg]==x)
				return beg;
			else
			{
				res=search(x,mid+1,end);
				return res;
			}
		}
		if(a[beg]<=a[mid-1] && beg<=(mid-1)) // first half array is sorted.
		{
			if( a[beg]x)
			{
				res=binarySearch(x,beg,mid-1);
				return res;
			}
			
			res = search(x,mid+1,end);
			return res;
		}
		
		else if((mid+1)==end)
		{
			if(a[end]==x)
				return end;
			else
			{
				res=search(x,beg,mid-1);
				return res;
			}
		}
		if( a[mid+1]<=x && a[end]>=x && (mid+1)>n)
			int c=search(n,0,7);
		if(c==-1)
			cout<<"element does not exists";
		else 
			cout<<c;
		getch();
		return 0;
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
}