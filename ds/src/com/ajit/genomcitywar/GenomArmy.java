package com.ajit.genomcitywar;

import java.util.ArrayList;

public class GenomArmy {

	private int numberOfArrowFired = 0;
	private ArrayList<GenomArcher> genomArchers;
	private int numberOfArchersRemaining;
	
	public GenomArmy(ArrayList<GenomArcher> genomArchers){
		this.genomArchers = genomArchers;
		this.setNumberOfArchersRemaining(genomArchers.size());
	}

	public void doFight(EnemyArmy enemyArmy){
		for (GenomArcher genomArcher : genomArchers) {
			genomArcher.fireArrows(enemyArmy, this);
			if(enemyArmy.getRemaingEnemies() == 0){
				break;
			}
		}
		if(enemyArmy.getRemaingEnemies() == 0){
			System.out.println("battle won");
		}
		System.out.println("total enemies killed =" + (enemyArmy.getTotalEnemies() - enemyArmy.getRemaingEnemies()));
		System.out.println("total Arrows fired =" + numberOfArrowFired);
		
	}

	public void setNumberOfArrowFired(int numberOfArrowFired) {
		this.numberOfArrowFired = numberOfArrowFired;
	}

	public int getNumberOfArrowFired() {
		return numberOfArrowFired;
	}

	public void setNumberOfArchersRemaining(int numberOfArchersRemaining) {
		this.numberOfArchersRemaining = numberOfArchersRemaining;
	}

	public int getNumberOfArchersRemaining() {
		return numberOfArchersRemaining;
	}
	
}
