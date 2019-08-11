package com.ajit.genomcitywar;

import java.util.ArrayList;

public class EnemyArmy {
	
	private ArrayList<Enemy> enemyArmy;
	private int totalEnemies;
	private int remaingEnemies;
	
	public EnemyArmy(ArrayList<Enemy> enemyArmy){
		this.setEnemyArmy(enemyArmy);
		this.setTotalEnemies(enemyArmy.size());
		this.setRemaingEnemies(enemyArmy.size());
	}

	public void setEnemyArmy(ArrayList<Enemy> enemyArmy) {
		this.enemyArmy = enemyArmy;
	}

	public ArrayList<Enemy> getEnemyArmy() {
		return enemyArmy;
	}

	public void setTotalEnemies(int totalEnemies) {
		this.totalEnemies = totalEnemies;
	}

	public int getTotalEnemies() {
		return totalEnemies;
	}

	public void setRemaingEnemies(int remaingEnemies) {
		this.remaingEnemies = remaingEnemies;
	}

	public int getRemaingEnemies() {
		return remaingEnemies;
	}
	
}
