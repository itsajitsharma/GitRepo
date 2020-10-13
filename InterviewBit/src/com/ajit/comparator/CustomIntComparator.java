package com.ajit.comparator;

import java.util.Comparator;


public class CustomIntComparator implements Comparator<Integer>{

	@Override
	public int compare(Integer arg0, Integer arg1) {
		System.out.println("Comparing "+ arg0 +" and " + arg1);
		if(arg0 == arg1){
			return 0;
		}
		long first = Long.parseLong( String.valueOf(arg0) + String.valueOf(arg1) );
		long second = Long.parseLong( String.valueOf(arg1) + String.valueOf(arg0) );
		return first>second?1:-1;
	}
	

}
