package com.ajit.interviewbit;

import java.util.Stack;

class Solution {
	
	Stack<Integer> stack = new Stack<Integer>();
	Stack<Integer> minStack = new Stack<Integer>();
	
    public void push(int x) {
        if(minStack.isEmpty()){
        	minStack.push(x);
        }
        else{
        	int min = minStack.peek();
        	if(min <= x){
        		minStack.push(min);
        	}
        	else {
        		minStack.push(x);
        	}
        }
        stack.push(x);
    }

    public void pop() {
        if(!stack.isEmpty()){
        	stack.pop();
        	minStack.pop();
        }
    }

    public int top() {
        if(stack.isEmpty()){
        	return -1;
        }
        else{
        	return stack.peek();
        }
    }

    public int getMin() {
        if(minStack.isEmpty()){
        	return -1;
        }
        else{
        	return minStack.peek();
        }
    }
}