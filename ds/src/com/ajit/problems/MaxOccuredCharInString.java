package com.ajit.problems;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Vector;

// this program prints the maximum occured character from a vector of strings for each string.

public class MaxOccuredCharInString {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String inputStr = "{\"abcd\"}";
		String resultStr = null;
		if(inputStr != null && inputStr.indexOf("{\"")> -1 ){
			int startIndex = inputStr.indexOf("{\"");
			int endIndex = inputStr.indexOf("\"}", startIndex);
			if (endIndex > -1){
				resultStr = inputStr.substring(startIndex+2, endIndex);
			}
		}
		System.out.println(resultStr);*/
		Vector<String > text = new Vector<String>();
		text.add("i lok trrrrve my mother india");
		text.add("India is great");
		findMax(text );
	}

	private static void findMax(Vector<String> text) {
		for(String txt : text) {
			char[] arr = txt.toCharArray();
			TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
			for (int i = 0; i < arr.length; i++) {
				Character ch = arr[i];
				if( (ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122)){
					if(map.containsKey(ch)){
						int count = map.get(ch);
						map.put(ch, count+1);
					}
					else{
						map.put(ch, 1);
					}
				}
			}
			
			int greatestCount = 0;
			StringBuffer str = new StringBuffer();
			for (Character character : map.keySet()) {
				int val = map.get(character);
				if (val > greatestCount){
					greatestCount = val;
					str.setLength(0);
					str.append(character);
				}else if(val == greatestCount){
					str.append(character);
				}
			}
			System.out.println(str.toString()+" "+greatestCount);
		}
		
	}

}
