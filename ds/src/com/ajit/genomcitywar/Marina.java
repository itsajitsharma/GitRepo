package com.ajit.genomcitywar;

import java.util.ArrayList;

public class Marina {

	int numberOfArrowsProcessed = 0;
	
	public void processArrows(ArrayList<Arrow> arrows) {
		for (Arrow arrow : arrows) {
			numberOfArrowsProcessed++;
			if(numberOfArrowsProcessed%5 == 0){
				arrow.setAttack(2);
			}
			else{
				arrow.setAttack(1);
			}
		}
	}

}
