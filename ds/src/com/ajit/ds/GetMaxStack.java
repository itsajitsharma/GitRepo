package com.ajit.ds;

public class GetMaxStack {

	private int[] stk;
	private Integer maxValue;
	private int size;
	private int top = -1;

	public GetMaxStack(int size) {
		this.size = size;
		stk = new int[size];
	}

	public void push(int item) throws Exception {
		if (top == size - 1) {
			throw new Exception("stack overflow");
		}

		if (top == -1) {
			maxValue = item;
			stk[++top] = item;
		} else {
			if (item > maxValue) {
				int insertValue = maxValue + item;
				maxValue = item;
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
		if (item > maxValue) {
			returnValue = maxValue;
			maxValue = item - maxValue;
		}
		else{
			returnValue = item;
		}
		
		if(top == -1){
			maxValue = null;
		}
		
		return returnValue;
	}

	public int getMax() throws Exception {
		return maxValue;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Insert value 10, 3, 12, 8, 15, 1
		
		GetMaxStack stk = new GetMaxStack(5);
		try {
			
			int value;
			value =10;
			stk.push(value);
			System.out.println("Element " + value + " pushed");
			stk.printStackState();
			value =3;
			stk.push(3);
			System.out.println("Element " + value + " pushed");
			stk.printStackState();
			value =12;
			stk.push(12);
			System.out.println("Element " + value + " pushed");
			stk.printStackState();
			value =8;
			stk.push(8);
			System.out.println("Element " + value + " pushed");
			stk.printStackState();
			value =15;
			stk.push(15);
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
		System.out.println("]      Max value is = " + maxValue);
	}

}
