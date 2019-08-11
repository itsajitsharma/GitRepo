package com.ajit.genomcitywar;

public class Enemy {
	
	private int archerId;
	private int numberOfArrowsForDeath;
	
	public Enemy(int archerId){
		this.setNumberOfArrowsForDeath(2*archerId);
	}

	public void setArcherId(int archerId) {
		this.archerId = archerId;
	}

	public int getArcherId() {
		return archerId;
	}

	public void setNumberOfArrowsForDeath(int numberOfArrowsForDeath) {
		this.numberOfArrowsForDeath = numberOfArrowsForDeath;
	}

	public int getNumberOfArrowsForDeath() {
		return numberOfArrowsForDeath;
	}

	
}
