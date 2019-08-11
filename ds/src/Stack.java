
public class Stack {
	private LnkdList linkList;
	public Stack(){
		linkList = new LnkdList();
	}
	
	public void push(int value){
		linkList.addAtFront(value);
	}
	
	public int pop() throws EmptyStackException{
		
		return linkList.deleteFromFront();
	}
}
