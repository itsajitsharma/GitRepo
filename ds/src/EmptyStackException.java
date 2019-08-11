
public class EmptyStackException extends Exception{

	private String mMessage;
	public EmptyStackException(String message){
		mMessage = message;
		
	}
	
	public String getMessage(){
		return mMessage;
	}
	
	public String toString(){
		return mMessage;
	}
} 
