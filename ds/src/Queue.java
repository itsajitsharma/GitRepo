
public class Queue {
	private LnkdList linkList;
	public Queue(){
		linkList = new LnkdList();
	}
	public void insert(int info){
		linkList.add(info);
	}
	public int delete(){
		return	linkList.deleteFromFront();
	}
}
