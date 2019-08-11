package com.ajit.ds;

public class GetMinStack {

	private int[] stk;
	private Integer minValue;
	private int size;
	private int top = -1;

	public GetMinStack(int size) {
		this.size = size;
		stk = new int[size];
	}

	public void push(int item) throws Exception {
		if (top == size - 1) {
			throw new Exception("stack overflow");
		}

		if (top == -1) {
			minValue = item;
			stk[++top] = item;
		} else {
			if (item < minValue) {
				int insertValue = -(minValue - item);
				minValue = item;
				stk[++top] = insertValue;
			} else {
				stk[++top] = item;
			}
		}
	}

	public int pop() throws Exception {
		if (top == -1) {
			throw new Exception("stack underflow");
		}

		/*
		 * if (top == 0) { maxValue = item; stk[++top] = item; }
		 */
		int returnValue = 0;
		int item = stk[top--];
		if (item < minValue) {
			returnValue = minValue;
			minValue = - (item - minValue);
		}
		else{
			returnValue = item;
		}
		
		if(top == -1){
			minValue = null;
		}
		
		return returnValue;
	}

	public int getMin() throws Exception {
		return minValue;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Insert value 10, 3, 12, -1, -3, 1
		//This program works for positive numbers only. Need to update for negative numbers
		
		GetMinStack stk = new GetMinStack(5);
		try {
			
			int value;
			value =10;
			stk.push(value);
			System.out.println("Element " + value + " pushed");
			stk.printStackState();
			value =3;
			stk.push(value);
			System.out.println("Element " + value + " pushed");
			stk.printStackState();
			value =12;
			stk.push(value);
			System.out.println("Element " + value + " pushed");
			stk.printStackState();
			value =-1;
			stk.push(value);
			System.out.println("Element " + value + " pushed");
			stk.printStackState();
			value =-3;
			stk.push(value);
			System.out.println("Element " + value + " pushed");
			stk.printStackState();
			value = stk.pop();
			System.out.println("Element " + value + " poped");
			stk.printStackState();
			value = stk.pop();
			System.out.println("Element " + value + " poped");
			stk.printStackState();
			value = stk.pop();
			System.out.println("Element " + value + " poped");
			stk.printStackState();
			value = stk.pop();
			System.out.println("Element " + value + " poped");
			stk.printStackState();
			value = stk.pop();
			System.out.println("Element " + value + " poped");
			stk.printStackState();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

	private void printStackState() {
		System.out.print("stack Elements are - [");
		for (int i =0 ;i<=top; i++){
			System.out.print(stk[i] + ", ");
		}
		System.out.println("]      Min value is = " + minValue);
	}

}
